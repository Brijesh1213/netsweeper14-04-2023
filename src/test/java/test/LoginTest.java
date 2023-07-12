package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

	

	

	public LoginTest() throws Throwable {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1)
	public void verifyPageTitleTest() throws Throwable {
		// test = extent.createTest("verifyPage
		// TitleTest").assignAuthor("Brijesh").assignCategory("Smoke").assignDevice("Windows");
		String title = loginPage.loginPageTitle();
		Assert.assertEquals(title, "Netsweeper WebAdmin");
	}

	@Test(priority = 2)
	public void verifyLogo() throws InterruptedException {
		// test = extent.createTest("verify
		// Logo").assignAuthor("Brijesh").assignCategory("Smoke").assignDevice("Windows");
		//loginPage.doLogin();
		Thread.sleep(5000);
		Assert.assertTrue(loginPage.logo());
	}

}
