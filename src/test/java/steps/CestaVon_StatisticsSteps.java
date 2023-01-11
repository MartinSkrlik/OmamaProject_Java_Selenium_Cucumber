package steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.CestaVon_CommonPage;
import runner.TestRunner;
import utility.ReportExtender;

import java.util.HashMap;
import java.util.List;

import static page.CestaVon_UsersPage.usersPageItems.NextPageButton;

public class CestaVon_StatisticsSteps extends TestStepActions {

    static TestRunner TestRunner = new TestRunner();
    private static HashMap<String, Object> globalParametersMap = TestRunner.getGlobalParametersMap();
    private WebDriver driver = (WebDriver) globalParametersMap.get("driver");
    CestaVon_CommonPage page = new CestaVon_CommonPage(driver);
    HashMap<String, String> textparameters = new HashMap<>();

    @And("Save list")
    public void saveList() {
        int allOmamasDysplayedOnPage = 10;
        String inactive_omama_list = "", active_omama_list = "";
        clickElementUsingJavascript(driver, page.getAscDescOrderElement(5), "Sort inactive omamas");
        sleep(1000);

        // Get inactive omama list
        boolean isEnabled = driver.findElement(NextPageButton.getLocator()).isEnabled();
        int inactive_omamas = driver.findElements(page.getListActiveInactiveUserLocator("neaktívna")).size();
        ReportExtender.logInfo(Integer.toString(inactive_omamas));
        if (inactive_omamas > 0) {
            while (inactive_omamas > 0) {
                List<WebElement> omama_list = driver.findElements(page.getListActiveInactiveUserLocator("neaktívna"));
                for (WebElement omama : omama_list) {
                    inactive_omama_list += omama.getText() + " ";
                    textparameters.put("inactive_omama_list", inactive_omama_list);
                }
                if (inactive_omamas == allOmamasDysplayedOnPage && isEnabled) {
                    clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
                    waitForFullPageLoad(driver, 10);
                    inactive_omamas = driver.findElements(page.getListActiveInactiveUserLocator("neaktívna")).size();
                } else break;
            }
        }
        else {textparameters.put("inactvie_omama_count", "noOmamas");}
        ReportExtender.logInfo(inactive_omama_list);
        ReportExtender.logInfo(textparameters.get("inactive_omama_list"));

        // Get active omama list
        clickElementUsingJavascript(driver, page.getAscDescOrderElement(5), "Sort active omamas");
        sleep(1000);
        int active_omamas = driver.findElements(page.getListActiveInactiveUserLocator("aktívna")).size();
        ReportExtender.logInfo(Integer.toString(active_omamas));
        while (active_omamas > 0) {
            List<WebElement> omama_list = driver.findElements(page.getListActiveInactiveUserLocator("aktívna"));
            for (WebElement omama : omama_list) {
                active_omama_list += omama.getText() + " ";
                textparameters.put("active_omama_list", active_omama_list);
            }
            if (active_omamas == allOmamasDysplayedOnPage && isEnabled) {
                clickElementUsingJavascript(driver, NextPageButton.getElement(driver), NextPageButton.getDescription());
                waitForFullPageLoad(driver,10);
                active_omamas = driver.findElements(page.getListActiveInactiveUserLocator("aktívna")).size();
            }
            else break;
        }
        ReportExtender.logInfo(active_omama_list);
        ReportExtender.logInfo(textparameters.get("active_omama_list"));
    }

    @And("Open {string} statistics and verify details")
    public void openStatisticsAndVerifyDetails(String user) {
        waitForElementVisible(driver, page.getActiveInactiveStatisticsLocator(user,"aktívne"),"Wait for " + user + "statistic visible ",10);
        clickElementUsingJavascript(driver, page.getActiveInactiveStatisticsElement(user,"aktívne"),"Unwrap active user list");




        sleep(5000);


    }
}