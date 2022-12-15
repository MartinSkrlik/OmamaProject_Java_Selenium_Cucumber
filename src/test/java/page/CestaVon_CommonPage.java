package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_CommonPage {

	private WebDriver driver;

	public enum MainPage {

        MenuButton      (By.xpath("//div[@class='menu']"),
                "MENU BUTTON"),
        LogOffButton  (By.xpath("//li[@id='sign_out_button']"),
                "ODHLASIT BUTTON"),
        SelectedTab     (By.xpath("//h1[contains(@class,'headline')]"),
                "SELECTED TAB"),
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
    public By getButtonLocator(String value) {return By.xpath("//*[text()='" + value + "']/parent::button");}//pozriet xpath, *

    public WebElement getTabElement(String value) {return driver.findElement(getTabLocator(value));}
    public By getTabLocator(String value) {return By.xpath("//li[text()='" + value + "']");}

    public WebElement getInputElement(String value) {return driver.findElement(getInputLocator(value));}
    public By getInputLocator(String value) {return By.xpath("//input[@placeholder='" + value+ "']");}

    public WebElement getDropdownElement(String value) {return driver.findElement(getDropdownLocator(value));}
    public By getDropdownLocator(String value) {return By.xpath("//div[text()='" + value + "']");}

    public WebElement getUserElement(String value) {return driver.findElement(getUserLocator(value));}
    public By getUserLocator(String value)  {return By.xpath("//*[text()='" + value + "']/ancestor::tr");}

    public WebElement getFirstUserElement(String value) {return driver.findElement(getFirstUserLocator(value));}
    public By getFirstUserLocator(String value)  {return By.xpath("//*[contains(text(),'" + value + "')]/ancestor::tr");}

    public WebElement getInputElementVerify(String value1,String value2) {return driver.findElement(getInputLocatorVerify(value1,value2));}
    public By getInputLocatorVerify(String value1,String value2)  {return By.xpath("//input[@placeholder='" + value1 + "' and @value='" + value2 + "']");}

    public WebElement getClientInfoByIndexElement(int index1,String value1) {return driver.findElement(getClientInfoByIndexLocator(index1,value1));}
    public By getClientInfoByIndexLocator(int index1,String value1)  {return By.xpath("(//tbody[contains(@class,'tbody')]//td[" + index1 + "])[" + value1 + "]");}

    public WebElement getClientUsernameElement(String value1) {return driver.findElement(getClientUsernameLocator(value1));}
    public By getClientUsernameLocator(String value1)  {return By.xpath("(//div[@class='admin-omama-bottompanel']//h3)[" + value1 + "]");}

    public WebElement getInputTextfieldElement(String value) {return driver.findElement(getInputTextfieldLocator(value));}
    public By getInputTextfieldLocator(String value) {return By.xpath("//input[contains(@placeholder,'" + value + "')]");}

    public WebElement getYesNoPickerElement(String value1,String value2) {return driver.findElement(getYesNoPickerLocator(value1,value2));}
    public By getYesNoPickerLocator(String value1,String value2) {return By.xpath("//*[text()='" + value1 + "']/ancestor::div[@class='yesNoQuestion']//div[text()='" + value2 + "']");}

    public WebElement getDatePickerElement(int index) {return driver.findElement(getDatePickerLocator(index));}
    public By getDatePickerLocator(int index)  {return By.xpath("(//input[contains(@class,'calendar-picker')])[" + index + "]");}

    public WebElement getOmamaSpecificationElement(int index) {return driver.findElement(getOmamaSpecificationLocator(index));}
    public By getOmamaSpecificationLocator(int index) {return By.xpath("(//div[contains(@class,'trigger')])[" + index + "]");}

    public WebElement getTextfieldIndexElement(String value, int index) {return driver.findElement(getTextfieldIndexLocator(value,index));}
    public By getTextfieldIndexLocator(String value, int index) {return By.xpath("(//input[contains(@placeholder,'" + value + "')])[" + index + "]");}

    public WebElement getInputTextareaElement(String value) {return driver.findElement(getInputTextareaLocator(value));}
    public By getInputTextareaLocator(String value) {return By.xpath("//*[@name='" + value + "']");}

    public WebElement getInputDropdownElement(String value,int index) {return driver.findElement(getInputDropdownLocator(value,index));}
    public By getInputDropdownLocator(String value, int index) {return By.xpath("(//span[text()='" + value + "'])[" + index + "]");}

    public WebElement getTabIndexElement(String value,int index) {return driver.findElement(getTabIndexLocator(value,index));}
    public By getTabIndexLocator(String value, int index) {return By.xpath("(//li[text()='" + value + "'])[" + index + "]");}

}

