package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.remipassmoilesel.abcmapfr.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
@Controller
public class VoteController {

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteRepository votes;

    @ResponseBody
    @RequestMapping(value = Mappings.VOTES_ROOT, method = RequestMethod.GET)
    public void postVote(
            @RequestParam(value = "v", required = true) int value,
            @RequestParam(value = "p", required = false) String page) {
        logger.error(String.valueOf(value));
        logger.error(String.valueOf(value));
        logger.error(String.valueOf(value));
        logger.error(String.valueOf(value));
        logger.error(page);
        logger.error(page);
        logger.error(page);
        logger.error(page);
        logger.error(page);
    }

    @ResponseBody
    @RequestMapping(value = Mappings.VOTES_GET_BY_DATE,
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vote> votesGetByDate(
            @RequestParam(value = "date", required = true) Date date) {
        return votes.findByDate(date);
    }

    @ResponseBody
    @RequestMapping(value = Mappings.VOTES_GET_ALL,
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vote> votesGetAll() {
        int max = 100;
        int i = 0;
        ArrayList<Vote> rslt = new ArrayList<>();
        Iterator<Vote> it = votes.findAll().iterator();
        while (it.hasNext() && i < max) {
            rslt.add(it.next());
            i++;
        }

        return rslt;
    }

}
