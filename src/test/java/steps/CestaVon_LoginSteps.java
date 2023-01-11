package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.*;
import java.util.HashMap;

import static page.CestaVon_ClientProfilePage.ClientProfilePage.*;
import static page.CestaVon_LoginPage.loginPageItems.*;
import static page.CestaVon_SettingsPage.settingsPageItems.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;

public class CestaVon_LoginSteps extends TestStepActions {

	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
	CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
	HashMap<String, String> textparameters = new HashMap<>();

	@When("^Login user with username SECURE \"([^\"]*)\" and password SECURE \"([^\"]*)\"$")
	public void login_user_with_username_secure_and_password_secure(String username, String password) throws Throwable {
		setElementSecureText(UsernameElement.getElement(driver), username, UsernameElement.getDescription());
		setElementSecureText(PasswordElement.getElement(driver), password, PasswordElement.getDescription());
		ReportExtender.logScreen(driver);
		waitForElementClickable(driver, LoginButton.getLocator(), LoginButton.getDescription(), 15);
		clickElement(LoginButton.getElement(driver), LoginButton.getDescription());
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
	public void inputpincode(String Pincode) {
		waitForElementVisible(driver, PincodeElement.getLocator(), PincodeElement.getDescription(), 15);
		setElementSecureText(PincodeElement.getElement(driver), Pincode, PincodeElement.getDescription());
	}

	@When("Maximalize window")
	public void maximalizeWindow() {
		driver.manage().window().maximize();
	}

	@Then("Verify Login page is present")
	public void verifyLoginPageIsPresent() {
		waitForElementVisible(driver, LoginButton.getLocator(), LoginButton.getDescription(), 10);
		ReportExtender.logScreen(driver);
	}

	@And("Confirm password change negative")
	public void confirmPasswordChangeNegative() {
		waitForElementVisible(driver, ConfirmPasswordChangeButton.getLocator(), ConfirmPasswordChangeButton.getDescription(), 10);
		boolean isEnabled = driver.findElement(ConfirmPasswordChangeButton.getLocator()).isEnabled();
		new Validation("Confirm button is enabled", !isEnabled).isTrue();
		ReportExtender.logScreen(driver);
		clickElement(ConfirmPasswordChangeButton.getElement(driver), ConfirmPasswordChangeButton.getDescription());
	}

	@And("Verify user was deleted")
	public void verifyUserWasDeleted() {
		if(!verifyElementIsPresent(driver, page.getUserLocator(textparameters.get("username")), "Find if element was deleted"))
			{ReportExtender.logPass("Username " + (textparameters.get("username") + " was deleted"));}
		else
			ReportExtender.logWarning("Username " + (textparameters.get("username") + " was not deleted"));
	}

	@Then("Verify Omama is old Omama")
	public void verifyOmamaIsOldOmama() {
		new Validation("Verify assigned Omama", getElementText(AssignedOmama.getElement(driver), AssignedOmama.getDescription()), textparameters.get("omama")).stringEquals();
		ReportExtender.logScreen(driver);
	}
}
