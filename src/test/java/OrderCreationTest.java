
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.MainPageSamokat;
import org.junit.runners.Parameterized;
import pageObject.OrderPageSamokat;
import io.github.bonigarcia.wdm.WebDriverManager;


@RunWith(Parameterized.class)
public class OrderCreationTest {

    WebDriver driver;

    @Before
    public void startUp() {
       WebDriverManager.chromedriver().setup();

    }

    public String name;
    public String lastName;
    public String address;
    public int metroListItem;
    public String phoneNumber;
    public String date;
    public int rentalPeriodItem;
    public String color;
    public String comment;


    public OrderCreationTest(String name,
                             String lastName,
                             String address,
                             int metroListItem,
                             String phoneNumber,
                             String date,
                             int rentalPeriodItem,
                             String color,
                             String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroListItem = metroListItem;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriodItem = rentalPeriodItem;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] createOrders() {
        return new Object[][]{
                {"Алексендра", "Петрова", "Москва, ул.Ленина 7", 2, "+79046098780", "12.06.2024", 0, "black", "Хочу лететь как птица"},
             //   {"Виталий", "Иванов", "Москва, ул.Кирова 7", 3, "+79046098980", "12.07.2024", 5, "grey", ""},

        };
    }


    @Test
    public void checkOrderCreationWithUpperButton() {

       driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        MainPageSamokat mainPage = new MainPageSamokat(driver);
        mainPage.setCookiesApprove();
        mainPage.createOrderWithUpperButton();

        OrderPageSamokat orderPage = new OrderPageSamokat(driver);
        orderPage.createOrder(name, lastName, address, metroListItem, phoneNumber, date, rentalPeriodItem, color, comment);
        orderPage.checkOrderStatus("Заказ оформлен");


    }

    @Test
    public void checkOrderCreationWithLowerButton() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        MainPageSamokat mainPage = new MainPageSamokat(driver);
        mainPage.setCookiesApprove();
        mainPage.createOrderWithLowerButton();

        OrderPageSamokat orderPage = new OrderPageSamokat(driver);
        orderPage.createOrder(name, lastName, address, metroListItem, phoneNumber, date, rentalPeriodItem, color, comment);
        orderPage.checkOrderStatus("Заказ оформлен");


    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
