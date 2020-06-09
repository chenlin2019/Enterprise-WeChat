package test_app.WeChat.page;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReportPageTest {
    private static ReportPage reportPage;

    @BeforeAll
    public static void BeforAll() {
        reportPage = new MainPage().toworkbench().toReport();
    }

    @AfterAll
    public static void AfterAll() {
        reportPage.quit();
    }

    @Order(1)
    @Test
    void addDayReportTest() {
        String s = reportPage.addDayReport("today jobs", "tomorrow jobs", "other jobs").getreport();
        assertEquals("详情",s);
    }

    @Order(2)
    @Test
    void deleteDayReportTest() {
        reportPage.deleteDayReport();

    }

    @Disabled
    @Test
    void addweekReport() {
    }

    @Disabled
    @Test
    void deleteweekReport() {
    }
}