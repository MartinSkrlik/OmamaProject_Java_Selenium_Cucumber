package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_ClientProfilePage {

    private WebDriver driver;

    public enum ClientProfilePage {

        ClientFullName    (By.xpath("//b[contains(@class, 'admin-client-name')]"),
                "Client Full Name"),
        NameInput(By.xpath("//input[contains(@name, 'firstName')]"),
                "Name Input"),
        SurnameInput(By.xpath("//input[contains(@name, 'lastName')]"),
                "Surname input"),
        OmamaDropdown(By.xpath("(//div[contains(@class, 'omamaDropdown')]/div)[1]"),
                "Omama dropdown"),
        SaveButton(By.xpath("//div[contains(@class, 'registration_footer')]//span[text()='Uložiť']/parent::button"),
                "Save Button"),
        ActivationCalendarInput(By.xpath("(//span[contains(@class, 'ant-calendar-picker')])[3]"),
                "Activation Calendar Input"),
        CalendarTodayCell(By.xpath("//td[contains(@class, 'ant-calendar-today')]"),
                "Calendar Today Cell"),
        AssignedOmama(By.xpath("(//b[contains(@class, 'admin-client-date')])[2]"),
                "Assigned Omama"),
        ClientSubmenuTab(By.xpath("//li/a[contains(@class, 'active')]"),
                "Client submenu tab"),
        LessonDateInput(By.xpath("//h3[text()='Dátum']//following-sibling::input"),
                "Lesson date input"),
        ;
        private String description;
        private By findBy;

        private ClientProfilePage(By findBy, String description) {
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
    public CestaVon_ClientProfilePage(WebDriver driver) {
        this.driver = driver;
    }

}