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
    public By getInputLocator(String value) {return By.xpath("//input[@placeholder='" + value+ "']");}

    public WebElement getDropdownElement(String value) {return driver.findElement(getDropdownLocator(value));}
    public By getDropdownLocator(String value) {return By.xpath("//div[text()='" + value + "']");}

    public WebElement getUserElement(String value) {return driver.findElement(getUserLocator(value));}
    public By getUserLocator(String value)  {return By.xpath("//*[text()='" + value + "']/ancestor::tr");}

    public WebElement getInputElementVerify(String value1,String value2) {return driver.findElement(getInputLocatorVerify(value1,value2));}
    public By getInputLocatorVerify(String value1,String value2)  {return By.xpath("//input[@placeholder='" + value1 + "' and @value='" + value2 + "']");}

    public WebElement getClientInfoByIndexElement(int index1,String value1) {return driver.findElement(getClientInfoByIndexLocator(index1,value1));}
    public By getClientInfoByIndexLocator(int index1,String value1)  {return By.xpath("(//tbody[contains(@class,'tbody')]//td[" + index1 + "])[" + value1 + "]");}

    public WebElement getClientUsernameElement(String value1) {return driver.findElement(getClientUsernameLocator(value1));}
    public By getClientUsernameLocator(String value1)  {return By.xpath("(//div[@class='admin-omama-bottompanel']//h3)[" + value1 + "]");}
}