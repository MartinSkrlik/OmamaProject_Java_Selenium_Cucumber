package steps;

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
    
    @Then("Verify odhlasit button is visible")
	public void verify_odhlasit_button_is_visible(){
		waitForElementVisible(driver,OdhlasitButton.getLocator(),OdhlasitButton.getDescription(),15);
	}

	@Then("^Verify odhlasit button is not visible$")
    public void verify_page_title_is_not_visible() throws Throwable {
    	if(driver.findElements(OdhlasitButton.getLocator()).size() > 0) {
    		Log.info(SAUCEDEMO_PageMapper.FAIL_MESSAGE);
            ReportExtender.logPass("Validating " + PageTitle.getDescription() + ": </br>" + ReportExtender.addMarkup("FAIL") + SAUCEDEMO_PageMapper.FAIL_MESSAGE);
    	}
    	else{
    		Log.info(SAUCEDEMO_PageMapper.SUCCESS_MESSAGE);
            ReportExtender.logPass("Validating " + PageTitle.getDescription() + ": </br>" + ReportExtender.addMarkup("PASS") + SAUCEDEMO_PageMapper.SUCCESS_MESSAGE);
    	}
    }

    @Then("^Verify error message is visible$")
    public void verify_error_message_is_visible() throws Throwable {
		CestaVon_LoginPage page = new CestaVon_LoginPage(driver);
		waitForElementVisible(driver, page.getErrorMessageLocator("Nesprávne heslo"), ErrorMessage.getDescription(), 60);
    	new Validation("ERROR MESSAGE", getDisplayedText(page.getErrorMessageElement("Nesprávne heslo"), ErrorMessage.getDescription()), SAUCEDEMO_PageMapper.ERROR_MESSAGE).stringEquals();
//    	Thread.sleep(1000);
    	ReportExtender.logScreen(driver);
    }
}