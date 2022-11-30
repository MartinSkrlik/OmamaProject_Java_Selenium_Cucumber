package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_LoginPage {

	private WebDriver driver;

    public enum loginPageItems {

    	UsernameElement (By.xpath("//input[@name='username']"),
                     	"USERNAME Input"),
    	PasswordElement (By.xpath("//input[@name='password']"),
    					"PASSWORD Input"),
    	LoginButton 	(By.xpath("//button[contains(@class,'loginButton')]"),
    					"Click on LOGIN Button"),
        ErrorMessage 	(null,
    					"ERROR Message"),
        PincodeElement  (By.xpath("//input[contains(@placeholder,'pin')]"),
                        "PINCODE Input"),

        ;

    	private String description;
        private By findBy;

        private loginPageItems(By findBy, String description) {
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

    public enum MainPage {

        MenuButton      (By.xpath("//div[@class='menu']"),
                "MENU BUTTON"),
        OdhlasitButton  (By.xpath("//li[@id='sign_out_button']"),
                "ODHLASIT BUTTON"),
        SelectedTab     (By.xpath("//h1[@class='admin_headline']"),
                "NAME FOR SELECTED TAB")
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
        RegistrovatButton   (By.xpath("//*[text()='Registrovať']/parent::button"),
                "Click on Registrovat button")
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

    public CestaVon_LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement getErrorMessageElement(String value) {return driver.findElement(getErrorMessageLocator(value));}
    public By getErrorMessageLocator(String value) {return By.xpath("//p[text()='" + value + "']");}

    public WebElement getButtonElement(String value) {return driver.findElement(getButtonLocator(value));}
    public By getButtonLocator(String value) {return By.xpath("//*[text()='" + value + "']/parent::button");}

    public WebElement getTabElement(String value) {return driver.findElement(getTabLocator(value));}
    public By getTabLocator(String value) {return By.xpath("//*[text()='" + value + "']");}


}