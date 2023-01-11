package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import page.CestaVon_CommonPage;
import page.CestaVon_UsersPage;
import runner.TestRunner;
import utility.*;
import java.util.HashMap;
import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;
import static page.CestaVon_UsersPage.usersPageItems.RoleButton;

public class CestaVon_UsersSteps extends TestStepActions {

    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
    HashMap<String, String> textparameters = new HashMap<>();

    @And("Registry new {string} and save details")
    public void registryNewAndSaveDetails(String value) {
        waitForElementVisible(driver, page.getInputTextfieldLocator("Priezvisko"), "Wait for name input is visible", 10);
        setElementText(page.getInputTextfieldElement("Priezvisko"),RandomData.generateFirstName(), "Set name into input");
        setElementText(EmailInput.getElement(driver), RandomData.generateEmail(), EmailInput.getDescription());
        setElementText(page.getInputTextfieldElement("Región"), "test", "Set text into input");
        setElementText(page.getInputTextfieldElement("Mesto"), "Bratislava - Devín", "Set town into input");
        waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
        clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
        setElementText(page.getInputTextfieldElement("Uzivatelske"), RandomData.generateFirstName(), "Set username into input");
        setElementText(page.getInputTextfieldElement("Ulica"), RandomData.generateStreet(), "Set street into input");
        setElementText(page.getInputTextfieldElement("Heslo"),"Martintester123." , "Set heslo into input");
        setElementText(page.getInputTextfieldElement("Zopakovat"), "Martintester123.", "Set again heslo into input");
        setElementText(page.getInputTextfieldElement("cislo"), RandomData.generateMobileNumber(), "Set cislo into input");
        scrollElementIntoView(driver, DropDownRole.getElement(driver));
        waitForElementClickable(driver, DropDownRole.getLocator(), DropDownRole.getDescription(), 10);
        clickElement(DropDownRole.getElement(driver), DropDownRole.getDescription());
        waitForElementClickable(driver, page.getTabLocator(value), "Wait for dropdown visible", 10);
        clickElement(page.getTabElement(value), "Select specific role from dropdown");
        textparameters.put("username", getAttributeValue(page.getInputTextfieldElement("Priezvisko"), "Save username into variable"));
        textparameters.put("email", getAttributeValue(EmailInput.getElement(driver), EmailInput.getDescription()));
        textparameters.put("phoneNumber", getAttributeValue(page.getInputTextfieldElement("cislo"), "Save phone number into variable"));
        textparameters.put("town", getAttributeValue(page.getInputTextfieldElement("Mesto"), "Save town into variable"));
        ReportExtender.logScreen(driver);
    }

    @And("Find user with changed details")
    public void findUserWithChangedDetails() {
        waitForElementVisible(driver, page.getInputLocator("Meno"), "Wait for Meno input is visible", 10);
        setElementText(page.getInputElement("Meno"), textparameters.get("username"), "Set " + textparameters.get("username") + " into input");
        sleep(1000);
        ReportExtender.logScreen(driver);
    }

    @And("Select user with changed details")
    public void selectUserWithName() {
        waitForElementVisible(driver, page.getUserLocator(textparameters.get("username")), "Wait for " + textparameters.get("username") + " is visible", 10);
        clickElementUsingJavascript(driver, page.getUserElement(textparameters.get("username")), "Select " + textparameters.get("username"));
    }

    @Then("Verify admin details")
    public void verifyAdminDetails() {
        sleep(1000);
        new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username")).stringEquals();
        new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
        new Validation("Verify EMAIL", getElementText(EmailAdminText.getElement(driver), EmailAdminText.getDescription()), textparameters.get("email")).stringEquals();
        new Validation("Verify PHONE NUMBER", getElementText(PhoneAdminText.getElement(driver), PhoneAdminText.getDescription()), textparameters.get("phoneNumber")).stringEquals();
    }

    @Then("Save omama specification")
    public void saveomamaSpecification() {
        textparameters.put("mentor", getElementText(page.getOmamaSpecificationElement(1), "Save omama mentor"));
        textparameters.put("omamaLevel", getElementText(page.getOmamaSpecificationElement(4), "Save omama level"));
        textparameters.put("kidsCount",getElementText(page.getOmamaSpecificationElement(5), "Save kids count"));
        textparameters.put("jobSpecification", getElementText(page.getOmamaSpecificationElement(6), "Save job specification"));
        textparameters.put("onLevelSince",getAttributeValue(OnLevelSinceDate.getElement(driver), OnLevelSinceDate.getDescription()));
        ReportExtender.logScreen(driver);
    }

    @And("Verify omama specification was changed")
    public void verifyomamaSpecificationWasChanged() {
        scrollPageIntoBottom(driver);
        waitForElementVisible(driver, OnLevelSinceDate.getLocator(), "Wait for date picker", 10);
        new Validation("Verify omama mentor", getElementText(page.getOmamaSpecificationElement(1), ""), textparameters.get("mentor")).stringEquals();
        new Validation("Verify omama level", getElementText(page.getOmamaSpecificationElement(4), ""), textparameters.get("omamaLevel")).stringEquals();
        new Validation("Verify omama kids", getElementText(page.getOmamaSpecificationElement(5), ""), textparameters.get("kidsCount")).stringEquals();
        new Validation("Verify omama job specification", getElementText(page.getOmamaSpecificationElement(6), ""), textparameters.get("jobSpecification")).stringEquals();
        new Validation("Verify omama first date", getAttributeValue(OnLevelSinceDate.getElement(driver), ""), textparameters.get("onLevelSince")).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("Click on Rola and choose option {string}")
    public void clickOnRolaAndChooseOption(String value) {
        CestaVon_UsersPage page = new CestaVon_UsersPage(driver);
        waitForElementVisible(driver, RoleButton.getLocator(), RoleButton.getDescription(), 15);
        clickElement(RoleButton.getElement(driver), RoleButton.getDescription());
        waitForElementVisible(driver, page.getRolaOptionLocator(value), RoleButton.getDescription(), 15);
        clickElement(page.getRolaOptionElement(value), RoleButton.getDescription());
    }

    @Then("Verify Meno {string} and Rola {string} in filtered table")
    public void verifyMenoAndRolaInFilteredTable(String meno, String rola) {
        boolean properlyFiltered = true;
        int menoIndex = 0;
        int rolaIndex = 0;
        int numberOfPages = 0;
        //getting number of columns in table
        int numberOfColumns = driver.findElements(TableColumns.getLocator()).size();
        //getting number of rows in table
        int numberOfRows = 0;
        //getting number of pages in table
        numberOfPages = driver.findElements(PaginationNumbers.getLocator()).size();

        //indexes of Meno and Rola columns
        menoIndex = 1 + driver.findElements(page.getTableColumnPredecessorsLocator("Meno")).size();
        rolaIndex = 1 + driver.findElements(page.getTableColumnPredecessorsLocator("Rola")).size();

        ReportExtender.logInfo("Index Meno: " + menoIndex + ", index Rola: " + rolaIndex);

        //verifying filtering
        for (int y = 1; y <= numberOfPages; y++) {
            numberOfRows = driver.findElements(TableRows.getLocator()).size();
            ReportExtender.logInfo("Strana: " + y + "z " + numberOfPages + ", rozmer: " + numberOfRows + "x" + numberOfColumns + ".");
            for (int x = 1; x <= numberOfRows; x++) {
                //column Meno
                String menoString = driver.findElement(page.getTableNameValueLocator(x, menoIndex)).getText();
                if (!menoString.toLowerCase().startsWith(meno.toLowerCase())) {
                    properlyFiltered = false;
                }
                //column Rola
                String rolaString = driver.findElement(page.getTableValueLocator(x, rolaIndex)).getText();
                if (!rolaString.startsWith(rola)) {
                    properlyFiltered = false;
                    ReportExtender.logWarning("Rola " + rola + "does not match " + rolaString + ".");
                }

                ReportExtender.logInfo("Riadok: " + x + ", meno je: " + menoString + ", a rola je:" + rolaString);
            }
            if (PaginationNextButton.getElement(driver).getAttribute("aria-disabled").equals("false")) {
                scrollElementIntoMiddleOfScreen(driver, PaginationNextButton.getElement(driver));
                sleep(2000);
                clickElement(PaginationNextButton.getElement(driver), PaginationNextButton.getDescription());
            }
        }

        new Validation("Table is properly filtered", properlyFiltered).isTrue();
    }

    @And("Select user with name {string} and remember his information")
    public void selectUserWithNameAndRememberHisInformation(String value) {
        CestaVon_UsersPage page = new CestaVon_UsersPage(driver);
        waitForElementVisible(driver, page.getRowByNameLocator(value), "Waiting for row visible", 10);
        ReportExtender.logScreen(driver);
        textparameters.put("username", getElementText(page.getNameFromRowByNameElement(value), "Remembering Name"));
        textparameters.put("email", getElementText(page.getEmailFromRowByNameElement(value), "Remembering Email"));
        textparameters.put("phoneNumber", getElementText(page.getPhoneNumberFromRowByNameElement(value), "Remembering Phone Number"));
        textparameters.put("town", getElementText(page.getTownFromRowByNameElement(value), "Remembering City"));
        clickElement(page.getRowByNameElement(value), "Opening profile " + value + ".");
    }

    @Then("Verify all information is visible")
    public void verifyAllInformationIsVisible() {
        waitForElementVisible(driver, BackButton.getLocator(), BackButton.getDescription(), 10);
        ReportExtender.logScreen(driver);
        new Validation("Name visible in profile", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username")).stringEquals();
        new Validation("Phone Number visible in profile", getElementText(PhoneAdminText.getElement(driver), PhoneAdminText.getDescription()), textparameters.get("phoneNumber")).stringEquals();
        new Validation("Email visible in profile", getElementText(EmailAdminText.getElement(driver), EmailAdminText.getDescription()), textparameters.get("email")).stringEquals();
        new Validation("Town visible in profile", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
    }

    @And("Select first user contains {string}")
    public void selectFirstUser(String value) {
        waitForElementVisible(driver,page.getFirstUserLocator(value),"Wait for first user contains " + value,10);
        clickElementUsingJavascript(driver, page.getFirstUserElement(value), "Select first user contains " + value);
    }

    @And("Change user details")
    public void changeUserDetails() {
        waitForElementVisible(driver, page.getInputTextfieldLocator("Priezvisko"), "Wait for username input visieble", 10);
        setElementText(page.getInputTextfieldElement("Priezvisko"), RandomData.generateFirstName(), "Set random name into input");
//		setElementText(page.getInputTextfieldElement("Telefon"),RandomData.generateMobileNumber(), "Set random phone into input");
        setElementText(page.getInputTextfieldElement("Email"), RandomData.generateEmail(), "Set random email into input");
        setElementText(page.getInputTextfieldElement("Mesto"), "Bratislava - Devínska Nová Ves", "Set town into input");
        waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
        clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
        clickElementUsingJavascript(driver, page.getDropdownElement(RandomData.getRandomStatus()), "Change user status");
        ReportExtender.logScreen(driver);
    }

    @And("Save user details")
    public void saveUserDetails() {
        waitForElementVisible(driver,page.getInputTextfieldLocator("Priezvisko"),"Wait for input visible",10);
        textparameters.put("username", getAttributeValue(page.getInputTextfieldElement("Priezvisko"), "Save username into variable"));
        textparameters.put("email", getAttributeValue(page.getInputTextfieldElement("Email"), "Save email into variable"));
        textparameters.put("phoneNumber", getAttributeValue(page.getInputTextfieldElement("Telefon"), "Save phone number into variable"));
        textparameters.put("town",getAttributeValue(page.getInputTextfieldElement("Mesto"), "Save town into variable"));
        textparameters.put("status", getElementText(UserStatusText.getElement(driver), UserStatusText.getDescription()));
        ReportExtender.logScreen(driver);
    }

    @And("Select user with name {string}")
    public void selectUserWithName(String Username) {
        waitForElementVisible(driver, page.getUserLocator(Username), "Wait for username", 10);
        clickElementUsingJavascript(driver, page.getUserElement(Username), "Click on desired user");
    }

    @And("Set user status {string}")
    public void setUserStatus(String value) {
        scrollElementIntoView(driver, page.getDropdownElement(value));
        waitForElementClickable(driver, page.getDropdownLocator(value), "Wait for user status", 10);
        clickElementUsingJavascript(driver, page.getDropdownElement(value), "Change user status");
    }

    @Then("Verify user status")
    public void verifyUserstatus() {
        sleep(1000);
        new Validation("Verify user STATUS", StringUtils.chop(getElementText(StatusText.getElement(driver), StatusText.getDescription())), StringUtils.chop(textparameters.get("status"))).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @Then("Verify supervisor-mentor details")
    public void verifySupervisorMentorDetails() {
        sleep(1000);
        new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username")).stringEquals();
        new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
        new Validation("Verify EMAIL", getElementText(EmailMentorText.getElement(driver), EmailMentorText.getDescription()), textparameters.get("email")).contains();
        new Validation("Verify PHONE NUMBER", getElementText(PhoneMentorText.getElement(driver), PhoneMentorText.getDescription()), textparameters.get("phoneNumber")).contains();
    }
}
