package org.remipassmoilesel.abcmapfr;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.remipassmoilesel.abcmapfr.controllers.ScheduledBackupTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by remipassmoilesel on 19/06/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(AbcmapFrApplication.DEV_PROFILE)
public class ScheduledBackupTaskTest {

    @Autowired
    private ScheduledBackupTask backupTask;

    @Value("${app.db-backup-directory}")
    private String backupDirectoryPath;

    @Value("${app.db-max-backup-number}")
    private int maxBackupNumber;

    @Test
    public void test() throws Exception {

        Path backupDir = Paths.get(backupDirectoryPath);

        // clear all files from backup dir
        FileUtils.deleteDirectory(backupDir.toFile());
        Files.createDirectories(backupDir);

        int backupNumber = maxBackupNumber * 2;

        // do some backups
        for (int i = 0; i <= backupNumber; i++) {
            int returnCode = backupTask.processBackup();

            // test return codes
            assertEquals(returnCode, 0);
        }

        // test if files exists
        ArrayList<String> files = new ArrayList<>(Arrays.asList(backupDir.toFile().list()));
        assertTrue(files.size() >= backupNumber);

        // test if files are empty
        for(String fileName : files){
            long size = Files.size(backupDir.resolve(fileName));
            assertTrue(size > 100);
        }

        // clean backup dir
        backupTask.cleanBackupDir();

        // test backup number after clean
        ArrayList<String> files2 = new ArrayList<>(Arrays.asList(backupDir.toFile().list()));
        assertEquals(maxBackupNumber, files2.size());

    }

}
