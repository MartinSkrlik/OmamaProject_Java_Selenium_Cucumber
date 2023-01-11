package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CestaVon_ClientProfilePage {

    private WebDriver driver;

    public enum ClientProfilePage {

        ClientFullName(By.xpath("//b[contains(@class, 'admin-client-name')]"),
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
        FinishedLessonRow(By.xpath("//td[text()='Zrealizovaná']/parent::tr"),
                "Finished Lesson Row"),
        LessonDetailDateTime(By.xpath("(//div[@class='ActionDetailAdmin-main']/div/div)[1]"),
                "Lesson Detail Date Time"),
        LessonDetailNameAndAge(By.xpath("(//div[@class='ActionDetailAdmin-main']/div/div/div)[1]"),
                "Lesson Detail Name And Age"),
        LessonDetailActivityHeader(By.xpath("(//div[contains(@class, 'clientProfileAction-headline')])[1]"),
                "Lesson Detail Activity Header"),
        LessonDetailRatingHeader(By.xpath("(//div[contains(@class, 'clientProfileAction-headline')])[2]"),
                "Lesson Detail Rating Header"),
        LessonDetailPhotographiesHeader(By.xpath("(//div[contains(@class, 'clientProfileAction-headline')])[3]"),
                "Lesson Detail Photographies Header"),
        LessonDetailCommentHeader(By.xpath("(//div[contains(@class, 'clientProfileAction-headline')])[4]"),
                "Lesson Detail Comment Header"),
        LessonDetailRatingQuestions(By.xpath("//div[@class='clientend-question']"),
                "Lesson Detail Rating Questions"),
        LessonDetailRatingSelectedAnswers(By.xpath("//div[@class='clientend-question__buttons']/div[@class='selected']"),
                "Lesson Detail Rating Selected Answers"),
        ;
        private String description;
        private By findBy;

        private ClientProfilePage(By findBy, String description) {
            this.description = description;
            this.findBy = findBy;
        }

        public String getDescription() {
            return description;
        }

        public By getLocator() {
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