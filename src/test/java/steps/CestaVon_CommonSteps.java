package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.*;
import java.util.HashMap;

import static page.CestaVon_ClientProfilePage.ClientProfilePage.*;
import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;

public class CestaVon_CommonSteps extends TestStepActions {

    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
    HashMap<String, String> textparameters = new HashMap<>();

    @And("Click on button {string}")
    public void clickOnButton(String value) {
        waitForFullPageLoad(driver, 10);
        scrollElementIntoView(driver, page.getButtonElement(value));
        waitForElementClickable(driver, page.getButtonLocator(value), "Wait for " + value + "button is visible", 10);
        clickElementUsingJavascript(driver, page.getButtonElement(value), "Click on " + value + " button");
    }

    @And("Select from menu tab {string}")
    public void selectFromTabMenu(String value) {
        waitForElementClickable(driver, page.getTabLocator(value), "Wait for choice " + value + " is visible", 10);
        clickElementUsingJavascript(driver,page.getTabElement(value), "Click on choice " + value);
    }

    @And("Verify {string} tab is active")
    public void verifyTabIsActive(String value) {
        waitForElementVisible(driver, SelectedTab.getLocator(), "Wait for " + value + " tab is visible", 10);
        new Validation("Verify Tab is active", getElementText(SelectedTab.getElement(driver), SelectedTab.getDescription()), value).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("Unwrap dropdown {string}")
    public void unwrapDropdown(String value) {
        scrollElementIntoView(driver, page.getDropdownElement(value));
        waitForElementClickable(driver, page.getDropdownLocator(value), "Wait for dropdown " + value + " is clickable", 10);
        clickElementUsingJavascript(driver, page.getDropdownElement(value), "Unwrap dropdown " + value);
    }

    @And("Select into input {string} actual date")
    public void selectIntoInputActualDate(String value) {
        scrollElementIntoView(driver, page.getInputElement(value));
        waitForElementClickable(driver, page.getInputLocator(value), "Wait for specific input clickable", 10);
        clickElementUsingJavascript(driver, page.getInputElement(value), "Click on specific input");
        waitForElementClickable(driver, SelectCurrentDate.getLocator(), SelectCurrentDate.getDescription(), 10);
        clickElementUsingJavascript(driver, SelectCurrentDate.getElement(driver), "Select current day from date menu");
    }

    @Then("Wait for changes is processed")
    public void waitForChangesIsProcessed() {
        sleep(2000);
    }

    @And("Set Input Field {string} to {string}")
    public void setSearchFieldTo(String searchField, String searchText) {
        waitForElementVisible(driver, page.getInputLocator(searchField), "Wait for search field visible", 15);
        scrollElementIntoMiddleOfScreen(driver, page.getInputElement(searchField));
        sleep(1000);
        setElementText(page.getInputElement(searchField), searchText, "Setting element text");
        ReportExtender.logScreen(driver);
    }

    @And("Input into {string} search bar username {string}")
    public void searchForUsername(String textfield, String username) {
        waitForElementVisible(driver, page.getInputLocator(textfield), "Wait for " + textfield + " input is visible", 10);
        sleep(500);
        setElementText(page.getInputElement(textfield), username, "Set " + username + " into input");
        ReportExtender.logScreen(driver);
    }

    @Then("Go back to previous page")
    public void goBackToPreviousPage() {
        waitForElementClickable(driver, BackButton.getLocator(), BackButton.getDescription(), 10);
        clickElementUsingJavascript(driver, BackButton.getElement(driver), BackButton.getDescription());
    }

    @And("Clear input {string}")
    public void clearInput(String textfield) {
        waitForElementVisible(driver, page.getInputLocator(textfield), "Wait for textfield " + textfield + " is visible", 10);
        page.getInputElement(textfield).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        ReportExtender.logScreen(driver);
    }

    @And("Select first row from table")
    public void selectFirstRowFromTable() {
        waitForElementVisible(driver, FirstTableRow.getLocator(), FirstTableRow.getDescription(), 10);
        clickElement(FirstTableRow.getElement(driver), FirstTableRow.getDescription());
    }

    @And("Fill calendar with today date")
    public void FillCalendarWithTodayDate() {
        waitForElementVisible(driver, ActivationCalendarInput.getLocator(), ActivationCalendarInput.getDescription(), 10);
        clickElement(ActivationCalendarInput.getElement(driver), ActivationCalendarInput.getDescription());
        sleep(1000);
        waitForElementVisible(driver, CalendarTodayCell.getLocator(), CalendarTodayCell.getDescription(), 10);
        ReportExtender.logScreen(driver);
        clickElement(CalendarTodayCell.getElement(driver), CalendarTodayCell.getDescription());
    }

    @And("Refresh current page")
    public void refreshCurrentPage() {
        refreshPage(driver);
    }

    @Then("Remember number of rows")
    public void rememberNumberOfRows() {
        sleep(1000);
        int numberOfRows = driver.findElements(TableRows.getLocator()).size();
        String nRows = String.valueOf(numberOfRows);
        textparameters.put("numberOfTableRows", nRows);
        ReportExtender.logPass("Remembered " + nRows + " rows.");
        ReportExtender.logScreen(driver);
    }

    @And("Set date to {string}")
    public void setDateTo(String value) {
        waitForElementVisible(driver, LessonDateInput.getLocator(), LessonDateInput.getDescription(), 10);
        scrollElementIntoMiddleOfScreen(driver, LessonDateInput.getElement(driver));
        clickElementUsingJavascript(driver, LessonDateInput.getElement(driver), LessonDateInput.getDescription());
        setElementText(LessonDateInput.getElement(driver), value, LessonDateInput.getDescription());
        sleep(1000);
    }

    @And("Verify number of rows is incremented")
    public void verifyNumberOfRowsIsIncremented() {
        sleep(1000);
        int actualRows = driver.findElements(TableRows.getLocator()).size();
        String stringActualRows = String.valueOf(actualRows);
        String incrementedRememberedRows = String.valueOf(1 + Integer.parseInt(textparameters.get("numberOfTableRows")));
        new Validation("Verify number of rows is incremented", stringActualRows, incrementedRememberedRows).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("Verify number of rows is the same")
    public void verifyNumberOfRowsIsTheSame() {
        sleep(1000);
        int actualRows = driver.findElements(TableRows.getLocator()).size();
        String stringActualRows = String.valueOf(actualRows);
        new Validation("Verify number of rows is the same", stringActualRows, textparameters.get("numberOfTableRows")).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("Click ESC button")
    public void clickESCButton() {
        ReportExtender.logScreen(driver);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
    }

    @And("Click on menu button")
    public void clickOnMenuButton() {
        waitForElementVisible(driver, MenuButton.getLocator(), MenuButton.getDescription(), 15);
        clickElement(MenuButton.getElement(driver), MenuButton.getDescription());
    }

    @And("Click on {string} button from menu")
    public void clickOnButtonFromMenu(String value) {
        waitForElementVisible(driver,page.getErrorMessageLocator(value),"Wait for "+ value + " button",10);
        clickElementUsingJavascript(driver,page.getErrorMessageElement(value),"Click on " + value + " button");
    }

    @Then("^Verify odhlasit button is not visible$")
    public void verify_odhlasit_button_is_not_visible() throws Throwable {
        if (driver.findElements(LogOffButton.getLocator()).size() > 0) {
            Log.info(SAUCEDEMO_PageMapper.FAIL_MESSAGE);
            ReportExtender.logPass("Validating " + LogOffButton.getDescription() + ": </br>" + ReportExtender.addMarkup("FAIL") + SAUCEDEMO_PageMapper.FAIL_MESSAGE_ODHLASIT_BUTTON);
        } else {
            Log.info(SAUCEDEMO_PageMapper.SUCCESS_MESSAGE);
            ReportExtender.logPass("Validating " + LogOffButton.getDescription() + ": </br>" + ReportExtender.addMarkup("PASS") + SAUCEDEMO_PageMapper.SUCCESS_MESSAGE_ODHLSIT_BUTTON);
        }
    }

}
