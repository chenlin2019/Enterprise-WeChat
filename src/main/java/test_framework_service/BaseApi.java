package test_framework_service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * load（）用于读取所有的模型对象，生成ApiObjectModel列表
 * run（）根据给定的接口标记和接口名做数据运行
 */
public class BaseApi {

    List<ApiObjectModel> apis = new ArrayList<>();

    /**
     *
     * @param dir  此参数为yaml文件夹路劲
     *    加载文件夹，返回api文件列表，用于读取所有的模型对象
     */
    public void load(String dir) {
        Arrays.stream(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        })).forEach(path -> {
            try {
                apis.add(ApiObjectModel.load(dir+"/"+path));   // 把ApiObjectModel对象加载进列表中
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 根据测试用例中提供的api 对象的 name和对应的action，从自己的数据中检索对应的api，并调用对应的方法。
     * @param name
     * @param action
     */
    public void run(String name, String action){
        apis.stream().filter(api -> api.name.equals(name)).forEach(api->{
            api.methods.get(action).run();
        });
    }
}