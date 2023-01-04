package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import page.CestaVon_CommonPage;
import page.CestaVon_UsersPage;
import runner.TestRunner;
import utility.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static page.CestaVon_ClientProfilePage.ClientProfilePage.NameInput;
import static page.CestaVon_ClientProfilePage.ClientProfilePage.SurnameInput;
import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_LoginPage.loginPageItems.*;
import static page.CestaVon_SettingsPage.settingsPageItems.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;
import static page.CestaVon_UsersPage.usersPageItems.RoleButton;

public class CestaVon_LoginSteps extends TestStepActions {

	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
	CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
	int countOfClientsVisibleOnPage = 10, name_index = 2, surname_index = 3;
	String[] clientsName = new String[countOfClientsVisibleOnPage];
	String[] clientsSurname = new String[countOfClientsVisibleOnPage];
	String[] clientsUsername = new String[countOfClientsVisibleOnPage];
	String getEveryUserElement = ".";
	HashMap<String, String> textparameters = new HashMap<>();

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
		if (driver.findElements(LogOffButton.getLocator()).size() > 0) {
			Log.info(SAUCEDEMO_PageMapper.FAIL_MESSAGE);
			ReportExtender.logPass("Validating " + LogOffButton.getDescription() + ": </br>" + ReportExtender.addMarkup("FAIL") + SAUCEDEMO_PageMapper.FAIL_MESSAGE_ODHLASIT_BUTTON);
		} else {
			Log.info(SAUCEDEMO_PageMapper.SUCCESS_MESSAGE);
			ReportExtender.logPass("Validating " + LogOffButton.getDescription() + ": </br>" + ReportExtender.addMarkup("PASS") + SAUCEDEMO_PageMapper.SUCCESS_MESSAGE_ODHLSIT_BUTTON);
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
	public void inputpincode(String Pincode) {
		waitForElementVisible(driver, PincodeElement.getLocator(), PincodeElement.getDescription(), 15);
		setElementSecureText(PincodeElement.getElement(driver), Pincode, PincodeElement.getDescription());
	}

	@And("Click on menu button")
	public void clickOnMenuButton() {
		waitForElementVisible(driver, MenuButton.getLocator(), MenuButton.getDescription(), 15);
		clickElement(MenuButton.getElement(driver), MenuButton.getDescription());
	}

	@And("Select from menu tab {string}")
	public void selectFromTabMenu(String value) {
		waitForElementClickable(driver, page.getTabLocator(value), "Wait for choice " + value + " is visible", 10);
		clickElementUsingJavascript(driver,page.getTabElement(value), "Click on choice " + value);
	}

	@And("Click on button {string}")
	public void clickOnButton(String value) {
		scrollElementIntoView(driver, page.getButtonElement(value));
		waitForElementClickable(driver, page.getButtonLocator(value), "Wait for " + value + "button is visible", 10);
		clickElementUsingJavascript(driver, page.getButtonElement(value), "Click on " + value + " button");
	}

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

	@And("Verify {string} tab is active")
	public void verifyTabIsActive(String value) {
		waitForElementVisible(driver, SelectedTab.getLocator(), "Wait for " + value + " tab is visible", 10);
		new Validation("Verify Tab is active", getElementText(SelectedTab.getElement(driver), SelectedTab.getDescription()), value).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@Then("Verify admin details")
	public void verifyAdminDetails() {
		sleep(1000);
		new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username")).stringEquals();
		new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
		new Validation("Verify EMAIL", getElementText(EmailAdminText.getElement(driver), EmailAdminText.getDescription()), textparameters.get("email")).stringEquals();
		new Validation("Verify PHONE NUMBER", getElementText(PhoneAdminText.getElement(driver), PhoneAdminText.getDescription()), textparameters.get("phoneNumber")).stringEquals();
	}

	@Then("Verify supervisor-mentor details")
	public void verifySupervisorMentorDetails() {
		sleep(1000);
		new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username")).stringEquals();
		new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
		new Validation("Verify EMAIL", getElementText(EmailMentorText.getElement(driver), EmailMentorText.getDescription()), textparameters.get("email")).contains();
		new Validation("Verify PHONE NUMBER", getElementText(PhoneMentorText.getElement(driver), PhoneMentorText.getDescription()), textparameters.get("phoneNumber")).contains();
	}

	@Then("Verify user status")
	public void verifyUserstatus() {
		sleep(1000);
		new Validation("Verify user STATUS", StringUtils.chop(getElementText(StatusText.getElement(driver), StatusText.getDescription())), StringUtils.chop(textparameters.get("status"))).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@Then("Verify details")
	public void verifyDetails() {
		sleep(1000);
		new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username")).stringEquals();
		new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
		ReportExtender.logScreen(driver);
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

	@And("Set user status {string}")
	public void setUserStatus(String value) {
		scrollElementIntoView(driver, page.getDropdownElement(value));
		waitForElementClickable(driver, page.getDropdownLocator(value), "Wait for user status", 10);
		clickElementUsingJavascript(driver, page.getDropdownElement(value), "Change user status");
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

	@And("Click on Rola and choose option {string}")
	public void clickOnRolaAndChooseOption(String value) {
		CestaVon_UsersPage page = new CestaVon_UsersPage(driver);
		waitForElementVisible(driver, RoleButton.getLocator(), RoleButton.getDescription(), 15);
		clickElement(RoleButton.getElement(driver), RoleButton.getDescription());
		waitForElementVisible(driver, page.getRolaOptionLocator(value), RoleButton.getDescription(), 15);
		clickElement(page.getRolaOptionElement(value), RoleButton.getDescription());
	}

	@And("Set Input Field {string} to {string}")
	public void setSearchFieldTo(String searchField, String searchText) {
		waitForElementVisible(driver, page.getInputLocator(searchField), "Wait for search field visible", 15);
		setElementText(page.getInputElement(searchField), searchText, "Setting element text");
		sleep(3000);
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


	@Then("Go back to previous page")
	public void goBackToPreviousPage() {
		waitForElementClickable(driver, BackButton.getLocator(), BackButton.getDescription(), 10);
		clickElementUsingJavascript(driver, BackButton.getElement(driver), BackButton.getDescription());
	}

	@And("Find user with changed details")
	public void findUserWithChangedDetails() {
		waitForElementVisible(driver, page.getInputLocator("Meno"), "Wait for Meno input is visible", 10);
		setElementText(page.getInputElement("Meno"), textparameters.get("username"), "Set " + textparameters.get("username") + " into input");
		sleep(1000);
		ReportExtender.logScreen(driver);
	}

	@And("Input into {string} search bar username {string}")
	public void searchForUsername(String textfield, String username) {
		waitForElementVisible(driver, page.getInputLocator(textfield), "Wait for " + textfield + " input is visible", 10);
		setElementText(page.getInputElement(textfield), username, "Set " + username + " into input");
		ReportExtender.logScreen(driver);
	}

	@And("Select user with name {string}")
	public void selectUserWithName(String Username) {
		waitForElementVisible(driver, page.getUserLocator(Username), "Wait for username", 10);
		clickElementUsingJavascript(driver, page.getUserElement(Username), "Click on desired user");
	}

	@And("Select user with changed details")
	public void selectUserWithName() {
		waitForElementVisible(driver, page.getUserLocator(textparameters.get("username")), "Wait for " + textparameters.get("username") + " is visible", 10);
		clickElementUsingJavascript(driver, page.getUserElement(textparameters.get("username")), "Select " + textparameters.get("username"));
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

	@And("Confirm password change negative")
	public void confirmPasswordChangeNegative() {
		waitForElementVisible(driver, ConfirmPasswordChangeButton.getLocator(), ConfirmPasswordChangeButton.getDescription(), 10);
		boolean isEnabled = driver.findElement(ConfirmPasswordChangeButton.getLocator()).isEnabled();
		new Validation("Confirm button is enabled", !isEnabled).isTrue();
		ReportExtender.logScreen(driver);
		clickElement(ConfirmPasswordChangeButton.getElement(driver), ConfirmPasswordChangeButton.getDescription());
	}

	@And("Verify if in {string} search bar was filtered only username {string}")
	public void verifyIfInSearchBarWasFilteredUsername(String textfield_input, String username) {
		String getEveryUserElement = ".";
		if ((verifyElementIsPresent(driver, page.getInputLocatorVerify(textfield_input, username), "Wait if meno input contains username " + username)) && (textfield_input.equals("Meno"))) {
			if (verifyElementIsPresent(driver, page.getClientInfoByIndexLocator(name_index, getEveryUserElement), "Get every name of client")) {
				List<WebElement> elements = driver.findElements(page.getClientInfoByIndexLocator(name_index, getEveryUserElement));
				for (WebElement element : elements) {
					new Validation("USERNAME visible on the page", element.getText(), username).stringEquals();
				}
			} else {
				ReportExtender.logWarning("Name " + username + " was not found in search bar " + textfield_input+ " search bar");
			}
		} else if ((verifyElementIsPresent(driver, page.getInputLocatorVerify(textfield_input, username), "Wait if meno input contains username " + username)) && (textfield_input.equals("Priezvisko"))) {
			if (verifyElementIsPresent(driver, page.getClientInfoByIndexLocator(surname_index, getEveryUserElement), "Get every surname of client")) {
				List<WebElement> elements = driver.findElements(page.getClientInfoByIndexLocator(surname_index, getEveryUserElement));
				for (WebElement element : elements) {
					new Validation("USERNAME visible on the page", element.getText(), username).stringEquals();
				}
			} else {
				ReportExtender.logWarning("Surname " + username + " was not found in search bar " + textfield_input + " search bar");
			}
		}
	}

	@And("Clear input {string}")
	public void clearInput(String textfield) {
		waitForElementVisible(driver, page.getInputLocator(textfield), "Wait for textfield " + textfield + " is visible", 10);
		page.getInputElement(textfield).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		ReportExtender.logScreen(driver);
	}

	@And("Remember clients name and surname belong to omama {string}")
	public void rememberNameAndSurnameOfClientsBelongToOmama(String omama_user) {
		waitForFullPageLoad(driver, 10);
		countOfClientsVisibleOnPage = driver.findElements(page.getClientInfoByIndexLocator(name_index, getEveryUserElement)).size();
		if (countOfClientsVisibleOnPage == 0) {
			ReportExtender.logWarning("Omama " + omama_user + "has no CLIENTS added to her");
		} else {
			for (int j = 1, i = 0; i < countOfClientsVisibleOnPage; i++, j++) {
				clientsName[i] = getElementText(page.getClientInfoByIndexElement(name_index, j + getEveryUserElement), "Save " + j + " client name");
			}
			for (int j = 1, i = 0; i < countOfClientsVisibleOnPage; i++, j++) {
				clientsSurname[i] = getElementText(page.getClientInfoByIndexElement(surname_index, j + getEveryUserElement), "Save " + j + " client surname");
			}
		}
		ReportExtender.logScreen(driver);
	}

	@And("Verify clients username belong to {string}")
	public void verifyUsernameOfClientsBelong(String omama_user) {
		waitForElementVisible(driver, page.getClientUsernameLocator(getEveryUserElement), "Wait for element is visible", 10);
		int count = driver.findElements(page.getClientUsernameLocator(getEveryUserElement)).size();
		if (countOfClientsVisibleOnPage == 0) {
			ReportExtender.logWarning("Omama " + omama_user + "has no CLIENTS added to her");
		} else {
			if (countOfClientsVisibleOnPage != count) {
				ReportExtender.logWarning("Omama " + omama_user + " has no the same count of CLIENTS like in Klienti tab");
			} else
				for (int j = 1, i = 0; i < countOfClientsVisibleOnPage; i++, j++) {
					clientsUsername[i] = getElementText(page.getClientUsernameElement(j + getEveryUserElement), "Get " + j + " name of clients");
					String s = clientsUsername[i];
					String[] b = s.split(" ");
					String clientUsername = (b[0] + " " + b[1]).trim();
					new Validation("Compare clients USERNAME", clientUsername, (clientsName[i] + " " + clientsSurname[i])).stringEquals();
				}
		}
		ReportExtender.logScreen(driver);
	}

	@And("Create new client and fill application form")
	public void createNewClientAndFillApplicationForm() {
		waitForElementVisible(driver, page.getInputTextfieldLocator("Meno"), "Wait for input meno visible", 10);
		setElementText(page.getInputTextfieldElement("Meno"), RandomData.generateFirstName(), "Set name into input");
		setElementText(page.getInputTextfieldElement("Priezvisko"), RandomData.generateLastName(), "Set priezvisko into input");
		setElementText(page.getInputTextfieldElement("Prezývka"), RandomData.generateLastName(), "Set prezyvka into input");
		setElementText(page.getInputTextfieldElement("Región"), "test", "Set region into input");
		setElementText(page.getInputTextfieldElement("Ulica"), RandomData.generateStreet(), "Set street into input");
		setElementText(page.getInputTextfieldElement("Mesto"), "Bratislava - Devín", "Set town into input");
		waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		setElementText(page.getInputTextfieldElement("Miesto"), "Nemocnica", "Set street into input");
		textparameters.put("username", getAttributeValue(page.getInputTextfieldElement("Meno"), "Save username into variable"));
		textparameters.put("surname",getAttributeValue(page.getInputTextfieldElement("Priezvisko"), "Save username into variable"));
		textparameters.put("town", getAttributeValue(page.getInputTextfieldElement("Mesto"), "Save town into variable"));
		ReportExtender.logScreen(driver);
		for (int j = 1; j < 6; j++) {
			scrollElementIntoView(driver, page.getDatePickerElement(j));
			waitForElementVisible(driver, page.getDatePickerLocator(j), "Wait for date picker", 10);
			clickElementUsingJavascript(driver, page.getDatePickerElement(j), "Click on date input");
			waitForElementClickable(driver, SelectCurrentDate.getLocator(), SelectCurrentDate.getDescription(), 10);
			clickElementUsingJavascript(driver, SelectCurrentDate.getElement(driver), SelectCurrentDate.getDescription());
			sleep(1000);
		}
		ReportExtender.logScreen(driver);
	}

	@And("Select from AnoNie picker {string} choice {string}")
	public void selectFromAnoNiePickerChoice(String picker_name, String picker_choice) {
		scrollElementIntoView(driver,page.getYesNoPickerElement(picker_name,picker_choice));
		waitForElementVisible(driver, page.getYesNoPickerLocator(picker_name, picker_choice),"Wait for choice picker", 10);
		clickElementUsingJavascript(driver,page.getYesNoPickerElement(picker_name,RandomData.getRandomAnoNie()),"Click on picker choice");
	}

	@And("Fill information about Rodina")
	public void fillInformationAboutRodina() {
		for (int i = 1; i < 3; i++) {
			setElementText(page.getTextfieldIndexElement("Meno a Priezvisko", i), RandomData.generateFirstName(), "Set name into input");
			setElementText(page.getTextfieldIndexElement("Telefón", i), RandomData.generateMobileNumber(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Zdravotný stav", i), RandomData.generateLastName(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Ukončené vzdelanie", i), RandomData.generateLastName(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Trieda", i), RandomData.generateLastName(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Zamestnanie", i), RandomData.generateLastName(), "Set mobil phone into input");
		}
		setElementText(page.getInputTextfieldElement("Počet súrodencov"), RandomData.generateStreetNumber(), "Set number of children into input");
		setElementText(page.getInputTextfieldElement("Dieťa je narodené v poradí"), RandomData.generateStreetNumber(), "Set name into input");
		setElementText(page.getInputTextfieldElement("Jazyk/jazyky, ktorými sa v domácnosti rozpráva"), RandomData.generateStreetNumber(), "Set name into input");
		ReportExtender.logScreen(driver);
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

	@And("Select first row from table")
	public void selectFirstRowFromTable() {
		waitForElementVisible(driver, FirstTableRow.getLocator(), FirstTableRow.getDescription(), 10);
		clickElement(FirstTableRow.getElement(driver), FirstTableRow.getDescription());
	}

	String firstName;
	String lastName;
	@And("Save First and Last Name")
	public void saveFirstAndLastName() {
		firstName = getAttributeValue(NameInput.getElement(driver), NameInput.getDescription());
		lastName = getAttributeValue(SurnameInput.getElement(driver), SurnameInput.getDescription());
	}

	@And("Fill information about Tehotenstvo a porod")
	public void fillInformationAboutTehotenstvoAPorod() {
		scrollElementIntoView(driver, page.getInputTextareaElement("pregnancy"));
		setElementText(page.getInputTextareaElement("pregnancy"), RandomData.generateFirstName(), "Input random text into pregnancy textfield");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Fajčenie počas obdobia tehotenstva", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Alkohol počas obdobia tehotenstva", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Drogy počas obdobia tehotenstva", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		setElementText(page.getInputTextareaElement("medicinePregrancy"), RandomData.getRandomAnoNie(), "Input random Ano/Nie");
		clickElementUsingJavascript(driver, page.getDropdownElement("Vyvolaný"), "Select button");
		ReportExtender.logScreen(driver);
		scrollElementIntoView(driver, page.getInputTextareaElement("childbirthComplications"));
		setElementText(page.getInputTextareaElement("childbirthComplications"), RandomData.generateFirstName(), "Input random text into child complication textfield");
		setElementText(page.getInputTextareaElement("birthWeight"), RandomData.generateStreetNumber(), "Input random number");
		setElementText(page.getInputTextareaElement("birthLength"), RandomData.generateStreetNumber(), "Input random number");
		setElementText(page.getInputTextareaElement("birthHeadCircumference"), RandomData.generateStreetNumber(), "Input random number");
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Kojenie")
	public void fillInformationAboutKojenie() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Kojené dieťa", RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Kojené dieťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		setElementText(page.getInputTextareaElement("breastFeedTime"), RandomData.generateStreetNumber(), "Input random number");
		clickElementUsingJavascript(driver, page.getDropdownElement("Zvoľte"), "Unwrap dropdown Zvolte");
		clickElementUsingJavascript(driver, page.getTabElement("4"), "Select random choice");
		scrollElementIntoView(driver, page.getYesNoPickerElement("Fajčenie počas kojenia", RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Fajčenie počas kojenia", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Alkohol počas kojenia", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Drogy počas kojenia", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		setElementText(page.getInputTextareaElement("medicineBreastFeeding"), RandomData.generateLastName(), "Input random text");
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Psychomotoricky vyvin dietata")
	public void fillInformationAboutPsychomotorickyVyvinDietata() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Drogy počas kojenia",RandomData.getRandomAnoNie()));
		int i = 1;
		while (i < 7) {
			waitForElementClickable(driver, page.getInputDropdownLocator("nevyplnené", 1), "Wait for dropdown is clickable", 10);
			clickElementUsingJavascript(driver, page.getInputDropdownElement("nevyplnené", 1), "Click on dropdown");
			waitForElementClickable(driver, page.getTabIndexLocator("4 mesiace",i), "Wait for choice is visible", 10);
			clickElementUsingJavascript(driver, page.getTabIndexElement("4 mesiace",i), "Click on choice ");
			i++;
		}
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Vseobecne zdravie")
	public void fillInformationAboutVseobecneZdravie() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Alkohol počas prvých 2 rokov dieťaťa", RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Fajčenie počas prvých 2 rokov dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Alkohol počas prvých 2 rokov dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Drogy počas prvých 2 rokov dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		setElementText(page.getInputTextareaElement("medicineNearChildBelowTwoYears"), RandomData.generateLastName(), "Input random text");
		ReportExtender.logScreen(driver);
		setElementText(page.getInputTextareaElement("hospitalization"), RandomData.generateLastName(), "Input random text");
		setElementText(page.getInputTextareaElement("injury"), RandomData.generateLastName(), "Input random text");
		setElementText(page.getInputTextareaElement("healthIssues"), RandomData.generateLastName(), "Input random text");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Povinné očkovania", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Pravidelné prehliadky v pediatrickej ambulancii", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		setElementText(page.getInputTextareaElement("examinationOffice"), RandomData.generateLastName(), "Input random text");
		setElementText(page.getInputTextareaElement("healthCondition"), RandomData.generateLastName(), "Input random text");
		for (int j = 6; j < 7; j++) {
			scrollElementIntoView(driver, page.getDatePickerElement(j));
			waitForElementVisible(driver, page.getDatePickerLocator(j), "Wait for date picker", 10);
			clickElementUsingJavascript(driver, page.getDatePickerElement(j), "Click on date input");
			waitForElementClickable(driver, SelectCurrentDate.getLocator(), SelectCurrentDate.getDescription(), 10);
			clickElementUsingJavascript(driver, SelectCurrentDate.getElement(driver), SelectCurrentDate.getDescription());
		}
		ReportExtender.logScreen(driver);
	}

	@And("Click on {string} button from menu")
	public void clickOnButtonFromMenu(String value) {
		waitForElementVisible(driver,page.getErrorMessageLocator(value),"Wait for "+ value + " button",10);
		clickElementUsingJavascript(driver,page.getErrorMessageElement(value),"Click on " + value + " button");
	}

	@Then("Wait for changes is processed")
	public void waitForChangesIsProcessed() {
		sleep(2000);
	}

	@And("Select first user contains {string}")
	public void selectFirstUser(String value) {
		waitForElementVisible(driver,page.getFirstUserLocator(value),"Wait for first user contains " + value,10);
		clickElementUsingJavascript(driver, page.getFirstUserElement(value), "Select first user contains " + value);
	}

	@And("Verify user was deleted")
	public void verifyUserWasDeleted() {
		if(!verifyElementIsPresent(driver, page.getUserLocator(textparameters.get("username")), "Find if element was deleted"))
			{ReportExtender.logPass("Username " + (textparameters.get("username") + " was deleted"));}
		else
			ReportExtender.logWarning("Username " + (textparameters.get("username") + " was not deleted"));
	}

	@And("Fill information about Obavy")
	public void fillInformationAboutObavy() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Spánok dieťaťa", RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Zrak dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Sluch dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Spánok dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Rast a výživu dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Zdravie dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Celkový vývin dieťaťa", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		ReportExtender.logScreen(driver);
	}


	@And("Fill information about Byvanie")
	public void fillInformationAboutByvanie() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Varíte v rovnakej izbe ako spáva dieťa?", RandomData.getRandomAnoNie()));
		setElementText(page.getInputTextareaElement("houseMaterial"), RandomData.generateLastName(), "Input random text");
		setElementText(page.getInputTextareaElement("houseRooms"), RandomData.generateRandomNumber(), "Input random text");
		setElementText(page.getInputTextareaElement("houseInhabitants"), RandomData.generateRandomNumber(), "Input random text");
		setElementText(page.getInputTextareaElement("houseFuel"), RandomData.generateLastName(), "Input random text");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Varíte v rovnakej izbe ako spáva dieťa?", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Osobne udaje")
	public void fillInformationAboutskolaAOsobneUdaje() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Fotografie v tlačených materiáloch", RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Fotografie v aplikácii", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Fotografie na sociálnych médiách a webe", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Fotografie v tlačených materiáloch", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Videá a reportáže", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Pediatrické skríningy", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Meranie vývinu", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Študijný a kariérny vývoj", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Neskoršie kontaktovanie a meranie dopadu programu", RandomData.getRandomAnoNie()), "Click random Ano/nie");
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Skola")
	public void fillInformationAboutSkola() {
		scrollElementIntoView(driver, page.getYesNoPickerElement("Materská škola", RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Materská škola", "Áno"), "Click random Ano/nie");
		setElementText(page.getInputTextfieldElement("Názov materskej školy"), RandomData.generateFirstName(), "Set name into input");
		clickElementUsingJavascript(driver, page.getYesNoPickerElement("Základná škola", "Áno"), "Click random Ano/nie");
		setElementText(page.getInputTextfieldElement("Názov základnej školy"), RandomData.generateFirstName(), "Set name into input");
		clickElementUsingJavascript(driver,page.getDropdownElement("Špeciálna"),"Select specialna skola");
		ReportExtender.logScreen(driver);
		for (int j = 7; j < 11; j++) {
			scrollElementIntoView(driver, page.getDatePickerElement(j));
			waitForElementVisible(driver, page.getDatePickerLocator(j), "Wait for date picker", 10);
			clickElementUsingJavascript(driver, page.getDatePickerElement(j), "Click on date input");
			waitForElementClickable(driver, SelectCurrentDate.getLocator(), SelectCurrentDate.getDescription(), 10);
			clickElementUsingJavascript(driver, SelectCurrentDate.getElement(driver), SelectCurrentDate.getDescription());
			sleep(1000);
		}
		ReportExtender.logScreen(driver);
	}

	@And("Verify client details")
	public void verifyClientDetails() {
		sleep(1000);
		new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username") + " " + textparameters.get("surname")).stringEquals();
		new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@And("Verify properly ordered gallery")
	public void verifyProperlyOrderedGallery() {
		sleep(1000);
		boolean properlyOrderedGallery = true;
		boolean properlyOrderedPictures = true;
		int year = 2000;
		int galleryCount = driver.findElements(page.getGalleryCountLocator(getEveryUserElement)).size();
		if(galleryCount > 0){
			// If galleries are present verify properly ordered according years
			List<WebElement> galleryYears = driver.findElements(page.getGalleryCountLocator(getEveryUserElement));
			for (WebElement element : galleryYears) {
				int galleryEle = Integer.parseInt(element.getText());
				if (galleryEle > year)
				{
					year = galleryEle;
				}
				else properlyOrderedGallery = false;
			}
			new Validation("Galleries are properly ordered", properlyOrderedGallery).isTrue();
			ReportExtender.logScreen(driver);
			for (int i = 1; i <= galleryCount; i++) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern( "[dd][d]. [MM][M]. uuuu HH:mm" );
				String startInput = "01. 01. 2030 00:00";
				LocalDateTime stop = LocalDateTime.parse(startInput, dateFormat);
				clickElementUsingJavascript(driver, page.getGalleryCountElement(i+getEveryUserElement),"Unwrap galery");
				sleep(1000);
				int pictureCount = driver.findElements(page.getPictureCountLocator(getEveryUserElement)).size();
				if(pictureCount > 0) {
					// If pictures are present verify properly ordered according dates
					for (int j = 1; j <= pictureCount; j++) {
						String addPictureDate = driver.findElement(page.getPictureCountLocator(j+getEveryUserElement)).getText();
						LocalDateTime start = LocalDateTime.parse(addPictureDate, dateFormat);
						if (stop.isAfter(start) || stop.equals(start))
						{
							stop = start;
						}
						else properlyOrderedPictures = false;
					}
				ReportExtender.logScreen(driver);
				}
				else {ReportExtender.logInfo("USER HAVE NO PICTURES IN GALLERY");
				ReportExtender.logScreen(driver);}
				clickElementUsingJavascript(driver, page.getDropdownElement("Galéria"),"Close galery" );
				sleep(1000);
			}
			new Validation("Pictures are properly ordered", properlyOrderedPictures).isTrue();
		}
		else { ReportExtender.logPass("USER HAVE NO GALLERIES");
		ReportExtender.logScreen(driver);}
	}

	@And("In profil page select tab {string}")
	public void inProfilPageSelecttab(String tab) {
		waitForElementClickable(driver, page.getProfilTabLocator(tab), "Wait for choice " + tab + " is visible", 10);
		clickElementUsingJavascript(driver,page.getProfilTabElement(tab), "Click on choice " + tab);
	}

	@And("Verify tab {string} is selected")
	public void verifyTabLekcieIsSelected(String tab) {
		new Validation("Verify tab " + tab + " is selected", page.getProfilTabElement(tab).getAttribute("class"), "active").stringEquals();
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Activity")
	public void fillInformationAboutActivity() {
		textparameters.put("childrenAidPhoto1","https://live.staticflickr.com/65535/50300582746_0da48987d3_z.jpg");
		textparameters.put("childrenAidPhoto2","https://live.staticflickr.com/65535/50304048661_78b946ca28_z.jpg");
		textparameters.put("childrenActivityPhoto1","https://live.staticflickr.com/65535/50300743352_1775b8e1db_z.jpg");
		textparameters.put("childrenActivityPhoto2","https://live.staticflickr.com/65535/50300833902_72e8958afb_z.jpg");
		textparameters.put("urlChildrenActivityLink","https://youtu.be/tOAI2th94z8");

		setElementText(page.getInputElement("Názov aktivity"), RandomData.generateFirstName(), "Set activity name into textarea");
		setElementText(page.getInputTextfieldElement("Číslo lekcie"), RandomData.generateStreetNumber(), "Set random activity number into textarea");
		ReportExtender.logScreen(driver);
		setElementText(page.getActivityInputElement("Ciel aktivity"), RandomData.generateFirstName(), "Set random text into activity textarea");
		setElementText(page.getActivityInputElement("Pomôcky"), RandomData.generateFirstName(), "Set random text to Pomocky textarea");
		setElementText(page.getActivityInputElement("Priebeh"), RandomData.generateFirstName(), "Set random text to Priebeh textarea");
		setElementText(page.getInputTextfieldElement("Mesiac"), RandomData.generateRandomNumber(), "Set random number into Mesiac textarea");
		setElementText(page.getInputTextfieldElement("Týždeň"), RandomData.generateRandomNumber(), "Set random number into Tyzden textarea");
		setElementText(page.getTextfieldIndexElement("url obrazku", 1), textparameters.get("childrenAidPhoto1"), "Set image into textarea");
		clickElementUsingJavascript(driver,page.getPlusButtonElement(1),"Accept your select");
		setElementText(page.getTextfieldIndexElement("url obrazku", 1), textparameters.get("childrenAidPhoto2"), "Set image into textarea");
		clickElementUsingJavascript(driver,page.getPlusButtonElement(1),"Accept your select");
		setElementText(page.getTextfieldIndexElement("url obrazku", 2), textparameters.get("childrenActivityPhoto1"), "Set image into textarea");
		clickElementUsingJavascript(driver,page.getPlusButtonElement(2),"Accept your select");
		setElementText(page.getTextfieldIndexElement("url obrazku", 2), textparameters.get("childrenActivityPhoto2"), "Set image into textarea");
		clickElementUsingJavascript(driver,page.getPlusButtonElement(2),"Accept your select");
		setElementText(page.getTextfieldIndexElement("url youtube videa", 1), textparameters.get("urlChildrenActivityLink"), "Set image into textarea");
		clickElementUsingJavascript(driver,page.getPlusButtonElement(3),"Accept your select");
	}

	@And("Verify activity details")
	public void verifyActivityDetails() {
		waitForElementClickable(driver,page.getButtonLocator("Zmazať"),"Wait for zmazat button is visible",10);
		new Validation("Verify activity name and number", getElementText(ActivityName.getElement(driver), ActivityName.getDescription()), (textparameters.get("activityName") + " " + textparameters.get("activityNumber"))).contains();
		new Validation("Verify activity goal", getElementText(page.getActivityTextElement(6),"Activity goal text"), textparameters.get("activityGoal")).stringEquals();
		new Validation("Verify activity aids", getElementText(page.getActivityTextElement(7),"Activity aids text"), textparameters.get("activityAids")).stringEquals();
		new Validation("Verify activity process", getElementText(page.getActivityTextElement(11),"Activity process text").trim(), textparameters.get("activityProcess")).contains();
		ReportExtender.logScreen(driver);
	}

	@And("Click ESC button")
	public void clickESCButton() {
		ReportExtender.logScreen(driver);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}

	@And("Save activity details")
	public void saveActivityDetails() {
		textparameters.put("activityName", getAttributeValue(page.getInputTextfieldElement("Názov aktivity"), "Save name activity into variable"));
		textparameters.put("activityNumber", getAttributeValue(page.getInputTextfieldElement("Číslo lekcie"), "Save activity number into variable"));
		textparameters.put("activityGoal", getElementText(page.getActivityInputElement("Ciel aktivity"), "Save activity goal into variable"));
		textparameters.put("activityAids",getElementText(page.getActivityInputElement("Pomôcky"), "Save activity aids into variable"));
		textparameters.put("activityProcess", getElementText(page.getActivityInputElement("Priebeh"),"Save activity process into variable" ));
		ReportExtender.logScreen(driver);
	}

	@And("Find new created activity")
	public void findNewCreatedActivity() {
		waitForElementVisible(driver, page.getInputLocator("Názov aktivity"), "Wait for Nazov aktivity input is visible", 10);
		setElementText(page.getInputElement("Názov aktivity"), textparameters.get("activityName"), "Set " + textparameters.get("username") + " into input");
		waitForElementVisible(driver, page.getUserLocator(textparameters.get("activityName")), "Wait for activity visible", 10);
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver, page.getUserElement(textparameters.get("activityName")), "Click on activity");
	}

	@And("Save activity attributes")
	public void saveActivityAttributes() {
		waitForElementVisible(driver,page.getActivityAttributeLocator(1),"Wait for activity visible",10);
		textparameters.put("activityName", getElementText(page.getActivityAttributeElement(1), "Save activity name into variable"));
		textparameters.put("activityNumber", getElementText(page.getActivityAttributeElement(2), "Save activity number into variable"));
		textparameters.put("activityMonth", getElementText(page.getActivityAttributeElement(3), "Save activity month into variable"));
		textparameters.put("activityWeek",getElementText(page.getActivityAttributeElement(4), "Save activity week into variable"));
	}

	@And("Verify if name attribute filter desired activity")
	public void verifyIfNameAttributeFilterDesiredActivity() {
		setElementText(page.getInputElement("Názov aktivity"), textparameters.get("activityName"), "Set " + textparameters.get("activityName") + " into input");
		new Validation("Verify activity name", getElementText(page.getActivityAttributeElement(1),"Get activity name"), (textparameters.get("activityName"))).stringEquals();
		ReportExtender.logScreen(driver);
		page.getInputElement("Názov aktivity").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	@And("Verify if number attribute filter desired activity")
	public void verifyIfNumberAttributeFilterDesiredActivity() {
		setElementText(page.getInputElement("Číslo lekcie"), textparameters.get("activityNumber"), "Set " + textparameters.get("activityNumber") + " into input");
		new Validation("Verify activity name", getElementText(page.getActivityAttributeElement(1),"Get activity name"), (textparameters.get("activityName"))).stringEquals();
		ReportExtender.logScreen(driver);
		page.getInputElement("Číslo lekcie").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	@And("Verify if month attribute filter desired activity")
	public void verifyIfMonthAttributeFilterDesiredActivity() {
		setElementText(page.getInputElement("Mesiac"), textparameters.get("activityMonth"), "Set " + textparameters.get("activityMonth") + " into input");
		new Validation("Verify activity name", getElementText(page.getActivityAttributeElement(1),"Get activity name"), (textparameters.get("activityName"))).stringEquals();
		ReportExtender.logScreen(driver);
		page.getInputElement("Mesiac").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	@And("Verify if week attribute filter desired activity")
	public void verifyIfWeekAttributeFilterDesiredActivity() {
		setElementText(page.getInputElement("Týždeň"), textparameters.get("activityWeek"), "Set " + textparameters.get("activityWeek") + " into input");
		new Validation("Verify activity name", getElementText(page.getActivityAttributeElement(1),"Get activity name"), (textparameters.get("activityName"))).stringEquals();
		ReportExtender.logScreen(driver);
		page.getInputElement("Týždeň").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	@And("Verify if tab Nazov properly sorting data")
	public void verifyIfTabNazovProperlySortingData() {
		clickElementUsingJavascript(driver,page.getAscOrderElement(1),"Set ascending order");
		String[] arrayOrdered = new String[10];
		String[] ascOrdered = new String[10];
		int i = 0;
		// Verify sorting in ascending order
		List<WebElement> ASC_ORDER = driver.findElements(ActivityList.getLocator());
		for ( WebElement element : ASC_ORDER ){
			ascOrdered[i] = element.getText().substring(0,4);
			arrayOrdered[i] = element.getText().substring(0,4);
			i = i + 1;
		}
		Arrays.sort(ascOrdered, String.CASE_INSENSITIVE_ORDER);
		for (int a = 0; a < arrayOrdered.length ; a++){
			if (!ascOrdered[a].equals(arrayOrdered[a])){
				ReportExtender.logWarning("Data is not in ascending order");
			}
		}
		ReportExtender.logScreen(driver);
		// Verify sorting in descending order
//		clickElementUsingJavascript(driver,page.getDescOrderElement(1),"Set descending order");
//		int j = 0;
//		List<WebElement> DESC_ORDER = driver.findElements(ActivityList.getLocator());
//		for ( WebElement element : DESC_ORDER ){
//			ascOrdered[j] = element.getText();
//			arrayOrdered[j] = element.getText();
//			j = j + 1;
//		}
//		Arrays.sort(ascOrdered, Collections.reverseOrder());
//		for (int a = 0; a < arrayOrdered.length ; a++){
////			ReportExtender.logInfo(ascOrdered[a]);
////			ReportExtender.logInfo(arrayOrdered[a]);
//			if (!ascOrdered[a].equals(arrayOrdered[a])){
//				ReportExtender.logWarning("Data is not in descending order");
//			}
//		}
//		ReportExtender.logScreen(driver);
	}

	@And("Verify if tabs Číslo lekcie, Mesiac and Týždeň properly sorting data")
	public void verifyIfTabscisloLekcieMesiacAndTyzdenProperlySortingData() {
		for (int i = 2; i < 5 ; i++ ) {
			clickElementUsingJavascript(driver, page.getAscOrderElement(i), "Set ascending order");
			Float[] arrayOrdered = new Float[10];
			Float[] ascOrdered = new Float[10];
			int j = 0;
			// Verify sorting in ascending order
			List<WebElement> ASC_ORDER = driver.findElements(ActivityList.getLocator());
			for ( WebElement element : ASC_ORDER ){
				String ele = element.getText();
				float number = Float.parseFloat(ele);
				ascOrdered[j] = number;
				arrayOrdered[j] = number;
				j = j + 1;
			}
			Arrays.sort(ascOrdered);
			for (int a = 0; a < arrayOrdered.length ; a++){
				if (!ascOrdered[a].equals(arrayOrdered[a])){
					ReportExtender.logWarning("Data is not in ascending order");
				}
			}
			ReportExtender.logScreen(driver);
			// Verify sorting in descending order
			clickElementUsingJavascript(driver,page.getDescOrderElement(i),"Set descending order");
			int k = 0;
			List<WebElement> DESC_ORDER = driver.findElements(ActivityList.getLocator());
			for ( WebElement element : DESC_ORDER ){
				String ele = element.getText();
				float number = Float.parseFloat(ele);
				ascOrdered[k] = number;
				arrayOrdered[k] = number;
				k = k + 1;
			}
			Arrays.sort(ascOrdered, Collections.reverseOrder());
			for (int a = 0; a < arrayOrdered.length ; a++){
				if (!ascOrdered[a].equals(arrayOrdered[a])){
					ReportExtender.logWarning("Data is not in descending order");
				}
			}
			ReportExtender.logScreen(driver);
		}
	}

	@And("Change activity details")
	public void changeActivityDetails() {
		setElementText(page.getInputElement("Názov aktivity"), RandomData.generateFirstName(), "Set activity name into textarea");
		setElementText(page.getInputTextfieldElement("Číslo lekcie"), RandomData.generateStreetNumber(), "Set random activity number into textarea");
		setElementText(page.getActivityInputElement("Ciel aktivity"), RandomData.generateFirstName(), "Set random text into activity textarea");
		setElementText(page.getActivityInputElement("Pomôcky"), RandomData.generateFirstName(), "Set random text to Pomocky textarea");
		setElementText(page.getActivityInputElement("Priebeh"), RandomData.generateFirstName(), "Set random text to Priebeh textarea");
	}
}
