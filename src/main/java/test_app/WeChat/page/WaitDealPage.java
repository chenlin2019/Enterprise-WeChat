package test_app.WeChat.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class WaitDealPage extends AppBasePage {

    private final By addbutton = By.id("gym");
    private final By dealcontent = By.id("b2k");
    private final By savebutton = By.xpath("//*[@text='保存']");
    private final By deletebutton = By.id("gwt");
    private final By getlist = By.id("gw9");



    public WaitDealPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public WaitDealPage addWaitDeal(String text) {
        click(addbutton);
        sendkey(dealcontent, text);
        click(savebutton);
        return this;
    }

    public WaitDealPage deleteWaitDeal(){
        click(deletebutton);
        return this;
    }

    public List<String> getList(){

        return driver.findElements(getlist).stream().map(x->x.getText()).collect(Collectors.toList());
    }



}
