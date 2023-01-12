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

//    @And("Save users list")
//    public void saveUsersList() {
//        int allUsersDysplayedOnOnePage = 10;
//        String inactiveUsersList = "";
//        clickElementUsingJavascript(driver, page.getAscDescOrderElement(5), "Sort inactive users");
//        sleep(1000);
//
//        // Save inactive users list
//        boolean isEnabled = driver.findElement(NextPageButton.getLocator()).isEnabled();
//        int inactive_users_count = driver.findElements(page.getListActiveInactiveUserLocator("neaktívna")).size();
//        ReportExtender.logInfo(Integer.toString(inactive_users_count));
//        if (inactive_users_count > 0) {
//            while (inactive_users_count > 0) {
//                List<WebElement> users_list = driver.findElements(page.getListActiveInactiveUserLocator("neaktívna"));
//                for (WebElement user : users_list) {
//                    inactiveUsersList += user.getText() + " ";
//                    textparameters.put("inactive_users_list", inactiveUsersList);
//                }
//                if (inactive_users_count == allUsersDysplayedOnOnePage && isEnabled) {
//                    clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
//                    waitForFullPageLoad(driver, 10);
//                    inactive_users_count = driver.findElements(page.getListActiveInactiveUserLocator("neaktívna")).size();
//                } else break;
//            }
//        } else {
//            textparameters.put("inactive_users_list", "Zoznam je prázdny");
//        }
//        refreshPage(driver);
//        ReportExtender.logInfo(inactiveUsersList);
//        ReportExtender.logInfo(textparameters.get("inactive_users_list"));
//        ReportExtender.logScreen(driver);
//
//        // Save active users list
//        String activeUsersList = "";
//        doubleClickElement(driver, page.getAscDescOrderElement(5), "Sort active users");
//        sleep(1000);
//        int active_users_count = driver.findElements(page.getListActiveInactiveUserLocator("aktívna")).size();
//        ReportExtender.logInfo(Integer.toString(active_users_count));
//        if (active_users_count > 0) {
//            while (active_users_count > 0) {
//                List<WebElement> users_list = driver.findElements(page.getListActiveInactiveUserLocator("aktívna"));
//                for (WebElement user : users_list) {
//                    activeUsersList += user.getText() + " ";
//                    textparameters.put("active_users_list", activeUsersList);
//                }
//                if (active_users_count == allUsersDysplayedOnOnePage && isEnabled) {
//                    clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
//                    waitForFullPageLoad(driver, 10);
//                    active_users_count = driver.findElements(page.getListActiveInactiveUserLocator("aktívna")).size();
//                } else break;
//            }
//        } else {
//            textparameters.put("active_users_list", "Zoznam je prázdny");
//        }
//        refreshPage(driver);
//        ReportExtender.logInfo(activeUsersList);
//        ReportExtender.logInfo(textparameters.get("active_users_list"));
//        ReportExtender.logScreen(driver);
//
//        // Save all users list
//        String allUsersList = "";
//        sleep(1000);
//        int all_users_count = driver.findElements(SaveUsersListFromUsersTab.getLocator()).size();
//        ReportExtender.logInfo(Integer.toString(all_users_count));
//        if (all_users_count > 0) {
//            while (all_users_count > 0) {
//                List<WebElement> users_list = driver.findElements(SaveUsersListFromUsersTab.getLocator());
//                for (WebElement user : users_list) {
//                    allUsersList += user.getText() + " ";
//                    textparameters.put("all_users_list", allUsersList);
//                }
//                if (all_users_count == allUsersDysplayedOnOnePage && isEnabled) {
//                    clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
//                    waitForFullPageLoad(driver, 10);
//                    all_users_count = driver.findElements(page.getListActiveInactiveUserLocator("aktívna")).size();
//                } else break;
//            }
//        } else {
//            textparameters.put("all_users_list", "Zoznam je prázdny");
//        }
//        ReportExtender.logInfo(allUsersList);
//        ReportExtender.logInfo(textparameters.get("all_users_list"));
//        ReportExtender.logScreen(driver);
//    }

    @And("Save users list in loop")
    public void saveUsersListInLoop() {
        int allUsersDysplayedOnOnePage = 10;
        indexparameters.put(1,"Všetko"); indexparameters.put(5,"aktívna"); indexparameters.put(9,"neaktívna");
        indexparameters.put(2,"Omama"); indexparameters.put(6,"aktívna");
        indexparameters.put(3,"Mentor"); indexparameters.put(7,"neaktívna");
        indexparameters.put(4,"Supervízor"); indexparameters.put(8,"aktívna");
        for (int i = 1; i < 4 ; i++) {
            waitForElementClickable(driver, page.getDropdownLocator(indexparameters.get(i)), "Wait for dropdown " + indexparameters.get(i) + " is clickable", 10);
            clickElementUsingJavascript(driver, page.getDropdownElement(indexparameters.get(i)), "Unwrap dropdown " + indexparameters.get(i));
            waitForElementClickable(driver, page.getTabLocator(indexparameters.get(i+1)), "Wait for choice " + indexparameters.get(i+1) + " is visible", 10);
            clickElementUsingJavascript(driver,page.getTabElement(indexparameters.get(i+1)), "Click on choice " + indexparameters.get(i+1));
            boolean isEnabled = driver.findElement(NextPageButton.getLocator()).isEnabled();
            for (int j = 0; j < 3 ; j++) {
                int users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j))).size();




            }













        }
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