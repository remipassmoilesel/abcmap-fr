package org.remipassmoilesel.abcmapfr;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@ActiveProfiles(AbcmapFrApplication.DEV_PROFILE)
public class ExternalResourcesTest {

    @Test
    public void test() throws IOException {

        List<String> toTest = Arrays.asList(
                "https://sourceforge.net/projects/abc-map/files/abcmap-win.zip/download",
                "https://sourceforge.net/projects/abc-map/files/abcmap-mac.zip/download",
                "https://sourceforge.net/projects/abc-map/files/abcmap-linux.zip/download",
                "https://sourceforge.net/projects/abc-map/files/abcmap-sources.zip/download"
        );

        CloseableHttpClient client = HttpClientBuilder.create().build();

        for (String url : toTest) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();

            assertTrue(url, code == 200);
        }

    }

}
