package steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.*;
import java.util.HashMap;
import static page.CestaVon_SettingsPage.settingsPageItems.*;


public class CestaVon_SettingsSteps extends TestStepActions{
    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);

    @And("Click on Change password")
    public void clickOnChangePassword() {
        waitForElementVisible(driver, ChangePasswordButton.getLocator(), ChangePasswordButton.getDescription(), 10);
        clickElement(ChangePasswordButton.getElement(driver), ChangePasswordButton.getDescription());
    }

    @And("Set old password as {string}")
    public void setOldPasswordAs(String value) {
        waitForElementVisible(driver, OldPasswordInput.getLocator(), OldPasswordInput.getDescription(), 10);
        setElementSecureText(OldPasswordInput.getElement(driver), value, OldPasswordInput.getDescription());
    }

    @And("Set new password as {string}")
    public void setNewPasswordAs(String value) {
        waitForElementVisible(driver, NewPasswordInput.getLocator(), NewPasswordInput.getDescription(), 10);
        setElementSecureText(NewPasswordInput.getElement(driver), value, NewPasswordInput.getDescription());
    }

    @And("Set confirm new password as {string}")
    public void setConfirmNewPasswordAs(String value) {
        waitForElementVisible(driver, ConfirmNewPasswordInput.getLocator(), ConfirmNewPasswordInput.getDescription(), 10);
        setElementSecureText(ConfirmNewPasswordInput.getElement(driver), value, ConfirmNewPasswordInput.getDescription());
    }

    @And("Confirm password change")
    public void confirmPasswordChange() {
        waitForElementVisible(driver, ConfirmPasswordChangeButton.getLocator(), ConfirmPasswordChangeButton.getDescription(), 10);
        boolean isEnabled = driver.findElement(ConfirmPasswordChangeButton.getLocator()).isEnabled();
        new Validation("Confirm button is enabled", isEnabled).isTrue();
        ReportExtender.logScreen(driver);
        clickElement(ConfirmPasswordChangeButton.getElement(driver), ConfirmPasswordChangeButton.getDescription());
    }
}
