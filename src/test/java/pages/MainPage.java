package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private AndroidDriver<AndroidElement> driver;

    public MainPage() {
    }

    public MainPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/additionButton")
    public AndroidElement keyPlus;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/subtractButton")
    public AndroidElement keyMinus;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/multiplicationButton")
    public AndroidElement keyMul;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/divisionButton")
    public AndroidElement keyDiv;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/resetButton")
    public AndroidElement keyReset;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/inputFieldRight")
    public AndroidElement inputFieldRight;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/inputFieldLeft")
    public AndroidElement inputFieldLeft;

    @AndroidFindBy(id = "com.vbanthia.androidsampleapp:id/resultTextView")
    public AndroidElement result;

}
