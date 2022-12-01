//package page;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class SAUCEDEMO_LoginPage {
//
//	private WebDriver driver;
//
//    public enum loginPageItems {
//
//    	UsernameElement (By.xpath("//input[@id='user-name']"),
//                     	"USERNAME Input"),
//    	PasswordElement (By.xpath("//input[@id='password']"),
//    					"PASSWORD Input"),
//    	LoginButton 	(By.xpath("//input[@id='login-button']"),
//    					"LOGIN Button"),
//    	PageTitle 		(By.xpath("//span[@class='title']"),
//    					"PAGE TITLE"),
//    	ErrorMessage 	(null,
//    					"ERROR Message"),
//        ;
//
//    	private String description;
//        private By findBy;
//
//        private loginPageItems(By findBy, String description) {
//            this.description = description;
//            this.findBy = findBy;
//        }
//
//        public String getDescription(){
//            return description;
//        }
//
//        public By getLocator(){
//            return findBy;
//        }
//
//        public WebElement getElement(WebDriver driver) {
//        	return driver.findElement(getLocator());
//        }
//    }
//
//    public SAUCEDEMO_LoginPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public WebElement getErrorMessageElement(String value) {
//        return driver.findElement(getErrorMessageLocator(value));
//    }
//
//    public By getErrorMessageLocator(String value) {
//    	//the following line is an example of how to create a dynamic xpath
//        return By.xpath("//h3[@data-test='" + value + "']");
//    }
//}