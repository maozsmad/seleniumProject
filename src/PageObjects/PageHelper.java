package PageObjects;

import MainClass.GenericWebDriver;
import PageObjects.ikeaPageObject.ikeaHomePage;
//this class holds all of the pageObjects pages.
import PageObjects.ikeaPageObject.ikeaProductPage;
import PageObjects.ikeaPageObject.wishListPage;

public class PageHelper extends GenericPageObject {
	
	public 	LoginPage loginpage;
	public ComboBoxPage comboboxpage;
	public PageObjects.ikeaPageObject.ikeaHomePage ikeapageobject;
	public PageObjects.ikeaPageObject.ikeaProductPage ikeaproductpage;
	public PageObjects.ikeaPageObject.wishListPage wishlistpage;	
	
public PageHelper(GenericWebDriver webdriver) {
		super(webdriver);
		
		loginpage = new LoginPage(webdriver);
		comboboxpage= new ComboBoxPage(webdriver);
		ikeapageobject = new PageObjects.ikeaPageObject.ikeaHomePage(webdriver);
		ikeaproductpage = new PageObjects.ikeaPageObject.ikeaProductPage(webdriver);
		wishlistpage = new PageObjects.ikeaPageObject.wishListPage(webdriver);
	}



}
