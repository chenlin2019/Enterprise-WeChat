package test_web.page;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
class ContactPageTest {
    static MainPage main;
    static ContactPage contact;

    @BeforeAll
    public static void BeforeAll() {
        main = new MainPage();
        contact = main.toContact();
    }

    @BeforeEach
    public  void BeforeEach(){
//        contact.driver.navigate().refresh();
    }
    @AfterEach
    public  void AfterEach(){
        contact.driver.navigate().refresh();
    }

//    @Disabled
    @Test
    @Order(1)
    public void addMember() {
        try {
            String username = contact.addMembers("riley02", "100002", "18682090432").search("riley02").getName();
            assertEquals("riley02", username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Disabled
    @Test
    @Order(2)
    public void deleteMember() {
        contact.search("riley02").deleteMember();
    }

//    @Disabled
    @Order(3)
    @Test
    public void addePartment(){
       String getdepartmentName= contact.addepartment("department1").getdepartmentName();
        assertEquals("department1",getdepartmentName);
    }

//    @Disabled
    @Order(4)
    @Test
    public void deletePartment(){
        contact.deletedepartment();
    }

//    @Disabled
    @Order(5)
    @Test
    public void addTag(){
        String tagname = contact.addtag("tag01").searchtag("tag01").gettagname();
        assertEquals("tag01",tagname);
    }

    //    @Disabled
    @Order(6)
    @Test
    public void deleteTag(){
        contact.searchtag("tag01").deletetag();
    }


    @AfterAll
    public static void AfterAll() throws InterruptedException {
        Thread.sleep(10000);
        contact.quit();
    }
}