package au.com.revit.core;

import org.openqa.selenium.WebDriver;

public class World {


    private static final World m_world = new World();
    private ProjectProperties m_projectProperties;
    private AssertObject m_assertObject;
    private CacheTestNgParams m_cacheTestNgParams;
    private WebDriver driver;

    public static World getInstance() {
        return m_world;
    }

    public String getApplicationEnvironment() throws InterruptedException {
        return getTestNgParams().getEnvironmentName();
    }

    private CacheTestNgParams getTestNgParams() throws InterruptedException {
        if (this.m_cacheTestNgParams == null) {
            this.m_cacheTestNgParams = CacheTestNgParams.getInstance();
        }
        return this.m_cacheTestNgParams;
    }

    public WebDriver getDriver() throws InterruptedException {
        if (this.driver == null) {
            System.out.println("CacheTestNgParams Env -  " + getTestNgParams().getEnvironmentName());
            this.driver = ThreadLocalDriver.getInstance().getDriver();
            System.out.println("driver " + driver);
        }
        return this.driver;
    }

    public ProjectProperties loadEnvSpecificProperties() throws InterruptedException {
        return getProjectProperties();
    }

    public ProjectProperties getProjectProperties() throws InterruptedException {
        if (this.m_projectProperties == null) {
            this.m_projectProperties = ProjectProperties.getInstance(getTestNgParams().getEnvironmentName());
            getTestNgParams().setBrowser(getTestNgParams().getBrowser());
        }
        return this.m_projectProperties;
    }

    public String getApplicationUrl() throws InterruptedException {
        return this.getProjectProperties().getApplicationUrl();
    }

    public String getURI() throws InterruptedException {
        return this.getProjectProperties().getURI();
    }

    public String getForecastURI() throws InterruptedException {
        return this.getProjectProperties().getForecastURI();
    }

    public String getAPIKey() throws InterruptedException {
        return this.getProjectProperties().getAPIKey();
    }

    public AssertObject getSoftAssertionObject() {
        if (this.m_assertObject == null) {
            this.m_assertObject = new AssertObject();
        }
        return this.m_assertObject;
    }


}
