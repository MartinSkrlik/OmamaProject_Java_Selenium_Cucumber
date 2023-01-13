package steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.ReportExtender;
import utility.Validation;

import java.util.HashMap;
import java.util.List;

import static page.CestaVon_CommonPage.MainPage.*;
import static page.CestaVon_UsersPage.usersPageItems.*;
public class CestaVon_StatisticsSteps extends TestStepActions {

    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
    HashMap<String, String> textparameters = new HashMap<>();
    HashMap<Integer,String> indexparameters = new HashMap<>();

    @And("Save list of Omamas, Mentors and Supervizors")
    public void saveListOfOmamaMenorAndSupervizor() {
        int allUsersDysplayedOnOnePage = 10;
        int list_index = 10;
        indexparameters.put(1,"Všetko"); indexparameters.put(5,"aktívna"); indexparameters.put(9,"neaktívna");
        indexparameters.put(2,"Omama"); indexparameters.put(6,"aktívna");
        indexparameters.put(3,"Mentor"); indexparameters.put(7,"neaktívna");
        indexparameters.put(4,"Supervízor"); indexparameters.put(8,"aktívna");
        // Save Omama, Mentor and Supervizor list of active and inactive users
        for (int i = 1; i < 4 ; i++) {
            waitForElementClickable(driver, page.getDropdownLocator(indexparameters.get(i)), "Wait for dropdown " + indexparameters.get(i) + " is clickable", 10);
            clickElementUsingJavascript(driver, page.getDropdownElement(indexparameters.get(i)), "Unwrap dropdown " + indexparameters.get(i));
            waitForElementClickable(driver, page.getTabLocator(indexparameters.get(i+1)), "Wait for choice " + indexparameters.get(i+1) + " is visible", 10);
            clickElementUsingJavascript(driver,page.getTabElement(indexparameters.get(i+1)), "Click on choice " + indexparameters.get(i+1));
            boolean isEnabled = driver.findElement(NextPageButton.getLocator()).isEnabled();
            for (int j = 0; j < 3 ; j++) {
                String allUsersList = "";
                clickElementUsingJavascript(driver, page.getAscDescOrderElement(5), "Sort users");
                sleep(1000);
                int users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j))).size();
                ReportExtender.logInfo(Integer.toString(users_count));
                if (users_count > 0) {
                    while (users_count > 0) {
                        List<WebElement> users_list = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j)));
                        for (WebElement user : users_list) {
                            allUsersList += user.getText() + " ";
                            indexparameters.put(list_index, allUsersList);
                        }
                        if (users_count == allUsersDysplayedOnOnePage && isEnabled) {
                            clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
                            waitForFullPageLoad(driver, 10);
                            users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j))).size();
                        } else break;
                    }
                } else {indexparameters.put(list_index, "Zoznam je prázdny");}
            ReportExtender.logPass(indexparameters.get(list_index));
            clickElementUsingJavascript(driver, FirstPageButton.getElement(driver), FirstPageButton.getDescription());
            list_index++;
            }
        }
    }

    @And("Save list of Clients")
    public void saveListOfClients() {

    }

    @And("Open {string} statistics and verify details")
    public void openStatisticsAndVerifyDetails(String user) {
        String inactive_list = "", active_list = "";
        waitForElementVisible(driver, page.getActiveInactiveStatisticsLocator(user, "neaktívne"), "Wait for " + user + "statistic visible ", 10);
        clickElementUsingJavascript(driver, page.getActiveInactiveStatisticsElement(user, "neaktívne"), "Unwrap active user list");
        sleep(1000);
        String userList = driver.findElement(SaveUserListFromStatistics.getLocator()).getText();
        ReportExtender.logInfo(userList);
        // If users list is empty, check if it is also empty in users tab
        if (userList.equals("Zoznam je prázdny")) {
            new Validation("Verify if user list is empty", userList, textparameters.get("inactive_users_list")).stringEquals();
        }
        // If users list is not empty, check with users list from users tab
        else {
            List<WebElement> omama_list = driver.findElements(SaveUserListFromStatistics.getLocator());
            for (WebElement omama : omama_list) {
                inactive_list += omama.getText() + " ";
                textparameters.put("inactive_omama_list_to_compare", inactive_list);
            }
            new Validation("Compare users list", textparameters.get("inactive_users_list"),textparameters.get("inactive_omama_list_to_compare")).stringEquals();
        }
        ReportExtender.logScreen(driver);
    }



}