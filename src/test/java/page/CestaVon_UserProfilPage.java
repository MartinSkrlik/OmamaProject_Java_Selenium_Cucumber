package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_UserProfilPage {

	private WebDriver driver;

	public enum UserProfilPage {

        GetUserName    (By.xpath("//b[contains(@class,'name')]"),
                "Get new created USERNAME for verfication"),
        GetUserPhone   (By.xpath("//*[text()='Telefón:']/following-sibling::td"),
                "Get PHONE NUMBER for verification"),
        GetUserEmail    (By.xpath("//*[text()='Email:']/following-sibling::td"),
                "Get EMAIL for verification")
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