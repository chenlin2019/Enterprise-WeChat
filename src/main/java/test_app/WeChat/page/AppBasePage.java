package test_app.WeChat.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_framework.BasePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AppBasePage extends BasePage {
    // IOSDriver
    // AndroidDriver<MobileElement> driver;
    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;
    private final int timeOutInSecondsDefault = 20; // 设置超时时间
    private String packageName;  //定义包名
    private String activityName; //定义启动页
    private Integer winheight; // 获取高度
    private Integer winwidth;   // 获取宽度

    public AppBasePage() {
        // todo:暂时占位
    }

    public AppBasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeOutInSecondsDefault);
    }

    public AppBasePage(String packageName, String activityName) {
        this.packageName = packageName;
        this.activityName = activityName;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "hogwarts");
        desiredCapabilities.setCapability("appPackage", this.packageName);
        desiredCapabilities.setCapability("appActivity", this.activityName);
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("udid", "127.0.0.1:7555");
//        desiredCapabilities.setCapability("dontStopAppOnReset", "true");
//        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
        //todo: 等待优化
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeOutInSecondsDefault);
    }

    public void quit() {
        driver.quit();
    }

    public MobileElement find(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by);
        } catch (Exception e) {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return driver.findElement(by);
        }
    }

    /**
     * 根据单条件滚动查找
     * @param content
     * @param type
     */
    public void scroll(String content, String type) {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) this.driver;
        String uiautomatorStr = null;
        // find element by text
        if (type == "TEXT") {
            uiautomatorStr =
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + content + "\").instance(0));";
            driver.findElementByAndroidUIAutomator(uiautomatorStr);

        } else if (type == "ID") {// find element by id
            uiautomatorStr =
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"" + content + "\").instance(0))";
            driver.findElementByAndroidUIAutomator(uiautomatorStr);
        } else if (type == "AccessibilityId") {
            uiautomatorStr =
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"" + content + "\").instance(0))";
            driver.findElementByAndroidUIAutomator(uiautomatorStr);
        }
    }

    /**
     * 根据className多条件滚动查找
     * @param content
     * @param className
     * @param type
     * @param index
     */
    public void scroll(String content, String className, String type, int index) {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) this.driver;
        String uiautomatorStr = null;
        // find element by classname && index
        if (className == "CLASSNAME" && type == "INDEX") {
            uiautomatorStr =
                    "new UiScrollable(new UiSelector().scrollable(true).index(" + index + ")).getChildByText(new UiSelector().className(\"" + content + "\").instance(0))";
            driver.findElementByAndroidUIAutomator(uiautomatorStr);
        }
        // find element by classname && instance
        else if (className == "CLASSNAME" && type == "INSTENCE") {
            uiautomatorStr =
                    "new UiScrollable(new UiSelector().scrollable(true).instance(" + index + ")).getChildByText(new UiSelector().className(\"" + content + "\").instance(0))";
            driver.findElementByAndroidUIAutomator(uiautomatorStr);
        }
    }

    /**
     * 指定方向翻页
     * @param direction
     */
    public void swipe(String direction) {
        this.winheight = driver.manage().window().getSize().getHeight();
        this.winwidth = driver.manage().window().getSize().getWidth();
        Integer up_y = (int) (this.winheight * 0.3);
        Integer down_y = up_y * 3;
        Integer left_x = (int) (this.winwidth * 0.3);
        Integer rigth_x = left_x * 3;
        Integer center_x = (int) (this.winwidth * 0.5);
        Integer center_y = (int) (this.winheight * 0.5);
        TouchAction touchAction = new TouchAction(driver);
        try {
            if (direction == "down") {
                touchAction.longPress(PointOption.point(center_x, down_y))
                        .moveTo(PointOption.point(center_x, up_y))
                        .release().perform();

            } else if (direction == "up") {
                touchAction.longPress(PointOption.point(center_x, up_y))
                        .moveTo(PointOption.point(center_x, down_y))
                        .release().perform();

            } else if (direction == "left") {
                touchAction.longPress(PointOption.point(left_x, center_y))
                        .moveTo(PointOption.point(rigth_x, center_y))
                        .release().perform();

            } else if (direction == "right") {
                touchAction.longPress(PointOption.point(rigth_x, down_y))
                        .moveTo(PointOption.point(left_x, up_y))
                        .release().perform();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void click(By by) {
        find(by).click();
    }

    public void untilClick(By by, By byuntil) {
        do {
            click(by);
        }
        while (driver.findElements(byuntil).size() == 0);
    }

    public void sendkey(By by, String text) {
        find(by).clear();
        find(by).sendKeys(text);
    }

}
