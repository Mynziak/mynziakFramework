package core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dima on 06.06.2016.
 */
public class BrowseFactory extends MethodsFactory {

    private static final String DIR_CHROME ="C:\\chromedriver.exe ";

    @BeforeTest

    @Parameters({"browser"})
    public void setupBrowser(@Optional("CH") String browser){

        if(browser.equalsIgnoreCase("FF")){
            myDriver =new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("CH")){
            //  System.setProperty("webdriver.chrome.driver", DIR_CHROME);
            myDriver = new ChromeDriver();
        }
        driver = new EventFiringWebDriver(myDriver);
        driver.register(new DriverListener());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS); //wait for finishing scripts
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //wait for
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
