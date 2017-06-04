package PageObjects;

import MainClass.GenericWebDriver;
import enums.ByTypes;

public class LoginPage extends GenericPageObject{

	private static final String clickButtonXpath = "//button[@class='btn btn-primary']";
	private static final String PasswordXpath = "//input[@id='password']";
	private static final String UserNameXpath = "//input[@id='username']";
	//llllll
	public LoginPage(GenericWebDriver webdriver) {
		super(webdriver);
		
	}
	public void enterUserName(String username) {
		webdriver.LocatedBy(ByTypes.xpath,UserNameXpath).sendKeys(username);
	}
	public void enterPassword(String password) {
		webdriver.LocatedBy(ByTypes.xpath,PasswordXpath).sendKeys(password);
	}
	public void clickSubmit() {
		webdriver.LocatedBy(ByTypes.xpath, clickButtonXpath).click();
	}
	public String getUserName (){
		String username = webdriver.LocatedBy(ByTypes.xpath,"//*[@id='username']").getText();
		return username;
	}
	public String getPassword(){
		String password = webdriver.LocatedBy(ByTypes.xpath,"//*[@id='password']").getText();
		return password;
	}

}
