package test_framework_UI;

import test_app.WeChat.page.AppBasePage;
import test_web.page.WebBasePage;

/**
 * @program: Enterprise WeChat
 * @description: 创建对象时,区分web，app，api
 * @author:
 * @create: 2020-06-25 13:16
 **/
public class UIAutoFactory {
    public static BasePage create(String driverName){
        if (driverName.toLowerCase().equals("web")||driverName.toLowerCase().equals("selenium")){
            return new WebBasePage();
        }else if(driverName.toLowerCase().equals("app")||driverName.toLowerCase().equals("appium")){
            return new AppBasePage();
        }else {
            return null;
        }
    }
}
