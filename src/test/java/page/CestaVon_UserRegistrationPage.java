package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_UserRegistrationPage {

	private WebDriver driver;

	public enum UserRegistrationPage {

	    EmailInput          (By.xpath("(//input[@class='ant-input'])[2]"),
                "Input Email - create user"),
        ConfirmTownInput    (By.xpath("//ul[@class='suggestions']/li"),
                "Confirm Town - create user"),
        DropDownRole        (By.xpath("//*[contains(text(),'Vyber')]"),
                "Dropdown menu - pick role - create user"),
        ;
        private String description;
        private By findBy;

        private UserRegistrationPage(By findBy, String description) {
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
    public CestaVon_UserRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

}