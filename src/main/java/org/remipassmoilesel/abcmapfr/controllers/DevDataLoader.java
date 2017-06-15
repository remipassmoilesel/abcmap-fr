package org.remipassmoilesel.abcmapfr.controllers;

import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.entities.Stats;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.remipassmoilesel.abcmapfr.repositories.StatsRepository;
import org.remipassmoilesel.abcmapfr.repositories.VoteRepository;
import org.remipassmoilesel.abcmapfr.utils.DevDataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

@Component
public class DevDataLoader implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(DevDataLoader.class);

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (voteRepository.count() < 1) {
            populateVoteTable();
            logger.warn("Fake votes added");
        }

        if (statsRepository.count() < 1) {
            populateStatsTable();
            logger.warn("Fake stats added");
        }

    }

    private void populateStatsTable() {
        for (int i = 0; i < 100; i++) {
            Stats s = DevDataFactory.newStat(new DateTime().minusDays(i).toDate(), null);
            statsRepository.save(s);
        }
    }

    private void populateVoteTable() {
        for (int i = 0; i < 100; i++) {
            Vote v = DevDataFactory.newVote(-1, null);
            voteRepository.save(v);
        }
    }

}
