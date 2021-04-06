import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;

public class SampleTest {

    private AndroidDriver<AndroidElement> driver;
    private MainPage mainPage;

    @Before
    public void setUp() throws MalformedURLException {
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

    @Test
    public void sampleTest() {
        System.out.println(mainPage.keyPlus.getId());;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

