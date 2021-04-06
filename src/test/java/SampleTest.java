import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import jdk.jfr.Description;
import junit.framework.TestCase;
import org.junit.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;

public class SampleTest {

    private static AndroidDriver<AndroidElement> driver;
    private static MainPage mainPage;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        Properties cfg = new Properties();
        try (FileInputStream in =
                     new FileInputStream("src/test/resources/test.properties")) {
            cfg.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", cfg.getProperty("name"));
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") +
                cfg.getProperty("application"));
        desiredCapabilities.setCapability("platformName", cfg.getProperty("platform"));
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL(cfg.getProperty("appiumUrl"));

        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
        mainPage = new MainPage(driver);
    }

    @Before
    public void reset() {
        mainPage.keyReset.click();
    }

    @Ignore
    @Test
    public void intsPlusTest() {
        List<Integer> integers = Arrays.asList(1, 20, 300, 4000, 5000);
        for (int i = 0; i < integers.size(); i++) {
            mainPage.inputFieldLeft.setValue(integers.get(i).toString());
            mainPage.inputFieldRight.setValue(integers.get(integers.size() - 1 - i).toString());
            mainPage.keyPlus.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            String expected = String.format("%d,00", integers.get(i) + integers.get(integers.size() - 1 - i));
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    /*
     *выкидывает ошибку при отрицательном результате
     * */
    @Ignore
    @Test
    public void intsMinusTest() {
        List<Integer> integers = Arrays.asList(1, 20, 300, 4000, 5000);
        for (int i = 0; i < integers.size(); i++) {
            mainPage.inputFieldLeft.setValue(integers.get(i).toString());
            mainPage.inputFieldRight.setValue(integers.get(integers.size() - 1 - i).toString());
            mainPage.keyMinus.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            String expected = String.format("%d,00", integers.get(i) - integers.get(integers.size() - 1 - i));
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Ignore
    @Test
    public void intsMulTest() {
        List<Integer> integers = Arrays.asList(1, 20, 300, 4000, 5000);
        for (int i = 0; i < integers.size(); i++) {
            mainPage.inputFieldLeft.setValue(integers.get(i).toString());
            mainPage.inputFieldRight.setValue(integers.get(integers.size() - 1 - i).toString());
            mainPage.keyMul.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            String expected = String.format("%d,00", integers.get(i) * integers.get(integers.size() - 1 - i));
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Ignore
    @Test
    public void intsDivTest() {
        List<Integer> integers = Arrays.asList(1, 20, 300, 4000, 5000);
        for (int i = 0; i < integers.size(); i++) {
            mainPage.inputFieldLeft.setValue(integers.get(i).toString());
            mainPage.inputFieldRight.setValue(integers.get(integers.size() - 1 - i).toString());
            mainPage.keyDiv.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            float one = integers.get(i);
            float two = integers.get(integers.size() - 1 - i);
            String expected = String.format("%.2f", one / two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Ignore
    @Test
    public void floatsSumTest() {
        List<Float> floats = Arrays.asList(0.1f, 0.02f, 0.3f, 1.4f, 2.5f);
        for (int i = 0; i < floats.size(); i++) {
            mainPage.inputFieldLeft.setValue(floats.get(i).toString());
            mainPage.inputFieldRight.setValue(floats.get(floats.size() - 1 - i).toString());
            mainPage.keyPlus.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            float one = floats.get(i);
            float two = floats.get(floats.size() - 1 - i);
            String expected = String.format("%.2f", one + two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Ignore
    @Test
    public void floatsMinusTest() {
        List<Float> floats = Arrays.asList(0.1f, 0.02f, 0.3f, 1.4f, 2.5f);
        for (int i = 0; i < floats.size(); i++) {
            mainPage.inputFieldLeft.setValue(floats.get(i).toString());
            mainPage.inputFieldRight.setValue(floats.get(floats.size() - 1 - i).toString());
            mainPage.keyMinus.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            float one = floats.get(i);
            float two = floats.get(floats.size() - 1 - i);
            String expected = String.format("%.2f", one - two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Ignore
    @Test
    public void floatsMulTest() {
        List<Float> floats = Arrays.asList(0.1f, 0.02f, 0.3f, 1.4f, 2.5f);
        for (int i = 0; i < floats.size(); i++) {
            mainPage.inputFieldLeft.setValue(floats.get(i).toString());
            mainPage.inputFieldRight.setValue(floats.get(floats.size() - 1 - i).toString());
            mainPage.keyMul.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            float one = floats.get(i);
            float two = floats.get(floats.size() - 1 - i);
            String expected = String.format("%.2f", one * two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Ignore
    @Test
    public void floatsDivTest() {
        List<Float> floats = Arrays.asList(0.1f, 0.02f, 0.3f, 1.4f, 2.5f);
        for (int i = 0; i < floats.size(); i++) {
            mainPage.inputFieldLeft.setValue(floats.get(i).toString());
            mainPage.inputFieldRight.setValue(floats.get(floats.size() - 1 - i).toString());
            mainPage.keyDiv.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            float one = floats.get(i);
            float two = floats.get(floats.size() - 1 - i);
            String expected = String.format("%.2f", one / two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @Test //канает только семь знаков перед запятой
    public void maxSizeTest() {
        float one = 1f;
        float two = 1f;

        while (String.valueOf(two).length() < 10) {
            two *= 10f;
            mainPage.inputFieldLeft.setValue(String.valueOf(one));
            mainPage.inputFieldRight.setValue(String.valueOf(two));
            mainPage.keyMul.click();
            String v = mainPage.inputFieldRight.getText().replace('.',',');
            String expected = String.format("%.1f", two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

