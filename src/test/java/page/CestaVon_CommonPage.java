package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_CommonPage {

	private WebDriver driver;

	public enum MainPage {

        MenuButton          (By.xpath("//div[@class='menu']"),
                "MENU BUTTON"),
        LogOffButton        (By.xpath("//li[@id='sign_out_button']"),
                "ODHLASIT BUTTON"),
        SelectedTab         (By.xpath("//h1[contains(@class,'headline')]"),
                "SELECTED TAB"),
        FirstTableRow       (By.xpath("//tbody/tr[1]"),
                "First table row"),
        TableColumns        (By.xpath("//thead//th"),
                "Table rows"),
        TableRows           (By.xpath("//tbody//tr"),
                "Table columns"),
        PaginationNextButton(By.xpath("//li[contains(@class, 'pagination-next')]"),
                "Pagination next button"),
        PaginationNumbers   (By.xpath("//li[contains(@class, 'pagination-item')]"),
                "Pagination numbers"),
        ActivityName        (By.xpath("(//div[@class='clientsBox']//h3)[1]"),
                "Save activity name and number"),
        ActivityList        (By.xpath("//td[contains(@class,'column-sort')]"),
                "List of activities"),
        DeleteActivityButton(By.xpath("(//span[text()='Zmazať']/parent::button)[5]"),
                "Confirm activity deletion with button Zmazat")
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
    public By getButtonLocator(String value) {return By.xpath("//span[text()='" + value + "']/parent::button");}

    public WebElement getTabElement(String value) {return driver.findElement(getTabLocator(value));}
    public By getTabLocator(String value) {return By.xpath("//li[text()='" + value + "']");}

    public WebElement getInputElement(String value) {return driver.findElement(getInputLocator(value));}
    public By getInputLocator(String value) {return By.xpath("//input[@placeholder='" + value+ "']");}

    public WebElement getDropdownElement(String value) {return driver.findElement(getDropdownLocator(value));}
    public By getDropdownLocator(String value) {return By.xpath("//div[text()='" + value + "']");}

    public WebElement getUserElement(String value) {return driver.findElement(getUserLocator(value));}
    public By getUserLocator(String value)  {return By.xpath("//*[text()='" + value + "']/ancestor::tr");}

    public WebElement getFirstUserElement(String value) {return driver.findElement(getFirstUserLocator(value));}
    public By getFirstUserLocator(String value)  {return By.xpath("//div[contains(text(),'" + value + "')]/ancestor::tr");}

    public WebElement getInputElementVerify(String value1,String value2) {return driver.findElement(getInputLocatorVerify(value1,value2));}
    public By getInputLocatorVerify(String value1,String value2)  {return By.xpath("//input[@placeholder='" + value1 + "' and @value='" + value2 + "']");}

    public WebElement getClientInfoByIndexElement(int index1,String value1) {return driver.findElement(getClientInfoByIndexLocator(index1,value1));}
    public By getClientInfoByIndexLocator(int index1,String value1)  {return By.xpath("(//tbody[contains(@class,'tbody')]//td[" + index1 + "])[" + value1 + "]");}

    public WebElement getClientUsernameElement(String value1) {return driver.findElement(getClientUsernameLocator(value1));}
    public By getClientUsernameLocator(String value1)  {return By.xpath("(//div[@class='admin-omama-bottompanel']//h3)[" + value1 + "]");}

    public WebElement getInputTextfieldElement(String value) {return driver.findElement(getInputTextfieldLocator(value));}
    public By getInputTextfieldLocator(String value) {return By.xpath("//input[contains(@placeholder,'" + value + "')]");}

    public WebElement getTableColumnPredecessorsElement(String value) {
        return driver.findElement(getTableColumnPredecessorsLocator(value));
    }

    public By getTableColumnPredecessorsLocator(String value) {
        return By.xpath("//span[text()='" + value + "']/ancestor::th/preceding-sibling::th");
    }

    public WebElement getTableNameValueElement(int row, int column) {
        return driver.findElement(getTableNameValueLocator(row, column));
    }

    public By getTableNameValueLocator(int row, int column) {
        return By.xpath("//tbody/tr[" + row + "]/td[" + column + "]/div");
    }

    public WebElement getTableValueElement(int row, int column) {
        return driver.findElement(getTableValueLocator(row, column));
    }

    public By getTableValueLocator(int row, int column) {
        return By.xpath("//tbody/tr[" + row + "]/td[" + column + "]");
    }

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

    public WebElement getDropdownItemElement(String value) {return driver.findElement(getDropdownItemLocator(value));}
    public By getDropdownItemLocator(String value) {return By.xpath("//li[text()='" + value + "']");}

    public WebElement getGalleryCountElement(String value) {return driver.findElement(getGalleryCountLocator(value));}
    public By getGalleryCountLocator(String value) {return By.xpath("(//div[contains(@class,'bottompanel')]//div[contains(@class,'galleryFolder')])[" + value + "]");}

    public WebElement getPictureCountElement(String value) {return driver.findElement(getPictureCountLocator(value));}
    public By getPictureCountLocator(String value) {return By.xpath("(//div[contains(@class,'bottompanel')]//div[contains(@class,'imageWrapper')])[" + value + "]");}

    public WebElement getProfilTabElement(String value) {return driver.findElement(getProfilTabLocator(value));}
    public By getProfilTabLocator(String value) {return By.xpath("//a[text()='" + value + "']");}

    public WebElement getActivityInputElement(String value) {return driver.findElement(getActivityInputLocator(value));}
    public By getActivityInputLocator(String value) {return By.xpath("//textarea[@placeholder='" + value + "']");}

    public WebElement getPlusButtonElement(int index) {return driver.findElement(getPlusButtonLocator(index));}
    public By getPlusButtonLocator(int index) {return By.xpath("(//i[contains(@class,'plus')])[" + index + "]");}

    public WebElement getActivityTextElement(int index) {return driver.findElement(getActivityTextLocator(index));}
    public By getActivityTextLocator(int index) {return By.xpath("(//div[@class='clientsBox']//div)[" + index + "]");}

    public WebElement getActivityAttributeElement(int index) {return driver.findElement(getActivityAttributeLocator(index));}
    public By getActivityAttributeLocator(int index) {return By.xpath("(//tr[contains(@class,'level')]//td)[" + index + "]");}

    public WebElement getAscDescOrderElement(int index) {return driver.findElement(getAscDescOrderLocator(index));}
    public By getAscDescOrderLocator(int index) {return By.xpath("(//div[@title='Zoradiť'])[" + index + "]");}

    public WebElement getListActiveInactiveUserElement(String value) {return driver.findElement(getListActiveInactiveUserLocator(value));}
    public By getListActiveInactiveUserLocator(String value) {return By.xpath("//td[text()='" + value + "']/parent::tr/td[2]/div");}

    public WebElement getActiveInactiveStatisticsElement(String value1, String value2) {return driver.findElement(getActiveInactiveStatisticsLocator(value1,value2));}
    public By getActiveInactiveStatisticsLocator(String value1, String value2) {return By.xpath("//div[text()='" + value1 + "']/parent::div[@class='userCounter']//div[text()='" + value2 + "']");}

}

