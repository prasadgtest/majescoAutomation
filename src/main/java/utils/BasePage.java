package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public void goToURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        waitForPageLoad();
    }

    //Click Method
    public void clickOn(By elementLocation) {
        waitForVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }

    //Enter Data
    public void enterText(By elementLocation, String text) {
        waitForVisibility(elementLocation);
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Get  Text
    public String getText(By elementLocation) {
        waitForVisibility(elementLocation);
        return driver.findElement(elementLocation).getText();
    }

    //Get  Attribute
    public String getAttribute(By elementLocation, String attributeName) {
        waitForVisibility(elementLocation);
        return driver.findElement(elementLocation).getAttribute(attributeName);
    }

    //Click Method
    public void clickOn(WebElement elementLocation) {
        waitForVisibility(elementLocation);
        elementLocation.click();
    }

    //Enter Data
    public void enterText(WebElement elementLocation, String text) {
        waitForVisibility(elementLocation);
        elementLocation.sendKeys(text);
    }

    public void enterText(WebElement elementLocation, Keys key) {
        waitForVisibility(elementLocation);
        elementLocation.sendKeys(key);
    }

    //Get  Text
    public String getText(WebElement elementLocation) {
        waitForVisibility(elementLocation);
        return elementLocation.getText();
    }

    //Get  Text
    public String getAttribute(WebElement elementLocation, String attributeName) {
        waitForVisibility(elementLocation);
        return elementLocation.getAttribute(attributeName);
    }

    //Wait
    public void waitForVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //Wait
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public List<WebElement> waitForElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

    public boolean isElemDisplayed(WebElement elem) {
        boolean flag = false;
        if(elem.isDisplayed()) {
            return true;
        }
        return flag;
    }

    public boolean waitForPageLoad() {
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return ((Long)((JavascriptExecutor)webDriver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor)webDriver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(Exception e){
        }
    }

    public void javaScriptClickOn(WebElement elem) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", elem);
    }
}