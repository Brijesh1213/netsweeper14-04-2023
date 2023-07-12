package test;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.BasePage;
import pages.LoginPage;

public class AccountsTest extends BaseTest {

	public AccountsTest() throws Throwable {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 0)
	public void verifycreateAccount() throws InterruptedException {
		// Logger log = new Logger ();
		// test = extent.createTest("Create
		// Account").assignAuthor("Brijesh").assignCategory("Smoke").assignDevice("Windows,Chrome");

		abstractPage.getInstancePage(AccountsPage.class).createAdminAccount();
		Assert.assertEquals(
				abstractPage.getInstancePage(BasePage.class)
						.getText(abstractPage.getInstancePage(AccountsPage.class).getAccountCreateText()),
				"×" + "\n" + "Successfully added new admin account Selenium");

	}

	@Test(priority = 1)
	public void verifydeleteAccount() throws InterruptedException {

		abstractPage.getInstancePage(AccountsPage.class).deleteAccount("Selenium", "org");
		abstractPage.getInstancePage(BasePage.class).popUp("accept");
		Assert.assertEquals(
				abstractPage.getInstancePage(BasePage.class)
						.getText(abstractPage.getInstancePage(AccountsPage.class).getDeleteAccountText()),
				"Deleted account Selenium." + "\n" + "×");

	}

	@Test(priority=2)
	public void verifyHelpTextforAccountList() {
		Object[] arr = {
				"The List tab is used to edit and delete Accounts. Click the Edit link for the desired Account to display the Account information.",
				"The Status column displays the status of the Account: Expired Password, Blocked, Login, Logout, and Timeout Alert.",
				"Use the Import/Export buttons to import or export Accounts.",
				"The Email button opens a window that allows you to send an email to one or more Accounts." };
		System.out.println(Arrays.toString(arr));
		Assert.assertEquals(abstractPage.getInstancePage(AccountsPage.class).helpText("LIST"), arr);
	}

	@Test(priority=3)
	public void verifyHelpTextforAccountManageGroups() {
		Object[] arr = {
				"Use this tab to view or assign Accounts to Groups. Use the Export button to export the list. Use the Import button to import a list and quickly assign Group Assignments to existing Account." };

		Assert.assertEquals(abstractPage.getInstancePage(AccountsPage.class).helpText("MANAGE GROUPS"), arr);
	}

	@Test(priority=4)
	public void verifyHelpTextforAccountTemplates() {
		Object[] arr = {
				"Use the Account Templates tab to view or modify an Account Template. Click the Create button to add a new Template. You can also Import and Export Templates." };
		System.out.println(Arrays.toString(arr));
		// System.out.println(Arrays.asList(abstractPage.getInstancePage(AccountsPage.class).helpText("TEMPLATES")).toString());
		Assert.assertEquals(abstractPage.getInstancePage(AccountsPage.class).helpText("TEMPLATES"), arr);
	}

	@Test(priority=5)
	public void verifyHelpTextforAccountLockouts() {
		Object[] arr = {
				"The Lockouts tab displays information on Accounts that are locked out. This includes the Remote IP, Cookie, Location, Number of Attempts and the Last Attempt." };
		System.out.println(Arrays.toString(arr));
		// System.out.println(Arrays.asList(abstractPage.getInstancePage(AccountsPage.class).helpText("LOCKOUTS")).toString());
		Assert.assertEquals(abstractPage.getInstancePage(AccountsPage.class).helpText("LOCKOUTS"), arr);
	}

}