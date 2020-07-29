package test_framework_service;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * 接口的的method对象
 * 目的：为ApiObjectModel加载的yaml文件的method 序列化对象
 */
public class ApiObjectMethodModel {

    public String method="get";
    public String url= "";
    public HashMap<String, String> query;
    public String save;
    public HashMap<String, Object> json;
    public String get;
    public String post;


    /**
     * 发送http请求
     *
     * @return
     */
    public Response run() {
        //多环境支持


        if (post != null) {
            url = post;
            method = "post";
        }
        if (get != null) {
            url = get;
            method = "get";
        }
        //  读取配置文件，获得域名与ip对应关系，在此替换
//          url = url.replaceAll("domain", "ip");

        return given().log().all()
                .queryParams(query)
                .request(method, url)
                .then().log().all()
                .extract().response();
    }
}

