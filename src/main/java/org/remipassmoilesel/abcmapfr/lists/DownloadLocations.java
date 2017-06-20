package org.remipassmoilesel.abcmapfr.lists;

import java.util.HashMap;

/**
 * Created by remipassmoilesel on 17/06/17.
 */
public class DownloadLocations {

    private static HashMap<String, String> downloadLocations = new HashMap<>();

    static {
        downloadLocations.put("windows", "https://sourceforge.net/projects/abc-map/files/abcmap-win.zip/download");
        downloadLocations.put("mac", "https://sourceforge.net/projects/abc-map/files/abcmap-mac.zip/download");
        downloadLocations.put("linux", "https://sourceforge.net/projects/abc-map/files/abcmap-linux.zip/download");
        downloadLocations.put("source", "https://sourceforge.net/projects/abc-map/files/abcmap-sources.zip/download");

        String manualRoot = "/static/manual";
        downloadLocations.put("tutoriel_cotentin.pdf", manualRoot + "/tutoriel_cotentin.pdf");
        downloadLocations.put("manual_fr.pdf", manualRoot + "/manual_fr.pdf");
        downloadLocations.put("tutoriel_cotentin_exe.zip", manualRoot + "/tutoriel_cotentin_exe.zip");
        downloadLocations.put("tutoriel_cotentin_swf.zip", manualRoot + "/tutoriel_cotentin_swf.zip");
        downloadLocations.put("tutoriel_cotentin.swf", manualRoot + "/tutoriel_cotentin.swf");

    }

    public static String get(String type) {
        return downloadLocations.get(type);
    }

}
