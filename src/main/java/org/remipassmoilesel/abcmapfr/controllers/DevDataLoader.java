package org.remipassmoilesel.abcmapfr.controllers;

import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.AbcmapFrApplication;
import org.remipassmoilesel.abcmapfr.entities.*;
import org.remipassmoilesel.abcmapfr.repositories.*;
import org.remipassmoilesel.abcmapfr.utils.DevDataFactory;
import org.remipassmoilesel.abcmapfr.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private UpdateInformationRepository updateInformationRepository;
    @Autowired
    private SoftwareUtilisationRepository softwareUtilisationRepository;

    @Autowired
    private Environment env;

    @Value("${app.db-name}")
    private String dbName;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.warn("Database name: " + dbName);

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

        if (updateInformationRepository.count() < 1) {
            populateUpdateInformationsTable();
            logger.warn("-- Fake update informations added");
        }

        if (softwareUtilisationRepository.count() < 1) {
            populateSoftwareUtilisationTable();
            logger.warn("-- Fake software utilisations added");
        }

    }

    private void populateSoftwareUtilisationTable() {

        DateTime start = new DateTime();

        for (int i = 0; i < 100; i++) {
            SoftwareUtilisation s = DevDataFactory.newSoftwareUtilisation(start.minusDays(i).toDate());
            softwareUtilisationRepository.save(s);
        }
    }

    private void populateUpdateInformationsTable() {

        DateTime start = new DateTime();

        for (int i = 0; i <= 3; i++) {
            UpdateInformation ui = new UpdateInformation(
                    start.minusDays(i).toDate(),
                    "CodeVersion-" + i + " -- " + Utils.generateLoremIpsum(500),
                    i);
            updateInformationRepository.save(ui);
        }

    }

    private void populateStatsTable() {

        // start at 3 in order to download stats today
        for (int i = 3; i < 100; i++) {
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
