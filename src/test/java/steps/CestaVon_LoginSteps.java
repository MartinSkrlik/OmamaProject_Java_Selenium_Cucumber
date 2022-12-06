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
import utility.Log;
import utility.ReportExtender;
import utility.SAUCEDEMO_PageMapper;
import utility.Validation;

import java.util.HashMap;
import java.util.List;

import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_LoginPage.loginPageItems.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;
import static page.CestaVon_UsersPage.adminMainPageItems.*;

public class CestaVon_LoginSteps extends TestStepActions {

	static TestRunner TestRunner = new TestRunner();
	private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
	private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
	CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
	String Username, Email, PhoneNumber, Town, Status = "";

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
		ReportExtender.logScreen(driver);
	}

	@And("Click on menu button")
	public void clickOnMenuButton() {
		waitForElementVisible(driver, MenuButton.getLocator(), MenuButton.getDescription(), 15);
		clickElement(MenuButton.getElement(driver), MenuButton.getDescription());
	}

	@And("Select from menu tab {string}")
	public void selectFromTabMenu(String value) {
		waitForElementClickable(driver, page.getTabLocator(value), "Wait for specific tab is visible", 10);
		clickElement(page.getTabElement(value), "Click on specific tab");
		ReportExtender.logScreen(driver);
	}

	@And("Click on button {string}")
	public void clickOnButton(String value) {
		scrollElementIntoView(driver, page.getButtonElement(value));
		waitForElementClickable(driver, page.getButtonLocator(value), "Wait for specific button is clickable", 10);
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver, page.getButtonElement(value), "Click on specific button");
	}

	@And("Registry new {string} user and save details")
	public void registryNewUserAndSaveDetails(String value) {
		waitForElementVisible(driver, NameSurnameInput.getLocator(), NameSurnameInput.getDescription(), 10);
		setElementText(NameSurnameInput.getElement(driver), "Martin Tester", NameSurnameInput.getDescription());
		setElementText(EmailInput.getElement(driver), "Martintester@gmail.com", EmailInput.getDescription());
		setElementText(RegionInput.getElement(driver), "test", RegionInput.getDescription());
		setElementText(TownInput.getElement(driver), "Bratislava - Devín", TownInput.getDescription());
		waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		setElementText(UsernameInput.getElement(driver), "Martin_TEST", UsernameInput.getDescription());
		setElementText(StreetInput.getElement(driver), "Testerska 38", StreetInput.getDescription());
		setElementText(PasswordInput.getElement(driver), "Martintester123.", PasswordInput.getDescription());
		setElementText(AgainHesloInput.getElement(driver), "Martintester123.", AgainHesloInput.getDescription());
		setElementText(PhoneNumberInput.getElement(driver), "0915123456", PhoneNumberInput.getDescription());
		scrollElementIntoView(driver, DropDownRole.getElement(driver));
		waitForElementClickable(driver, DropDownRole.getLocator(), DropDownRole.getDescription(), 10);
		clickElement(DropDownRole.getElement(driver), DropDownRole.getDescription());
		waitForElementClickable(driver, page.getTabLocator(value), "Wait for dropdown visible", 10);
		clickElement(page.getTabElement(value), "Select specific role from dropdown");
		Username = getAttributeValue(NameSurnameInput.getElement(driver), NameSurnameInput.getDescription());
		Email = getAttributeValue(EmailInput.getElement(driver), EmailInput.getDescription());
		PhoneNumber = getAttributeValue(PhoneNumberInput.getElement(driver), PhoneNumberInput.getDescription());
		Town = getAttributeValue(TownInput.getElement(driver), TownInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Verify {string} tab is active")
	public void verifyTabIsActive(String value) {
		waitForElementVisible(driver, SelectedTab.getLocator(), SelectedTab.getDescription(), 10);
		new Validation("Verify Tab is active", getElementText(SelectedTab.getElement(driver), SelectedTab.getDescription()), value).stringEquals();
		ReportExtender.logScreen(driver);
	}

	@Then("Verify details new created user")
	public void verifyDetailsNewCreatedUser() {
		waitForElementVisible(driver, GetUserName.getLocator(), GetUserName.getDescription(), 10);
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
		waitForElementClickable(driver, page.getDropdownLocator(value), "Wait for specific dropdown clickable", 10);
		clickElementUsingJavascript(driver, page.getDropdownElement(value), "Unwrap specific dropdown");
		ReportExtender.logScreen(driver);
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
			waitForElementVisible(driver, NextPageButton.getLocator(), NextPageButton.getDescription(), 10);
			if (verifyElementIsPresent(driver, NextPageButtonDisabled.getLocator(), NextPageButtonDisabled.getDescription())) {
				ReportExtender.logFail("USERNAME WAS NOT FOUND");
				ReportExtender.logScreen(driver);
				driver.close();
			}
			clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
			waitForFullPageLoad(driver, 10);
		}
		ReportExtender.logScreen(driver);
		clickElementUsingJavascript(driver, page.getUserElement(value), "Click on desired user");
	}

	@And("Change user details")
	public void changeUserDetails() {
		waitForElementVisible(driver, NameSurnameInput.getLocator(), NameSurnameInput.getDescription(), 10);
		setElementText(NameSurnameInput.getElement(driver), "Martin Tester Change", NameSurnameInput.getDescription());
		setElementText(TownInput.getElement(driver), "Bratislava - Devínska Nová Ves", TownInput.getDescription());
		waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@And("Set user status {string}")
	public void setUserStatus(String value) {
		scrollElementIntoView(driver, page.getDropdownElement(value));
		waitForElementClickable(driver, page.getDropdownLocator(value), "Wait for user status", 10);
		clickElementUsingJavascript(driver, page.getDropdownElement(value), "Change user status");
		ReportExtender.logScreen(driver);
	}

	@And("Save user details")
	public void saveUserDetails() {
		Username = getAttributeValue(NameSurnameInput.getElement(driver), NameSurnameInput.getDescription());
		Town = getAttributeValue(TownInput.getElement(driver), TownInput.getDescription());
		Status = getElementText(GetUserStatus.getElement(driver), GetUserStatus.getDescription());
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

	@And("Verify user details were changed")
	public void verifyUserDetailsWereChanged() {
		sleep(3000);
		new Validation("Verify USERNAME", getElementText(GetUserName.getElement(driver), GetUserName.getDescription()), Username).stringEquals();
		new Validation("Verify user TOWN", getElementText(GetUserTown.getElement(driver), GetUserTown.getDescription()), Town).stringEquals();
		new Validation("Verify user STATUS", StringUtils.chop(getElementText(GetStatus.getElement(driver), GetStatus.getDescription())), StringUtils.chop(Status)).contains();
		ReportExtender.logScreen(driver);
	}

	@And("Change user details to original state")
	public void changeUserDetailsToOriginalState() {
		waitForElementVisible(driver, NameSurnameInput.getLocator(), NameSurnameInput.getDescription(), 10);
		setElementText(NameSurnameInput.getElement(driver), "Martin Tester", NameSurnameInput.getDescription());
		setElementText(TownInput.getElement(driver), "Bratislava - Devín", TownInput.getDescription());
		waitForElementClickable(driver, ConfirmTownInput.getLocator(), ConfirmTownInput.getDescription(), 10);
		clickElement(ConfirmTownInput.getElement(driver), ConfirmTownInput.getDescription());
		ReportExtender.logScreen(driver);
	}

	@Then("Go back to previous page")
	public void goBackToPreviousPage() {
		waitForElementClickable(driver, SpatButton.getLocator(), SpatButton.getDescription(), 10);
		clickElementUsingJavascript(driver, SpatButton.getElement(driver), SpatButton.getDescription());
	}

	@And("Find user with changed status")
	public void findUserWithChangedStatus() {
		waitForFullPageLoad(driver, 10);
		while (driver.findElements(page.getUserLocator(Username)).size() == 0) {
			waitForElementVisible(driver, NextPageButton.getLocator(), NextPageButton.getDescription(), 10);
			if (verifyElementIsPresent(driver, NextPageButtonDisabled.getLocator(), NextPageButtonDisabled.getDescription())) {
				ReportExtender.logInfo("Username was deleted or does not exist");
				ReportExtender.logScreen(driver);
				driver.close();
			}
			clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
			waitForFullPageLoad(driver, 10);
		}
		clickElementUsingJavascript(driver, page.getUserElement(Username), "Click on desired user");
	}

	@And("Input into {string} search bar username {string}")
	public void searchForUsername(String value, String username) {
		waitForElementVisible(driver, page.getInputLocator(value), "Wait for " + value + " input is visible", 10);
		setElementText(page.getInputElement(value), username, "Set USERNAME into input");
		if (!verifyElementIsPresent(driver, page.getUserLocator(username), "Verify username is visible")) {
			ReportExtender.logWarning("Username was deleted or does not exist");
			ReportExtender.logScreen(driver);
			return;
		}
		ReportExtender.logScreen(driver);
	}

	@And("Select user with name {string}")
	public void selectUserWithName(String username) {
		waitForElementVisible(driver, page.getUserLocator(username), "Wait for username", 10);
		clickElementUsingJavascript(driver, page.getUserElement(username), "Click on desired user");
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
	public void verifyIfInSearchBarWasFilteredUsername(String textfield_input,String username) {
		if ((verifyElementIsPresent(driver, page.getInputLocatorVerify(textfield_input, username), "Wait if meno input contains username " + username)) && (textfield_input.equals("Meno")))
		{
			if (verifyElementIsPresent(driver,GetNameKlientiTab.getLocator(), GetNameKlientiTab.getDescription()))
			{
				List<WebElement> elements = driver.findElements(GetNameKlientiTab.getLocator());
				for (WebElement element : elements) {
					new Validation("USERNAME visible on the page", element.getText(), username).stringEquals();
			}}
			else {ReportExtender.logWarning("Username " + username + " was not found in search bar "+ textfield_input);}
		}

		else if ((verifyElementIsPresent(driver, page.getInputLocatorVerify(textfield_input, username), "Wait if meno input contains username " + username)) && (textfield_input.equals("Priezvisko")))
		{
			if (verifyElementIsPresent(driver,GetSurnameKlientiTab.getLocator(), GetSurnameKlientiTab.getDescription())) {
				List<WebElement> elements = driver.findElements(GetSurnameKlientiTab.getLocator());
				for (WebElement element : elements) {
					new Validation("USERNAME visible on the page", element.getText(), username).stringEquals();
				}}
			else {ReportExtender.logWarning("Username " + username + " was not found in search bar "+ textfield_input);}
		}
		ReportExtender.logScreen(driver);
	}

	@And("Clear input {string}")
	public void clearInput(String textfield) {
		waitForElementVisible(driver,page.getInputLocator(textfield),"Wait for textfield " + textfield + " is visible",10);
		page.getInputElement(textfield).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		ReportExtender.logScreen(driver);
	}

	@And("Verify if filtered clients belong {string}")
	public void verifyIfFilteredClientsBelong(String Omama_user) {


	}
}