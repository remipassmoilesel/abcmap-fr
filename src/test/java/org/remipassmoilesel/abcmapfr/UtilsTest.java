package org.remipassmoilesel.abcmapfr;

import org.junit.Test;
import org.remipassmoilesel.abcmapfr.utils.Utils;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by remipassmoilesel on 19/06/17.
 */

public class UtilsTest {

    @Test
    public void test() {
        assertTrue(Utils.anonymizeIpAdress("127.0.0.1").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.0.11").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.0.111").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.01.1").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.01.12").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.01.122").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.012.1").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.012.12").endsWith("X.X"));
        assertTrue(Utils.anonymizeIpAdress("127.0.012.121").endsWith("X.X"));
    }

}
