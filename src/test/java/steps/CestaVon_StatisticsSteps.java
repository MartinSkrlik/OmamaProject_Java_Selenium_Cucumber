package steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.ReportExtender;

import java.util.Arrays;
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
    HashMap<Integer, String> indexparameters = new HashMap<>();
    HashMap<Integer, Integer> integerparameters = new HashMap<>();

    @And("Save list of Omamas, Mentors and Supervizors")
    public void saveListOfOmamaMenorAndSupervizor() {
        int allUsersDysplayedOnOnePage = 10;
        int list_index = 10;
        int count_users = 1;
        indexparameters.put(1, "Všetko");
        indexparameters.put(2, "Omama");
        indexparameters.put(5, "aktívna");
        indexparameters.put(3, "Mentor");
        indexparameters.put(9, "neaktívna");
        indexparameters.put(4, "Supervízor");
        indexparameters.put(7, "neaktívna");
        indexparameters.put(6, "aktívna");
        indexparameters.put(8, "aktívna");
        // Save Omama, Mentor and Supervizor list of active and inactive users
        for (int i = 1; i < 4; i++) {
            waitForElementClickable(driver, page.getDropdownLocator(indexparameters.get(i)), "Wait for dropdown " + indexparameters.get(i) + " is clickable", 10);
            clickElementUsingJavascript(driver, page.getDropdownElement(indexparameters.get(i)), "Unwrap dropdown " + indexparameters.get(i));
            waitForElementClickable(driver, page.getTabLocator(indexparameters.get(i + 1)), "Wait for choice " + indexparameters.get(i + 1) + " is visible", 10);
            clickElementUsingJavascript(driver, page.getTabElement(indexparameters.get(i + 1)), "Click on choice " + indexparameters.get(i + 1));
            boolean isEnabled = driver.findElement(NextPageButton.getLocator()).isEnabled();
            for (int j = 0; j < 3; j++) {
                int count = 0;
                String allUsersList = "";
                clickElementUsingJavascript(driver, page.getAscDescOrderElement(5), "Sort users");
                sleep(1000);
                int users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7 + j), indexparameters.get(7 - j))).size();
                if (users_count > 0) {
                    while (users_count > 0) {
                        List<WebElement> users_list = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7 + j), indexparameters.get(7 - j)));
                        for (WebElement user : users_list) {
                            allUsersList += user.getText() + " ";
                            indexparameters.put(list_index, allUsersList);
                            count += 1;
                            integerparameters.put(count_users, count);
                        }
                        if (users_count == allUsersDysplayedOnOnePage && isEnabled) {
                            clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
                            waitForFullPageLoad(driver, 10);
                            users_count = driver.findElements(page.getListActiveInactiveUserLocator(indexparameters.get(7 + j), indexparameters.get(7 - j))).size();
                        } else break;
                    }
                } else {
                    indexparameters.put(list_index, "Zoznam je prázdny");
                    integerparameters.put(count_users, 0);
                }
                clickElementUsingJavascript(driver, FirstPageButton.getElement(driver), FirstPageButton.getDescription());
                list_index++;
                count_users++;
            }
            ReportExtender.logScreen(driver);
        }
    }

    @And("Verify details about Omamas, Mentors and Supervizors")
    public void veirfyDetailsAboutOmamasMentorsAndSupervizors() {
        indexparameters.put(40, "Omamy");
        integerparameters.put(13, 4);
        indexparameters.put(41, "Mentorky");
        integerparameters.put(14, 2);
        indexparameters.put(42, "Supervízori");
        integerparameters.put(15, 3);
        int count = 1, user_index = 25, index_parameters = 10;
        for (int k = 40; k < 43; k++) {
            // Verify number of users from users tab
            for (int j = 13; j < 16; j++, count++, user_index++, index_parameters++) {
                String users_list = "";
                String users_count = driver.findElement(page.getActiveInactiveStatisticsLocator(indexparameters.get(k), integerparameters.get(j))).getText();
                String userNumber = Integer.toString(integerparameters.get(count));
                if (!users_count.equals(userNumber)) {
                    ReportExtender.logWarning("User lists have no the same count");
                }
                clickElementUsingJavascript(driver, page.getActiveInactiveStatisticsElement(indexparameters.get(k), integerparameters.get(j)), "Open list of users");
                sleep(1000);
                String list = driver.findElement(SaveUserListFromStatistics.getLocator()).getText();
                // If users list is empty, check if it is also empty in users tab
                if (list.equals("Zoznam je prázdny")) {
                    indexparameters.put(user_index, "Zoznam je prázdny");
                    if (!list.equals(indexparameters.get(index_parameters))) {
                        ReportExtender.logWarning("Users lists " + list + " and " + indexparameters.get(index_parameters) + " are not the same!");
                    }
                }
                // If users list is not empty, check with users list from users tab
                else {
                    List<WebElement> uList = driver.findElements(SaveUserListFromStatistics.getLocator());
                    for (WebElement omama : uList) {
                        users_list += omama.getText() + " ";
                        indexparameters.put(user_index, users_list);
                    }
                    if (!indexparameters.get(index_parameters).equals(indexparameters.get(user_index))) {
                        ReportExtender.logWarning("Users lists " + indexparameters.get(index_parameters) + " and " +
                                indexparameters.get(user_index) + " are not the same!");}
                    }
                ReportExtender.logScreen(driver);
                clickElementUsingJavascript(driver, page.getButtonElement("OK"), "Click on OK button");
            }
        }
    }

    @And("Save list of Clients")
    public void saveListOfClients() {
        int list_index = 19;
        int count_users = 10;
        indexparameters.put(44, "Všetci"); indexparameters.put(46, "Aktívni");
        indexparameters.put(45, "Neaktívni"); indexparameters.put(47, "Všetci");
        for (int j = 44; j < 47; j++, count_users++, list_index++ ) {
            String usersList = "";
            int users_count = 0;
            // Save active, inactive and all users from users tab
            waitForElementClickable(driver, page.getDropdownLocator(indexparameters.get(j)), "Wait for dropdown " + indexparameters.get(j) + " is clickable", 10);
            clickElementUsingJavascript(driver, page.getDropdownElement(indexparameters.get(j)), "Unwrap dropdown " + indexparameters.get(j));
            waitForElementClickable(driver, page.getTabLocator(indexparameters.get(j + 1)), "Wait for choice " + indexparameters.get(j + 1) + " is visible", 10);
            clickElementUsingJavascript(driver, page.getTabElement(indexparameters.get(j + 1)), "Click on choice " + indexparameters.get(j + 1));
            String NextPageEnabled = "false";
            while (NextPageEnabled.equals("false")) {
                NextPageEnabled = driver.findElement(NextPageButton.getLocator()).getAttribute("aria-disabled");
                int users_visible_on_page = driver.findElements(page.getClientFirstNameLocator(".")).size();
                if (users_visible_on_page > 0) {
                    for (int i = 1; i < users_visible_on_page + 1; i++) {
                        String surname = driver.findElement(page.getClientSecondNameLocator(i+".")).getText();
                        usersList += surname + " ";
                        indexparameters.put(list_index, usersList);
                        String firstName = driver.findElement(page.getClientFirstNameLocator(i+".")).getText();
                        usersList += firstName + " ";
                        indexparameters.put(list_index, usersList);
                        users_count += 1;
                        integerparameters.put(count_users, users_count);
                    }
                }
                else {
                    indexparameters.put(list_index, "Zoznam je prázdny");
                    integerparameters.put(count_users, 0);
                }
                clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
                sleep(1000);
            }
        ReportExtender.logScreen(driver);
        refreshPage(driver);
        }
    }

    @And("Verify details about Clients")
    public void verifyDetailsAboutClients() {
        indexparameters.put(43, "Klienti");
        int count = 10, index_parameters = 19, user_index = 34;
        for (int j = 13; j < 16; j++, count++, user_index++, index_parameters++) {
            String users_list = "";
            String users_count = driver.findElement(page.getActiveInactiveStatisticsLocator(indexparameters.get(43), integerparameters.get(j))).getText();
            String userNumber = Integer.toString(integerparameters.get(count));
            if (!users_count.equals(userNumber)) {
                ReportExtender.logWarning("User lists have no the same count");
            }
            clickElementUsingJavascript(driver, page.getActiveInactiveStatisticsElement(indexparameters.get(43), integerparameters.get(j)), "Open list of users");
            sleep(1000);
            String list = driver.findElement(SaveUserListFromStatistics.getLocator()).getText();
            // If users list is empty, check if it is also empty in users tab
            if (list.equals("Zoznam je prázdny")) {
                if (!list.equals(indexparameters.get(index_parameters))) {
                    ReportExtender.logWarning("Users lists " + list + " and " + indexparameters.get(index_parameters) + " are not the same!");
                    ReportExtender.logScreen(driver);}
            }
            // If users list is not empty, check with users list from users tab
            else {
                List<WebElement> uList = driver.findElements(SaveUserListFromStatistics.getLocator());
                for (WebElement omama : uList) {
                    users_list += omama.getText() + " ";
                    indexparameters.put(user_index, users_list);
                }
                String[] statistics_list_sorted = indexparameters.get(user_index).split(" ");
                String[] users_list_sorted = indexparameters.get(index_parameters).split(" ");
                Arrays.sort(statistics_list_sorted); Arrays.sort(users_list_sorted);
                String compare_statistics_list = Arrays.toString(statistics_list_sorted);
                String compare_user_list = Arrays.toString(users_list_sorted);
                if (!compare_statistics_list.equals(compare_user_list)) {
                    ReportExtender.logWarning("Users lists " + compare_statistics_list + " and " + compare_user_list + " are not the same!");
                }
                ReportExtender.logScreen(driver);
                clickElementUsingJavascript(driver, page.getButtonElement("OK"), "Click on OK button");
            }
        }
    }
}







