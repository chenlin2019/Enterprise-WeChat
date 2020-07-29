package test_framework_UI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class WebTest {

    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        //todo: 加载通用配置
    }

    @BeforeEach
    void beforeEach(){
        //todo: 每个用例相关
    }

    @ParameterizedTest(name = "{index}{1}")
    @MethodSource
    void classic(UiAuto uiAuto,String path) {
        basePage.run(uiAuto);
    }
    static Stream<Arguments> classic(){
        basePage = UIAutoFactory.create("web");
        // 创建page对象
        basePage.loadpages("src/main/resources/test_framework_data");

        List<Arguments> all = new ArrayList<>();
        Arrays.asList(
                "/test_framework_data/webuiauto_1.yaml"
        ).stream().forEach(path->{
            UiAuto uiAuto = basePage.loadstep(path);
            all.add(arguments(uiAuto, path));
        });
        return all.stream();
    }
}

















