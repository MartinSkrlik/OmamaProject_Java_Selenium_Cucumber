package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_ClientProfilePage;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import static page.CestaVon_ClientProfilePage.ClientProfilePage.*;
import static page.CestaVon_UserProfilPage.UserProfilPage.*;
import static page.CestaVon_UserRegistrationPage.UserRegistrationPage.*;

public class CestaVon_ClientsSteps extends TestStepActions {

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

    @And("Verify client details")
    public void verifyClientDetails() {
        sleep(1000);
        new Validation("Verify USERNAME", getElementText(UsernameText.getElement(driver), UsernameText.getDescription()), textparameters.get("username") + " " + textparameters.get("surname")).stringEquals();
        new Validation("Verify TOWN", getElementText(UserTownText.getElement(driver), UserTownText.getDescription()), textparameters.get("town")).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("Save First and Last Name and Omama")
    public void saveFirstAndLastNameAndOmama() {
        waitForElementVisible(driver, NameInput.getLocator(), NameInput.getDescription(), 10);
        textparameters.put("firstName", getAttributeValue(NameInput.getElement(driver), NameInput.getDescription()));
        waitForElementVisible(driver, SurnameInput.getLocator(), SurnameInput.getDescription(), 10);
        textparameters.put("lastName", getAttributeValue(SurnameInput.getElement(driver), SurnameInput.getDescription()));
        waitForElementVisible(driver, OmamaDropdown.getLocator(), OmamaDropdown.getDescription(), 10);
        textparameters.put("omama", OmamaDropdown.getElement(driver).getText());
    }

    @And("Set Omama to {string}")
    public void setOmamaTo(String value) {
        scrollElementIntoMiddleOfScreen(driver, OmamaDropdown.getElement(driver));
        sleep(1000);
        waitForElementClickable(driver, OmamaDropdown.getLocator(), OmamaDropdown.getDescription(), 10);
        ReportExtender.logScreen(driver);
        clickElementUsingJavascript(driver, OmamaDropdown.getElement(driver), OmamaDropdown.getDescription());
        scrollElementIntoMiddleOfScreen(driver, page.getDropdownItemElement(value));
        sleep(1000);
        waitForElementClickable(driver, page.getDropdownItemLocator(value), "Waiting for dropdown item clickable", 10);
        clickElementUsingJavascript(driver, page.getDropdownItemElement(value), "Clicking dropdown item");
        ReportExtender.logScreen(driver);
    }

    @And("Save client profile edit")
    public void saveClientProfileEdit() {
        scrollElementIntoView(driver, SaveButton.getElement(driver));
        waitForElementClickable(driver, SaveButton.getLocator(), "Wait for button to be clickable", 10);
        clickElementUsingJavascript(driver, SaveButton.getElement(driver), "Click on save button");
        sleep(2000);
    }

    @Then("Verify client name is {string} and {string}")
    public void verifyClientNameIsAnd(String value1, String value2) {
        waitForElementVisible(driver, ClientFullName.getLocator(), ClientFullName.getDescription(), 10);
        String fullName = getElementText(ClientFullName.getElement(driver), "Get Client full name");
        new Validation("First name properly changed", fullName, value1).contains();
        new Validation("Surname properly changed", fullName, value2).contains();
    }

    @Then("Verify Omama is {string}")
    public void verifyOmamaIs(String value) {
        new Validation("Verify assigned Omama", getElementText(AssignedOmama.getElement(driver), AssignedOmama.getDescription()), value).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("Set Omama to old Omama")
    public void setOmamaToOldOmama() {
        scrollElementIntoMiddleOfScreen(driver, OmamaDropdown.getElement(driver));
        sleep(1000);
        waitForElementClickable(driver, OmamaDropdown.getLocator(), OmamaDropdown.getDescription(), 10);
        ReportExtender.logScreen(driver);
        clickElementUsingJavascript(driver, OmamaDropdown.getElement(driver), OmamaDropdown.getDescription());
        scrollElementIntoMiddleOfScreen(driver, page.getDropdownItemElement(textparameters.get("omama")));
        sleep(1000);
        waitForElementClickable(driver, page.getDropdownItemLocator(textparameters.get("omama")), "Waiting for dropdown item clickable", 10);
        clickElementUsingJavascript(driver, page.getDropdownItemElement(textparameters.get("omama")), "Clicking dropdown item");
        ReportExtender.logScreen(driver);
    }

    @Then("Verify first client active status is {string}")
    public void verifyFirstClientActiveStatusIs(String value) {
        waitForElementVisible(driver, page.getTableColumnPredecessorsLocator("Stav"), "Waiting for table column predecessors locator visible", 10);
        int stavIndex = 1 + driver.findElements(page.getTableColumnPredecessorsLocator("Stav")).size();
        waitForElementVisible(driver, page.getTableValueLocator(1, stavIndex), "Waiting for Table value locator visible.", 10);
        String stav = driver.findElement(page.getTableValueLocator(1, stavIndex)).getText();
        new Validation("Verify client status", stav, value).stringEquals();
        ReportExtender.logScreen(driver);
    }

    @And("In profil page select tab {string}")
    public void inProfilPageSelecttab(String tab) {
        waitForElementClickable(driver, page.getProfilTabLocator(tab), "Wait for choice " + tab + " is visible", 10);
        clickElementUsingJavascript(driver,page.getProfilTabElement(tab), "Click on choice " + tab);
    }

    @And("Verify tab {string} is selected")
    public void verifyTabIsSelected(String tab) {
        new Validation("Verify tab " + tab + " is selected", page.getProfilTabElement(tab).getAttribute("class"), "active").stringEquals();
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

    @Then("Verify subpage {string} is active in clients profile")
    public void verifySubpageIsActiveInClientsProfile(String value) {
        //new Validation("Verify active subpage text", getElementText(ClientSubmenuTab.getElement(driver), ClientSubmenuTab.getDescription()), value).stringEquals();
    }

    @And("Choose first finished lesson")
    public void chooseFirstFinishedLesson() {
        waitForElementVisible(driver, FinishedLessonRow.getLocator(), FinishedLessonRow.getDescription(), 10);
        scrollElementIntoView(driver, FinishedLessonRow.getElement(driver));
        sleep(1000);
        ReportExtender.logScreen(driver);
        clickElementUsingJavascript(driver, FinishedLessonRow.getElement(driver), FinishedLessonRow.getDescription());
    }

    @And("Verify lesson details {string} {string}")
    public void verifyLessonDetails(String firstName, String lastName) {
        CestaVon_ClientProfilePage page = new CestaVon_ClientProfilePage(driver);
        //date and time
        ReportExtender.logWarning("String should contain date and time:");
        waitForElementVisible(driver, LessonDetailDateTime.getLocator(), LessonDetailDateTime.getDescription(), 10);
        ReportExtender.logWarning(getElementText(LessonDetailDateTime.getElement(driver), "Element text"));
        //name and age
        ReportExtender.logWarning("String should client name (" + firstName + " " + lastName + ") and age in month/week format");
        waitForElementVisible(driver, LessonDetailNameAndAge.getLocator(), LessonDetailNameAndAge.getDescription(), 10);
        ReportExtender.logWarning(getElementText(LessonDetailNameAndAge.getElement(driver), "Element text"));
        //activities
        waitForElementVisible(driver, LessonDetailActivityHeader.getLocator(), LessonDetailActivityHeader.getDescription(), 10);
        String actualActivitiesText = getElementText(LessonDetailActivityHeader.getElement(driver), LessonDetailActivityHeader.getDescription());
        new Validation("Verify \"Aktivity\" is present", actualActivitiesText, "Aktivity").stringEquals();
        ReportExtender.logScreen(driver);
        sleep(1000);
        //rating
        waitForElementVisible(driver, LessonDetailRatingHeader.getLocator(), LessonDetailRatingHeader.getDescription(), 10);
        String actualRatingText = getElementText(LessonDetailRatingHeader.getElement(driver), LessonDetailRatingHeader.getDescription());
        new Validation("Verify \"Hodnotenie\" is present", actualRatingText, "Hodnotenie").stringEquals();

        int numberOfQuestions = driver.findElements(LessonDetailRatingQuestions.getLocator()).size();
        int numberOfAnsweredQuestions = driver.findElements(LessonDetailRatingSelectedAnswers.getLocator()).size();
        String nOfQuestions = String.valueOf(numberOfQuestions);
        String nOfAnsweredQuestions = String.valueOf(numberOfAnsweredQuestions);
        new Validation("Verify that all questions are answered", nOfAnsweredQuestions, nOfQuestions).stringEquals();
        //photos
        waitForElementVisible(driver, LessonDetailPhotographiesHeader.getLocator(), LessonDetailPhotographiesHeader.getDescription(), 10);
        scrollElementIntoView(driver, LessonDetailPhotographiesHeader.getElement(driver));
        sleep(1000);
        String actualPhotographiesText = getElementText(LessonDetailPhotographiesHeader.getElement(driver), LessonDetailPhotographiesHeader.getDescription());
        new Validation("Verify \"Fotografie z Lekcia\" is present", actualPhotographiesText, "Fotografie z Lekcia").stringEquals();
        //comment
        waitForElementVisible(driver, LessonDetailCommentHeader.getLocator(), LessonDetailCommentHeader.getDescription(), 10);
        String actualCommentText = getElementText(LessonDetailCommentHeader.getElement(driver), LessonDetailCommentHeader.getDescription());
        new Validation("Verify \"Komentár\" is present", actualCommentText, "Komentár").stringEquals();
        ReportExtender.logScreen(driver);
    }

}
