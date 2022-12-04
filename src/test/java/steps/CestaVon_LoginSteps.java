package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.Log;
import utility.ReportExtender;
import utility.SAUCEDEMO_PageMapper;
import utility.Validation;

import java.util.HashMap;

import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_LoginPage.loginPageItems.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;

public class CestaVon_LoginSteps extends TestStepActions {
	
	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver)globalParametersMap.get("driver");
	CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
	String	Username,Email,PhoneNumber,Town,Status = "";

	@When("^Login user with username SECURE \"([^\"]*)\" and password SECURE \"([^\"]*)\"$")
    public void login_user_with_username_secure_and_password_secure(String username, String password) throws Throwable {
    	setElementSecureText(UsernameElement.getElement(driver), username, UsernameElement.getDescription());
    	setElementSecureText(PasswordElement.getElement(driver), password, PasswordElement.getDescription());
    	ReportExtender.logScreen(driver);    
        waitForElementClickable(driver, LoginButton.getLocator(), LoginButton.getDescription(), 15);
    	clickElement(LoginButton.getElement(driver), LoginButton.getDescription());
	}

	@Then("^Verify odhlasit button is not visible$")
    public void verify_odhlasit_button_is_not_visible() throws Throwable {
    	if(driver.findElements(OdhlasitButton.getLocator()).size() > 0) {
    		Log.info(SAUCEDEMO_PageMapper.FAIL_MESSAGE);
            ReportExtender.logPass("Validating " + OdhlasitButton.getDescription() + ": </br>" + ReportExtender.addMarkup("FAIL") + SAUCEDEMO_PageMapper.FAIL_MESSAGE_ODHLASIT_BUTTON);
    	}
    	else{
    		Log.info(SAUCEDEMO_PageMapper.SUCCESS_MESSAGE);
            ReportExtender.logPass("Validating " + OdhlasitButton.getDescription() + ": </br>" + ReportExtender.addMarkup("PASS") + SAUCEDEMO_PageMapper.SUCCESS_MESSAGE_ODHLSIT_BUTTON);
    	}
    }

    @Then("^Verify error message is visible$")
    public void verify_error_message_is_visible() throws Throwable {
		CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
		waitForElementVisible(driver, page.getErrorMessageLocator("Nesprávne heslo"), ErrorMessage.getDescription(), 60);
    	new Validation("ERROR MESSAGE", getDisplayedText(page.getErrorMessageElement("Nesprávne heslo"), ErrorMessage.getDescription()), SAUCEDEMO_PageMapper.ERROR_MESSAGE).contains();
    	Thread.sleep(1000);
    	ReportExtender.logScreen(driver);
    }

	@And("Input pin code {string}")
	public void inputpincode (String Pincode)  {
		waitForElementVisible(driver,PincodeElement.getLocator(),PincodeElement.getDescription(),15);
		setElementSecureText(PincodeElement.getElement(driver),Pincode,PincodeElement.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Click on menu button")
	public void clickOnMenuButton() {
		waitForElementVisible(driver,MenuButton.getLocator(),MenuButton.getDescription(),15);
		clickElement(MenuButton.getElement(driver),MenuButton.getDescription());
	}

	@And("Select from menu tab {string}")
	public void selectFromTabMenu(String value) {
		waitForElementClickable(driver,page.getTabLocator(value),"Wait for specific tab is visible",10);
		clickElement(page.getTabElement(value),"Click on specific tab");
	}

	@And("Click on button {string}")
	public void clickOnButton(String value) {
		scrollElementIntoView(driver,page.getButtonElement(value));
		waitForElementClickable(driver,page.getButtonLocator(value),"Wait for specific button is clickable",10);
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver, page.getButtonElement(value),"Click on specific button");
	}

	@And("Registry new {string} user and save details")
	public void registryNewUserAndSaveDetails(String value) {
		waitForElementVisible(driver,NameSurnameInput.getLocator(),NameSurnameInput.getDescription(),10);
		setElementText(NameSurnameInput.getElement(driver),"Martin Tester",NameSurnameInput.getDescription());
		setElementText(EmailInput.getElement(driver),"Martintester@gmail.com",EmailInput.getDescription());
		setElementText(RegionInput.getElement(driver),"test",RegionInput.getDescription());
		setElementText(TownInput.getElement(driver),"Bratislava - Devín",TownInput.getDescription());
		waitForElementClickable(driver,ConfirmTownInput.getLocator(),ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		setElementText(UsernameInput.getElement(driver),"Martin_TEST",UsernameInput.getDescription());
		setElementText(StreetInput.getElement(driver),"Testerska 38",StreetInput.getDescription());
		setElementText(PasswordInput.getElement(driver),"Martintester123.",PasswordInput.getDescription());
		setElementText(AgainHesloInput.getElement(driver),"Martintester123.",AgainHesloInput.getDescription());
		setElementText(PhoneNumberInput.getElement(driver),"0915123456",PhoneNumberInput.getDescription());
		scrollElementIntoView(driver, DropDownRole.getElement(driver));
		waitForElementClickable(driver,DropDownRole.getLocator(), DropDownRole.getDescription(), 10);
		clickElement(DropDownRole.getElement(driver), DropDownRole.getDescription());
		waitForElementClickable(driver,page.getTabLocator(value),"Wait for dropdown visible",10);
		clickElement(page.getTabElement(value),"Select specific role from dropdown");
		Username = getAttributeValue(NameSurnameInput.getElement(driver),NameSurnameInput.getDescription());
		Email = getAttributeValue(EmailInput.getElement(driver),EmailInput.getDescription());
		PhoneNumber = getAttributeValue(PhoneNumberInput.getElement(driver),PhoneNumberInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Verify {string} tab is active")
	public void verifyTabIsActive(String value) {
		waitForElementVisible(driver,SelectedTab.getLocator(),SelectedTab.getDescription(),10);
		new Validation("Verify Tab is active", getElementText(SelectedTab.getElement(driver), SelectedTab.getDescription()), value).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@And("Input new created username into {string} search bar and select")
	public void inputNewCreatedUsernameIntoSearchBarAndSelect(String value) {
		waitForElementVisible(driver,page.getInputLocator(value),"Wait for input is visible",10);
		setElementText(page.getInputElement(value),Username,"Set value into input");
		waitForElementVisible(driver,SelectNewCreatedUser.getLocator(),SelectNewCreatedUser.getDescription(),10);
		ReportExtender.logScreen(driver);
		clickElement(SelectNewCreatedUser.getElement(driver), SelectNewCreatedUser.getDescription());
	}

	@Then("Verify details new created Admin user")
	public void verifyDetailsNewCreatedAdminUser() {
		waitForElementVisible(driver,GetUserName.getLocator(), GetUserName.getDescription(),10);
		new Validation("Verify USERNAME", getElementText(GetUserName.getElement(driver), GetUserName.getDescription()), Username).stringEquals();
		new Validation("Verify EMAIL", getElementText(GetUserEmailAdmin.getElement(driver), GetUserEmailAdmin.getDescription()), Email).stringEquals();
		new Validation("Verify PHONE NUMBER", getElementText(GetUserPhoneAdmin.getElement(driver), GetUserPhoneAdmin.getDescription()), PhoneNumber).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@When("Maximalize window")
	public void maximalizeWindow() {
		driver.manage().window().maximize();
	}

	@Then("Verify details new created user")
	public void verifyDetailsNewCreatedUser() {
		waitForElementVisible(driver,GetUserName.getLocator(), GetUserName.getDescription(),10);
		new Validation("Verify USERNAME", getElementText(GetUserName.getElement(driver), GetUserName.getDescription()), Username).stringEquals();
//		new Validation("Verify EMAIL", getElementText(GetUserEmail.getElement(driver), GetUserEmail.getDescription()), Email).stringEquals();
//		new Validation("Verify PHONE NUMBER", getElementText(GetUserPhone.getElement(driver), GetUserPhone.getDescription()), PhoneNumber).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@Then("Verify Login page is present")
	public void verifyLoginPageIsPresent() {
		waitForElementVisible(driver,LoginButton.getLocator(), LoginButton.getDescription(),10);
		ReportExtender.logScreen(driver);
	}

	@And("Unwrap dropdown {string}")
	public void unwrapDropdown(String value) {
		scrollElementIntoView(driver,page.getDropdownElement(value));
		waitForElementClickable(driver,page.getDropdownLocator(value),"Wait for specific dropdown clickable",10);
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver,page.getDropdownElement(value),"Unwrap specific dropdown"); }

	@And("Select into input {string} actual date")
	public void selectIntoInputActualDate(String value) {
		scrollElementIntoView(driver,page.getInputElement(value));
		waitForElementClickable(driver,page.getInputLocator(value),"Wait for specific input clickable",10);
		clickElementUsingJavascript(driver,page.getInputElement(value),"Click on specific input");
		waitForElementClickable(driver,SelectCurrentDate.getLocator(),SelectCurrentDate.getDescription(),10);
		clickElementUsingJavascript(driver, SelectCurrentDate.getElement(driver),"Select current day from date menu");
	}

	@And("Find user {string}")
	public void findUser(String value) {
		waitForFullPageLoad(driver,10);
		while (driver.findElements(page.getUserLocator(value)).size() == 0) {
			waitForElementVisible(driver,NextPageButton.getLocator(),NextPageButton.getDescription(),10);
			if (verifyElementIsPresent(driver,NextPageButtonDisabled.getLocator(),NextPageButtonDisabled.getDescription()))
				{ReportExtender.logFail("USERNAME WAS NOT FOUND");
				ReportExtender.logScreen(driver);
				driver.close();}
			clickElementUsingJavascript(driver,NextPageButton.getElement(driver), NextPageButton.getDescription());
			waitForFullPageLoad(driver,10);
		}
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver,page.getUserElement(value),"Click on desired user");
	}

	@And("Change user details")
	public void changeUserDetails() {
		waitForElementVisible(driver,NameSurnameInput.getLocator(),NameSurnameInput.getDescription(),10);
		setElementText(NameSurnameInput.getElement(driver),"Martin Tester Change",NameSurnameInput.getDescription());
		setElementText(TownInput.getElement(driver),"Bratislava - Devínska Nová Ves",TownInput.getDescription());
		waitForElementClickable(driver,ConfirmTownInput.getLocator(),ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Set user status {string}")
	public void setUserStatus(String value) {
		scrollElementIntoView(driver,page.getDropdownElement(value));
		waitForElementClickable(driver,page.getDropdownLocator(value),"Wait for user status",10);
		clickElementUsingJavascript(driver,page.getDropdownElement(value),"Change user status");
		ReportExtender.logScreen(driver);
	}

	@And("Save user details")
	public void saveUserDetails() {
		Username = getAttributeValue(NameSurnameInput.getElement(driver),NameSurnameInput.getDescription());
		Town = getAttributeValue(TownInput.getElement(driver), TownInput.getDescription());
		Status = getElementText(GetUserStatus.getElement(driver), GetUserStatus.getDescription());
	}

	@And("Verify user details were changed")
	public void verifyUserDetailsWereChanged() {
		sleep(3000);
		new Validation("Verify USERNAME",getElementText(GetUserName.getElement(driver),GetUserName.getDescription()),Username).stringEquals();
		new Validation("Verify user TOWN",getElementText(GetUserTown.getElement(driver),GetUserTown.getDescription()),Town).stringEquals();
		new Validation("Verify user STATUS", StringUtils.chop(getElementText(GetStatus.getElement(driver), GetStatus.getDescription())), StringUtils.chop(Status)).contains();
		ReportExtender.logScreen(driver);
	}

	@And("Change user details to original state")
	public void changeUserDetailsToOriginalState() {
		waitForElementVisible(driver,NameSurnameInput.getLocator(),NameSurnameInput.getDescription(),10);
		setElementText(NameSurnameInput.getElement(driver),"Martin Tester",NameSurnameInput.getDescription());
		setElementText(TownInput.getElement(driver),"Bratislava - Devín",TownInput.getDescription());
		waitForElementClickable(driver,ConfirmTownInput.getLocator(),ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@Then("Go back to previous page")
	public void goBackToPreviousPage() {
		waitForElementClickable(driver,SpatButton.getLocator(),SpatButton.getDescription(),10);
		clickElementUsingJavascript(driver,SpatButton.getElement(driver), SpatButton.getDescription());
	}

	@And("Find user with changed status")
	public void findUserWithChangedStatus() {
		waitForFullPageLoad(driver, 10);
		while (driver.findElements(page.getUserLocator(Username)).size() == 0) {
			waitForElementVisible(driver, NextPageButton.getLocator(), NextPageButton.getDescription(), 10);
			if (verifyElementIsPresent(driver, NextPageButtonDisabled.getLocator(), NextPageButtonDisabled.getDescription())) {
				ReportExtender.logFail("USERNAME WAS NOT FOUND");
				ReportExtender.logScreen(driver);
				driver.close();
			}
			clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
			waitForFullPageLoad(driver, 10);
		}
		clickElementUsingJavascript(driver, page.getUserElement(Username), "Click on desired user");
	}

}

