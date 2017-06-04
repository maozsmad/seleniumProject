package PageObjects.ikeaPageObject;

import org.openqa.selenium.WebElement;

import MainClass.GenericWebDriver;
import PageObjects.GenericPageObject;
import enums.ByTypes;

public class ikeaProductPage extends GenericPageObject {

	private static final String GoToWishList = "//div[@class='goto_wishlist col-xs-12']/a[@class='btn button secondary']";

	public ikeaProductPage(GenericWebDriver webdriver) {
		super(webdriver);
		
	}
	
	private static final String itemPrice= " //div[@class='product_details']/div[@class='price ng-binding']";
	private static final String addItemToWishList = " //div[@class='button radius large add_to_wishlist ng-scope']";
	
	public String getPrice(){
		WebElement element  = webdriver.LocatedBy(ByTypes.xpath, itemPrice);	
		if(element!=null){
			return element.getText().replace(" ¤", "");
		}else
			return null;
		
	}
	
	public void addItemToWishListButton(){
		webdriver.LocatedBy(ByTypes.xpath,addItemToWishList).click();
	}
	public void clickWishList(){
		webdriver.LocatedBy(ByTypes.xpath, GoToWishList).click();
	}
}
//span[@class='product_code blockit']/span[@class='ng-binding']
