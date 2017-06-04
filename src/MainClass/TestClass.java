package MainClass;


import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.MoveToOffsetAction;

import PageObjects.GenericPageObject;
import Services.Utils.fileUtils;
import enums.ByTypes;

public class TestClass extends BasicTest {

	// @Test
	public void Login() {
		webdriver.openURL("http://auto-course.us-west-2.elasticbeanstalk.com/loginPage.jsp");
		webdriver.LocatedBy(ByTypes.xpath, "//input[@id='username']").sendKeys("maoz");
		webdriver.LocatedBy(ByTypes.xpath, "//input[@id='password']").sendKeys("maoz");
		webdriver.LocatedBy(ByTypes.xpath, "//button[@class='btn btn-primary']").click();
		String username = webdriver.LocatedBy(ByTypes.xpath, "//*[@id='username']").getText();
		String password = webdriver.LocatedBy(ByTypes.xpath, "//*[@id='password']").getText();
		Assert.assertEquals(username, "maoz");
		Assert.assertEquals(password, "maoz");

	}

	@Test
	public void testGetElement() throws Exception {
		webdriver.openURL("http://auto-course.us-west-2.elasticbeanstalk.com/table.html");
		List<WebElement> cells = webdriver.getList("//table[@class= 'table']//tr//td");
		for (int i = 0; i < cells.size(); i++) {
			System.out.println("Text in cell is " + i + " " + cells.get(i).getText());
			System.getProperty("env");
		}

	}

	@Test
	public void getImageName() {
		webdriver.openURL("http://auto-course.us-west-2.elasticbeanstalk.com/images.html");
		String LogoName = webdriver.LocatedBy(ByTypes.xpath, "//div//img").getAttribute("src");
		System.out.println(LogoName);
	}

	@Test
	public void LoginTest() {
		webdriver.openURL("http://auto-course.us-west-2.elasticbeanstalk.com/loginPage.jsp");
		String UserName = "maoz";
		String PassWord = "121212";
		pagehelper.loginpage.enterUserName(UserName);
		pagehelper.loginpage.enterPassword(PassWord);
		pagehelper.loginpage.clickSubmit();
		Assert.assertEquals(pagehelper.loginpage.getUserName(), UserName);
		Assert.assertEquals(pagehelper.loginpage.getPassword(), PassWord);
		webdriver.printScreen("newfile");
		webdriver.LocatedBy(ByTypes.id, "ddsd");
	}

	@Test
	public void ComboBoxTest() throws Exception {
		webdriver.openURL("http://auto-course.us-west-2.elasticbeanstalk.com/addItemToCatalog.jsp");
		String qty = "5";
		String Category = "Phones";
		String Description = "blabla";
		String ItemName = "blabla";
		pagehelper.comboboxpage.QTYfromCombox(qty);
		pagehelper.comboboxpage.categoryFromCombox(Category);
		pagehelper.comboboxpage.itemNameField(ItemName);
		pagehelper.comboboxpage.itemDecriptionField(Description);
		pagehelper.comboboxpage.ClickSubmit();
		Assert.assertEquals("this are same", pagehelper.comboboxpage.getItemName(), ItemName);
	}

	@Test
	public void testAddCooclie() throws Exception {
		webdriver.openURL("http://www.google.co.il");
		webdriver.addCookie("automation", "test_SVG");
	}

	@Test
	public void CheckSpan() {
		webdriver.openURL("http://auto-course.us-west-2.elasticbeanstalk.com/precedingFollowingParent.jsp");
		WebElement element = pagehelper.comboboxpage.spanTest();
		if (element != null) {
			Assert.assertEquals("ddd", element.getText());
		}

	}

	@Test
	public void open2Browsers() throws Exception {
		webdriver.openURL("http://google.co.il");
		GenericWebDriver webdriver2 = new GenericWebDriver();
		webdriver.init("http://localhost:4444/wd/hub", testlog);
		webdriver2.openURL("http://www.walla.co.il");

	}

	@Test
	public void addItemToList() {
		testlog.addStep("open IKEA URL");
		
		

		webdriver.openURL("https://www.ikea.co.il");

		// import from DB/external file so the test will not fail for item that
		// doesnt exist.
		testlog.addAction("search item in the web");
		pagehelper.ikeapageobject.TypeInSearchField("20169559");
		pagehelper.ikeapageobject.clickSearchBtn();
		testlog.addAction("verify item price");
		pagehelper.ikeaproductpage.getPrice();
		testlog.assertStrings(pagehelper.ikeaproductpage.getPrice(), "695");
		testlog.addStep("add item to wish list");
		pagehelper.ikeaproductpage.addItemToWishListButton();
		pagehelper.ikeaproductpage.clickWishList();
		testlog.addAction("verfiy item description");
		String ItemInWishList = pagehelper.wishlistpage.checkItemInCheckList();
		Assert.assertEquals(ItemInWishList, "און נעליים עם 2 תאים");

	}
	@Test
	public void DragTest(){
		webdriver.openURL("https://www.w3schools.com/html/tryit.asp?filename=tryhtml5_draganddrop");
		webdriver.dragAndDrop1("//img[@id='drag1']", "//div[@id='div1']", 0, 0);
	}
	@Test 
	public void Drag1(){
		webdriver.openURL("http://www.elated.com/res/File/articles/development/javascript/jquery/drag-and-drop-with-jquery-your-essential-guide/draggable.html");
		
//		webdriver.dragAndDropBy(xpathFrom, xpathTo);xpathFrom, xpathTo);("//div[@id='makeMeDraggable']", "//div[@class='wideBox']/h1");
	}
	@Test
	public void Drag2(){
		
		

		webdriver.openURL("http://www.elated.com/res/File/articles/development/javascript/jquery/drag-and-drop-with-jquery-your-essential-guide/draggable.html");
		
		webdriver.dragtoRandom("//div[@id='makeMeDraggable']");
	}
	
	@Test
	public void ynetTest(){
		webdriver.openURL("http://www.yent.co.il");
		
	}
}
