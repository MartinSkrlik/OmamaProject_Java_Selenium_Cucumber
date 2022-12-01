package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_MainPage {

	private WebDriver driver;

	public enum MainPage {

        MenuButton      (By.xpath("//div[@class='menu']"),
                "MENU BUTTON"),
        OdhlasitButton  (By.xpath("//li[@id='sign_out_button']"),
                "ODHLASIT BUTTON"),
        SelectedTab     (By.xpath("//h1[contains(@class,'headline')]"),
                "SELECTED TAB"),
        MenoSearchBar   (By.xpath("//input[contains(@class,'input')]"),
                "Search bar MENO"),
        SelectNewCreatedUser    (By.xpath("//tr[@data-row-key='Martin_TEST']"),
                "Click on new created USER after searching in MENO search bar")
        ;

        private String description;
        private By findBy;

        private MainPage(By findBy, String description) {
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
        public CestaVon_MainPage(WebDriver driver) { this.driver = driver; }
}