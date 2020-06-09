package test_web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class BasePage {

    RemoteWebDriver driver ;
    WebDriverWait wait;

    public BasePage() {
        driver = new ChromeDriver();
        // 隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize(); // 最大化浏览器
        wait=new WebDriverWait(driver, 10);
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver, 10);
    }


    public void quit() {
        driver.quit();
    }



    public String mygetText(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String string = driver.findElement(by).getText();
        return string;
    }

    public void  click(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by)); // 等待元素存在
        driver.findElement(by).click();

    }



    public void sendKey(By by,String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));//等待元素可见
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);

    }

    public void move(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).perform();
    }

    public void moveClick(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().perform();
    }

    public void Upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

}
