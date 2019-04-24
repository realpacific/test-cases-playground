import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testEasy() {
        driver.navigate().to("https://www.wikipedia.org/");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Wikipedia"));

        driver.findElement(By.id("searchInput")).sendKeys("prashant barahi");
        driver.findElement(By.className("pure-button")).submit();
        driver.navigate().back();
        Assertions.assertThat(driver.findElement(By.id("js-link-box-en")).getText()).contains("English");

    }


    @After
    public void afterTest() {
        driver.quit();
    }
}
