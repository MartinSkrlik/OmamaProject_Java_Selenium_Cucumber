package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_SettingsPage {

    private WebDriver driver;

    public enum settingsPageItems {

        ChangePasswordButton   (By.xpath("//button[span[contains(text(), 'Zmeniť heslo')]]"),
                "CHANGE PASSWORD BUTTON"),
        OldPasswordInput       (By.xpath("//input[contains(@placeholder, 'Pôvodné heslo')]"),
                "OLD PASSWORD INPUT"),
        NewPasswordInput       (By.xpath("//input[contains(@placeholder, 'Nové heslo')]"),
                "NEW PASSWORD INPUT"),
        ConfirmNewPasswordInput(By.xpath("//input[contains(@placeholder, 'Potvrdiť nové heslo')]"),
                "CONFIRM NEW PASSWORD INPUT"),
        ConfirmPasswordChangeButton (By.xpath("//button[span[contains(text(), 'Zmeniť')]]"),
                "CONFIRM PASSWORD CHANGE BUTTON")
        ;

        private String description;
        private By findBy;

        private settingsPageItems(By findBy, String description) {
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

    public CestaVon_SettingsPage(WebDriver driver) { this.driver = driver; }

}