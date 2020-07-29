package test_framework_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

class BaseApiTest {
    static BaseApi baseApi;

    @BeforeAll
    static void beforeAll(){
        baseApi=new BaseApi();
        baseApi.load("src/main/resources/test_framework_service/api");
    }

    @Test
    void load() {
        assertThat(baseApi.apis.size(), equalTo(2));
    }

    @Test
    void run() {
        baseApi.run("wework", "get_token");
        baseApi.run("tags", "list");
        baseApi.run("tags", "add");


    }

}