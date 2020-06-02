package page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ContactPage extends BasePage {
    /****************添加成员元素组***********************/
    private By addMember = By.linkText("添加成员");
    private By username = By.name("username");
    private By acctid = By.name("acctid");
    private By mobile = By.name("mobile");
    private By savesure = By.cssSelector(".js_btn_save");
    private By search = By.cssSelector("#memberSearchInput");
    private By getname = By.cssSelector(".member_display_cover_detail_name");
    private By delete = By.cssSelector(".js_del_member");
    private By deletesure = By.linkText("确认");
    /****************添加部门元素组***********************/
    private By addbutton = By.cssSelector(".member_colLeft_top_addBtn");
    private By addepartment = By.linkText("添加部门");
    private By name = By.name("name");
    private By menu = By.linkText("选择所属部门");
    private By department = By.xpath("//form/div[3]/div/div/ul/li/a/i");
    private By departmentsure = By.linkText("确定");
    private By getdepartmentname = By.xpath("//li[@class='jstree-node js_editable jstree-leaf jstree-last']/a");
    private By departmentList = By.xpath("//li[@class='jstree-node js_editable jstree-leaf jstree-last']");
    private By more = By.xpath("//li[@class='jstree-node js_editable jstree-leaf jstree-last']/a/span");
    private By delepartment = By.xpath("//ul[@class='vakata-context jstree-contextmenu jstree-default-contextmenu']/li/a[contains(text(),'删除')]");

    /****************添加标签元素组***********************/
    private By taglistbutton = By.linkText("标签");
    private By addtagbutton = By.linkText("添加标签");
    private By tagname = By.name("name");
    private By tagsure = By.linkText("确定");
    private By gettagname = By.cssSelector(".ww_commonCntHead_title_inner_text");
    private By searchtag = By.xpath("//input[@class='qui_inputText ww_inputText ww_searchInput_text js_search_in']");
    private By taginfo = By.linkText("标签详情");
    private By deletetag = By.linkText("删除");
    private By deletetagsure = By.linkText("确定");




    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage addMembers(String username, String acctid, String mobile) {
        while (driver.findElements(this.username).size() == 0) {
            click(addMember);
        }
        sendKey(this.username, username);
        sendKey(this.acctid, acctid);
        sendKey(this.mobile, mobile);
        click(this.savesure);
        return this;
    }

    public ContactPage search(String keyword) {
        sendKey(this.search, keyword);
        return this;
    }

    public ContactPage deleteMember() {
        click(this.delete);
        click(this.deletesure);
        return this;
    }

    public String getName() {

        return mygetText(this.getname);
    }

    public ContactPage addepartment(String name) {
        click(this.addbutton);
        click(this.addepartment);
        sendKey(this.name, name);
        click(this.menu);
        click(this.department);
        click(this.departmentsure);
        driver.navigate().refresh();
        return this;
    }

    public String getdepartmentName() {
        return mygetText(this.getdepartmentname);
    }

    public ContactPage deletedepartment() {
        move(this.departmentList);
        click(this.more);
        click(this.delepartment);
        click(this.departmentsure);
        return this;
    }


    public ContactPage addtag(String tagname) {
        click(this.taglistbutton);
        moveClick(this.addtagbutton);
        sendKey(this.tagname, tagname);
        click(this.tagsure);
        return this;
    }
    public ContactPage searchtag(String keyword){
        sendKey(this.searchtag,keyword);
        return this;
    }

    public ContactPage deletetag() {

        while (driver.findElements(this.deletetag).size() == 0) {
            click(this.taginfo);
        }
        click(this.deletetag);
        click(this.deletetagsure);
        return this;
    }


    public String gettagname() {

        return mygetText(this.gettagname);
    }
}
