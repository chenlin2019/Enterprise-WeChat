package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: Enterprise WeChat
 * @description:
 * @author:
 * @create: 2020-06-25 13:33
 **/
public class BasePage {

    /**
     * 读取操作步骤数据
     *
     * @param path
     * @return
     */
    public UiAuto loadstep(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        // 定义模板对象
        UiAuto uiAuto = null;
        try {
            uiAuto = mapper.readValue(BasePage.class.getResourceAsStream(path), UiAuto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiAuto;
    }

    /**
     * 读取页面数据
     *
     * @param path
     */
    public PageObjectModel loadpage(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom = null;
        try {
            pom = mapper.readValue(new File(path), PageObjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    /**
     * 创建page集合，用于保存page对象
     */
    List<PageObjectModel> pages = new ArrayList<>();

    /**
     * 读取文件夹
     *
     * @param dir 存放page文件的文件夹
     */
    public void loadpages(String dir) {
        // 用page文件夹创建file文件对象.list（）筛选出文件名包含page的的文件
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page"); // 返回文件名
            }
        })).forEach(pagePath -> {
            pagePath = dir + "/" + pagePath;
            pages.add(loadpage(pagePath));
        });
    }

    public void run(UiAuto uiAuto) {
        uiAuto.steps.stream().forEach(step -> {
            if (step.containsKey("sendkey")) {
                System.out.println("sendkey");
            } else if (step.containsKey("click")) {
                System.out.println("click");
            } else if (step.containsKey("action")) {
                action(step);
            }
        });
    }

    public void click(HashMap<String, Object> map) {}

    public void sendkey(HashMap<String, Object> map) {}

    /**
     * 从测试流程yaml文件中解析测试步骤
     * @param step  需要解析的map对象
     */
    public void action(HashMap<String, Object> step) {
        if (step.containsKey("page")) {
            // 获取两个键的值
            String action = step.get("action").toString();
            String page = step.get("page").toString();
            //
            pages.stream()
                    .filter(pom -> pom.name.equals(page))
                    .findFirst()
                    .get()
                    .methods
                    .get(action)
                    .forEach(pageMethod -> action(pageMethod)); // 通过测试步骤的action键，从page中找到对应的键值
        }
    }

}
