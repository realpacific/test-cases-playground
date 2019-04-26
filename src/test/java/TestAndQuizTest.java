import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
        // Use Select class for selecting value from dropdown

        Select dropdown = new Select(driver.findElement(By.id("testingDropdown")));
        dropdown.selectByVisibleText("Automation Testing");


        //Scroll down the webpage by 5000 pixels
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0, 5000)");


        //Maximize the browser
        driver.manage().window().maximize();

    }


    @After
    public void afterTest() {
        driver.quit();
    }
}
