package test_web.tool;

import org.junit.jupiter.api.Test;


class SaveReadCookieTest {
        /*
            浏览器复用技术：
      1、cmd执行： chrome -remote-debugging-port=9222
      2、扫码登录企业微信
      3、  执行此用例
    * */

    @Test
    void saveCookies() throws Exception{
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        String filepath = "src/test/java/webauto/data/cookies.txt";
        SaveReadCookie saveReadCookie= new SaveReadCookie();
        saveReadCookie.saveCookies(url,filepath);
    }

}