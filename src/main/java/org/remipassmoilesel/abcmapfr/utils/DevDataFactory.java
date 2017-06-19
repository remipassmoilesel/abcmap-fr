package org.remipassmoilesel.abcmapfr.utils;

import org.remipassmoilesel.abcmapfr.entities.*;

import java.util.Date;

/**
 * Created by remipassmoilesel on 14/06/17.
 */
public class DevDataFactory {

    private static final String STAT_EXAMPLE = "{\"oses\": [[\"Windows\", 199], [\"Macintosh\", 42], [\"Linux\", 16], [\"Android\", 8], [\"Unknown\", 2]], \"end_date\": \"2017-05-01 00:00:00\", \"countries\": [[\"France\", 183], [\"Tunisia\", 13], [\"Belgium\", 6], [\"Ivory Coast\", 5], [\"Benin\", 5], [\"Senegal\", 5], [\"Madagascar\", 4], [\"Algeria\", 4], [\"Djibouti\", 4], [\"Morocco\", 4], [\"Gabon\", 4], [\"Canada\", 3], [\"Burkina Faso\", 3], [\"Spain\", 3], [\"Switzerland\", 3], [\"Reunion\", 3], [\"Niger\", 3], [\"Cameroon\", 2], [\"United States\", 2], [\"Mali\", 2], [\"Congo - Kinshasa\", 1], [\"Guinea\", 1], [\"French Polynesia\", 1], [\"Australia\", 1], [\"Europe (specific country unknown)\", 1], [\"Lebanon\", 1]], \"oses_with_downloads\": [\"Android\", \"Linux\", \"Macintosh\", \"Unknown\", \"Windows\"], \"downloads\": [[\"2017-04-24 00:00:00\", 46], [\"2017-04-25 00:00:00\", 38], [\"2017-04-26 00:00:00\", 36], [\"2017-04-27 00:00:00\", 41], [\"2017-04-28 00:00:00\", 32], [\"2017-04-29 00:00:00\", 19], [\"2017-04-30 00:00:00\", 18], [\"2017-05-01 00:00:00\", 37]], \"messages\": [], \"period\": \"daily\", \"start_date\": \"2017-04-24 00:00:00\", \"oses_by_country\": {\"Canada\": {\"Windows\": 3}, \"Madagascar\": {\"Windows\": 4}, \"Congo - Kinshasa\": {\"Windows\": 1}, \"Guinea\": {\"Windows\": 1}, \"French Polynesia\": {\"Windows\": 1}, \"France\": {\"Windows\": 125, \"Android\": 4, \"Macintosh\": 40, \"Linux\": 14}, \"Ivory Coast\": {\"Windows\": 4, \"Macintosh\": 1}, \"Cameroon\": {\"Windows\": 2}, \"Burkina Faso\": {\"Windows\": 1, \"Android\": 2}, \"Benin\": {\"Windows\": 4, \"Android\": 1}, \"Australia\": {\"Windows\": 1}, \"Algeria\": {\"Windows\": 3, \"Android\": 1}, \"Djibouti\": {\"Windows\": 4}, \"Belgium\": {\"Windows\": 6}, \"Spain\": {\"Windows\": 1, \"Linux\": 2}, \"United States\": {\"Windows\": 1, \"Unknown\": 1}, \"Morocco\": {\"Windows\": 4}, \"Gabon\": {\"Windows\": 3, \"Macintosh\": 1}, \"Mali\": {\"Windows\": 2}, \"Switzerland\": {\"Windows\": 3}, \"Europe (specific country unknown)\": {\"Unknown\": 1}, \"Tunisia\": {\"Windows\": 13}, \"Lebanon\": {\"Windows\": 1}, \"Senegal\": {\"Windows\": 5}, \"Reunion\": {\"Windows\": 3}, \"Niger\": {\"Windows\": 3}}, \"total\": 267, \"stats_updated\": \"2017-05-01 21:01:09\", \"summaries\": {\"os\": {\"top\": \"Windows\", \"percent\": 74, \"modifier_text\": \"\"}, \"geo\": {\"top\": \"France\", \"percent\": 68, \"modifier_text\": \"\"}, \"time\": {\"downloads\": 267}}}";


    public static Vote newVote(int vote, Date date) {

        if (vote == -1) {
            vote = Utils.randInt(1, 5);
        }

        if (date == null) {
            date = new Date();
        }

        return new Vote(vote, date);
    }

    public static Stats newStat(Date date, String content) {

        if (date == null) {
            date = new Date();
        }

        if (content == null) {
            content = STAT_EXAMPLE;
        }

        return new Stats(date, content, Utils.randInt(150, 700));
    }

    public static Message newMessage(String object, String message, String mail, Date date) {
        if (object == null) {
            object = "error";
        }
        if (message == null) {
            message = Utils.generateLoremIpsum(500);
        }

        if (date == null) {
            date = new Date();
        }

        if (mail == null) {
            mail = "jeancharles@delahoya.com";
        }
        return new Message(object, message, mail, date);
    }

    public static Subscription newSubscription(String mail, Date date) {
        if (mail == null) {
            mail = "gege@lagrimace.fr" + Utils.randInt(1, 8000);
        }

        if (date == null) {
            date = new Date();
        }
        return new Subscription(date, mail);
    }

    public static SoftwareUtilisation newSoftwareUtilisation(Date date) {

        if (date == null) {
            date = new Date();
        }

        SoftwareUtilisation su = new SoftwareUtilisation(
                date,
                "User-Agent - " + Utils.generateLoremIpsum(50),
                "Accept-Language - " + Utils.generateLoremIpsum(50),
                "127.0.X.X" + Utils.generateLoremIpsum(9),
                "1.03");

        return su;
    }
}
