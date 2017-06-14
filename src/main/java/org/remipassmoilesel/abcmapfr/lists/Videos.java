package org.remipassmoilesel.abcmapfr.lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by remipassmoilesel on 14/06/17.
 */
public class Videos {

    public static List<String[]> getList() {
        return Arrays.asList(
                new String[]{
                        "Présentation: une carte de promenade en 10 minutes",
                        "//www.youtube.com/embed/lO-DigOoq0o"
                },
                new String[]{
                        "Installation et mise en oeuvre en 5 minutes",
                        "//www.youtube.com/embed/-Uo33WH3lrg"
                },
                new String[]{
                        "Géo-référencer une carte, mesurer des distances et relever des azimuts",
                        "//www.youtube.com/embed/4zHBoM3HUJI"
                }
        );
    }

}
