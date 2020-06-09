package test_app.WeChat.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class TaskPage extends BasePage{
    //  添加日程
    private final By addButton = By.id("gym");
    private final By taskName = By.id("b2k");
    private final By saveButton = By.id("ag2");
    private final By taskList = By.id("gg_");
    // 删除日程
    private final By delList = By.id("bi1");
    private final By deletebutton = By.id("bfi");
    private final By deleteSure = By.id("b_o");



    public TaskPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public TaskPage addTaskPage(String text){
        click(addButton);
        sendkey(taskName,text);
        click(saveButton);
        return this;
    }
    public TaskPage deleteTaskPage(){
        click(delList);
        click(deletebutton);
        click(deleteSure);
        return this;
    }

    public List<String> getList(){
        return driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());
    }


}
