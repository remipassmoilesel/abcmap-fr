package org.remipassmoilesel.abcmapfr;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.remipassmoilesel.abcmapfr.controllers.MainController;
import org.remipassmoilesel.abcmapfr.entities.Stats;
import org.remipassmoilesel.abcmapfr.repositories.StatsRepository;
import org.remipassmoilesel.abcmapfr.utils.DevDataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(AbcmapFrApplication.DEV_PROFILE)
public class StatsTest {

    private static final Logger logger = LoggerFactory.getLogger(StatsTest.class);

    private MockMvc mockMvc;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private MainController mainController;

    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void test() throws Exception {

        statsRepository.deleteAll();

        DateTime start = new DateTime().plusHours(1);
        List<Stats> stats = new ArrayList<>();

        // create fake stats
        long nbrFake = 20;
        for (int i = 0; i < nbrFake; i++) {
            Stats st = DevDataFactory.newStat(start.plusDays(i).toDate(), null);
            stats.add(st);
        }
        statsRepository.save(stats);

        // retrieve all stats
        long count = statsRepository.count();
        assertTrue(count == nbrFake);

        // retrieve stats of today
        Stats today = mainController.getStatsOfTheWeek();

        assertTrue(today != null);

        // test retrieving stats of the day
        mockMvc.perform(get(Mappings.GET_STATS_OF_THE_DAY))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*", hasSize(greaterThan(1))));

        //statsRepository.deleteAll();

    }
}