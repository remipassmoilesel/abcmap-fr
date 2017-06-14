package org.remipassmoilesel.abcmapfr;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.remipassmoilesel.abcmapfr.entities.StatsOfTheDay;
import org.remipassmoilesel.abcmapfr.repositories.StatsOfTheDayRepository;
import org.remipassmoilesel.abcmapfr.utils.DevDataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatsOfTheDayTest {

    private static final Logger logger = LoggerFactory.getLogger(StatsOfTheDay.class);

    @Autowired
    private StatsOfTheDayRepository statsRepository;

    @Test
    public void repositoryTest() {

        statsRepository.deleteAll();

        DateTime start = new DateTime();
        List<StatsOfTheDay> stats = new ArrayList<>();

        // create fake stats
        long nbrFake = 20;
        for (int i = 0; i < nbrFake; i++) {
            StatsOfTheDay st = DevDataFactory.newStat(start.plusDays(i + 1).toDate(), null);
            stats.add(st);
        }
        statsRepository.save(stats);

        // retrieve all stats
        long count = statsRepository.count();
        assertTrue(count == nbrFake);

        // retrieve stats of today
        List<StatsOfTheDay> today = statsRepository.findBetween(start.toDate(), start.plusHours(25).toDate());

        assertTrue(today.size() == 1);

        //statsRepository.deleteAll();
    }

}
