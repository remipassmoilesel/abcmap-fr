package org.remipassmoilesel.abcmapfr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
public class Mappings {

    private static final Logger logger = LoggerFactory.getLogger(Mappings.class);
    public static final String MODEL_ARGUMENT_NAME = "mappings" ;

    public static final String ROOT = "/";

    public static final String WELCOME = ROOT + "welcome";
    public static final String DOWNLOAD = ROOT + "download";
    public static final String FAQ = ROOT + "faq";
    public static final String ABOUT_PROJECT = ROOT + "about-project";
    public static final String NEW_VERSION = ROOT + "newversion";
    public static final String HELP = ROOT + "help";
    public static final String CONTACT = ROOT + "contact";

    public static final String VOTES_ROOT = ROOT + "votes/";
    public static final String VOTES_GET_BY_DATE = VOTES_ROOT + "bydate";
    public static final String VOTES_GET_ALL = VOTES_ROOT + "all";

    public static MappingMap getMap() {

        MappingMap result = new MappingMap();

        for (Field f : Mappings.class.getDeclaredFields()) {
            try {
                Object val = f.get(null);
                if (val instanceof String) {
                    result.put(f.getName(), (String) val);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to access field: " + f);
            }
        }

        return result;
    }

    /**
     * Special class used to show errors
     */
    public static class MappingMap extends HashMap<String, String> {
        @Override
        public String get(Object key) {
            String res = super.get(key);
            if (res == null) {
                logger.error("Key do not exist: " + key, new Exception("Key do not exist: " + key));
            }
            return res;
        }
    }

    public static void includeMappings(Model model) {
        model.addAttribute(Mappings.MODEL_ARGUMENT_NAME, Mappings.getMap());
    }
}
