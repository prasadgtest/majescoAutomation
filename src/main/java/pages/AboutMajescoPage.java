package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.BasePage;

public class AboutMajescoPage extends BasePage {
    private MajescoSearchPage majescoSearchPage;
    private static final Logger logger = LoggerFactory.getLogger(AboutMajescoPage.class);
    public AboutMajescoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@aria-current='page' and contains(text(), 'About Majesco')]")
    private WebElement aboutMajescoPage;


    public AboutMajescoPage verifyAboutMajescoPageDisplayed() {
        try {
            majescoSearchPage = new MajescoSearchPage(driver);
            majescoSearchPage.clickOnAboutMajescoLink();
            if(isElemDisplayed(aboutMajescoPage)) {
                logger.info("About Majesco Page is Displayed Successfully");
                Assert.assertTrue(true, "About Majesco Page is Displayed Successfully");
            } else {
                logger.info("About Majesco Page is Displayed Successfully");
                Assert.assertTrue(false, "About Majesco Page is Displayed Successfully");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return this;
    }
}