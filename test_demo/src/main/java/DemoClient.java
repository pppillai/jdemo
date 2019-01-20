import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.Asserts;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class DemoClient {


    public static void dopost() throws Exception{
        String url = "http://localhost:8083/demo/api/image";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");

        String postJsonData = "{\"image\":\"test\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());


        String ans = response.toString();

        String image = "\"image\":\"";
        String str = ans.substring(image.length()+1);

        String finalAns = new String(Base64.decodeBase64(str.getBytes()));

        Asserts.check(finalAns.equalsIgnoreCase("test"), "H");

    }





}
