package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_StatisticsPage {

	private WebDriver driver;

	public enum StatisticsPage {

	    SaveUserListFromStatistics  (By.xpath("//div[contains(@class,'confirm-content')]/div/div"),
                "Save user list from statistics"),
        NonCanceledActionCount      (By.xpath("(//tbody)[2]//div/span[1]"),
                "Save count of non canceled actions from statistics tab"),
        PreSchoolClubCount          (By.xpath("(//tbody)[2]//td[7]"),
                "Save count of pre-school club from statistics tab"),
        ParentClubCount             (By.xpath("(//tbody)[2]//td[8]"),
                "Save count of pre-parent club from statistics tab")
        ;
        private String description;
        private By findBy;

        private StatisticsPage(By findBy, String description) {
            this.description = description;
            this.findBy = findBy;
        }

        public String getDescription(){
            return description;
        }

        public By getLocator(){
            return findBy;
        }

        public WebElement getElement(WebDriver driver) {
            return driver.findElement(getLocator());
        }
    }
    public CestaVon_StatisticsPage(WebDriver driver) { this.driver = driver; }

}