import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAndQuizTest {
    private WebDriver driver;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testEasy() {
        driver.navigate().to("https://www.testandquiz.com/selenium/testing.html");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Sample Test Page"));

        driver.findElement(By.id("fname")).sendKeys("Hello world");
        driver.findElement(By.id("idOfButton")).click();

        driver.findElement(By.id("male")).click();
        Assertions.assertThat(driver.findElement(By.id("male")).isSelected()).isTrue();
        Assertions.assertThat(driver.findElement(By.id("female")).isSelected()).isFalse();

        driver.findElement(By.id("female")).click();
        Assertions.assertThat(driver.findElement(By.id("female")).isSelected()).isTrue();
        Assertions.assertThat(driver.findElement(By.id("male")).isSelected()).isFalse();

    }


    @After
    public void afterTest() {
        driver.quit();
    }
}
