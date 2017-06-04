package PageObjects.ikeaPageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import MainClass.GenericWebDriver;
import PageObjects.GenericPageObject;
import enums.ByTypes;

public class ikeaHomePage extends GenericPageObject{

	public ikeaHomePage(GenericWebDriver webdriver){
		super(webdriver);
	}
	


	private static final String searchFieldXpath = "//div[@class='search_inner']//input";
	private static final String SearchButton = "search_btn_find";
	public void TypeInSearchField(String value){
		WebElement element = webdriver.LocatedBy(ByTypes.xpath, searchFieldXpath);
		if(element!=null){
			element.sendKeys(value);
			element.sendKeys(Keys.ENTER);
		}
		
	}
	public void clickSearchBtn(){
		webdriver.LocatedBy(ByTypes.id, SearchButton).click();
	}
}
