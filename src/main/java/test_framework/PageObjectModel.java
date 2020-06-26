package test_framework;

import java.util.HashMap;
import java.util.List;

/**
 * @program: Enterprise WeChat
 * @description: 此对象为序列化页面(page)对象模板
 * @author:
 * @create: 2020-06-25 10:33
 **/
public class PageObjectModel {
    public String name="";
    public String description="";
    public HashMap<String, List<HashMap<String,Object>>> methods;
}
