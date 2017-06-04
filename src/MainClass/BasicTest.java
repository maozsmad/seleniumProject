package MainClass;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.logging.LogEntry;

import PageObjects.LoginPage;
import PageObjects.PageHelper;
import Services.IReporter;
import TestObjects.HtmlReporterImpl;
import TestObjects.TestLog;

public class BasicTest {

	GenericWebDriver webdriver;
	PageHelper pagehelper;
	TestLog testlog;
	IReporter reporter;

	@Before
	public void setup() throws Exception {
		
		testlog = new TestLog();
		reporter = new HtmlReporterImpl();
		
		webdriver = new GenericWebDriver();
		webdriver.init("http://localhost:4444/wd/hub", testlog);
		pagehelper = new PageHelper(webdriver);
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		List<LogEntry> logs = webdriver.getWebDriverLogs().getAll();
		for(int i = 0; i<logs.size();i++){
			System.out.println(logs.get(i).getLevel().toString()+logs.get(i).getMessage());
		}
		if(logs.size()==0){
			System.out.println("No logs found");
		}
		webdriver.quit();
		reporter.saveTestLog(testlog);
	}
}
