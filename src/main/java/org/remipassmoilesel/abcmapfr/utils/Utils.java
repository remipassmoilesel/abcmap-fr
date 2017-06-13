package org.remipassmoilesel.abcmapfr.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Random;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
public class Utils {

    private static Random rand = new Random();

    /**
     * Return a random int
     *
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String nodeToHtmlString(Node doc) throws TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.transform(domSource, result);
        return writer.toString();
    }

}
