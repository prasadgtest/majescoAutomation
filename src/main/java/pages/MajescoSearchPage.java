package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import utils.BasePage;
import org.testng.Assert;
import utils.ReadProperties;


public class MajescoSearchPage extends BasePage {
    public AboutMajescoPage aboutMajescoPage = null;
    private static final Logger logger = LoggerFactory.getLogger(MajescoSearchPage.class);
    public MajescoSearchPage(WebDriver driver) {
        super(driver);
        aboutMajescoPage = new AboutMajescoPage(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), 'Sign in')]")
    private WebElement googleLogo;

    @FindBy(name = "q")
    private WebElement googleSearchBox;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]")
    private WebElement getGoogleSearchButton;

    @FindBy(xpath = "//*[contains(text(), 'About Majesco')]")
    private WebElement aboutMajescoLink;


    public AboutMajescoPage clickOnAboutMajescoLink() {
        try {
            clickOn(aboutMajescoLink);
            waitForPageLoad();
        } catch (Exception e){
            logger.info(e.getMessage());
        }
        return new AboutMajescoPage(driver);
    }

    public MajescoSearchPage navigateToGoogleWebAppln() {
        logger.info("Navigating to Google Application");
        goToURL(ReadProperties.readConfigProperty("appGoogleURL"));
        waitForPageLoad();
        if(isElemDisplayed(googleLogo)) {
            logger.info("Google Application Launched Successfully");
            Reporter.log("Google Application Launched Successfully");
            Assert.assertTrue(true, "Google Application Launched Successfully");
        } else {
            logger.info("Google Application NOT Launched Successfully");
            Assert.assertTrue(false, "Google Application NOT Launched Successfully");
        }
        return this;
    }

    public MajescoSearchPage searchForMajesco() {
        enterText(googleSearchBox, ReadProperties.readConfigProperty("companyNameToSearch"));
        enterText(googleSearchBox, Keys.TAB);
        clickOn(getGoogleSearchButton);
        waitForPageLoad();
        if(isElemDisplayed(aboutMajescoLink)) {
            logger.info("About Majesco Link on Google Search Page Displayed Successfully ");
            Assert.assertTrue(true, "About Majesco Link on Google Search Page Displayed Successfully");
        } else {
            logger.info("About Majesco Link on Google Search Page NOT Displayed Successfully");
            Assert.assertTrue(false, "About Majesco Link on Google Search Page NOT Displayed Successfully");
        }
        return new MajescoSearchPage(driver);
    }
}
