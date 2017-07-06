package org.remipassmoilesel.abcmapfr.lists;

import org.remipassmoilesel.abcmapfr.Mappings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remipassmoilesel on 14/06/17.
 */
public class Faq {

    public static List[] getLists() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();

        Mappings.MappingMap mappings = Mappings.getMap();
        String downloadPage = mappings.get("DOWNLOAD");
        String projectPage = mappings.get("ABOUT_PROJECT");
        String helpPage = mappings.get("HELP");
        String licensePage = mappings.get("LICENSE");
        String newversionPage = mappings.get("NEW_VERSION");
        String contactPage = mappings.get("CONTACT");

        String facebookPage = "https://www.facebook.com/abcmap";
        String googleplusPage = "https://plus.google.com/114048401722237104335";

        titles.add("Questions techniques");
        questions.add("Comment bien démarrer Abc-Map ?");
        answers.add("<p><b>Tout d'abord rendez-vous sur la </b><a href='" + downloadPage + "' target = '_blank'>page de téléchargement</a>" +
                " pour remplir le petit formulaire. Validez puis choisissez le lien qui correspond à votre système:" +
                " Windows, Linux ou Mac.</p>" +
                "<p><b>Ensuite, décompressez l'archive</b> à l'aide d'un logiciel de compression, par exemple" +
                "    <a href='http://www.7-zip.org/download.html' target='_blank'>7-zip</a>. Si vous ne parvenez pas à ouvrir l'archive " +
                "    ZIP du logiciel, rendez-vous <a href='#question3'>plus bas</a> sur cette page.</p>" +
                "<p><b>Ouvrez le dossier Abc-Map et lancez le fichier 'abcmap.exe | .sh | .command'</b> (par exemple en double-cliquant dessus). " +
                "    Attendez quelques minutes.</p>" +
                "<p><b>Pour les utilisateurs de Linux</b>, un tutoriel sur le lancement des scripts est disponible sur le forum" +
                "    Ubuntu: <a href='http://doc.ubuntu-fr.org/tutoriel/script_shell#executer_un_script' target='_blank'>" +
                "    http://doc.ubuntu-fr.org/tutoriel/...</a></p>" +
                "<p><b>Pour les utilisateurs Mac</b>, une page d'aide explique comment ouvrir une application provenant d'un développeur" +
                "    non identifié: <a href='http://support.apple.com/kb/PH11436?viewlocale=fr_FR' target='_blank'>" +
                "    http://support.apple.com/kb/...</a> Malheureusement il est possible que le logiciel ne fonctionne pas avec d'anciennes versions" +
                "    de Mac OS.</p>" +
                "<p><b>Si un message d'erreur apparait, essayez d'exécuter les lanceurs spéciaux.</b> Chaque lanceur concerne un fonctionnement" +
                "    particulier expliqué dans le fichier 'LisezMoi.txt'. Dans tous les cas, si vous en avez la possibilité préférez" +
                "    'abcmap.exe | .sh | .command'</p>" +
                "<p><b>Dans tous les cas assurez vous également que le logiciel posséde des droits d'éxécution suffisants.</b> Deux mesures simples" +
                "    pourront certainement vous aider:" +
                "    <ol>" +
                "        <li>Utilisez le logiciel dans un repertoire qui vous appartient comme par exemple" +
                "            'Mes documents' ou 'Home'.</li>" +
                "        <li>Sur Linux et les sytèmes utilisant des droits d'accés, vous serez certainement amené à" +
                "            autoriser explicitement l'éxecution du programme.</li>" +
                "    </ol></p>" +
                "<p><b>Ensuite il ne vous reste plus qu'à vous lancer</b>, par exemple à partir du tutoriel qui vous conviendra" +
                "    sur <a href='{$url->HELP_URL->url}'>la page d'aide du site</a>.</p>");

        titles.add("");
        questions.add("Abc-Map et Java sont-ils sûrs ?");
        answers.add("<p><b>Abc-Map est un logiciel libre (à code source ouvert et vérifiable) recommandé par plusieurs académies françaises</b>" +
                " (tous les liens sur la " +
                "<a href='" + projectPage + "'>page du présentation du projet</a>)" +
                "</p> " +
                "<p>Java est un support pour faire fonctionner des programmes utilisé partout dans le monde." +
                "<ul><li>Fiche explicative sur Java par le site Clubic:" +
                "    <a href='http://www.clubic.com/telecharger-fiche121472-java-runtime-environment.html' target='_blank'>" +
                "        http://www.clubic.com/...</a></li> <li>Et sur Comment Ca Marche:" +
                "    <a href='http://www.commentcamarche.net/download/telecharger-34055318-java-runtime-environment' target='_blank'>" +
                "        http://www.commentcamarche.net/...</a></li>" +
                "</ul></p>");

        titles.add("");
        questions.add("Je n'arrive pas à télécharger l'archive du logiciel correctement.");
        answers.add("<p>Si vous téléchargez loin du serveur (France) ou si le serveur est chargé il se peut que l'archive du logiciel soit corrompue à" +
                "    l'arrivée. Dans ce cas vous pouvez:</p>" +
                "" +
                "<ul>" +
                "    <li>Essayer à nouveau jusqu'à obtention d'un fichier sain,</li>" +
                "    <li>Télécharger l'archive à l'aide d'un gestionnaire de téléchargement (par exemple" +
                "        <a href='http://www.freedownloadmanager.org/' target='_blank'>http://www.freedownloadmanager.org/</a>),</li>" +
                "    <li>Essayer de récupérer l'archive à l'aide d'un logiciel spécialisé ( par exemple à l'aide de ce tutoriel:" +
                "        <a href='http://www.cestfacile.org/reparer-archive-zip-corrompue.htm' target='_blank'>http://www.cestfacile.org/...</a>)</li>" +
                "</ul>");

        titles.add("");
        questions.add("Comment savoir si Abc-Map fonctionnera sur mon ordinateur ?");
        answers.add("<p>Abc-Map est un logiciel multi-plateforme c’est à dire qu’il est capable de fonctionner" +
                "    sur plusieurs systèmes d’exploitation. Le logiciel a été testé sur:</p>" +
                "<ul>" +
                "    <li>Windows XP</li>" +
                "    <li>Windows 7</li>" +
                "    <li>Windows 8</li>" +
                "    <li>Ubuntu 12</li>" +
                "    <li>Ubuntu 14</li>" +
                "    <li>XUbuntu 14</li>" +
                "    <li>Debian 7.8</li>" +
                "    <li>HandyLinux (03-2015)</li>" +
                "    <li>OS X Mountain Lion</li>" +
                "</ul>" +
                "" +
                "<p>Malheureusement il est possible que le logiciel ne fonctionne pas avec d'anciennes" +
                "    versions de Mac OS.</p>" +
                "" +
                "<p>" +
                "    <a href='" + helpPage + "'>Une vidéo tutoriel de 5 minutes</a> décrivant l’installation " +
                "étape par étape est disponible en ligne. En cas de problèmes pensez également à consulter cette FAQ." +
                "</p>");

        titles.add("");
        questions.add("Puis-je importer/exporter des tracés au format GPX ou KML ?");
        answers.add("<p>L'export et l'import de ces fichiers est impossible pour l'instant, mais cette fonctionnalité sera " +
                "disponible dans la deuxième version du logiciel. </p>");

        titles.add("");
        questions.add("Abc-Map a des problèmes de mémoire / Comment faire de plus grandes cartes ?");
        answers.add("<p>Vous pouvez exécuter Abc-Map avec plus de mémoire vive, pour optimiser ses capacités et" +
                "    faire de plus grandes cartes:</p>" +
                "" +
                "<ol>" +
                "    <li>Téléchargez la version qui correspond à votre système,</li>" +
                "    <li>Créez un fichier texte de commande en vous inspirant du fichier de lancement de la version Linux</li>" +
                "    <li>Puis modifiez la commande suivante en remplaçant ### par la taille de mémoire vive que vous souhaitez utiliser au" +
                "    maximum en Mo (par défaut: 1024 Mo maximum):" +
                "    <div style='text-align: center'><b>java -Xms512m -Xmx###m -jar ./abcmap.jar</b></div></li>" +
                "    <li>En fonction de votre système autorisez l'exécution du fichier de commande</li>" +
                "    <li>Utilisez désormais le fichier de commande pour lancer Abc-Map</li>" +
                "</ol>");

        titles.add("");
        questions.add("L'impression des projets ne fonctionne pas correctement.");
        answers.add("<p>Il peut arriver qu'Abc-Map ne fonctionne pas très bien avec certains pilotes d'impression. Dans ce cas, vous" +
                " avez plusieurs solutions:</p>" +
                "<ul>" +
                "    <li><b>Imprimer le projet au format PDF</b> pour ensuite l'imprimer avec votre lecteur de PDF habituel." +
                "        Vous pouvez utiliser par exemple" +
                "        <a href='http://www.pdfforge.org/pdfcreator' target='_blank'>PDF Creator</a>,</li>" +
                "" +
                "    <li><b>Exporter les cadres de mise en page</b> et les imprimer à l'aide d'un logiciel de visionnage d'images." +
                "        vous pouvez utiliser par exemple" +
                "        <a href='http://www.xnview.com/fr/' target='_blank'>XnView</a>,</li>" +
                "" +
                "    <li><b>Exporter la carte entière</b> pour la modifier avec le logiciel de votre choix.</li>" +
                "" +
                "</ul>");

        titles.add("Questions sur le projet");
        questions.add("C’est gratuit ? Pourquoi ? Abc-Map est-il sûr ?");
        answers.add("<p><b>Abc-Map est un logiciel libre.</b> Un logiciel libre est un logiciel dont vous " +
                "    pouvez consulter, modifier et redistribuer le code source. Il est presque impossible " +
                "    qu'un logiciel libre soit malicieux puisque son code est lisible et accessible pour vérification. " +
                "    N'importe qui est en mesure de vérifier ce que ce programme fait en détail." +
                "</p>" +
                "<p>" +
                "    Le mouvement du logiciel libre est un mouvement mondial qui garantit aux" +
                "    utilisateurs des libertés essentielles. Vous utilisez quotidiennement des logiciels" +
                "    libres comme par exemple: <a href='http://www.videolan.org/vlc/' target='_blank'>VLC</a>," +
                "    <a href='https://www.mozilla.org/fr/firefox/new/' target='_blank'>Firefox</a> ou" +
                "    <a href='https://fr.libreoffice.org/' target='_blank'>LibreOffice</a>." +
                "</p>");

        titles.add("");
        questions.add("Quel est l’intérêt d'Abc-Map par rapport à d'autres logiciels ?");
        answers.add("<p>Le but d’Abc-Map n’est pas de concurrencer les très bons logiciels existant tels que" +
                " <a href='http://www.qgis.org/' target='_blank'>QGis</a> ou" +
                " <a href='http://www.gvsig.org/' target='_blank'>GvSig</a>. Ces logiciels très complets sont " +
                "d'un niveau technique supérieur mais sont également bien plus complexes.</p>" +
                "" +
                "<p>Le but de ce logiciel est de proposer une solution simple pour créer des cartes, " +
                "pour tous ceux qui ne souhaitent pas se former à des logiciels lourds et complexes.</p>" +
                "" +
                "<p><b>L'avantage principal de ce logiciel est de permettre la création de cartes rapides sans " +
                "perdre de temps en formation ou en difficultés diverses.</b></p>");

        titles.add("");
        questions.add("Sous quelle licence est diffusé ce logiciel ?");
        answers.add("<p>Abc-Map est un logiciel libre diffusé sous <a href='http://www.gnu.org/copyleft/gpl.html' target='_blank'>Licence Publique Générale" +
                "    GNU version 3</a>. Vous pouvez consulter le texte complet <a target='_blank' href='" + licensePage + "'>en ligne</a> ou directement" +
                "    dans le dossier du logiciel.</p>");

        titles.add("");
        questions.add("Puis-je utiliser ce logiciel pour une activité professionnelle ?");
        answers.add("Abc-Map est libre gratuit pour tout type d'activité. La licence d'utilisation du" +
                "    logiciel est <a target='_blank' href='" + licensePage + "'>disponible en ligne</a>" +
                "    ou directement dans le dossier du logiciel.");

        titles.add("");
        questions.add("Pourquoi faire un don ?");
        answers.add("<p>Abc-Map est disponible gratuitement et a été développé bénévolement. Il vous permet de créer des cartes facilement" +
                " et pour tous usages, y compris professionnels. Le développement de ce logiciel est long et difficile, et vos dons financent des" +
                " heures de développement pour le maintenir et le perfectionner.</p>" +
                "" +
                "<p><b>Faire un don financera des heures de développement. 8€ peuvent faire la différence," +
                " et plusieurs dons cumulés permettront de fiabiliser et de perfectionner le logiciel. Rendez vous sur la" +
                "</b> <a href='" + newversionPage + "'> page de présentation du projet</a> <b>" +
                "pour connaitre le détail des améliorations à l'étude.</b></p>");


        titles.add("Contact");
        questions.add("Je souhaite aider le développement de ce logiciel.");
        answers.add("" +
                "<p>Le développeur de ce logiciel est à la recherche de partenariats." +
                "    Ce projet vous plait ? Vous souhaitez aider à sa diffusion ?</p>" +
                "<ul>" +
                "    <li><b>Partagez</b> ce logiciel avec vos proches !</li>" +
                "    <li><a href='" + facebookPage + "' target='_blank'>Rendez-vous sur la page Facebook du projet,</a></li>" +
                "    <li><a href='" + googleplusPage + "' target='_blank'>Rendez-vous sur la page Google + du projet,</a></li>" +
                "    <li><b>Vous possédez un espace d'expression en ligne ?</b> Ecrivez vos ressentis sur ce logiciel," +
                "        le développeur peut vous fournir de l'aide, de la documentation et des tutoriels sur mesure correspondant à vos besoins" +
                "        ou à ceux de votre communauté.</li>" +
                "    <li><b>Faites un don</b> sécurisé en ligne via la <a href='" + projectPage + "'>" +
                "        page de présentation du projet</a></li>" +
                "</ul>");


        titles.add("");
        questions.add("Je souhaite donner mon avis sur ce logiciel.");
        answers.add("<p>Vos suggestions sont bienvenues !</p>" +
                "<ul>" +
                "    <li><b>Remplissez en 10 minutes</b> le <a target='_blank' href='http://goo.gl/forms/TRUIBHVWe9'>" +
                "        questionnaire d'expérience utilisateur</a>,</li>" +
                "    <li><b>Votez</b> sur le site en bas de chaque page ou directement via le logiciel,</li>" +
                "    <li><a href='" + contactPage + "'>Contactez</a> le développeur de ce logiciel pour lui faire part de vos remarques.</li>" +
                "</ul>");


        titles.add("");
        questions.add("J’ai d’autres questions.");
        answers.add("<p>Posez les directement au développeur de ce logiciel sur <a href='" + contactPage + "'>" +
                "    la page de contact.</a></p>");

        return new List[]{titles, questions, answers};
    }

}
