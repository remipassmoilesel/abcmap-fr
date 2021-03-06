package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.remipassmoilesel.abcmapfr.repositories.VotesRepository;
import org.remipassmoilesel.abcmapfr.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private VotesRepository voteRepository;

    @ResponseBody
    @RequestMapping(value = Mappings.VOTE_ROOT, method = RequestMethod.GET)
    public void postVote(HttpServletRequest request,
            @RequestParam(value = "v", required = true) int value,
            @RequestParam(value = "p", required = false) String page) {

        Vote vote = new Vote(value, new Date(), page);
        vote.setAnonymRemoteAddr(Utils.anonymizeIpAdress(request.getRemoteAddr()));
        vote.setUserAgent(request.getHeader("User-Agent"));
        vote.setLanguage(request.getHeader("Accept-Language"));

        voteRepository.save(vote);

    }

    @ResponseBody
    @RequestMapping(value = Mappings.VOTE_GET_BY_DATE,
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vote> votesGetByDate(
            @RequestParam(value = "date", required = true) Date date) {
        return voteRepository.findByDate(date);
    }

    @ResponseBody
    @RequestMapping(value = Mappings.VOTE_GET_ALL,
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vote> votesGetAll() {
        int max = 100;
        int i = 0;
        ArrayList<Vote> rslt = new ArrayList<>();
        Iterator<Vote> it = voteRepository.findAll().iterator();
        while (it.hasNext() && i < max) {
            rslt.add(it.next());
            i++;
        }

        return rslt;
    }

}
