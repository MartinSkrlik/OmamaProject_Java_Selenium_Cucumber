package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import page.CestaVon_LoginPage;
import runner.TestRunner;
import utility.Log;
import utility.ReportExtender;
import utility.SAUCEDEMO_PageMapper;
import utility.Validation;
import java.util.HashMap;

import static page.CestaVon_MainPage.MainPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;
import static page.CestaVon_LoginPage.loginPageItems.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;

public class CestaVon_LoginSteps extends TestStepActions {
	
	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver)globalParametersMap.get("driver");
	CestaVon_LoginPage page = new CestaVon_LoginPage(driver);
	String	Username,Email,PhoneNumber;

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
		CestaVon_LoginPage page = new CestaVon_LoginPage(driver);
		waitForElementVisible(driver, page.getErrorMessageLocator("Nesprávne heslo"), ErrorMessage.getDescription(), 60);
    	new Validation("ERROR MESSAGE", getDisplayedText(page.getErrorMessageElement("Nesprávne heslo"), ErrorMessage.getDescription()), SAUCEDEMO_PageMapper.ERROR_MESSAGE).contains();
    	Thread.sleep(1000);
    	ReportExtender.logScreen(driver);
    }

	@And("Input pin code {string}")
	public void inputpincode (String Pincode)  {
		waitForElementVisible(driver,PincodeElement.getLocator(),PincodeElement.getDescription(),15);
		setElementSecureText(PincodeElement.getElement(driver),Pincode,PincodeElement.getDescription());
	}

	@And("Click on menu button")
	public void clickOnMenuButton() {
		waitForElementVisible(driver,MenuButton.getLocator(),MenuButton.getDescription(),15);
		clickElement(MenuButton.getElement(driver),MenuButton.getDescription());
	}

	@And("Select from menu tab {string}")
	public void selectFromTabMenu(String value) {
		waitForElementClickable(driver,page.getTabLocator(value),"Wait for specific tab is visible",10);
		ReportExtender.logScreen(driver);
		clickElement(page.getTabElement(value),"Click on specific tab");
	}

	@And("Click on button {string}")
	public void clickOnButton(String value) {
		waitForElementClickable(driver,page.getButtonLocator(value),"Wait for specific button is visible",10);
		ReportExtender.logScreen(driver);
		clickElement(page.getButtonElement(value),"Click on specific button");
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
		clickElement(DropDownRole.getElement(driver), DropDownRole.getDescription());
		waitForElementClickable(driver,page.getTabLocator(value),"Wait for dropdown visible",10);
		clickElement(page.getTabElement(value),"Select specific role from dropdown");
		Username = getAttributeValue(NameSurnameInput.getElement(driver),NameSurnameInput.getDescription());
		Email = getAttributeValue(EmailInput.getElement(driver),EmailInput.getDescription());
		PhoneNumber = getAttributeValue(PhoneNumberInput.getElement(driver),PhoneNumberInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Confirm Registration")
	public void confirmRegistration() {
		sleep(5000);
		scrollElementIntoView(driver, RegistrovatButton.getElement(driver));
		waitForElementVisible(driver,RegistrovatButton.getLocator(), RegistrovatButton.getDescription(),10);
		clickElement(RegistrovatButton.getElement(driver), RegistrovatButton.getDescription());
	}

	@And("Verify {string} tab is active")
	public void verifyTabIsActive(String value) {
		waitForElementVisible(driver,SelectedTab.getLocator(),SelectedTab.getDescription(),10);
		new Validation("Verify Tab is active", getElementText(SelectedTab.getElement(driver), SelectedTab.getDescription()), value).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@And("Input new created username into meno search bar and select")
	public void inputNewCreatedUsernameIntoMenoSearchBarAndSelect() {
		waitForElementVisible(driver,MenoSearchBar.getLocator(),MenoSearchBar.getDescription(),10);
		setElementText(MenoSearchBar.getElement(driver),Username,MenoSearchBar.getDescription());
		waitForElementVisible(driver,SelectNewCreatedUser.getLocator(),SelectNewCreatedUser.getDescription(),10);
		ReportExtender.logScreen(driver);
		clickElement(SelectNewCreatedUser.getElement(driver), SelectNewCreatedUser.getDescription());
	}

	@Then("Verify details new created user")
	public void verifyDetailsNewCreatedUser() {
		waitForElementVisible(driver,GetUserEmail.getLocator(), GetUserName.getDescription(),10);
		new Validation("Verify USERNAME", getElementText(GetUserName.getElement(driver), GetUserName.getDescription()), Username).stringEquals();
		new Validation("Verify EMAIL", getElementText(GetUserEmail.getElement(driver), GetUserEmail.getDescription()), Email).stringEquals();
		new Validation("Verify PHONE NUMBER", getElementText(GetUserPhone.getElement(driver), GetUserPhone.getDescription()), PhoneNumber).stringEquals();
		ReportExtender.logScreen(driver);
	}
}
