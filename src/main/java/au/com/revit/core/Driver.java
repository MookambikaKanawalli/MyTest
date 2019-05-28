package au.com.revit.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Driver {

    public synchronized static WebDriver createDriver(CacheTestNgParams cacheTestNgParams){
        WebDriver driver = null;
        String environmentName = cacheTestNgParams.getEnvironmentName();
        String browser = cacheTestNgParams.getBrowser();
        String cmUrl = cacheTestNgParams.getCmUrl();
        String headless = cacheTestNgParams.getHeadless();
        String browserVersion = cacheTestNgParams.getBrowserVersion();
        if(browser.equalsIgnoreCase(Constants.INCOGNITO+"_"))
        {
            System.out.println("incognito");
            System.setProperty("webdriver.chrome.driver", ProjectProperties.getInstance(environmentName).getDriverFilePath("chrome"));
            ChromeOptions options =new ChromeOptions();
            options.addArguments("--incognito");
            if(!headless.isEmpty() && headless.equalsIgnoreCase("true")){
                options.addArguments("--headless");
                options.addArguments("window-size=1200,1100");
                options.addArguments("--disable-gpu");
                options.addArguments("start-maximized");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                options.setHeadless(true);
            }
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();

            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("firefox");
            System.setProperty("webdriver.gecko.driver", ProjectProperties.getInstance(environmentName).getDriverFilePath("firefox"));
            driver=new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            System.out.println("chrome-normal");
            System.setProperty("webdriver.chrome.driver", ProjectProperties.getInstance(environmentName).getDriverFilePath("chrome"));
            driver=new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }
}
