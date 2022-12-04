package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_UserRegistrationPage {

	private WebDriver driver;

	public enum UserRegistrationPage {

        NameSurnameInput    (By.xpath("//input[contains(@placeholder,'Priezvisko')]"),
                "Input Name and Surname - create user"),
        EmailInput          (By.xpath("(//input[@class='ant-input'])[2]"),
                "Input Email - create user"),
        RegionInput         (By.xpath("//input[contains(@placeholder,'Región')]"),
                "Input Region - create user"),
        TownInput           (By.xpath("//input[contains(@placeholder,'Mesto')]"),
                "Input Town - create user"),
        ConfirmTownInput    (By.xpath("//ul[@class='suggestions']/li"),
                "Confirm Town - create user"),
        StreetInput         (By.xpath("//input[contains(@placeholder,'Ulica')]"),
                "Input Street - create user"),
        UsernameInput       (By.xpath("//input[contains(@placeholder,'Uzivatelske')]"),
                "Input Username - create user"),
        PasswordInput       (By.xpath("//input[contains(@placeholder,'Heslo')]"),
                "Input Password - create user"),
        AgainHesloInput     (By.xpath("//input[contains(@placeholder,'Zopakovat')]"),
                "Input Heslo Again - create user"),
        PhoneNumberInput    (By.xpath("//input[contains(@placeholder,'cislo')]"),
                "Input Phone Number - create user"),
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