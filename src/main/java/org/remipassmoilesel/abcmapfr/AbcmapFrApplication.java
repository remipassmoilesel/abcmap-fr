package org.remipassmoilesel.abcmapfr;

import org.remipassmoilesel.abcmapfr.utils.UpdateFilesListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.nio.file.Paths;

@SpringBootApplication
public class AbcmapFrApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(AbcmapFrApplication.class);

    private static SpringApplication mainApp;

    public static void main(String[] args) {

        mainApp = new SpringApplication(AbcmapFrApplication.class);

        // listen application to update files
        UpdateFilesListener updater = new UpdateFilesListener();
        updater.addPeer(Paths.get("src/main/resources"), Paths.get("target/classes"));

        // first file update
        mainApp.addListeners(updater);

        mainApp.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AbcmapFrApplication.class);
    }

}
