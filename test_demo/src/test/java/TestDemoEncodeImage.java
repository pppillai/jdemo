import demo.http.DemoClientWrapper;
import demo.urls.Site;
import demo.utils.ImageUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class TestDemoEncodeImage {

    private static Logger log = Logger.getLogger(TestDemoEncodeImage.class);
//    public static String image = "test";
//    public static String hardCodePrefilter = "1234";
    public static String service = Site.encodedImagePath;


    @DataProvider(name="images")
    public Object[][] images(){
        return new Object[][]{
                {"test", "This test will fail as service returns a hardcoded value"},
                {"1234", "This test will pass as we are matching with hardcoded return value"}
        };
    }

    @Test(priority = 1, dataProvider = "images")
    public void verifyExpectedImageAfterDecoding(String image, String msg) {
        String responseImage = ImageUtils.getDecodedImage(service, image);

        log.info(msg);
        Assert.assertTrue(responseImage.equals(image), msg);
    }

    @Test(priority = 2, dataProvider = "images")
    public void verifyEncodeServiceEncodesImage(String image, String msg) {
        String responseImage = ImageUtils.getEncodedImage(service, image);
        String expectedImage = ImageUtils.encodeString(image);

        log.info(msg);
        Assert.assertTrue(responseImage.contains(expectedImage), msg);
    }

    @Test(priority = 3)
    public void verifyPostCallToApiReturnsPostBodyWithoutEncoding(){
        final String service = Site.serviceUrl;
        final String path = Site.servicePath;
        final String data = "{\"image\":\"test\"}";
        DemoClientWrapper demoClientWrapper = new DemoClientWrapper(service, path);
        Map<Integer, String> response = demoClientWrapper.postRequest(data);
        String strResponse = response.get(Integer.valueOf(200));

        Assert.assertTrue(data.equals(strResponse), "Service returns corrupted data");
    }
}
