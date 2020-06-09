package test_app.WeChat.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WorkBenchPage extends BasePage {

    public WorkBenchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ReportPage toReport(){
        if (find(By.xpath("//*[@text='工作台']"))!=null){
            scroll("汇报","TEXT");
        }
        click(By.xpath("//*[@text='汇报']"));
        return new  ReportPage(driver);
    }

}
