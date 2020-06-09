package test_app.WeChat.page;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskPageTest {

    static TaskPage schedulepage;

    @BeforeAll
    public static void BeforeAll(){
        schedulepage = new MainPage().toTask();

    }

    @AfterAll
    public static void AfterAll(){
        schedulepage.quit();
    }

    @Order(1)
    @Test
    public void addTask() {
        List<String> list = schedulepage.addTaskPage("日程测试01").getList();
        assertTrue(list.contains("日程测试01"));
    }


    @Order(2)
    @Test
    public void deleteTask(){
        List<String> list = schedulepage.deleteTaskPage().getList();
        assertFalse(list.contains("日程测试01"));
    }


}