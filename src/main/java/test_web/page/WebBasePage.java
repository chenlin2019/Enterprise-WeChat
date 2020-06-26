package test_web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_framework.BasePage;
import test_web.tool.SaveReadCookie;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class WebBasePage extends BasePage {

    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
        driver = new ChromeDriver();
        // 隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize(); // 最大化浏览器
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    public void quit() {
        driver.quit();
    }


    public String mygetText(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String string = driver.findElement(by).getText();
        return string;
    }

    public void click(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by)); // 等待元素存在
        driver.findElement(by).click();

    }


    public void sendKey(By by, String content) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));//等待元素可见
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);

    }

    public void move(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).perform();
    }

    public void moveClick(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().perform();
    }

    public void Upload(By by, String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

    /**
     * 把page文件的map值，封装成by形式，并调用sendKey方法
     *
     * @param map
     */
    @Override
    public void sendkey(HashMap<String, Object> map) {
        super.sendkey(map);



    }

    /**
     * 把page文件的map值，封装成by形式，并调用click方法
     *
     * @param map 参数为map，解析成by
     */
    @Override
    public void click(HashMap<String, Object> map) {
        super.click(map);
        String key = (String) map.keySet().toArray()[0];
        String value = (String) map.values().toArray()[0];
        By by = null;
        if (key.toLowerCase().equals("id")) {
            by = By.id(value);
        } else if (key.toLowerCase().equals("linkText".toLowerCase())) {
            by = By.linkText(value);
        } else if (key.equals("partialLinkText".toLowerCase())) {
            // partialLinkText  为模糊匹配
            by = By.partialLinkText(value);
        } else if (key.equals("Xpath".toLowerCase())) {
            by = By.xpath(value);
        }
        click(by);
    }


    /**
     * 重写 实现页面内的各种点击事件
     *
     * @param step 需要解析的map对象
     */
    @Override
    public void action(HashMap<String, Object> step) {
        super.action(step);
        // 如果键为action，获取键的值，判断如果为get，调用driver.get方法打开浏览器
        if (step.containsKey("action")) {
            String action = step.get("action").toString().toLowerCase();
            if (action.equals("get")) {
                //登录网页
                driver.get(step.get("url").toString());
                SaveReadCookie savereadcookie = new SaveReadCookie();
                try {
                    // 清除cookies
                    driver.manage().deleteAllCookies();
                    // 设置cookies
                    savereadcookie.loadCookies(driver,step.get("cookiesFile").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //再次登录网页
                driver.get(step.get("url").toString());
            }
            // 如果键为click，获取键的值，并使用过click方法
        } else if (step.containsKey("click")) {
            HashMap<String, Object> by_map = (HashMap<String, Object>) step.get("click");
            click(by_map);

        } else if (step.containsKey("sendKeys")) {

        }


    }
}
