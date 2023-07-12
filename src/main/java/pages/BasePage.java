package pages;


import java.time.Duration;  
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;


public class BasePage extends AbstractPage {
 

//Global Variables	
public WebDriverWait wait;

//public locators to access it from everywhere
public By sideBarList = By.xpath("//div[@class='widget stay-on-collapse']/nav/ul/li/a/span");
public By tabsOnAccountPage = By.xpath("//div[@id='contentBody']/ul/li/a");
//locator for clearing session
public By preferenceTab = By.xpath("//nav[@role='navigation']/ul/li[7]/ul/li[1]");
public By clearSessionDataButton = By.xpath("//input[@value='Clear Session Data']");
//public webelements
public WebElement getSideBarList() {
	return getElementforPage(sideBarList);
}
public WebElement getPreferenceTab() {
	return getElementforPage(preferenceTab);
}
public WebElement getClearSessionDataButton() {
	return getElementforPage(clearSessionDataButton);
}

//Constuctor
public BasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
//unimplemented Methods of Abstact class   
	@Override
	public String getTitle() {
		return driver.getTitle();
	}
	@Override
	public WebElement getElementforPage(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some err occered while creation of element  : " + locator.toString());
			e.printStackTrace();
		}
		return element;
	}
	@Override
	public void waitforElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Exception occered while waiting for" + locator.toString());
		}

	}
	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("Exception occered while waiting for title" + title);
		}
	}
	
//Methods for use	
	public void waitForElementVisibility(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOf((WebElement) locator));
		} catch (Exception e) {
			System.out.println("Exception occered while waiting for Webelement" + locator);
			e.printStackTrace();
		}
	}

	// you have webElement that has list of String text, you want to select or click
	// on perticular element which has matching Text
	
	public WebElement getStringBasedElement(By locator, String text) {
		List<WebElement> list = driver.findElements(locator);
		WebElement X = null;
		for (WebElement X1 : list) {
		
			if (X1.getText().equals(text)) {
				X = X1;
				break;
			}
		}
		return X;
	}

	public WebElement getAttributeBasedElement(By locator, String attributeValue, String attribute) {
		WebElement element = null;
		try {
			List<WebElement> list = driver.findElements(locator);
			for (WebElement X1 : list) {
				if (X1.getAttribute(attribute).equals(attributeValue)) {
					element = X1;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(
					"Some err occered while creation of element by getAttributeBasedElement" + locator.toString());
			e.printStackTrace();
		}
		return element;

	}

	public void scroll(WebElement element) {
		JavascriptExecutor JsE = (JavascriptExecutor) driver;
		JsE.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public void popUp(String AcceptorDismiss) {
		
		if (AcceptorDismiss.equalsIgnoreCase("accept")) {
			driver.switchTo().alert().accept();
		}
		else {
			driver.switchTo().alert().dismiss();
		}
	}
	public void selectByValue(WebElement E, String value) {
		Select s = new Select(E);
		s.selectByValue(value);
	}
	public void clickOnSideBarTabFinalValue(String nameOfTabInSideBar, WebElement finalSideBarValueToClick, By sideBarList ) {
		
		try {
			if (finalSideBarValueToClick.isDisplayed()) {
				finalSideBarValueToClick.click();
			} else {
				getStringBasedElement(sideBarList, nameOfTabInSideBar).click();
				finalSideBarValueToClick.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}}
	public void sessionClear() throws InterruptedException {
		
		driver.switchTo().frame(1);
		clickOnSideBarTabFinalValue("My Account", getPreferenceTab(), sideBarList);
		getStringBasedElement(tabsOnAccountPage, "SETTINGS").click();
		getClearSessionDataButton().click();
		driver.switchTo().defaultContent();
	}
	
}