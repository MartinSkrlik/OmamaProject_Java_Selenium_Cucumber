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

import static page.CestaVon_CommonPage.MainPage.FirstPageButton;
import static page.CestaVon_CommonPage.MainPage.SaveUserListFromStatistics;
import static page.CestaVon_UsersPage.usersPageItems.NextPageButton;
public class CestaVon_StatisticsSteps extends TestStepActions {

    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
    HashMap<String, String> textparameters = new HashMap<>();
    HashMap<Integer,String> indexparameters = new HashMap<>();
    HashMap<Integer,Integer> integerparameters = new HashMap<>();

    @And("Save list of Omamas, Mentors and Supervizors")
    public void saveListOfOmamaMenorAndSupervizor() {
        int allUsersDysplayedOnOnePage = 10;
        int list_index = 10;
        int count_users = 1;
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
                int count = 0;
                String allUsersList = "";
                clickElementUsingJavascript(driver, page.getAscDescOrderElement(5), "Sort users");
                sleep(1000);
                int users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j))).size();
                if (users_count > 0) {
                    while (users_count > 0) {
                        List<WebElement> users_list = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j)));
                        for (WebElement user : users_list) {
                            allUsersList += user.getText() + " ";
                            indexparameters.put(list_index, allUsersList);
                            count += 1;
                            integerparameters.put(count_users,count);
                        }
                        if (users_count == allUsersDysplayedOnOnePage && isEnabled) {
                            clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
                            waitForFullPageLoad(driver, 10);
                            users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7+j),indexparameters.get(7-j))).size();
                        } else break;
                    }
                } else {indexparameters.put(list_index, "Zoznam je prázdny");
                        integerparameters.put(count_users, 0);}
            clickElementUsingJavascript(driver, FirstPageButton.getElement(driver), FirstPageButton.getDescription());
            list_index++; count_users++;
            }
        ReportExtender.logScreen(driver);
        }
    }

    @And("Verify details about Omamas, Mentors and Supervizors")
    public void veirfyDetailsAboutOmamasMentorsAndSupervizors() {
        indexparameters.put(20, "Omamy");integerparameters.put(10, 4);
        indexparameters.put(21, "Mentorky");integerparameters.put(11, 2);
        indexparameters.put(22, "Supervízori");integerparameters.put(12, 3);
        String list = "";
        int count = 1, user_index = 25, index_parameters = 10;
        String users_count = "";
        for (int k = 20; k < 23; k++) {
            for (int j = 10; j < 13; j++, count++, user_index++, index_parameters++) {
                String users_list = "";
                users_count = driver.findElement(page.getActiveInactiveStatisticsLocator(indexparameters.get(k), integerparameters.get(j))).getText();
                String userNumber = Integer.toString(integerparameters.get(count));
                if (!users_count.equals(userNumber)) {
                    ReportExtender.logWarning("User lists have no the same count");
                }
                clickElementUsingJavascript(driver,page.getActiveInactiveStatisticsElement(indexparameters.get(k), integerparameters.get(j)),"Open list of users");
                sleep(1000);
                list = driver.findElement(SaveUserListFromStatistics.getLocator()).getText();
                // If users list is empty, check if it is also empty in users tab
                if (list.equals("Zoznam je prázdny")) {
                    new Validation("Verify if user list is empty", list, indexparameters.get(index_parameters)).stringEquals();
                }
                // If users list is not empty, check with users list from users tab
                else {
                    List<WebElement> uList = driver.findElements(SaveUserListFromStatistics.getLocator());
                    for (WebElement omama : uList) {
                        users_list += omama.getText() + " ";
                        indexparameters.put(user_index, users_list);
                    }
                    new Validation("Compare " + indexparameters.get(k) + " users list", indexparameters.get(index_parameters), indexparameters.get(user_index)).stringEquals();
                }
                ReportExtender.logScreen(driver);
                clickElementUsingJavascript(driver, page.getButtonElement("OK"), "Click on OK button");
            }

        }

    }

}









