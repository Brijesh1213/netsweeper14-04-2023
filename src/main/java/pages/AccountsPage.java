package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage extends BasePage {

	public AccountsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// locators for create account
	
	private By accountsTab = By.xpath("//nav[@role='navigation']/ul/li[2]/ul/li[1]");
	private By accountsSideTab = By.xpath("//nav[@role='navigation']/ul/li[2]");
	private By pageTitle = By.xpath("//ol[@class='breadcrumb']/li[2]/a/strong");
	private By advanceTab = By.xpath("(//div[@class='container_fluid']//div[@class='contentContainer']/ul/li/a)[2]");
	private By createAdvanceAccountList = By.xpath("//form[@role='form']/div/div/input");
	private By toolbarIcon = By.xpath("//div[@class='accounts_toolbar datatables_toolbar']/div[1]/span/i");
	private By submit = By.xpath("//input[@type='submit']");
	private By checkbox = By.xpath("//input[@class='checkbox']");
	private By accountCreateText = By.xpath("//div[@id='controls_message_bar']");
	// locator for delete account
	private By loginName = By.xpath("//tr[@role='row']/td[2]/a");// tr[@role='row']/td[3]
	private By orgName = By.xpath("//tr[@role='row']/td[4]");
	private By deleteAccountText = By.xpath("//div[@id='accounts_messages']/div");
	private By searchBox = By.xpath("//input[@type='search']");
	private By selectSearchBoxValue = By.xpath("//div[@id='accounts_table_filter']/select");
	// locators for help Text
	private By helpTextButton = By.xpath("//div[@id='fullHelpDiv']");
	private By helpTextAccountList = By.xpath("//div[@id='help_text']/p");
	private By helpTextManageGroups = By.xpath("//div[@id='help_text']/b");
	private By helpTextTemplateOrLockout = By.xpath("//div[@id='help_text']");
	
	

	// private By classification = By.xpath("//tr[@role='row']/td[2]");
	// WebElements for createAdminAccount()
	public WebElement getsideBar() {
		return getElementforPage(sideBarList);
	}

	public WebElement getaccountsTab() {
		return getElementforPage(accountsTab);
	}

	public WebElement getAccountsSideTab() {
		return getElementforPage(accountsSideTab);
	}

	public WebElement getcreateAdvanceList() {
		return getElementforPage(createAdvanceAccountList);
	}

	public WebElement getSubmit() {
		return getElementforPage(submit);
	}

	public WebElement getCheckboxNoPassword() {
		return getElementforPage(checkbox);
	}

	public WebElement getPageTitle() {
		return getElementforPage(pageTitle);
	}

	public WebElement getCreatePlusicon() {
		return getElementforPage(toolbarIcon);
	}

	public WebElement getAdvanceTab() {
		return getElementforPage(advanceTab);
	}

	public WebElement getAccountCreateText() {
		return getElementforPage(accountCreateText);
	}

	// for delete account
	public WebElement getLoginName() {
		return getElementforPage(loginName);
	}

	public WebElement getDeleteAccountText() {
		return getElementforPage(deleteAccountText);
	}
	public WebElement getSearchBox() {
		return getElementforPage(searchBox);
	}
	public WebElement getSelectSearchBoxValue() {
		return getElementforPage(selectSearchBoxValue);
	}

	// for HelpText
	public WebElement getHelpTextButton() {
		return getElementforPage(helpTextButton);
	}

	public WebElement getHelpTextAccountList() {
		return getElementforPage(helpTextAccountList);
	}

	public WebElement getTabsOnAccountPage() {
		return getElementforPage(tabsOnAccountPage);
	}
	public WebElement gethelpTextTemplateOrLockout() {
		return getElementforPage(helpTextTemplateOrLockout);
	}

	// methodss
	public void createAdminAccount() throws InterruptedException {
		driver.switchTo().frame(1);
//		try {
//			if (getaccountsTab().isDisplayed()) {
//				getaccountsTab().click();
//			} else {
//				getStringBasedElement(sideBarList, "Accounts").click();
//				getaccountsTab().click();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		clickOnSideBarTabFinalValue("Accounts", getaccountsTab(),sideBarList);
		getStringBasedElement(tabsOnAccountPage, "LIST").click();
		getStringBasedElement(sideBarList, "Accounts").click();
		getaccountsTab().click();
		getAttributeBasedElement(toolbarIcon, "accounts_create_button_icon", "id").click();
		System.out.println("clicked on plus icon");
		getAdvanceTab().click();
		System.out.println("clicked on advance Tab");
		Thread.sleep(1000);
		waitforElementPresent(createAdvanceAccountList);
		getAttributeBasedElement(createAdvanceAccountList, "userid", "name").click();
		getAttributeBasedElement(createAdvanceAccountList, "userid", "name").sendKeys("Selenium");
		System.out.println("Login Name : Selenium Provided");
		getAttributeBasedElement(createAdvanceAccountList, "organization", "name").click();
		getAttributeBasedElement(createAdvanceAccountList, "organization", "name").sendKeys("org");
		System.out.println("orgName Name : org");
		getCheckboxNoPassword().click();
		scroll(getSubmit());
		getSubmit().click();
	}

	public void deleteAccount(String LoginName, String orgName) throws InterruptedException {
		
		driver.switchTo().frame(1);
		clickOnSideBarTabFinalValue("Accounts", getaccountsTab(),sideBarList);
		getStringBasedElement(tabsOnAccountPage, "LIST").click();
		Thread.sleep(300);
		getSearchBox().sendKeys(LoginName);
		Thread.sleep(300);
		scroll(getSelectSearchBoxValue());
		selectByValue(getSelectSearchBoxValue(), "1");
		Thread.sleep(300);
		//selectByValue(accountCreateText, orgName);
		if (getText(getStringBasedElement(loginName, LoginName)).equals(LoginName)
				&& getText(getStringBasedElement(this.orgName, orgName)).equals(orgName)) {
			getStringBasedElement(this.orgName, orgName).click();
			Thread.sleep(300);
		}
		getAttributeBasedElement(toolbarIcon, "delete_button_icon", "id").click();
		Thread.sleep(300);
	}

	public Object[] helpText(String tabName) {
		driver.switchTo().frame(1);
		clickOnSideBarTabFinalValue("Accounts", getaccountsTab(),sideBarList);
		System.out.println("clicked on accounts");
		ArrayList<String> arr = new ArrayList<String>();
		Object[] arrO = null;
		int i;
		if (tabName.equalsIgnoreCase(getStringBasedElement(tabsOnAccountPage, tabName).getText())) {
			getStringBasedElement(tabsOnAccountPage, tabName).click();
			System.out.println("clicked on : " + tabName);
			System.out.println(gethelpTextTemplateOrLockout().getAttribute("style"));
			try {
				getHelpTextButton().click();
				
				if (gethelpTextTemplateOrLockout().getAttribute("style").equalsIgnoreCase("display: block;")) {
					System.out.println("Help Text is allready clicked");
				} else if(gethelpTextTemplateOrLockout().getAttribute("style").equalsIgnoreCase("display: none;")) {
					getHelpTextButton().click();
					
					System.out.println("clicked on helpTextButton");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			List<WebElement> helpTexts = null;
			if (tabName.equalsIgnoreCase("List")) {
				helpTexts = driver.findElements(helpTextAccountList);
				for (i = 1; i <= helpTexts.size(); i++) {
					arr.add(getText(driver.findElement(By.xpath("//div[@id='help_text']/p[" + i + "]"))));
				}
			} else if (tabName.equalsIgnoreCase("TEMPLATES")||tabName.equalsIgnoreCase("LOCKOUTS")||tabName.equalsIgnoreCase("MANAGE GROUPS") ) {
				helpTexts = driver.findElements(helpTextTemplateOrLockout);
				for (i = 1; i <= helpTexts.size(); i++) {
					arr.add(getText(driver.findElement(By.xpath("//div[@id='help_text']"))));
				}
			}
			arrO = arr.toArray();
			System.out.println(arrO.toString());
		}
		return arrO;

	}
}