import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import page_object.MainPageSamokat;

import static config.Config.URL;

public class BaseTest {

    WebDriver driver;

    @Before
    public void startUp() {

        WebDriverManager.chromedriver().setup();
        MainPageSamokat mainPage = new MainPageSamokat(driver);
        driver = new ChromeDriver();
        driver.get(URL);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

