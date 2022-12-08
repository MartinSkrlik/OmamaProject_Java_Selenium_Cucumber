package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_UserProfilPage {

	private WebDriver driver;

	public enum UserProfilPage {

        GetUserName          (By.xpath("//b[contains(@class,'name')]"),
                "Get new created USERNAME for verfication"),
        GetUserPhoneAdmin    (By.xpath("//*[text()='Telefón:']/following-sibling::td"),
                "Get PHONE NUMBER for verification"),
        GetUserEmailAdmin    (By.xpath("//*[text()='Email:']/following-sibling::td"),
                "Get EMAIL for verification"),
        GetUserPhoneMentor   (By.xpath("//b[contains(text(),'Tel. č.')]//parent::div"),
                "Get PHONE NUMBER for verification"),
        GetUserEmailMentor   (By.xpath("//b[contains(text(),'Email')]//parent::div"),
                "Get EMAIL for verification"),
        SelectCurrentDate    (By.xpath("//td[contains(@class,'today')]"),
                "Pick current DAY from date menu"),
        GetUserStatus        (By.xpath("//div[@class='selected']"),
                "Get user status AKTIVNY or NEAKTIVNY"),
        GetUserTown          (By.xpath("//div[contains(@class,'city')]"),
                "Get user TOWN from user profil"),
        GetStatus            (By.xpath("(//b[contains(@class,'date')])[2]"),
                "Get user status"),
        SpatButton           (By.xpath("(//div[contains(@class,'back')])[2]"),
                "Click on Spat BUTTON")
        ;
        private String description;
        private By findBy;

        private UserProfilPage(By findBy, String description) {
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

    public CestaVon_UserProfilPage(WebDriver driver) {
        this.driver = driver;
    }
}