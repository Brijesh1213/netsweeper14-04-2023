package test;

import java.awt.Robot; 
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pages.AbstractPage;
import pages.AccountsPage;
import pages.BasePage;
import pages.LoginPage;
import utility.ExcelMethods;

public class BaseTest {

//Global Variable
	WebDriver driver;
// objects
	BasePage basePage;
	LoginPage loginPage;
	AccountsPage accountsPage;
	AbstractPage abstractPage;
    ExcelMethods excel;
//Constructor
 public BaseTest() throws Throwable{
	 
	
 }
//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

//---commented for extent reports----
//	ExtentTest test;
//	ExtentReports extent = new ExtentReports();
//	ExtentSparkReporter spark = new ExtentSparkReporter("Extentreport.html");

//locators for reaching to initial state
	private By firstContButton = By.xpath("input[value='Continue']");
	private By advanceButton = By.id("details-button");
	private By proceedLink = By.id("proceed-link");
	private By continueButton = By.xpath("//div[@id='body_container']/div/form/input[2]");

//webelements to reach to initial state

	public WebElement getFirstContButton() {
		return abstractPage.getElementforPage(firstContButton);
	}

	public WebElement getAdvanceButton() {
		return abstractPage.getElementforPage(advanceButton);
	}

	public WebElement getProceedLink() {
		return abstractPage.getElementforPage(proceedLink);
	}

	public WebElement getContinueButton() {
		return abstractPage.getElementforPage(continueButton);
	}

	@BeforeMethod
	public void initialisation() throws Throwable {
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--ignore-ssl-errors=yes");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--incognito");
		driver=new ChromeDriver(options);
		//driver=new ChromeDriver();
		abstractPage = new BasePage(driver);
		excel = new ExcelMethods("Sheet1", ".\\src\\test\\resources\\testData.xlsx");
		System.out.println("URL redirecting to : " + excel.getCellData("Sheet1", "URL", 2));
		driver.get(excel.getCellData("Sheet1", "URL", 2));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		 Robot robot = new Robot();
		  System.out.println("About to zoom out");
		  for (int i = 0; i < 3; i++) {
		   robot.keyPress(KeyEvent.VK_CONTROL);
		   robot.keyPress(KeyEvent.VK_MINUS);
		   robot.keyRelease(KeyEvent.VK_MINUS);
		   robot.keyRelease(KeyEvent.VK_MINUS);
		   robot.keyRelease(KeyEvent.VK_CONTROL);
		  }
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 
		try {

//			List<WebElement> fContbutton = driver.findElements(firstContButton);
//			System.out.println(fContbutton.size());
//			if (fContbutton.size() > 0) {
//				getAdvanceButton().click();
//			} else {
//				System.out.println("This Page has no first continue Button");
//			}
//
//			
//			List<WebElement> advbutton = driver.findElements(advanceButton);
//			System.out.println(advbutton.size());
//			if (advbutton.size() > 0) {
//				getAdvanceButton().click();
//			} else {
//				System.out.println("This Page has no Advance Button");
//			}
//
//			List<WebElement> pLink = driver.findElements(proceedLink);
//			System.out.println(pLink.size());
//			if (pLink.size() > 0) {
//			getProceedLink().click();
//		} else {
//			System.out.println("This Page has no Proceed Link");
//		}
			List<WebElement> Cbutton = driver.findElements(continueButton);
			System.out.println(Cbutton.size());
			if (Cbutton.size() > 0) {
				getContinueButton().click();
			} else {
				System.out.println("This Page has no Continue Button Link");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		abstractPage.getInstancePage(LoginPage.class).doLogin(excel.getCellData("Sheet1","UserName", 2),excel.getCellData("Sheet1","Password", 2));
		abstractPage.getInstancePage(BasePage.class).sessionClear();
	}

   @AfterMethod
	public void teardown() {
		driver.quit();

	}

//-----commented for extent reports
//      public void teardown(ITestResult Result) throws Throwable {	
//		 baseTest = new BaseTest(driver);
//		 try {
//			if(Result.getStatus()== ITestResult.FAILURE) {
//				  //failure is defined as 2
//				  test.log(Status.FAIL, "Test Case Failed Is" + Result.getName());
//				  test.log(Status.FAIL, "Test Case Failed Is" + Result.getThrowable());
//				  
//				  String screenshotPath = baseTest.getScreenshot(driver, null);
//				 // extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromPath(screenshotPath));
//				  test.fail( MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			  } 
//			  
//			  else if (Result.getStatus()==ITestResult.SKIP) {
//				  test.log(Status.SKIP, "test case skipped is"+ Result.getName());
//			  }
//		 }
//		 catch(Exception e){
//			 
//				  test.pass("Test Case Passed Is" + Result.getName());
//			  
//		 }

	// above method need to be added for ur screenshot and for html report.

//---commented for extent	 
	// @BeforeMethod
//	 public void setExtent() {
//		 extent.attachReporter(spark);
//
//	 }

	// @AfterMethod
	public void endReport() {
		// extent.flush();
		// extent.close();
	}

//	 public String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException { //method for extent
//		 
//			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//			
//			TakesScreenshot ts = (TakesScreenshot) driver;
//			File source = ts.getScreenshotAs(OutputType.FILE);
//			String Destination =  System.getProperty("user.dir") + "/FailedScreenshot"+ ScreenshotName + dateName+ ".png" ;
//			FileUtils.copyFile (source, new File (Destination));
//			return Destination;
//		   
//		 }

}
