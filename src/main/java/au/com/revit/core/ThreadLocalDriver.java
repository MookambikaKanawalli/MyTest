package au.com.revit.core;

import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {
    private WebDriver driver;


    private static ThreadLocal<ThreadLocalDriver> thread_local = new ThreadLocal<ThreadLocalDriver>() {
        @Override
        protected ThreadLocalDriver initialValue() {
            return new ThreadLocalDriver();
        }
    };

    public static ThreadLocalDriver getInstance() {
        return thread_local.get();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        System.out.println("set driver successful");
    }


}
