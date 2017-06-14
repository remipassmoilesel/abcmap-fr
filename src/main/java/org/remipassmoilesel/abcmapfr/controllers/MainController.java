package org.remipassmoilesel.abcmapfr.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.remipassmoilesel.abcmapfr.entities.StatsOfTheDay;
import org.remipassmoilesel.abcmapfr.lists.Faq;
import org.remipassmoilesel.abcmapfr.lists.Functionalities;
import org.remipassmoilesel.abcmapfr.lists.Recommendations;
import org.remipassmoilesel.abcmapfr.lists.Videos;
import org.remipassmoilesel.abcmapfr.repositories.StatsOfTheDayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private StatsOfTheDayRepository statsRepository;

    @RequestMapping(value = Mappings.ROOT, method = RequestMethod.GET)
    public String showIndex() {
        return "redirect:" + Mappings.WELCOME;
    }

    @RequestMapping(value = Mappings.WELCOME, method = RequestMethod.GET)
    public String showWelcome(Model model) {

        List<String> functionalities = Functionalities.getList();
        model.addAttribute("functionnalities", functionalities);

        Mappings.includeMappings(model);
        return Templates.WELCOME;
    }

    @RequestMapping(value = Mappings.DOWNLOAD, method = RequestMethod.GET)
    public String showDownload(Model model) {

        Mappings.includeMappings(model);
        return Templates.DOWNLOAD;
    }

    @RequestMapping(value = Mappings.FAQ, method = RequestMethod.GET)
    public String showFaq(Model model) {

        List[] lists = Faq.getLists();

        model.addAttribute("titles", lists[0]);
        model.addAttribute("questions", lists[1]);
        model.addAttribute("answers", lists[2]);

        Mappings.includeMappings(model);
        return Templates.FAQ;
    }

    @RequestMapping(value = Mappings.ABOUT_PROJECT, method = RequestMethod.GET)
    public String showProject(Model model) {

        List<String[]> list = Recommendations.getList();
        model.addAttribute("recommendations", list);

        Mappings.includeMappings(model);
        return Templates.ABOUT_PROJECT;
    }

    @RequestMapping(value = Mappings.NEW_VERSION, method = RequestMethod.GET)
    public String showNewVersion(Model model) {

        Mappings.includeMappings(model);
        return Templates.NEW_VERSION;
    }

    @RequestMapping(value = Mappings.HELP, method = RequestMethod.GET)
    public String showHelp(Model model) {

        List<String[]> list = Videos.getList();

        model.addAttribute("videos", list);
        model.addAttribute("titlesJs", list);
        model.addAttribute("sourcesJs", list);

        Mappings.includeMappings(model);
        return Templates.HELP;
    }

    @RequestMapping(value = Mappings.CONTACT, method = RequestMethod.GET)
    public String showContact(Model model) {

        Mappings.includeMappings(model);
        return Templates.CONTACT;
    }

    @ResponseBody
    @RequestMapping(value = Mappings.GET_STATS_OF_THE_DAY, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getStatsOfTheDay() throws IOException {

        Date begin = new DateTime().withTimeAtStartOfDay().toDate();
        Date end = new DateTime().plusDays(1).withTimeAtStartOfDay().toDate();

        List<StatsOfTheDay> rslt = statsRepository.findBetween(begin, end);

        // today stats are here, return it
        if (rslt.size() > 0) {
            return rslt.get(0).getContent();
        }

        // no stats, grab them
        else {
            return grabAndSaveStats();
        }
    }

    private String grabAndSaveStats() throws IOException {

        logger.info("Grabbing stats data");

        String dateFormat = "yyyy-MM-dd";
        String today = new DateTime().withTimeAtStartOfDay().toString(dateFormat);
        String sevenDaysAgo = new DateTime().minusDays(7)
                .withTimeAtStartOfDay().toString(dateFormat);

        String url = "https://sourceforge.net/projects/abc-map/files/stats/json?start_date=" + sevenDaysAgo + "&end_date=" + today;

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        // save content
        statsRepository.save(new StatsOfTheDay(new Date(), result.toString()));

        return result.toString();
    }


}
