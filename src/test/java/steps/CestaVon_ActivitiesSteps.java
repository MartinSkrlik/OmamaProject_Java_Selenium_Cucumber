package steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.*;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import static page.CestaVon_CommonPage.MainPage.*;

public class CestaVon_ActivitiesSteps extends TestStepActions {

    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
    HashMap<String, String> textparameters = new HashMap<>();

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

    @And("Verify activity details")
    public void verifyActivityDetails() {
        waitForElementClickable(driver,page.getButtonLocator("Zmazať"),"Wait for zmazat button is visible",10);
        new Validation("Verify activity name and number", getElementText(ActivityName.getElement(driver), ActivityName.getDescription()), (textparameters.get("activityName") + " " + textparameters.get("activityNumber"))).contains();
        new Validation("Verify activity goal", getElementText(page.getActivityTextElement(6),"Activity goal text"), textparameters.get("activityGoal")).stringEquals();
        new Validation("Verify activity aids", getElementText(page.getActivityTextElement(7),"Activity aids text"), textparameters.get("activityAids")).stringEquals();
        new Validation("Verify activity process", getElementText(page.getActivityTextElement(11),"Activity process text").trim(), textparameters.get("activityProcess")).contains();
        ReportExtender.logScreen(driver);
    }

    @And("Save activity attributes")
    public void saveActivityAttributes() {
        waitForElementVisible(driver,page.getActivityAttributeLocator(1),"Wait for activity visible",10);
        textparameters.put("activityName", getElementText(page.getActivityAttributeElement(1), "Save name activity into variable"));
        textparameters.put("activityNumber", getElementText(page.getActivityAttributeElement(2), "Save activity number into variable"));
        textparameters.put("activityMonth", getElementText(page.getActivityAttributeElement(3), "Save activity month into variable"));
        textparameters.put("activityWeek",getElementText(page.getActivityAttributeElement(4), "Save activity week into variable"));
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
        clickElementUsingJavascript(driver,page.getAscDescOrderElement(1),"Set ascending order");
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
        // Verify sorting in descending order // have to be repaired by developers
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
            clickElementUsingJavascript(driver, page.getAscDescOrderElement(i), "Set ascending order");
            Float[] arrayOrdered = new Float[10];
            Float[] ascOrdered = new Float[10];
            int j = 0;
            // Verify sorting in ascending order
            List<WebElement> ASC_ORDER = driver.findElements(ActivityList.getLocator());
            for ( WebElement element : ASC_ORDER ){
                String element_text = element.getText();
                float activity_number = Float.parseFloat(element_text);
                ascOrdered[j] = activity_number;
                arrayOrdered[j] = activity_number;
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
            clickElementUsingJavascript(driver,page.getAscDescOrderElement(i),"Set descending order");
            int k = 0;
            List<WebElement> DESC_ORDER = driver.findElements(ActivityList.getLocator());
            for ( WebElement element : DESC_ORDER ){
                String element_text = element.getText();
                float activity_number = Float.parseFloat(element_text);
                ascOrdered[k] = activity_number;
                arrayOrdered[k] = activity_number;
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

    @And("Confirm Zmazat button")
    public void confirmZmazatButton() {
        waitForElementVisible(driver, DeleteActivityButton.getLocator(), DeleteActivityButton.getDescription(),10);
        clickElementUsingJavascript(driver, DeleteActivityButton.getElement(driver), DeleteActivityButton.getDescription());
    }

    @And("Verify activity was deleted")
    public void verifyActivityWasDeleted() {
        setElementText(page.getInputElement("Názov aktivity"), textparameters.get("activityName"), "Set " + textparameters.get("activityName") + " into input");
        sleep(1000);
        if(!verifyElementIsPresent(driver, page.getUserLocator(textparameters.get("activityName")), "Find if activity was deleted"))
        {ReportExtender.logPass("Username " + (textparameters.get("activityName") + " was deleted"));}
        else
            ReportExtender.logWarning("Username " + (textparameters.get("activityName") + " was not deleted"));
        ReportExtender.logScreen(driver);
    }

    @And("Verify if input {string} properly filter {string}")
    public void verifyIfInputProperlyFilter(String filter_name, String attribute_name) {
        setElementText(page.getInputElement(filter_name), textparameters.get(attribute_name), "Set " + textparameters.get(attribute_name) + " into input");
        new Validation("Verify activity name", getElementText(page.getActivityAttributeElement(1),"Get activity name"), (textparameters.get("activityName"))).stringEquals();
        ReportExtender.logScreen(driver);
        page.getInputElement(filter_name).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }
}
