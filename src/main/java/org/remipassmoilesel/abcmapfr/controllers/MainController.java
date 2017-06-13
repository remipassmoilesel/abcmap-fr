package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
@Controller
public class MainController {

    @RequestMapping(value = Mappings.INDEX, method = RequestMethod.GET)
    public String showIndex() {
        return Templates.EXAMPLE_CONTENT;
    }

}
