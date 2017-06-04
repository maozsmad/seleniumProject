package MainClass;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Services.Utils.fileUtils;
import TestObjects.TestLog;
import enums.ByTypes;

public class GenericWebDriver {

	RemoteWebDriver webdriver;
	TestLog testlog;
	Actions action;

	public void init(String remoteURL, TestLog testlog) throws Exception {
		DesiredCapabilities capability = new DesiredCapabilities().chrome();
		LoggingPreferences loggingPreferences = new LoggingPreferences();
		loggingPreferences.enable(LogType.BROWSER, Level.ALL);
		capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		
		webdriver = new RemoteWebDriver(new URL(remoteURL), capability);
		this.testlog = testlog;
	}
	
	public LogEntries getWebDriverLogs(){
		return webdriver.manage().logs().get(LogType.BROWSER);
	}

	public void openURL(String webURL) {
		webdriver.get(webURL);
	}

	public void quit() {
		webdriver.quit();
	}

	// default method to use for webdriver Wait.
	public WebElement LocatedBy(ByTypes type, String value) {
		return LocatedBy(type, value, 10);
	}

	// method for special cases when timeOut needs to be change.
	public WebElement LocatedBy(ByTypes type, String value, int timeOut) {

		WebDriverWait wait = new WebDriverWait(webdriver, timeOut, 1000);
		WebElement element = null;
		try {
			switch (type) {
			case id:
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
				break;
			case xpath:
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
				break;
			case className:
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value)));
				break;
			case link:
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(value)));
				break;
			case name:
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printScreen("elementnotfound");

		}
		// testlog.addAction("Element found");
		return element;

	}

	public List<WebElement> getList(String Xpath) {
		WebDriverWait wait = new WebDriverWait(webdriver, 10, 1000);
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Xpath)));

		return list;

	}

	public void printScreen(String prefix) {
		File screenshot = null;

		String newPath = null;

		try {
			WebDriver augmentedDriver = new Augmenter().augment(webdriver);
			screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			newPath = System.getProperty("user.dir") + "\\files\\scr" + prefix + screenshot.getName();
			fileUtils.copyFile(screenshot, newPath);

		} catch (WebDriverException e) {

			System.out.println(e.getStackTrace());

		}

	}

	public void selectComboBoxOptions(String optionVale, String xpath) throws Exception {

		WebElement comboBox = LocatedBy(ByTypes.xpath, xpath);

		Select select = new Select(comboBox);

		select.selectByVisibleText(optionVale);

	}

	public List<String> getComboBoxValues(String xpath) {
		// get the combox element
		WebElement comboBoxElement = LocatedBy(ByTypes.xpath, xpath);
		// create a combox element from the webElement
		Select select = new Select(comboBoxElement);
		// create a list of webElements from the combox value
		List<WebElement> combovalues = select.getOptions();
		// create a list of STrings that will hold the text
		List<String> listStr = new ArrayList<String>();
		// iterate the webelement list and gets the text from each webElement
		for (WebElement element : combovalues) {
			listStr.add(element.getText());
		}
		// return the list of strings
		return listStr;
	}

	public void addCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		webdriver.manage().addCookie(cookie);
	}

	public boolean isCookieExist(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		if (cookie != null) {
			if (cookie.getValue().equals(value)) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	public void dragAndDrop1(String xpathFrom, String xpathTo, int xoffset, int yoffset) {
		Actions actions = new Actions(webdriver);
		Random random = new Random();

		WebElement elementFrom = LocatedBy(ByTypes.xpath, xpathFrom);

		WebDriverWait wait = new WebDriverWait(webdriver, 10, 1000);

		actions.dragAndDrop(elementFrom, wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTo))))
				.build().perform();
		actions.dragAndDropBy(elementFrom, xoffset, yoffset);

	}

	public void dragByLocation(String xpathFrom, int xoffset, int yoffset) {
		Actions actions = new Actions(webdriver);
		WebElement elementFrom = LocatedBy(ByTypes.xpath, xpathFrom);
		actions.dragAndDropBy(elementFrom, xoffset, yoffset);
	}

	public void dragtoRandom(String xpathFrom) {
		Random random = new Random();
		int xoffset = random.nextInt(100);
		int yoffset = random.nextInt(100);
		WebElement elementFrom = LocatedBy(ByTypes.xpath, xpathFrom);
		Actions actions = new Actions(webdriver);
		actions.clickAndHold(elementFrom).moveByOffset(xoffset, yoffset).release().perform();

	}

	public void dragAndDrop1(String xpathFrom) {
		dragAndDrop1(xpathFrom, null, 0, 0);
	}

}