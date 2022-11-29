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

import static page.CestaVon_LoginPage.loginPageItems.*;

public class CestaVon_LoginSteps extends TestStepActions {
	
	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver)globalParametersMap.get("driver");

	@When("^Login user with username SECURE \"([^\"]*)\" and password SECURE \"([^\"]*)\"$")
    public void login_user_with_username_secure_and_password_secure(String username, String password) throws Throwable {
    	setElementSecureText(UsernameElement.getElement(driver), username, UsernameElement.getDescription());
    	setElementSecureText(PasswordElement.getElement(driver), password, PasswordElement.getDescription());
    	ReportExtender.logScreen(driver);    
        waitForElementClickable(driver, LoginButton.getLocator(), LoginButton.getDescription(), 15);
    	clickElement(LoginButton.getElement(driver), LoginButton.getDescription());
    }
    
    @Then("Click on odhlasit button")
	public void clickonodhlasitButton(){
		waitForElementVisible(driver,OdhlasitButton.getLocator(),OdhlasitButton.getDescription(),15);
		ReportExtender.logScreen(driver);
		clickElement(OdhlasitButton.getElement(driver),OdhlasitButton.getDescription());
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

	@And("Click on zaregistrovat zariadenie button")
	public void clickOnZaregistrovatZariadenieButton() {
		waitForElementClickable(driver, LoginButton.getLocator(), LoginButton.getDescription(), 15);
		ReportExtender.logScreen(driver);
		clickElement(LoginButton.getElement(driver), LoginButton.getDescription());
	}

	@And("Click on menu button")
	public void clickOnMenuButton() {
		waitForElementVisible(driver,MenuButton.getLocator(),MenuButton.getDescription(),15);
		clickElement(MenuButton.getElement(driver),MenuButton.getDescription());
	}

	@Then("Click on odhlasit button in menu")
	public void clickOnOdhlasitButtonInmenu() {
		waitForElementVisible(driver,OdhlasitButtonInMenu.getLocator(),OdhlasitButtonInMenu.getDescription(),15);
		ReportExtender.logScreen(driver);
		clickElement(OdhlasitButtonInMenu.getElement(driver),OdhlasitButtonInMenu.getDescription());
	}


}
