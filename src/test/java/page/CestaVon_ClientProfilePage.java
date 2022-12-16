package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_ClientProfilePage {

    private WebDriver driver;

    public enum ClientProfilePage {

        ClientFullName    (By.xpath("//b[contains(@class, 'admin-client-name')]"),
                "Cele meno"),
        NameInput(By.xpath("//input[contains(@name, 'firstName')]"),
                "Meno input"),
        SurnameInput(By.xpath("//input[contains(@name, 'lastName')]"),
                "Meno input"),
        ;
        private String description;
        private By findBy;

        private ClientProfilePage(By findBy, String description) {
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
    public CestaVon_ClientProfilePage(WebDriver driver) {
        this.driver = driver;
    }

}