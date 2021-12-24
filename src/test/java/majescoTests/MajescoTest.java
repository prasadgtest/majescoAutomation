package majescoTests;

import java.lang.reflect.Method;

import utils.extentReportsUtils.ExtentTestManager;
import org.testng.annotations.Test;


public class MajescoTest extends MajescoBaseTest {

    @Test(priority = 0, description="Navigate to Google Web Application", groups="Web")
    public void googleLaunchWebAppln(Method method) {
        ExtentTestManager.startTest(method.getName(), "Navigate to Google Web Application");
        majescoSearchPagePF
                .navigateToGoogleWebAppln();
    }

    @Test(priority = 1, description="Search for Majesco in Google Web Application", groups="Web")
    public void searchMajescoInGoogleSearch(Method method) {
        ExtentTestManager.startTest(method.getName(), "Search for Majesco in Google Web Application");
        majescoSearchPagePF
                .searchForMajesco();
    }

    @Test(priority = 2, description = "Verify the About Majesco page is Displayed", groups="Web")
    public void verifyAboutMajescoPageDisplayed(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify the About Majesco page is Displayed");
        aboutMajescoPagePF
                .verifyAboutMajescoPageDisplayed();
    }
}