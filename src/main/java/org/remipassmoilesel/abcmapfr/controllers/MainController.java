package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.remipassmoilesel.abcmapfr.lists.Faq;
import org.remipassmoilesel.abcmapfr.lists.Functionalities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

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
    public String showFaq(Model model) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        List[] lists = Faq.getLists();

        model.addAttribute("titles", lists[0]);
        model.addAttribute("questions", lists[1]);
        model.addAttribute("answers", lists[2]);

        Mappings.includeMappings(model);
        return Templates.FAQ;
    }

}
