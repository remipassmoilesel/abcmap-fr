package org.remipassmoilesel.abcmapfr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.remipassmoilesel.abcmapfr.controllers.MainController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(AbcmapFrApplication.DEV_PROFILE)
public class MainControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(StatsTest.class);

    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void welcomePageTests() throws Exception {

        // welcome page
        mockMvc.perform(get(Mappings.ROOT)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("downloadsThisWeek"))
                .andExpect(model().attributeExists("averageVote"))
                .andExpect(model().attributeExists("sumVote"));

        // enable translation
        mockMvc.perform(get(Mappings.TRANSLATE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute(MainController.GTRANSLATE_ATTR_NAME, "true"));

        // disable translation
        mockMvc.perform(get(Mappings.TRANSLATE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr(MainController.GTRANSLATE_ATTR_NAME, "true"))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute(MainController.GTRANSLATE_ATTR_NAME, ""));
    }

}
