package org.remipassmoilesel.abcmapfr;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.remipassmoilesel.abcmapfr.controllers.MainController;
import org.remipassmoilesel.abcmapfr.entities.Message;
import org.remipassmoilesel.abcmapfr.repositories.MessagesRepository;
import org.remipassmoilesel.abcmapfr.utils.Utils;
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
import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(AbcmapFrApplication.DEV_PROFILE)
public class MessagesTest {

    private static final Logger logger = LoggerFactory.getLogger(MessagesTest.class);

    private MockMvc mockMvc;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private MainController mainController;

    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void test() throws Exception {

        // try to send a message
        mockMvc.perform(post(Mappings.CONTACT)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("object", "error")
                .param("email", "jeandugenou@hubert.fr")
                .param("message", Utils.generateLoremIpsum(655)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", Matchers.not(Matchers.isEmptyString())));

        // try to send message with weird chars
        mockMvc.perform(post(Mappings.CONTACT)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("object", "error")
                .param("email", "adresse@mail.fr <script>alert(\"Gnéééé\")</script>")
                .param("message", "adresse@mail.fr <script>alert(\"Gnéééé\")</script>  '; \"; DROP DATABASE gneeee;"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", Matchers.not(Matchers.isEmptyString())));

        // test data in db
        Iterator<Message> it = messagesRepository.findAll().iterator();
        while (it.hasNext()) {
            Message msg = it.next();
            assertTrue(msg.getObject().contains("<") == false);
            assertTrue(msg.getObject().contains(">") == false);
        }
    }
}