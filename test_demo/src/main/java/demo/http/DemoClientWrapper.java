package demo.http;

import demo.urls.Site;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DemoClientWrapper {

    final static Logger log = Logger.getLogger(DemoClientWrapper.class);

    private String service;
    private String baseUrl = Site.baseUrl;

    public DemoClientWrapper(String service) {
        this.service = service;
    }

    public DemoClientWrapper(String baseUrl, String service) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    public Map<Integer, String> encodeImage(String image) {
        String postJsonData = "{\"image\":\"" + image + "\"}";
        return postRequest(postJsonData);
    }

    public Map<Integer, String> postRequest(String data) {
        Map<Integer, String> response = new HashMap();
        String encodedImage;
        String url = this.baseUrl + this.service;
        try {
            URL obj = new URL(url);
            log.info(String.format("Service Url : %s", obj.toString()));

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            String postJsonData = data;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postJsonData);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            log.info(String.format(
                    "Sending Post request to %s, with data %s", url, postJsonData
            ));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer resp = new StringBuffer();

            while ((output = in.readLine()) != null) {
                resp.append(output);
            }
            in.close();

            response.put(Integer.valueOf(responseCode), resp.toString());

            return response;
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }


}
