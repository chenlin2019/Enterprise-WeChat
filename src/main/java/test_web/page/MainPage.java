package test_web.page;
import org.openqa.selenium.By;
import test_web.tool.*;
import java.io.IOException;
/*
* 主页面
*
* */
public class MainPage extends WebBasePage {

    String url = "https://work.weixin.qq.com/wework_admin/frame";

    public MainPage() {
        super();
        SaveReadCookie savereadcookie = new SaveReadCookie();
        try {
            driver.get(url);
            driver.manage().deleteAllCookies();
            String cookiesPath ="src\\main\\resources\\test_framework_data\\cookies.txt".toString();
            savereadcookie.loadCookies(driver,cookiesPath);
            driver.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactPage toContact() {

        click(By.cssSelector("#menu_contacts"));
        return new ContactPage(driver);
    }

}
