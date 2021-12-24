package majescoTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.MajescoSearchPage;
import pages.AboutMajescoPage;
import utils.ReadProperties;

public class MajescoBaseTest {
    public WebDriver driver;
    public MajescoSearchPage majescoSearchPage;
    public MajescoSearchPage majescoSearchPagePF;
    public AboutMajescoPage aboutMajescoPage;
    public AboutMajescoPage aboutMajescoPagePF;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {
        String browserVersion = ReadProperties.readConfigProperty("chrome.version");
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void methodLevelSetup() {
        majescoSearchPagePF = PageFactory.initElements(driver, MajescoSearchPage.class);
        aboutMajescoPagePF = PageFactory.initElements(driver, AboutMajescoPage.class);
    }

    @AfterClass
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
