package demo.utils;

import demo.http.DemoClientWrapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.util.Map;


/*
 *
 * Utility Class
 * Use this class inside test
 * Use DemoClientWrapper object to make http calls.
 *
 * */


public class ImageUtils {

    private static Logger log = Logger.getLogger(ImageUtils.class);


    /*
    *
    * @param service url of service
    * @param image data
    *
    * Return decoded image data
    *
    * */
    public static String getDecodedImage(String service, String image) {

        int responseCode = 0;
        String finalImage = null;

        log.info("Creating a new client request");
        DemoClientWrapper dc = new DemoClientWrapper(service);

        Map<Integer, String> respMap = dc.encodeImage(image);
        log.info("Got response from image service");

        if (respMap == null) {
            return "";
        } else {

            for (Map.Entry<Integer, String> me : respMap.entrySet()) {
                responseCode = me.getKey().intValue();
                finalImage = me.getValue();
            }
        }

        if (responseCode == 200) {
            return decodeString(finalImage.substring("\"image\":\"".length() + 1));
        } else {
            log.error("Did not get HTTP OK status.");
        }


        return finalImage;
    }

    /*
     *
     * @param service url of service
     * @param image data
     *
     * Return encoded image data
     *
     * */
    public static String getEncodedImage(String service, String image) {
        int responseCode = 0;
        String finalImage = null;

        log.info("Creating a new client request");
        DemoClientWrapper dc = new DemoClientWrapper(service);

        Map<Integer, String> respMap = dc.encodeImage(image);
        log.info("Got response from image service");

        if (respMap == null) {
            return "";
        } else {

            for (Map.Entry<Integer, String> me : respMap.entrySet()) {
                responseCode = me.getKey().intValue();
                finalImage = me.getValue();
            }
        }

        if (responseCode == 200) {
            return finalImage;
        } else {
            log.error("Did not get HTTP OK status.");
        }


        return finalImage;
    }

    /*
     *
     * @param str string to encode Base64
     *
     * Return after removing html escaping chars
     * */
    public static String encodeString(String str) {
        return removeHtmlEscapingFromString(new String(Base64.encodeBase64(str.getBytes())));
    }

    /*
    * @param str string to decode Base64
    *
    * Return decoded string
    *
    * */
    public static String decodeString(String str) {
        return new String(Base64.decodeBase64(str.getBytes()));
    }

    /*
    * @param str string to remove html escaping chars.
    *
    * Return str
    * */
    public static String removeHtmlEscapingFromString(String str) {
        while (true) {
            if (str.endsWith("=")) {
                str = str.substring(0, str.length() - 1);
            } else {
                return str;
            }
        }
    }
}
