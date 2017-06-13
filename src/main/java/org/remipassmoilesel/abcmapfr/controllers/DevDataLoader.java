package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Utils;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.remipassmoilesel.abcmapfr.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

@Component
public class DevDataLoader implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(DevDataLoader.class);

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (voteRepository.count() < 1) {
            populateVoteTable();
        }
    }

    private void populateVoteTable() {
        for (int i = 0; i < 100; i++) {
            Vote v = new Vote(Utils.randInt(1, 5), new Date());
            voteRepository.save(v);
        }
    }

}
