package PageObjects.ikeaPageObject;



import MainClass.GenericWebDriver;
import PageObjects.GenericPageObject;
import enums.ByTypes;

public class wishListPage extends GenericPageObject{

	public wishListPage(GenericWebDriver webdriver) {
		super(webdriver);
		
	}
	private static final String item1inWishList = " //span[@class='prod_name blockit ng-binding']";
	
	public String checkItemInCheckList(){
		String itemName = webdriver.LocatedBy(ByTypes.xpath, item1inWishList).getText();
		return itemName;
	}
}
