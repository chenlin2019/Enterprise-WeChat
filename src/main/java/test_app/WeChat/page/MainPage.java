package test_app.WeChat.page;
import org.openqa.selenium.By;
public class MainPage extends BasePage{


    public MainPage() {
        super("com.tencent.wework", ".launch.WwMainActivity");
    }

    public TaskPage toTask(){
        click(By.xpath("//*[@text='日程']"));
        return new TaskPage(driver);
    }

    public WaitDealPage toWaitDeal(){
        click(By.id("gwk"));
        return new WaitDealPage(driver);
    }
    //汇报日报 周报

    public ContactsPage toContactPage(){
        click(By.xpath("//*[@text='通讯录']"));
        return new ContactsPage(driver);
    }

    public  WorkBenchPage toworkbench(){
        click(By.xpath("//*[@text='工作台']"));
        return new WorkBenchPage(driver);
    }

    public MyPage toAboutMePage(){
        click(By.xpath("//*[@text='我']"));
        return new MyPage(driver);
    }

}
