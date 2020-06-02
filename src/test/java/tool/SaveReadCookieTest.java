package tool;

import org.junit.jupiter.api.Test;
import java.io.IOException;
class SaveReadCookieTest {
    /*
            浏览器复用技术：
      1、cmd执行： chrome -remote-debugging-port=9222
      2、扫码登录企业微信
      3、  执行此用例
    * */

    @Test
    public void savecookies() throws IOException {

        String url = "https://work.weixin.qq.com/wework_admin/frame";
        String filepath = "src\\test\\java\\page\\cookies.txt";
        SaveReadCookie saveReadCookie= new SaveReadCookie();
        saveReadCookie.saveCookies(url,filepath);

    }

}