
import org.hamcrest.MatcherAssert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPageSamokat;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;


@RunWith(Parameterized.class)
public class MainPageTest {

    WebDriver driver;

    @Before
    public void startUp() {

        WebDriverManager.chromedriver().setup();
    }

    public int itemImportantQuestion;
    public String expectedText;

    public MainPageTest(int itemImportantQuestion, String expectedText) {
        this.itemImportantQuestion = itemImportantQuestion;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getTexts() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }


    @Test
    public void checkListImportantQuestions() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        MainPageSamokat mainPage = new MainPageSamokat(driver);

       List<String> listImportantQuestionTexts = mainPage.getTextFromImportantList();
       MatcherAssert.assertThat(listImportantQuestionTexts.get(itemImportantQuestion), is(expectedText));


    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
