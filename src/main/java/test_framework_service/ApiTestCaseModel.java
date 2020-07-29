package test_framework_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 用例层数据 对象
 *代表的是测试用例，提供读取测试用例、执行功能
 * 代表测试用例testcase
 *
 * load() 把单个用例文件序列化
 * run（）运行指定的
 */
public class ApiTestCaseModel {
    public String name = "";
    public String description = "";
    public List<HashMap<String, Object>> steps;

    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiTestCaseModel.class);
    }


    /**
     *
     * @param baseApi
     */
    public void run(BaseApi baseApi) {
        steps.stream().forEach(step->{
            baseApi.run(step.get("api").toString(),step.get("action").toString());
        });
    }

}
