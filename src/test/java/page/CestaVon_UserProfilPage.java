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
//        GetUserPhone   (By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/div[5]/div[1]/b"),
//                "Get PHONE NUMBER for verification"),
//        GetUserEmail   (By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[1]/div[1]/div[5]/div[2]/b"),
//                "Get EMAIL for verification")
        SelectCurrentDate    (By.xpath("//td[contains(@class,'today')]"),
                "Pick current DAY from date menu")
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