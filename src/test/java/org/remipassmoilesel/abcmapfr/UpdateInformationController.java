package org.remipassmoilesel.abcmapfr;

import org.hamcrest.Matchers;
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

import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(AbcmapFrApplication.DEV_PROFILE)
public class UpdateInformationController {

    private static final Logger logger = LoggerFactory.getLogger(StatsTest.class);

    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void updateInformationController() throws Exception {

        mockMvc.perform(get(Mappings.NEWS)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("version", "crazy value"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        mockMvc.perform(get(Mappings.NEWS)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("version", "1.03"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));

        mockMvc.perform(get(Mappings.NEWS)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("version", "1.02"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("CodeVersion-3")));

        mockMvc.perform(get(Mappings.NEWS)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("version", "1.01"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("CodeVersion-2")))
                .andExpect(content().string(Matchers.containsString("CodeVersion-3")));


    }


}
