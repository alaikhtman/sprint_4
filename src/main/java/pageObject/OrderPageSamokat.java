package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.MatcherAssert;
import static org.junit.Assert.*;


import java.util.List;

public class OrderPageSamokat {

    private WebDriver driver;

    //Поле имя
    private By nameField = By.xpath(".//*[@id='root']//input[@placeholder='* Имя']");

    //Поле фамилия
    private By lastNameField = By.xpath(".//*[@id='root']//input[@placeholder='* Фамилия']");

    //Поле адрес
    private By addressField = By.xpath(".//*[@id='root']//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле станция метро
    private By metroStationField = By.xpath(".//*[@id='root']//input[@placeholder='* Станция метро']");

    //Поле телефон
    private By phoneField = By.xpath(".//*[@id='root']//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка далее
    private By nextButton = By.xpath(".//*[@id='root']//button[text()='Далее']");

    //Поле даты
    private By dateField = By.xpath(".//*[@id='root']//input[@placeholder ='* Когда привезти самокат']");

    //Выпадающий список срока аренды
    private By rentalPeriodDropdownArrow = By.className("Dropdown-arrow");

    //Чекбокс черного цвета
    private By colorBlackCheckbox = By.xpath(".//*[@id='root']//label[@for = 'black']");

    //Чекбокс серого цвета
    private By colorGreyCheckbox = By.xpath(".//*[@id='root']//label[@for = 'grey']");

    //Поле комментария
    private By commentField = By.xpath(".//*[@id='root']//input[@placeholder='Комментарий для курьера']");

    //Кнопка заказать
    private By orderButton = By.xpath(".//*[@id='root']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка подтверждения заказа
    private By yesButton = By.xpath(".//button[text()='Да']");

    //Модальное окно заказа
    private By orderModalView = By.className("Order_Modal__YZ-d3");

    //Заказ оформлен
    private  By headerOrderModalView = By.className("Order_ModalHeader__3FDaJ");

    //Кнопка кук
    private By cookiesApprove = By.id("rcc-confirm-button");


    public OrderPageSamokat(WebDriver driver) {
        this.driver = driver;
    }



    //Метод заполнения имени
    public void enterName(String name) {
        assertTrue(driver.findElement(nameField).isEnabled());
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    //Метод заполнения фамилии
    public void enterLastName(String lastName) {
        assertTrue(driver.findElement(lastNameField).isEnabled());
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //Метод заполнения адреса
    public void enterAddress(String address) {
        assertTrue(driver.findElement(addressField).isEnabled());
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }

    //Метод выбора метро
    public void chooseMetro(int metroListItem) {
        driver.findElement(metroStationField).click();
        List<WebElement> elements = driver.findElements(By.className("select-search__row"));
        elements.get(metroListItem).click();
    }

    //Метода заполнения телефона
    public void enterPhone(String phoneNumber) {
        assertTrue(driver.findElement(phoneField).isEnabled());
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }

    //Метод нажатия кнопки далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();

    }

    //Метод заполнения даты
    public void enterDate(String date) {
        assertTrue(driver.findElement(dateField).isEnabled());
        driver.findElement(dateField).clear();
        driver.findElement(dateField).sendKeys(date);
    }

    //Метод выбора периода
    public void chooseRentalPeriod(int rentalPeriodItem) {
        driver.findElement(rentalPeriodDropdownArrow).click();
        List<WebElement> elements = driver.findElements(By.className("Dropdown-option"));
        elements.get(rentalPeriodItem).click();
    }

    //Метод выбора цвета
    public void chooseColor(String color) {
        driver.findElement(By.xpath(".//*[@id='root']//label[@for = '" + color + "']")).click();
    }

    //Метод добавление комментария
    public void enterComment (String comment) {
        assertTrue(driver.findElement(commentField).isEnabled());
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }

    //Метод нажатии кнопки заказ
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Метод подтверждения заказа
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }



    //Метод оформления заказа
    public void createOrder(
            String name,
            String lastName,
            String address,
            int metroListItem,
            String phoneNumber,
            String date,
            int rentalPeriodItem,
            String color,
            String comment) {

        enterName(name);
        enterLastName(lastName);
        enterAddress(address);
        chooseMetro(metroListItem);
        enterPhone(phoneNumber);
        clickNextButton();
        enterDate(date);
        chooseRentalPeriod(rentalPeriodItem);
        chooseColor(color);
        enterComment(comment);
        clickOrderButton();
        clickYesButton();
    }

    //метод ожидания прогрузки окна заказа
    public void waitForLoadOrderModalView() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(orderModalView)));
    }

   //метод проверки статуса заказа
    public void checkOrderStatus (String expectedStatus) {
        waitForLoadOrderModalView();
        MatcherAssert.assertThat(driver.findElement(headerOrderModalView).getText(), is(expectedStatus));
    }



}
