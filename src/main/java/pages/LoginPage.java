package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{


  public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
  private By username = By.id("username");
  private By password = By.name("password");
  private By login = By.name("Submit");
  private By logo = By.xpath("//img[@class='hide-on-collapse']");
  
//WebElements
public WebElement getUsername() {
	return getElementforPage(username);
}
public WebElement getPassword() {
	return getElementforPage(password);
}
public WebElement getLogin() {
	return getElementforPage(login);
}
public WebElement getLogo() {
	return getElementforPage(logo);
}

//Methods
public String loginPageTitle () {
	return getTitle();	
}  
public void doLogin(String userName, String password) throws InterruptedException {
	  getUsername().sendKeys(userName);
	  getPassword().sendKeys(password);
	  getLogin().click();
	  Thread.sleep(2000);
}
public Boolean logo() {
	driver.switchTo().frame(1);
	waitforElementPresent(logo);
	return getLogo().isDisplayed();
}


}