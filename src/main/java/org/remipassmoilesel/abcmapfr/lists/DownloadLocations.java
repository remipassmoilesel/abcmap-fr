package org.remipassmoilesel.abcmapfr.lists;

import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 17/06/17.
 */
public class DownloadLocations {


    private static HashMap<String, DownloadLocation> downloadLocations = new HashMap<>();

    static {
        downloadLocations.put("windows", new DownloadLocation("https://sourceforge.net/projects/abc-map/files/abcmap-win.zip/download"));
        downloadLocations.put("mac", new DownloadLocation("https://sourceforge.net/projects/abc-map/files/abcmap-mac.zip/download"));
        downloadLocations.put("linux", new DownloadLocation("https://sourceforge.net/projects/abc-map/files/abcmap-linux.zip/download"));
        downloadLocations.put("source", new DownloadLocation("https://sourceforge.net/projects/abc-map/files/abcmap-sources.zip/download"));

        String manualRoot = "/static/manual";
        downloadLocations.put("tutoriel_cotentin.pdf", new DownloadLocation(manualRoot + "/tutoriel_cotentin.pdf", "application/pdf"));
        downloadLocations.put("manual_fr.pdf", new DownloadLocation(manualRoot + "/manual_fr.pdf", "application/pdf"));
        downloadLocations.put("tutoriel_cotentin_exe.zip", new DownloadLocation(manualRoot + "/tutoriel_cotentin_exe.zip", "application/zip"));
        downloadLocations.put("tutoriel_cotentin_swf.zip", new DownloadLocation(manualRoot + "/tutoriel_cotentin_swf.zip", "application/zip"));
        downloadLocations.put("tutoriel_cotentin.swf", new DownloadLocation(manualRoot + "/tutoriel_cotentin.swf", "application/x-shockwave-flash"));

    }

    public static DownloadLocation get(String type) {
        return downloadLocations.get(type);
    }

    public static class DownloadLocation {

        private String location;
        private String contentType;

        public DownloadLocation(String location) {
            this.location = location;
        }

        public DownloadLocation(String location, String contentType) {
            this.location = location;
            this.contentType = contentType;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DownloadLocation that = (DownloadLocation) o;
            return Objects.equals(location, that.location) &&
                    Objects.equals(contentType, that.contentType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(location, contentType);
        }

        @Override
        public String toString() {
            return "DownloadLocation{" +
                    "location='" + location + '\'' +
                    ", contentType=" + contentType +
                    '}';
        }
    }

}
