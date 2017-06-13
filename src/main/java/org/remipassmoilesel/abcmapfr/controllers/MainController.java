package org.remipassmoilesel.abcmapfr.controllers;

import org.remipassmoilesel.abcmapfr.Mappings;
import org.remipassmoilesel.abcmapfr.Templates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
@Controller
public class MainController {

    @RequestMapping(value = Mappings.ROOT, method = RequestMethod.GET)
    public String showIndex() {
        return "redirect:" + Mappings.WELCOME;
    }

    @RequestMapping(value = Mappings.WELCOME, method = RequestMethod.GET)
    public String showWelcome(Model model) {

        List<String> functionnalities = Arrays.asList(

                "<b>Importez et assemblez automatiquement un fond de carte</b> à partir d'images ou"
                        + "de captures d'écran. Les sources sont nombreuses: sites de cartographie en ligne,"
                        + "cartes numérisées, images aériennes, ...",

                "<b>Tracez des formes vectorielles</b>, ins&eacute;rez des symboles, des &eacute;tiquettes de texte"
                        + "et travaillez avec des calques à transparence variable.",

                "<b>G&eacute;o-r&eacute;f&eacute;rencez vos cartes</b> pour utiliser un syst&egrave;me de"
                        + "coordonn&eacute;es sans difficult&eacute;s. Indiquez seulement les coordonn&eacute;es de deux"
                        + "points sur la carte.",

                "<b>Mesurez des distances</b> et relevez des azimuts à partir de formes géométriques simples.",

                "<b>Mettez en forme vos cartes pour impression</b> en quelques clics. Utilisez dans un même document"
                        + "plusieurs formats standards ou personnalis&eacute;s."

        );


        model.addAttribute("functionnalities", functionnalities);

        return Templates.WELCOME;
    }

}
