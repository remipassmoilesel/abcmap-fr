package org.remipassmoilesel.abcmapfr.controllers;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.remipassmoilesel.abcmapfr.entities.Message;
import org.remipassmoilesel.abcmapfr.entities.Stats;
import org.remipassmoilesel.abcmapfr.lists.Faq;
import org.remipassmoilesel.abcmapfr.lists.Functionalities;
import org.remipassmoilesel.abcmapfr.lists.Recommendations;
import org.remipassmoilesel.abcmapfr.lists.Videos;
import org.remipassmoilesel.abcmapfr.repositories.MessagesRepository;
import org.remipassmoilesel.abcmapfr.repositories.StatsRepository;
import org.remipassmoilesel.abcmapfr.repositories.VotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private VotesRepository votesRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    @RequestMapping(value = Mappings.ROOT, method = RequestMethod.GET)
    public String showIndex() {
        return "redirect:" + Mappings.WELCOME;
    }

    @RequestMapping(value = Mappings.WELCOME, method = RequestMethod.GET)
    public String showWelcome(Model model) {

        //statsRepository.deleteAll();

        List<String> functionalities = Functionalities.getList();
        model.addAttribute("functionnalities", functionalities);

        // get download number
        try {
            model.addAttribute("downloadsThisWeek", getDownloadsThisWeek());
        } catch (Exception e) {
            logger.error("Error while grabing downloads", e);
            model.addAttribute("downloadsThisWeek", -1);
        }

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.WELCOME;
    }

    private void includeVoteVars(Model model) {
        try {
            model.addAttribute("averageVote", votesRepository.averageVoteValue());
            model.addAttribute("sumVote", votesRepository.count());
        } catch (Exception e) {
            logger.error("Error while adding vote vars to model", e);
        }
    }

    @RequestMapping(value = Mappings.DOWNLOAD, method = RequestMethod.GET)
    public String showDownload(Model model) {

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.DOWNLOAD;
    }

    @RequestMapping(value = Mappings.FAQ, method = RequestMethod.GET)
    public String showFaq(Model model) {

        List[] lists = Faq.getLists();

        model.addAttribute("titles", lists[0]);
        model.addAttribute("questions", lists[1]);
        model.addAttribute("answers", lists[2]);

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.FAQ;
    }

    @RequestMapping(value = Mappings.ABOUT_PROJECT, method = RequestMethod.GET)
    public String showProject(Model model) {

        List<String[]> list = Recommendations.getList();
        model.addAttribute("recommendations", list);

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.ABOUT_PROJECT;
    }

    @RequestMapping(value = Mappings.NEW_VERSION, method = RequestMethod.GET)
    public String showNewVersion(Model model) {

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.NEW_VERSION;
    }

    @RequestMapping(value = Mappings.HELP, method = RequestMethod.GET)
    public String showHelp(Model model) {

        List<String[]> list = Videos.getList();

        model.addAttribute("videos", list);
        model.addAttribute("titlesJs", list);
        model.addAttribute("sourcesJs", list);

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.HELP;
    }

    @RequestMapping(value = Mappings.CONTACT, method = RequestMethod.GET)
    public String showContact(Model model) {

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.CONTACT;
    }

    @RequestMapping(value = Mappings.CONTACT, method = RequestMethod.POST)
    public String postContact(Model model,
                              @RequestParam(name = "object") String object,
                              @RequestParam(name = "email") String mail,
                              @RequestParam(name = "message") String message) {

        try {
            String escMail = StringEscapeUtils.escapeSql(StringEscapeUtils.escapeHtml(mail));
            String escObject = StringEscapeUtils.escapeSql(StringEscapeUtils.escapeHtml(object));
            String escMessage = StringEscapeUtils.escapeSql(StringEscapeUtils.escapeHtml(message));

            Message entity = new Message(escObject, escMessage, escMail, new Date());
            messagesRepository.save(entity);

            model.addAttribute("messageStatus", "saved");

        } catch (Exception e) {
            logger.error("Error while saving message", e);
            model.addAttribute("errorMessage", "Impossible de sauvegarder ce message.");
        }

        includeVoteVars(model);
        Mappings.includeMappings(model);
        return Templates.CONTACT;
    }

    @ResponseBody
    @RequestMapping(value = Mappings.GET_STATS_OF_THE_DAY, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getStatsOfTheDay() throws IOException {
        return getStatsOfTheWeek().getContent();
    }

    public Stats getStatsOfTheWeek() throws IOException {

        Date begin = new DateTime().withTimeAtStartOfDay().toDate();
        Date end = new DateTime().plusDays(1).withTimeAtStartOfDay().toDate();

        List<Stats> rslt = statsRepository.findBetween(begin, end);

        // today stats are here, return it
        if (rslt.size() > 0) {
            return rslt.get(0);
        }

        // no stats, grab them
        else {

            logger.info("Grabbing stats data");

            String dateFormat = "yyyy-MM-dd";
            String today = new DateTime().withTimeAtStartOfDay().toString(dateFormat);
            String sevenDaysAgo = new DateTime().withTimeAtStartOfDay().minusDays(7)
                    .toString(dateFormat);

            String url = "https://sourceforge.net/projects/abc-map/files/stats/json?start_date=" + sevenDaysAgo + "&end_date=" + today;

            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // add request header
            HttpResponse response = client.execute(request);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer rawJson = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                rawJson.append(line);
            }

            int totalDownloads = -1;
            try {
                totalDownloads = JsonPath.read(rawJson.toString(), "$.total");
            } catch (Exception e) {
                logger.error("Error while parsing JSON", e);
            }

            Stats st = new Stats(new Date(), rawJson.toString(), totalDownloads);

            // save content
            statsRepository.save(st);

            return st;
        }
    }

    public int getDownloadsThisWeek() throws IOException {
        return getStatsOfTheWeek().getTotalDownloads();
    }
}
