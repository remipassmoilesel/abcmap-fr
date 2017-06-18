package org.remipassmoilesel.abcmapfr.controllers;

import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.AbcmapFrApplication;
import org.remipassmoilesel.abcmapfr.entities.Message;
import org.remipassmoilesel.abcmapfr.entities.Stats;
import org.remipassmoilesel.abcmapfr.entities.Subscription;
import org.remipassmoilesel.abcmapfr.entities.Vote;
import org.remipassmoilesel.abcmapfr.repositories.MessagesRepository;
import org.remipassmoilesel.abcmapfr.repositories.StatsRepository;
import org.remipassmoilesel.abcmapfr.repositories.SubscriptionsRepository;
import org.remipassmoilesel.abcmapfr.repositories.VotesRepository;
import org.remipassmoilesel.abcmapfr.utils.DevDataFactory;
import org.remipassmoilesel.abcmapfr.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by remipassmoilesel on 12/06/17.
 */

@Component
public class DevDataLoader implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(DevDataLoader.class);

    @Autowired
    private VotesRepository voteRepository;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @Autowired
    Environment env;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (Arrays.asList(env.getActiveProfiles()).contains(AbcmapFrApplication.DEV_PROFILE) == false) {
            return;
        }

        if (voteRepository.count() < 1) {
            populateVoteTable();
            logger.warn("-- Fake votes added");
        }

        if (statsRepository.count() < 1) {
            populateStatsTable();
            logger.warn("-- Fake stats added");
        }

        if (messagesRepository.count() < 1) {
            populateMessagesTable();
            logger.warn("-- Fake messages added");
        }

        if (subscriptionsRepository.count() < 1) {
            populateSubscriptionsTable();
            logger.warn("-- Fake subscriptions added");
        }

    }

    private void populateStatsTable() {
        for (int i = 0; i < 100; i++) {
            Stats s = DevDataFactory.newStat(new DateTime().minusDays(i).toDate(), null);
            statsRepository.save(s);
        }
    }

    private void populateVoteTable() {

        DateTime start = new DateTime();

        for (int i = 0; i < 100; i++) {
            Vote v = DevDataFactory.newVote(-1, start.minusDays(i).toDate());
            voteRepository.save(v);
        }
    }

    private void populateMessagesTable() {

        DateTime start = new DateTime();

        for (int i = 0; i < 100; i++) {
            Message m = DevDataFactory.newMessage("Hello " + i, Utils.generateLoremIpsum(500),
                    "gerard@lagrimace.fr" + i,
                    start.minusDays(i).toDate());
            messagesRepository.save(m);
        }
    }

    private void populateSubscriptionsTable() {

        DateTime start = new DateTime();

        for (int i = 0; i < 100; i++) {
            Subscription s = DevDataFactory.newSubscription(
                    "gerard@lagrimace.fr" + i,
                    start.minusDays(i).toDate());
            subscriptionsRepository.save(s);
        }
    }

}
