package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dima on 06.06.2016.
 */
public class MethodsFactory extends StaticDriver {


    public void getUrl(String url) {
        Logger.info("Getting url [" + url + "]");
        driver.get(url);
    }

    protected boolean isElementPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() == 0) {
            Logger.error("Element is NOT present: " + locator);
            return false;
        } else {
            Logger.info("Element is present on Page: " + locator);
            return list.get(0).isDisplayed();
        }
    }

    public boolean isElementPresent(By locator, int seconds) {
        Wait wait = new FluentWait(driver)
                .withTimeout(seconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element '" + locator + "' is not present");
            return false;
        }
    }

    protected void type(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        Wait wait = new FluentWait(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(ElementNotVisibleException.class).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            Logger.info("Clicking on '" + locator + "'");
            driver.findElement(locator).click();
        } catch (TimeoutException e) {
            Logger.error("Can not click on element '" + locator + "'");
            e.printStackTrace();
        }
    }


    protected boolean isElementDisplayed(final By locator, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                try {
                    List<WebElement> list = driver.findElements(locator);
                    if (list.size() == 0) {
                        Logger.error("Element is NOT present: " + locator);
                        return false;
                    } else {
                        Logger.info("Element is present on Page: " + locator);
                        return list.get(0).isDisplayed();
                    }
                } catch (StaleElementReferenceException e) {                      // try again
                    return false;
                }

            }
        });
        return true;
    }

}
