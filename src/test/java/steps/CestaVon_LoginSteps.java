package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_CommonPage;
import page.CestaVon_UsersPage;
import runner.TestRunner;
import utility.*;

import java.util.HashMap;
import java.util.List;

import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_LoginPage.loginPageItems.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;
import static page.CestaVon_UsersPage.adminMainPageItems.RolaButton;
import static page.CestaVon_UsersPage.adminMainPageItems.RolaOption;

public class CestaVon_LoginSteps extends TestStepActions {

	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
	CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
	String Username, Email, PhoneNumber, Town, Status, OmamaLevel, KidsCount, JobSpecification,
			OnLevelSince, OmamaMentor = "";
	int countOfClientsVisibleOnPage = 10;
	int name_index = 2;
	int surname_index = 3;
	String[] clientsName = new String[countOfClientsVisibleOnPage];
	String[] clientsSurname = new String[countOfClientsVisibleOnPage];
	String[] clientsUsername = new String[countOfClientsVisibleOnPage];
	String getEveryUserElement = ".";

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
		if (driver.findElements(OdhlasitButton.getLocator()).size() > 0) {
			Log.info(SAUCEDEMO_PageMapper.FAIL_MESSAGE);
			ReportExtender.logPass("Validating " + OdhlasitButton.getDescription() + ": </br>" + ReportExtender.addMarkup("FAIL") + SAUCEDEMO_PageMapper.FAIL_MESSAGE_ODHLASIT_BUTTON);
		} else {
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
		ReportExtender.logScreen(driver);
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
		Username = getAttributeValue(page.getInputTextfieldElement("Priezvisko"), "Save username into variable");
		Email = getAttributeValue(EmailInput.getElement(driver), EmailInput.getDescription());
		PhoneNumber = getAttributeValue(page.getInputTextfieldElement("cislo"), "Save phone number into variable");
		Town = getAttributeValue(page.getInputTextfieldElement("Mesto"), "Save town into variable");
		ReportExtender.logScreen(driver);
	}

	@And("Verify {string} tab is active")
	public void verifyTabIsActive(String value) {
		waitForElementVisible(driver, SelectedTab.getLocator(), "Wait for " + value + " tab is visible", 10);
		new Validation("Verify Tab is active", getElementText(SelectedTab.getElement(driver), SelectedTab.getDescription()), value).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@Then("Verify details new created user")
	public void verifyDetailsNewCreatedUser() {
		sleep(2000);
//		waitForElementVisible(driver, GetUserName.getLocator(), GetUserName.getDescription(), 10);
		new Validation("Verify USERNAME", getElementText(GetUserName.getElement(driver), GetUserName.getDescription()), Username).stringEquals();
		new Validation("Verify TOWN", getElementText(GetUserTown.getElement(driver), GetUserTown.getDescription()), Town).stringEquals();
		if (driver.findElements(GetUserEmailAdmin.getLocator()).size() > 0) {
			new Validation("Verify EMAIL", getElementText(GetUserEmailAdmin.getElement(driver), GetUserEmailAdmin.getDescription()), Email).stringEquals();
		}
		if (driver.findElements(GetUserPhoneAdmin.getLocator()).size() > 0) {
			new Validation("Verify PHONE NUMBER", getElementText(GetUserPhoneAdmin.getElement(driver), GetUserPhoneAdmin.getDescription()), PhoneNumber).stringEquals();
		}
		if (driver.findElements(GetUserEmailMentor.getLocator()).size() > 0) {
			new Validation("Verify EMAIL", getElementText(GetUserEmailMentor.getElement(driver), GetUserEmailMentor.getDescription()), Email).contains();
		}
		if (driver.findElements(GetUserPhoneMentor.getLocator()).size() > 0) {
			new Validation("Verify PHONE NUMBER", getElementText(GetUserPhoneMentor.getElement(driver), GetUserPhoneMentor.getDescription()), PhoneNumber).contains();
		}
		if (driver.findElements(GetStatus.getLocator()).size() > 0) {
			new Validation("Verify user STATUS", StringUtils.chop(getElementText(GetStatus.getElement(driver), GetStatus.getDescription())), StringUtils.chop(Status)).stringEquals();
		}
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

	@And("Find user {string}")
	public void findUser(String value) {
		waitForFullPageLoad(driver, 10);
		while (driver.findElements(page.getUserLocator(value)).size() == 0) {
			if (verifyElementIsPresent(driver, NextPageButtonDisabled.getLocator(), NextPageButtonDisabled.getDescription())) {
				ReportExtender.logFail("USER WAS NOT FOUND");
				ReportExtender.logScreen(driver);
				driver.close();
			}
			waitForElementVisible(driver, NextPageButton.getLocator(), NextPageButton.getDescription(), 10);
			clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
			waitForFullPageLoad(driver, 10);
		}
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver, page.getUserElement(value), "Click on desired user");
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
		Username = getAttributeValue(page.getInputTextfieldElement("Priezvisko"), "Save username into variable");
		Email = getAttributeValue(page.getInputTextfieldElement("Email"), "Save email into variable");
		PhoneNumber = getAttributeValue(page.getInputTextfieldElement("Telefon"), "Save phone number into variable");
		Town = getAttributeValue(page.getInputTextfieldElement("Mesto"), "Save town into variable");
		Status = getElementText(GetUserStatus.getElement(driver), GetUserStatus.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Click on Rola and choose option {string}")
	public void clickOnRolaAndChooseOption(String value) {
		CestaVon_UsersPage page = new CestaVon_UsersPage(driver);
		waitForElementVisible(driver, RolaButton.getLocator(), RolaButton.getDescription(), 15);
		clickElement(RolaButton.getElement(driver), RolaButton.getDescription());
		waitForElementVisible(driver, page.getRolaOptionLocator(value), RolaOption.getDescription(), 15);
		clickElement(page.getRolaOptionElement(value), RolaOption.getDescription());
	}

	@And("Set Search Field {string} to {string}")
	public void setSearchFieldTo(String searchField, String searchText) {
		waitForElementVisible(driver, page.getInputLocator(searchField), "Wait for search field visible", 15);
		setElementText(page.getInputElement(searchField), searchText, "Setting element text");
	}

	@Then("Verify Meno {string} and Rola {string} in filtered table")
	public void verifyMenoAndRolaInFilteredTable(String meno, String rola) {
		boolean properlyFiltered = true;
		int menoIndex = 0;
		int rolaIndex = 0;
		//getting number of columns in table
		int numberOfColumns = driver.findElements(By.xpath("//thead//th")).size();
		//getting number of rows in table
		int numberOfRows = driver.findElements(By.xpath("//tbody/tr")).size();

		//getting indexes of Meno and Rola columns
		for (int x = 1; x <= numberOfColumns; x++) {
			if (driver.findElement(By.xpath("(//thead//th/span/div/span[1])[" + x + "]")).getText().startsWith("Meno")) {
				menoIndex = x;
			} else if (driver.findElement(By.xpath("(//thead//th/span/div/span[1])[" + x + "]")).getText().startsWith("Rola")) {
				rolaIndex = x;
			}
		}

		//verifying filtering
		for (int x = 1; x <= numberOfRows; x++) {
			//column Meno
			String menoString = driver.findElement(By.xpath("//tbody/tr[" + x + "]/td[" + menoIndex + "]/div")).getText();
			if (!menoString.toLowerCase().startsWith(meno.toLowerCase())) {
				properlyFiltered = false;
			}
			//column Rola
			String rolaString = driver.findElement(By.xpath("//tbody/tr[" + x + "]/td[" + rolaIndex + "]")).getText();
			if (!rolaString.startsWith(rola)) {
				properlyFiltered = false;
			}

			ReportExtender.logInfo("Rozmer:" + numberOfRows + "x" + numberOfColumns + ", index Meno: " + menoIndex + ", index Rola: " + rolaIndex + ", riadok: " + x + ", meno je: " + menoString + ", a rola je:" + rolaString);
		}

		new Validation("Table is properly filtered", properlyFiltered).isTrue();
	}

	@And("Change user details to original state")
	public void changeUserDetailsToOriginalState() {
		waitForElementVisible(driver, page.getInputTextfieldLocator("Priezvisko"), "Wait for meno input is visible", 10);
		setElementText(page.getInputTextfieldElement("Priezvisko"), Username, "Set saved username into input");
//		setElementText(page.getInputTextfieldElement("Telefon"),PhoneNumber, "Set saved phone into input");
		setElementText(page.getInputTextfieldElement("Email"), Email, "Set saved email into input");
		setElementText(page.getInputTextfieldElement("Mesto"), Town, "Set saved town into input");
		waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		clickElementUsingJavascript(driver, page.getDropdownElement(Status), "Set saved status");
		ReportExtender.logScreen(driver);
	}

	@Then("Go back to previous page")
	public void goBackToPreviousPage() {
		waitForElementClickable(driver, SpatButton.getLocator(), SpatButton.getDescription(), 10);
		clickElementUsingJavascript(driver, SpatButton.getElement(driver), SpatButton.getDescription());
	}

	@And("Find user with changed details")
	public void findUserWithChangedDetails() {
		waitForElementVisible(driver, page.getInputLocator("Meno"), "Wait for Meno input is visible", 10);
		setElementText(page.getInputElement("Meno"), Username, "Set " + Username + " into input");
		if (!verifyElementIsPresent(driver, page.getUserLocator(Username), "Verify username is visible")) {
			ReportExtender.logWarning("USERNAME WAS DELETED OR DOES NOT EXIST");
			ReportExtender.logScreen(driver);
			return;
		}
		ReportExtender.logScreen(driver);
	}

	@And("Input into {string} search bar username {string}")
	public void searchForUsername(String textfield, String username) {
		waitForElementVisible(driver, page.getInputLocator(textfield), "Wait for " + textfield + " input is visible", 10);
		setElementText(page.getInputElement(textfield), username, "Set " + username + " into input");
		if (!verifyElementIsPresent(driver, page.getUserLocator(username), "Verify username is visible")) {
			ReportExtender.logWarning("USERNAME WAS DELETED OR DOES NOT EXIST");
			ReportExtender.logScreen(driver);
			return;
		}
		ReportExtender.logScreen(driver);
	}

	@And("Select user with name {string}")
	public void selectUserWithName(String Username) {
		waitForElementVisible(driver, page.getUserLocator(Username), "Wait for username", 10);
		clickElementUsingJavascript(driver, page.getUserElement(Username), "Click on desired user");
	}

	@And("Select user with changed details")
	public void selectUserWithName() {
		waitForElementVisible(driver, page.getUserLocator(Username), "Wait for " + Username + " is visible", 10);
		clickElementUsingJavascript(driver, page.getUserElement(Username), "Select " + Username);
	}

	@And("Select user with name {string} and remember his information")
	public void selectUserWithNameAndRememberHisInformation(String value) {
		CestaVon_UsersPage page = new CestaVon_UsersPage(driver);
		waitForElementVisible(driver, page.getRowByNameLocator(value), "Waiting for row visible", 10);
		ReportExtender.logScreen(driver);
		Username = getElementText(page.getNameFromRowByNameElement(value), "Remembering Name");
		Email = getElementText(page.getEmailFromRowByNameElement(value), "Remembering Email");
		PhoneNumber = getElementText(page.getPhoneNumberFromRowByNameElement(value), "Remembering Phone Number");
		Town = getElementText(page.getTownFromRowByNameElement(value), "Remembering City");
		clickElement(page.getRowByNameElement(value), "Opening profile " + value + ".");
	}

	@Then("Verify all information is visible")
	public void verifyAllInformationIsVisible() {
		waitForElementVisible(driver, SpatButton.getLocator(), SpatButton.getDescription(), 10);
		ReportExtender.logScreen(driver);
		new Validation("Name visible in profile", getElementText(GetUserName.getElement(driver), GetUserName.getDescription()), Username).stringEquals();
		new Validation("Phone Number visible in profile", getElementText(GetUserPhoneAdmin.getElement(driver), GetUserPhoneAdmin.getDescription()), PhoneNumber).stringEquals();
		new Validation("Email visible in profile", getElementText(GetUserEmailAdmin.getElement(driver), GetUserEmailAdmin.getDescription()), Email).stringEquals();
		new Validation("Town visible in profile", getElementText(GetUserTown.getElement(driver), GetUserTown.getDescription()), Town).stringEquals();
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
				ReportExtender.logWarning("Name " + username + " was not found in " + textfield_input + " search bar");
			}
		} else if ((verifyElementIsPresent(driver, page.getInputLocatorVerify(textfield_input, username), "Wait if meno input contains username " + username)) && (textfield_input.equals("Priezvisko"))) {
			if (verifyElementIsPresent(driver, page.getClientInfoByIndexLocator(surname_index, getEveryUserElement), "Get every surname of client")) {
				List<WebElement> elements = driver.findElements(page.getClientInfoByIndexLocator(surname_index, getEveryUserElement));
				for (WebElement element : elements) {
					new Validation("USERNAME visible on the page", element.getText(), username).stringEquals();
				}
			} else {
				ReportExtender.logWarning("Surname " + username + " was not found in " + textfield_input + " search bar");
			}
		}
		ReportExtender.logScreen(driver);
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
				clientsName[i] = getElementText(page.getClientInfoByIndexElement(name_index, j + getEveryUserElement), "Get " + j + " name of clients");
			}
			for (int j = 1, i = 0; i < countOfClientsVisibleOnPage; i++, j++) {
				clientsSurname[i] = getElementText(page.getClientInfoByIndexElement(surname_index, j + getEveryUserElement), "Get " + j + " surname of clients");
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
		setElementText(page.getInputTextfieldElement("Meno"),RandomData.generateFirstName(), "Set name into input");
		setElementText(page.getInputTextfieldElement("Priezvisko"),RandomData.generateLastName(), "Set priezvisko into input");
		setElementText(page.getInputTextfieldElement("Prezývka"), RandomData.generateLastName(), "Set prezyvka into input");
		setElementText(page.getInputTextfieldElement("Región"), "test", "Set region into input");
		setElementText(page.getInputTextfieldElement("Ulica"),RandomData.generateStreet(), "Set street into input");
		setElementText(page.getInputTextfieldElement("Mesto"), "Bratislava - Devín", "Set town into input");
		waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		setElementText(page.getInputTextfieldElement("Miesto"), "Nemocnica", "Set street into input");
		ReportExtender.logScreen(driver);
		for (int j = 1; j < 6; j++) {
			scrollElementIntoView(driver, page.getDatePickerElement(j));
			waitForElementVisible(driver, page.getDatePickerLocator(j), "Wait for date picker", 10);
			clickElementUsingJavascript(driver, page.getDatePickerElement(j), "Click on date input");
			waitForElementClickable(driver, SelectCurrentDate.getLocator(), SelectCurrentDate.getDescription(), 10);
			clickElementUsingJavascript(driver, SelectCurrentDate.getElement(driver), SelectCurrentDate.getDescription());
			sleep(1000);
			ReportExtender.logScreen(driver);
		}
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
			setElementText(page.getTextfieldIndexElement("Meno a Priezvisko",i),RandomData.generateFirstName(), "Set name into input");
			setElementText(page.getTextfieldIndexElement("Telefón",i),RandomData.generateMobileNumber(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Zdravotný stav",i),RandomData.generateLastName(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Ukončené vzdelanie",i),RandomData.generateLastName(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Trieda",i),RandomData.generateLastName(), "Set mobil phone into input");
			setElementText(page.getTextfieldIndexElement("Zamestnanie",i),RandomData.generateLastName(), "Set mobil phone into input");
			ReportExtender.logScreen(driver);
		}
		setElementText(page.getInputTextfieldElement("Počet súrodencov"),RandomData.generateStreetNumber(),"Set number of children into input");
		setElementText(page.getInputTextfieldElement("Dieťa je narodené v poradí"),RandomData.generateStreetNumber(), "Set name into input");
		setElementText(page.getInputTextfieldElement("Jazyk/jazyky, ktorými sa v domácnosti rozpráva"),RandomData.generateStreetNumber(), "Set name into input");
		ReportExtender.logScreen(driver);
	}

	@Then("Save omama specification")
	public void saveomamaSpecification() {
		OmamaMentor = getElementText(page.getOmamaSpecificationElement(1),"Get omama mentor");
		OmamaLevel = getElementText(page.getOmamaSpecificationElement(4),"Get omama level");
		KidsCount = getElementText(page.getOmamaSpecificationElement(5),"Get kids count");
		JobSpecification = getElementText(page.getOmamaSpecificationElement(6),"Get job specification");
		OnLevelSince = getAttributeValue(OnLevelSinceDate.getElement(driver),OnLevelSinceDate.getDescription());
	}

	@And("Verify omama specification was changed")
	public void verifyomamaSpecificationWasChanged() {
		waitForElementVisible(driver, OnLevelSinceDate.getLocator(),"Wait for date picker",10);
		new Validation("Verify omama mentor", getElementText(page.getOmamaSpecificationElement(1),""), OmamaMentor).stringEquals();
		new Validation("Verify omama level", getElementText(page.getOmamaSpecificationElement(4),""), OmamaLevel).stringEquals();
		new Validation("Verify omama kids", getElementText(page.getOmamaSpecificationElement(5),""), KidsCount).stringEquals();
		new Validation("Verify omama job specification", getElementText(page.getOmamaSpecificationElement(6),""), JobSpecification).stringEquals();
		new Validation("Verify omama first date", getAttributeValue(OnLevelSinceDate.getElement(driver),""), OnLevelSince).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Tehotenstvo a porod")
	public void fillInformationAboutTehotenstvoAPorod() {
		scrollElementIntoView(driver,page.getInputTextareaElement("pregnancy"));
		setElementText(page.getInputTextareaElement("pregnancy"),RandomData.generateFirstName(),"Input random text into pregnancy textfield");
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Fajčenie počas obdobia tehotenstva",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Alkohol počas obdobia tehotenstva",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Drogy počas obdobia tehotenstva",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		setElementText(page.getInputTextareaElement("medicinePregrancy"),RandomData.getRandomAnoNie(),"Input random Ano/Nie");
		clickElementUsingJavascript(driver,page.getDropdownElement("Vyvolaný"),"Select button");
		ReportExtender.logScreen(driver);
		scrollElementIntoView(driver,page.getInputTextareaElement("childbirthComplications"));
		setElementText(page.getInputTextareaElement("childbirthComplications"),RandomData.generateFirstName(),"Input random text into child complication textfield");
		setElementText(page.getInputTextareaElement("birthWeight"),RandomData.generateStreetNumber(),"Input random number");
		setElementText(page.getInputTextareaElement("birthLength"),RandomData.generateStreetNumber(),"Input random number");
		setElementText(page.getInputTextareaElement("birthHeadCircumference"),RandomData.generateStreetNumber(),"Input random number");
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Kojenie")
	public void fillInformationAboutKojenie() {
		scrollElementIntoView(driver,page.getYesNoPickerElement("Kojené dieťa",RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Kojené dieťa",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		setElementText(page.getInputTextareaElement("breastFeedTime"),RandomData.generateStreetNumber(),"Input random number");
		clickElementUsingJavascript(driver, page.getDropdownElement("Zvoľte"), "Unwrap dropdown Zvolte");
		clickElementUsingJavascript(driver,page.getTabElement("4"), "Select random choice");
		ReportExtender.logScreen(driver);
		scrollElementIntoView(driver,page.getYesNoPickerElement("Fajčenie počas kojenia",RandomData.getRandomAnoNie()));
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Fajčenie počas kojenia",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Alkohol počas kojenia",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		clickElementUsingJavascript(driver,page.getYesNoPickerElement("Drogy počas kojenia",RandomData.getRandomAnoNie()),"Click random Ano/nie");
		setElementText(page.getInputTextareaElement("medicineBreastFeeding"),RandomData.generateLastName(),"Input random text");
		ReportExtender.logScreen(driver);
	}

	@And("Fill information about Psychomotorický vývin dieťaťa")
	public void fillInformationAboutPsychomotorickýVývinDieťaťa() {
	}
}
