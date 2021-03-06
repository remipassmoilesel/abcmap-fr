package org.remipassmoilesel.abcmapfr.controllers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.remipassmoilesel.abcmapfr.entities.*;
import org.remipassmoilesel.abcmapfr.lists.*;
import org.remipassmoilesel.abcmapfr.repositories.*;
import org.remipassmoilesel.abcmapfr.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
@Controller
public class MainController {

    public static final String GTRANSLATE_ATTR_NAME = "googleTranslateEnabled";

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Value("${app.google-analytics-api-key}")
    private String googleAnalyticsApiKey;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private VotesRepository votesRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @Autowired
    private UpdateInformationRepository updateInformationRepository;

    @Autowired
    private SoftwareUtilisationRepository softwareUtilisationRepository;

    @RequestMapping(value = Mappings.ROOT, method = RequestMethod.GET)
    public String showWelcome(Model model, HttpSession session) throws Exception {

        //statsRepository.deleteAll();

        List<String> functionalities = Functionalities.getList();
        model.addAttribute("functionnalities", functionalities);

        // get download number
        try {
            Stats stats = getStatsOfTheWeek();
            model.addAttribute("downloadsThisWeek",
                    stats == null ? -1 : stats.getTotalDownloads());
        } catch (Exception e) {
            logger.error("Error while grabing downloads", e);
            model.addAttribute("downloadsThisWeek", -1);
        }

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.ROOT;
    }

    @RequestMapping(value = Mappings.TRANSLATE, method = RequestMethod.GET)
    public String translate(Model model, HttpSession session) {

        Object curVal = session.getAttribute(GTRANSLATE_ATTR_NAME);
        if (curVal == null || curVal.equals("true") == false) {
            session.setAttribute(GTRANSLATE_ATTR_NAME, "true");
        } else {
            session.setAttribute(GTRANSLATE_ATTR_NAME, "");
        }

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.ROOT;
    }

    @RequestMapping(value = Mappings.DOWNLOAD, method = RequestMethod.GET)
    public String showDownload(Model model, HttpSession session,
                               @RequestParam(name = "downloadType", required = false) String downloadType) {

        // retrieve and add download link if needed
        if (downloadType != null) {
            String link = DownloadLocations.get(downloadType.trim().toLowerCase()).getLocation();

            if (link == null) {
                includeErrorMessage(model, "Téléchargement non disponible");
            } else {
                model.addAttribute("downloadLink", link);
            }
        }

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.DOWNLOAD;
    }

    @RequestMapping(value = Mappings.FAQ, method = RequestMethod.GET)
    public String showFaq(Model model, HttpSession session) {

        List[] lists = Faq.getLists();

        model.addAttribute("titles", lists[0]);
        model.addAttribute("questions", lists[1]);
        model.addAttribute("answers", lists[2]);

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.FAQ;
    }

    @RequestMapping(value = Mappings.LICENSE, method = RequestMethod.GET)
    public String showLicense(Model model, HttpSession session) {

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.LICENSE;
    }

    /**
     * Required to maintain good links with old websites
     *
     * @param model
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = Mappings.DOWNLOADER_PHP, method = RequestMethod.GET)
    public void getFile(Model model, HttpSession session, HttpServletResponse response,
                        @RequestParam(name = "id") String id) {

        id = id.trim().toLowerCase();

        // test if id is valid or return nothing
        DownloadLocations.DownloadLocation download = DownloadLocations.get(id);
        if (download == null) {
            return;
        }

        // return file content
        try {

            response.addHeader("Content-Type", MediaType.parseMediaType(download.getContentType()).toString());
            response.addHeader("Content-Disposition", "inline;filename=" + id);

            InputStream is = MainController.class.getResourceAsStream(download.getLocation());

            response.setContentType(download.getContentType().toString());

            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            logger.error("Error while serving file", ex);
            return;
        }
    }

    @RequestMapping(value = Mappings.ABOUT_PROJECT, method = RequestMethod.GET)
    public String showProject(Model model, HttpSession session) {

        List<String[]> list = Recommendations.getList();
        model.addAttribute("recommendations", list);

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.ABOUT_PROJECT;
    }

    @RequestMapping(value = Mappings.NEW_VERSION, method = RequestMethod.GET)
    public String showNewVersion(Model model, HttpSession session) {

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.NEW_VERSION;
    }

    @RequestMapping(value = Mappings.HELP, method = RequestMethod.GET)
    public String showHelp(Model model, HttpSession session) {

        List<String[]> list = Videos.getList();

        model.addAttribute("videos", list);
        model.addAttribute("titlesJs", list);
        model.addAttribute("sourcesJs", list);

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.HELP;
    }

    @RequestMapping(value = Mappings.CONTACT, method = RequestMethod.GET)
    public String showContact(Model model, HttpSession session) {

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.CONTACT;
    }

    @ResponseBody
    @RequestMapping(value = Mappings.SUBSCRIBE, method = RequestMethod.POST)
    public String postSubscribe(Model model,
                                @RequestParam(name = "email") String mail) {

        Subscription sub = new Subscription(new Date(), mail);
        subscriptionsRepository.save(sub);

        return "";
    }

    @RequestMapping(value = Mappings.CONTACT, method = RequestMethod.POST)
    public String postContact(Model model, HttpSession session,
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
            includeErrorMessage(model, "Impossible de sauvegarder ce message.");
        }

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.CONTACT;
    }

    @RequestMapping(value = Mappings.SITEMAP, method = RequestMethod.GET)
    public String showSitemap(Model model, HttpSession session) {

        List<String[]> links = Arrays.asList(
                new String[]{Mappings.ROOT, "Accueil"},
                new String[]{Mappings.DOWNLOAD, "Téléchargements"},
                new String[]{Mappings.FAQ, "Questions fréquentes"},
                new String[]{Mappings.HELP, "Aide et tutoriels"},
                new String[]{Mappings.ABOUT_PROJECT, "Découvrir le projet"},
                new String[]{Mappings.NEW_VERSION, "Nouvelle version à venir"},
                new String[]{Mappings.LICENSE, "Licence d'utilisation du logiciel"},
                new String[]{Mappings.SITEMAP_XML, "Fichier sitemap.xml"}
        );

        model.addAttribute("links", links);

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.SITEMAP;
    }

    @RequestMapping(value = Mappings.LEGAL_MENTION, method = RequestMethod.GET)
    public String showLegalmentions(Model model, HttpSession session) {

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.LEGAL_MENTION;
    }

    /**
     * Serve update informations about the software. An instance of software
     * contact this server, tell which version it is and get a message.
     * <p>
     * If no message must be shown, return nothing
     *
     * @param version
     * @return
     */
    @ResponseBody
    @RequestMapping(value = Mappings.NEWS, method = RequestMethod.GET)
    public String getNews(HttpServletRequest request,
                          @RequestParam(name = "version", required = true) String version) {

        // log traffic
        version = version.trim().toLowerCase();
        try {
            softwareUtilisationRepository.save(
                    new SoftwareUtilisation(
                            new Date(),
                            request.getHeader("User-Agent"),
                            request.getHeader("Accept-Language"),
                            Utils.anonymizeIpAdress(request.getRemoteAddr()),
                            version));
        } catch (Exception e) {
            logger.error("Error while saving traffic", e);
        }

        // check code
        Integer codeVersion = UpdateInformation.getCodeVersion(version);
        if (codeVersion == null) {
            return "";
        }

        List<UpdateInformation> informations = updateInformationRepository.getForCodeVersion(codeVersion);

        StringBuilder output = new StringBuilder();
        for (UpdateInformation inf : informations) {
            output.append(inf.getContent());
            output.append("\n\n");
        }

        return output.toString();
    }

    @ResponseBody
    @RequestMapping(value = Mappings.GET_STATS_OF_THE_DAY, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getJSONStatsOfTheWeek() throws IOException {
        return getStatsOfTheWeek().getContent();
    }

    @RequestMapping(value = Mappings.ADMIN_PAGE, method = RequestMethod.GET)
    public String showAdminPage(Model model) throws IOException {

        List<Vote> votes = votesRepository.getLasts(new PageRequest(0, 20));
        List<Message> messages = messagesRepository.getLasts(new PageRequest(0, 20));
        List<Subscription> subscriptions = subscriptionsRepository.getLasts(new PageRequest(0, 20));
        List<Stats> stats = statsRepository.getLasts(new PageRequest(0, 20));
        List<SoftwareUtilisation> softwareUtilisations = softwareUtilisationRepository.getLasts(new PageRequest(0, 20));

        model.addAttribute("votes", votes);
        model.addAttribute("messages", messages);
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("stats", stats);
        model.addAttribute("softwareUtilisations", softwareUtilisations);

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.ADMIN;
    }

    @RequestMapping(value = Mappings.ADMIN_PAGE_LOGIN, method = RequestMethod.GET)
    public String showLoginPage(Model model) throws IOException {

        includeMainModelVars(model);
        Mappings.includeMappings(model);
        return Templates.ADMIN_LOGIN;
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

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer rawJson = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                rawJson.append(line);
            }

            int totalDownloads = -1;
            try {

                // do not use "total" value from sourceforge, results are non significants
                JSONArray downloads = JsonPath.read(rawJson.toString(), "$.oses[*][1]");
                totalDownloads = 0;
                for (Integer i : downloads.toArray(new Integer[downloads.size()])) {
                    totalDownloads += i;
                }

            } catch (Exception e) {
                logger.error("Error while parsing JSON", e);
            }

            Stats st = new Stats(new Date(), rawJson.toString(), totalDownloads);

            // save content
            statsRepository.save(st);

            return st;
        }
    }

    private void includeMainModelVars(Model model) {
        try {

            model.addAttribute("averageVote", votesRepository.averageVoteValue());
            model.addAttribute("sumVote", votesRepository.count());

            if (googleAnalyticsApiKey != null) {
                model.addAttribute("googleAnalyticsApiKey", googleAnalyticsApiKey);
            }

        } catch (Exception e) {
            logger.error("Error while adding vote vars to model", e);
        }
    }

    private void includeErrorMessage(Model model, String message) {
        model.addAttribute("errorMessage", message);
    }

}
