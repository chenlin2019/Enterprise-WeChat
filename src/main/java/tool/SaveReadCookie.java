package tool;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 1、使用浏览器复用技术，获取cookies并写入本地文件中
 * 2、从本地文件读取cookies，并设置在当前浏览的的cookies中
 * 3、页面元素操作
 *
 * */
public class SaveReadCookie {

    public void saveCookies(WebDriver driver, String url, String filepath) throws IOException {
        /*
            浏览器复用技术：
         * cmd执行： chrome -remote-debugging-port=9222
         * */
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        //利用chromedriver控制chrome
        driver = new ChromeDriver(options);  // 把配置加载到chromedriver实现复用浏览器

        driver.get(url);
        Set<Cookie> cookies = driver.manage().getCookies();
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie);
                String value = cookie.getName() + ';' +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        cookie.getExpiry() + ";" +
                        cookie.isSecure();
                bw.write(value);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }


    public void loadCookies(WebDriver driver, String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "\\;");
                String name = st.nextToken();
                String value = st.nextToken();
                String domain = st.nextToken();
                String path = st.nextToken();
                Date expiry = null;
                String dt = st.nextToken().trim(); // 注意：字符串去空格
                if (!dt.equals("null")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    //把 string 转换成 date
                    expiry = sdf.parse(dt);
                }
                boolean isSecure = Boolean.parseBoolean(st.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
//                System.out.println(cookie);
                driver.manage().addCookie(cookie);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }
}
