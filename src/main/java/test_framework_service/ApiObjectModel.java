package test_framework_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 *  目的：用于处理指定的api.yaml文件 序列化两个变量name和 methods
 */
public class ApiObjectModel {

    public String name;
    public HashMap<String, ApiObjectMethodModel> methods;


    /**
     * 接口层对象
     * 1、创建一个YAMLFactory数据对象
     * 2、把yaml文件和ApiObjectModel.class的变量模型序列化封装 并返回
     * api object支持从yaml中读取
     * @param path yaml文件的路径
     * @return
     */
    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiObjectModel.class);
    }


    /**
     * @param method 此参数为 yaml文件的method的序列化对象ApiObjectMethodModel
     *   此方法 调用ApiObjectMethodModel对象 的 run方法
     */
    public void run(ApiObjectMethodModel method) {
        method.run();
    }

}
