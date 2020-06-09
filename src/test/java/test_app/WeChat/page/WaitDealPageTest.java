package test_app.WeChat.page;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WaitDealPageTest {

    static WaitDealPage waitDealPage;

    @BeforeAll
    public static void BeforeAll() {
        waitDealPage = new MainPage().toWaitDeal();
    }

    @AfterAll
    public static void AfterAll() {
        waitDealPage.quit();
    }

    @Order(1)
    @Test
    void addWaitDeal() {
        List<String> list = waitDealPage.addWaitDeal("java-app自动化实战01").getList();
        assertTrue(list.contains("java-app自动化实战01"));

    }

    @Order(2)
    @Test
    void delete() {
        List<String> list = waitDealPage.deleteWaitDeal().getList();
        assertFalse(list.contains("java-app自动化实战01"));
    }


}