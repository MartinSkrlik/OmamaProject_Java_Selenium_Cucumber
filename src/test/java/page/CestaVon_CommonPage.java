package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_CommonPage {

	private WebDriver driver;

	public enum MainPage {

        MenuButton      (By.xpath("//div[@class='menu']"),
                "MENU BUTTON"),
        OdhlasitButton  (By.xpath("//li[@id='sign_out_button']"),
                "ODHLASIT BUTTON"),
        SelectedTab     (By.xpath("//h1[contains(@class,'headline')]"),
                "SELECTED TAB"),
        NextPageButton  (By.xpath("//li[contains(@title,'Nasledujúca')]"),
                "Click on NEXT page"),
        NextPageButtonDisabled  (By.xpath("//li[contains(@aria-disabled,'true') and contains(@title,'Nasledujúca')]"),
                "Next page BUTTON is disabled"),
        InputUserName    (By.xpath("//input[contains(@class,'input')]"),
                "Input USERNAME into input")
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
        public CestaVon_CommonPage(WebDriver driver) { this.driver = driver; }

    public WebElement getErrorMessageElement(String value) {return driver.findElement(getErrorMessageLocator(value));}
    public By getErrorMessageLocator(String value) {return By.xpath("//p[text()='" + value + "']");}

    public WebElement getButtonElement(String value) {return driver.findElement(getButtonLocator(value));}
    public By getButtonLocator(String value) {return By.xpath("//*[text()='" + value + "']/parent::button");}

    public WebElement getTabElement(String value) {return driver.findElement(getTabLocator(value));}
    public By getTabLocator(String value) {return By.xpath("//li[text()='" + value + "']");}

    public WebElement getInputElement(String value) {return driver.findElement(getInputLocator(value));}
    public By getInputLocator(String value) {return By.xpath("//input[contains(@placeholder,'" + value + "')]");}

    public WebElement getDropdownElement(String value) {return driver.findElement(getDropdownLocator(value));}
    public By getDropdownLocator(String value) {return By.xpath("//div[text()='" + value + "']");}

    public WebElement getUserElement(String value) {return driver.findElement(getUserLocator(value));}
    public By getUserLocator(String value)  {return By.xpath("//*[text()='" + value + "']/ancestor::tr");}
}