import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SampleTest {

    private static AndroidDriver<AndroidElement> driver;
    private static MainPage mainPage;

    /**
     * иницилизация драйвера, чтение файла test.properties
     */
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

    /**
     * сброс полей в приложении перед каждым тестом
     */
    @Before
    public void reset() {
        mainPage.keyReset.click();
    }

    /**
     * сложение целых чисел
     * */
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
     * кладывает и умножает на 2 если значение из поля 1 <= значению из поля 2
     * */
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

    /**
     * тест умножения простых чисел
     * */
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

    //тест деления простых чисел
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

    //сумма float чисел
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

    //разность float чисел
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

    //умножение float чисел
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

    //деление float чисел
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

    //проверка изменения значения в поле после операции
    @Test
    public void maxSizeTest() {
        float one = 1f;
        float two = 1f;

        while (String.valueOf(two).length() < 10) {
            two *= 10f;
            mainPage.inputFieldLeft.setValue(String.valueOf(one));
            mainPage.inputFieldRight.setValue(String.valueOf(two));
            mainPage.keyMul.click();
            String v = mainPage.inputFieldRight.getText().replace('.', ',');
            String expected = String.format("%.1f", two);
            Assert.assertEquals(expected, v);
            reset();
        }
    }

    //проверка деления на 0
    @Test
    public void whenDivByZeroTest() {
        List<Float> floats = Arrays.asList(1000f, 40.02f, 0.3f, 1.4f, 2.5f);
        for (Float a : floats) {
            mainPage.inputFieldLeft.setValue(String.valueOf(a));
            mainPage.inputFieldRight.setValue(String.valueOf(0));
            mainPage.keyDiv.click();
            String v = mainPage.result.getText();
            String[] res = v.split(" ");
            v = res[res.length - 1];
            Assert.assertEquals("Infinity", v);
            reset();
        }

    }

    /**
     * Проверка нажатия всех кнопок операций без введенных значений
     */

    @Test
    public void whenNoOneValue() {
        List<AndroidElement> buttons = Arrays.asList(mainPage.keyDiv, mainPage.keyMinus,
                mainPage.keyMul, mainPage.keyPlus);
        String expect = "Please, fill the input fields correctly";
        for (AndroidElement button : buttons) {
            button.click();
            String v = mainPage.result.getText();
            Assert.assertEquals(expect, v);
            reset();
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

