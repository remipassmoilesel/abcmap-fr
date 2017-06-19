package org.remipassmoilesel.abcmapfr.controllers;

import org.joda.time.DateTime;
import org.remipassmoilesel.abcmapfr.AbcmapFrApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by remipassmoilesel on 19/06/17.
 */
@Component
public class ScheduledBackupTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledBackupTask.class);

    // 3 days between backups
    private static final long DELAY_BETWEEN_EXEC = 2 * 24 * 60 * 60 * 1000;

    // first backup 10 minutes after launch
    private static final long INITIAL_DELAY = 10 * 60 * 1000;

    @Value("${app.db-backup-directory}")
    private String backupDirectoryPath;

    @Value("${app.db-name}")
    private String dbName;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbUserPassword;

    @Value("${app.db-max-backup-number}")
    private int maxBackupNumber;

    private ReentrantLock lock = new ReentrantLock();

    @Scheduled(initialDelay = INITIAL_DELAY, fixedRate = DELAY_BETWEEN_EXEC)
    public void scheduledTask() {

        if (lock.tryLock() == false) {
            return;
        }

        try {
            processBackup();
            cleanBackupDir();
        } catch (Exception e) {
            logger.error("Error while backing up database", e);
        } finally {
            lock.unlock();
        }
    }

    public int processBackup() throws Exception {

        Path backupDir = Paths.get(backupDirectoryPath);

        // create directories if needed
        if (Files.isDirectory(backupDir) == false) {
            Files.createDirectories(backupDir);
            logger.warn("Backup directory created at: " + backupDir);
        }

        // create dump command
        String dumpPath = backupDir.resolve("dump_" + new DateTime().toString("yyyy-MM-dd-HH-mm-SSS") + ".sql").toString();
        String dumpCommand = String.format("mysqldump -u %s -p%s --databases %s > %s", dbUserName, dbUserPassword, dbName, dumpPath);

        logger.warn("Backup database in: " + dumpPath);
        logger.warn("Backup database command: " + dumpCommand);

        // execute it
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", dumpCommand});
        p.waitFor();

        // check response
        int exitVal = p.exitValue();

        if (exitVal != 0) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));

            StringBuilder errorOutput = new StringBuilder();
            String line = "\n";
            while ((line = reader.readLine()) != null) {
                errorOutput.append(line + "\n");
            }

            logger.error("Error while backing up database");
            logger.error(errorOutput.toString());
        }

        return exitVal;
    }

    public void cleanBackupDir() throws IOException {

        Path backupDir = Paths.get(backupDirectoryPath);

        // clean old files
        ArrayList<String> files = new ArrayList<>(Arrays.asList(backupDir.toFile().list()));
        Collections.sort(files);
        Collections.reverse(files);

        while (files.size() > maxBackupNumber) {
            String f = files.remove(0);
            Files.delete(backupDir.resolve(f));
            logger.warn("Deleting old backup: " + f);
        }

    }

}
