package PageObjects;

import org.openqa.selenium.WebElement;

import MainClass.GenericWebDriver;
import enums.ByTypes;

public class ComboBoxPage extends GenericPageObject {

	public ComboBoxPage(GenericWebDriver webdriver) {
		super(webdriver);

	}

	private static final String qtyComboBox = "//*[@class='col-xs-2']/select[@name='qty']";
	private static final String categoryCombox = "//*[label = 'Category']/select[@class='form-control']";
	//Another option is using sibling //label[text()='Category']//following-sibling::select
	private static final String itemNameCombox = "//input[@id='itemName']";
	private static final String itemDesciption = "//*[@class='col-xs-2'][2]/input";
	private static final String sendButton = "//input[@type='submit']";
	private static final String getItemName = "//label/div";

	public void QTYfromCombox(String value)  {
		try {
			webdriver.selectComboBoxOptions(value, qtyComboBox);
		} catch (Exception e) {
			webdriver.printScreen("SelectFromComboBoxFailed");
			e.printStackTrace();
		}
	}

	public void categoryFromCombox(String value)   {
		try {
			webdriver.selectComboBoxOptions(value, categoryCombox);
		} catch (Exception e) {
			webdriver.printScreen("SelectFromComboBoxFailed");
			e.printStackTrace();
		}
	}

	public void itemNameField(String value) throws Exception {
		webdriver.LocatedBy(ByTypes.xpath, itemNameCombox).sendKeys(value);
		;
	}

	public void itemDecriptionField(String value)  {
		webdriver.LocatedBy(ByTypes.xpath, itemDesciption).sendKeys(value);
		
	}

	public void ClickSubmit() {
		webdriver.LocatedBy(ByTypes.xpath, sendButton).click();

	}

	public String getItemName() {
		String itemName = webdriver.LocatedBy(ByTypes.xpath, getItemName).getText();
		return itemName;
	}
	public WebElement spanTest(){
		WebElement element = webdriver.LocatedBy(ByTypes.xpath, "//span[@id='abc']/following-sibling::span");
		return element;
	}
}
