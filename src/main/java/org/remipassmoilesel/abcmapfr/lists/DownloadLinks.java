package org.remipassmoilesel.abcmapfr.lists;

import java.util.HashMap;

/**
 * Created by remipassmoilesel on 17/06/17.
 */
public class DownloadLinks {

    private static HashMap<String, String> downloadLinks = new HashMap<>();

    static {
        downloadLinks.put("windows", "https://sourceforge.net/projects/abc-map/files/abcmap-win.zip/download");
        downloadLinks.put("mac", "https://sourceforge.net/projects/abc-map/files/abcmap-mac.zip/download");
        downloadLinks.put("linux", "https://sourceforge.net/projects/abc-map/files/abcmap-linux.zip/download");
        downloadLinks.put("sources", "https://sourceforge.net/projects/abc-map/files/abcmap-sources.zip/download");
    }

    public static String get(String type){
        return downloadLinks.get(type);
    }

}
