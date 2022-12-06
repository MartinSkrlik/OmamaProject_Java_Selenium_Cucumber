package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_UsersPage {

    private WebDriver driver;

    public enum adminMainPageItems {

        RolaButton  (By.xpath("//label[contains(text(), 'Rola')]"),
                "ROLA BUTTON"),
        RolaOption  (null,
                "ROLA OPTION"),
        GetNameKlientiTab  (By.xpath("//tbody[contains(@class,'tbody')]//td[2]"),
                "Get every NAME visible on the klienti tab"),
        GetSurnameKlientiTab  (By.xpath("//tbody[contains(@class,'tbody')]//td[3]"),
                "Get every SURNAME visible on the klienti tab"),
        GetUsernamePuzivateliaTab   (By.xpath("//div[@class='admin-omama-bottompanel']//h3"),
                "Get every USERNAME of clients visible in omama profil page"),
        ;
        private String description;
        private By findBy;

        private adminMainPageItems(By findBy, String description) {
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

    public CestaVon_UsersPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRolaOptionElement(String value) {
        return driver.findElement(getRolaOptionLocator(value));
    }

    public By getRolaOptionLocator(String value) {
        return By.xpath("//li[contains(@class, 'ant-dropdown-menu-item') and contains(text(), '" + value + "')]");
    }

    public WebElement getRowByNameElement(String value) {
        return driver.findElement(getRowByNameLocator(value));
    }

    public By getRowByNameLocator(String value) {
        return By.xpath("//tr[td[div[contains(text(), '" + value + "')]]]");
    }

    public WebElement getNameFromRowByNameElement(String value) {
        return driver.findElement(getNameFromRowByNameLocator(value));
    }

    public By getNameFromRowByNameLocator(String value) {
        return By.xpath("//tr[td[div[contains(text(), '" + value + "')]]]/td[2]/div");
    }

    public WebElement getEmailFromRowByNameElement(String value) {
        return driver.findElement(getEmailFromRowByNameLocator(value));
    }

    public By getEmailFromRowByNameLocator(String value) {
        return By.xpath("//tr[td[div[contains(text(), '" + value + "')]]]/td[3]");
    }

    public WebElement getPhoneNumberFromRowByNameElement(String value) {
        return driver.findElement(getPhoneNumberFromRowByNameLocator(value));
    }

    public By getPhoneNumberFromRowByNameLocator(String value) {
        return By.xpath("//tr[td[div[contains(text(), '" + value + "')]]]/td[4]");
    }

    public WebElement getTownFromRowByNameElement(String value) {
        return driver.findElement(getTownFromRowByNameLocator(value));
    }

    public By getTownFromRowByNameLocator(String value) {
        return By.xpath("//tr[td[div[contains(text(), '" + value + "')]]]/td[6]");
    }

}