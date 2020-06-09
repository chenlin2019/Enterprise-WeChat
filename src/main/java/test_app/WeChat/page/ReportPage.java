package test_app.WeChat.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReportPage extends BasePage {
    private final By dayreport = By.xpath("//*[@text='日报']");
    private final By todayworke = By.xpath("//*[@class='android.widget.EditText' and @index=1]");
    private final By tomorroworke = By.xpath("//*[@class='android.widget.EditText'and @index=3]");
    private final By otherworke = By.xpath("//*[@class='android.widget.EditText'and @index=5]");
    private final By addsavebutton = By.xpath("//*[@class='android.view.View' and @index='11']");
    private final By mydayreport = By.xpath("//*[@text='详情']");

    private final By recordbutton = By.xpath("//*[@text='记录']");
    private final By mysubmit  = By.xpath("//*[@text='我提交的']");
    private final By submitlistfirst  = By.xpath("//*[@id='com.tencent.wework:id/dse']");
    private final By morebutton  = By.id("gym");
    private final By deletebutton  = By.xpath("//*[@text='删除']");
    private final By delsurebutton  = By.xpath("//*[@text='确定']");


    private final By weekReport = By.xpath("//*[@text='周报']");

    public ReportPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ReportPage addDayReport(String daycontent, String weekcontent, String othercontent) {
        click(dayreport);
        sendkey(todayworke, daycontent);
        sendkey(tomorroworke, weekcontent);
        sendkey(otherworke, othercontent);
        swipe("down");
        untilClick(addsavebutton,mydayreport);
        return this;
    }

    public ReportPage deleteDayReport() {
        click(morebutton);
        click(deletebutton);
        click(delsurebutton);
        return this;
    }

    public String getreport(){
        return find(mydayreport).getText();
    }


    public ReportPage addweekReport() {
        return this;
    }

    public ReportPage deleteweekReport() {
        return this;
    }

}
