package steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.ReportExtender;
import utility.Validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static page.CestaVon_CommonPage.MainPage.FirstPageButton;
import static page.CestaVon_StatisticsPage.StatisticsPage.*;
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
        integerparameters.put(13, 3);
        indexparameters.put(41, "Mentorky");
        integerparameters.put(14, 1);
        indexparameters.put(42, "Supervízori");
        integerparameters.put(15, 2);
        int count = 1, user_index = 25, index_parameters = 10;
        for (int k = 40; k < 43; k++) {
            // Verify number of users from users tab
            for (int j = 13; j < 16; j++, count++, user_index++, index_parameters++) {
                String users_list = "";
                String users_count = driver.findElement(page.getActiveInactiveStatisticsLocator(indexparameters.get(k), integerparameters.get(j))).getText();
                String userNumber = Integer.toString(integerparameters.get(count));
                if (!users_count.equals(userNumber)) {
                    ReportExtender.logWarning("User lists have no the same count");
                    ReportExtender.logWarning(users_count);
                    ReportExtender.logWarning(userNumber);
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
                                indexparameters.get(user_index) + " are not the same!");
                    }
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
        indexparameters.put(44, "Všetci");
        indexparameters.put(46, "Aktívni");
        indexparameters.put(45, "Neaktívni");
        indexparameters.put(47, "Všetci");
        for (int j = 44; j < 47; j++, count_users++, list_index++) {
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
                int users_visible_on_page = driver.findElements(page.getClientAttributeLocator(3, ".")).size();
                if (users_visible_on_page > 0) {
                    for (int i = 1; i < users_visible_on_page + 1; i++) {
                        String surname = driver.findElement(page.getClientAttributeLocator(3, i + ".")).getText();
                        usersList += surname + " ";
                        indexparameters.put(list_index, usersList);
                        String firstName = driver.findElement(page.getClientAttributeLocator(2, i + ".")).getText();
                        usersList += firstName + " ";
                        indexparameters.put(list_index, usersList);
                        users_count += 1;
                        integerparameters.put(count_users, users_count);
                    }
                } else {
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
                ReportExtender.logWarning(users_count);
                ReportExtender.logWarning(userNumber);
            }
            clickElementUsingJavascript(driver, page.getActiveInactiveStatisticsElement(indexparameters.get(43), integerparameters.get(j)), "Open list of users");
            sleep(1000);
            String list = driver.findElement(SaveUserListFromStatistics.getLocator()).getText();
            // If users list is empty, check if it is also empty in users tab
            if (list.equals("Zoznam je prázdny")) {
                if (!list.equals(indexparameters.get(index_parameters))) {
                    ReportExtender.logWarning("Users lists " + list + " and " + indexparameters.get(index_parameters) + " are not the same!");
                    ReportExtender.logScreen(driver);
                }
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
                Arrays.sort(statistics_list_sorted);
                Arrays.sort(users_list_sorted);
                if (!Arrays.toString(statistics_list_sorted).equals(Arrays.toString(users_list_sorted))) {
                    ReportExtender.logWarning("Users lists " + Arrays.toString(statistics_list_sorted) + " and " + Arrays.toString(users_list_sorted) + " are not the same!");
                }
                ReportExtender.logScreen(driver);
                clickElementUsingJavascript(driver, page.getButtonElement("OK"), "Click on OK button");
            }
        }
    }

    @And("Verify details about Actions")
    public void verifyDetailsAboutActions() {
        String convertToIngeger;
        int actionCount = 0, preSchoolClubCount = 0, parentClubCount = 0;
        waitForElementVisible(driver, page.getStatsTabInStatisticsLocator("Omamy"), "Wait for Omama tab in statistics", 10);
        clickElementUsingJavascript(driver, page.getStatsTabInStatisticsElement("Omamy"), "Select Omama tab");
        sleep(1000);
        // Save actions count for verification
        List<WebElement> actionList = driver.findElements(NonCanceledActionCount.getLocator());
        for (WebElement actions : actionList) {
            convertToIngeger = actions.getText();
            actionCount += Integer.parseInt(convertToIngeger);
            integerparameters.put(2, actionCount);
        }
        // Save preschool club count for verification
        List<WebElement> preSchoolList = driver.findElements(PreSchoolClubCount.getLocator());
        for (WebElement actions : preSchoolList) {
            convertToIngeger = actions.getText();
            preSchoolClubCount += Integer.parseInt(convertToIngeger);
            integerparameters.put(3, preSchoolClubCount);
        }
        // Save parent club count for verification
        List<WebElement> parentList = driver.findElements(ParentClubCount.getLocator());
        for (WebElement actions : parentList) {
            convertToIngeger = actions.getText();
            parentClubCount += Integer.parseInt(convertToIngeger);
            integerparameters.put(4, parentClubCount);
        }
        // Verify actions count visible in statistics tab
        for (int i = 4; i > 1; i--) {
            String actions_count = driver.findElement(page.getActiveInactiveStatisticsLocator("Akcie", i)).getText();
            if (!actions_count.equals(String.valueOf(integerparameters.get(i)))) {
                ReportExtender.logWarning("Numbers in action tab are not the same like in Omama-statistics");
                ReportExtender.logWarning(actions_count);
                ReportExtender.logWarning(String.valueOf(integerparameters.get(i)));
                ReportExtender.logScreen(driver);
            }
        }
        String actions_count = driver.findElement(page.getActiveInactiveStatisticsLocator("Akcie", 1)).getText();
        int allActions = integerparameters.get(2) + integerparameters.get(3) + integerparameters.get(4);
        if (!actions_count.equals(String.valueOf(allActions))) {
            ReportExtender.logWarning("Numbers in action tab are not the same like in Omama-statistics");
            ReportExtender.logWarning(actions_count);
            ReportExtender.logWarning(String.valueOf(allActions));
            ReportExtender.logScreen(driver);
        }
        ReportExtender.logScreen(driver);
    }

    @And("Save clients town")
    public void saveClientsTown() {
        String communityList = "";
        int i = 1;
        String[] community = new String[100];
        waitForElementVisible(driver, page.getAscDescOrderLocator(5), "Wait for town column", 10);
        doubleClickElement(driver, page.getAscDescOrderElement(5), "Sort users town");
        String NextPageEnabled = "false";
        while (NextPageEnabled.equals("false")) {
            NextPageEnabled = driver.findElement(NextPageButton.getLocator()).getAttribute("aria-disabled");
            List<WebElement> communityTitleList = driver.findElements(page.getClientAttributeLocator(8, "."));
            for (WebElement comm : communityTitleList) {
                communityList += comm.getText();
                indexparameters.put(i, communityList);
                community[i] = comm.getText();
                if (community[i].equals(community[i - 1])) {
                    continue;
                }
                i += 1;
            }
            clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
            sleep(1000);
        }
        String list = Arrays.toString(community);
        String List = list.replaceAll("null", "").replaceAll(",", "").replaceAll(" ", "");
        textparameters.put("townList", List);
    }

    @And("Verify community details")
    public void verifyCommunityDetails() {
        String communityList = "";
        waitForElementVisible(driver, page.getStatsTabInStatisticsLocator("Komunity"), "Wait for Komunity tab in statistics", 10);
        clickElementUsingJavascript(driver, page.getStatsTabInStatisticsElement("Komunity"), "Select Komunity tab");
        sleep(1000);
        List<WebElement> communityTitleList = driver.findElements(SaveCommunityList.getLocator());
        for (WebElement comm : communityTitleList) {
            communityList += comm.getText() + ";";
        }
        String[] List = communityList.split(";");
        Arrays.sort(List);
        String allClientTowns = Arrays.toString(List);
        allClientTowns = allClientTowns.replaceAll(";","").replaceAll(" ","").replaceAll(",","");
        new Validation("Verify list of user towns", textparameters.get("townList"), allClientTowns).stringEquals();
    }
}