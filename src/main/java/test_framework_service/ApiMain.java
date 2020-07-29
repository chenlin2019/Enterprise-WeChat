package test_framework_service;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;


/*
* Launcher是JUnit5的启动类，也是对启动进行扩展的主要入口，扩展通过实现自定义的TestEngine来自定义测试类的发现和执行逻辑以达到定制化测试的目的
* Launcher: 发射器
*
* mvn package
* mvn clean package  -Dmaven.test.skip=true  打包
 * */
public class ApiMain {
    public static void main(String[] args){
        //必须1.6.0才能正确运行
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                    // selectPackage("test_framework_service"),  // 选择一个包
                        selectClass(ApiDDTest.class)        // 选择一个用例类
                ).filters(  // 过滤测试类
                        includeClassNamePatterns(".*Test")  // 参数为正则表达式
                )
                .build();
        System.out.println(request);

        Launcher launcher = LauncherFactory.create();   // 通过 LauncherFactory.create()　来获取Launcher默认实现

        TestPlan testPlan = launcher.discover(request);  // 指定测试类的查找和过滤规则

        testPlan.getRoots().forEach(uid->{
            System.out.println(uid.toString());
        });

        // Register a listener of your choice  注册您选择的听众:监听器
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);  //通过添加TestExecutionListener来进行测试结果的监听

        launcher.execute(request); //执行launcher.execute(req)方法启动测试

        TestExecutionSummary summary = listener.getSummary();
        System.out.println(summary.getTestsSucceededCount());
        System.out.println(summary.getTestsFoundCount());
        // Do something with the TestExecutionSummary.使用TestExecutionSummary做一些事情。
    }
}
