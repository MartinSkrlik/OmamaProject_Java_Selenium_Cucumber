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


    

}