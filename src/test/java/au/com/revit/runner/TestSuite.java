package au.com.revit.runner;

import au.com.revit.core.CacheTestNgParams;
import au.com.revit.core.Driver;
import au.com.revit.core.ThreadLocalDriver;
import au.com.revit.core.World;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@CucumberOptions(
        format = {"pretty", "html:target/cucumber-reports/cucumber-html", "json:target/cucumber-reports/cucumber-json/cucumbertest.json"},
        features = "src/test/resources/features",
        glue = "au/com/revit/stepdefinitions",
//        tags = {"@api","@all","@ui"})
        tags = {"@all"})
public class TestSuite {

    private TestNGCucumberRunner testNGCucumberRunner;

    @Parameters({"env", "browser", "headless", "testType"})
    @BeforeClass(alwaysRun = true)
    public synchronized void setUp(String environmentName, String browser, String headless, String testType) throws Exception {
        CacheTestNgParams.getInstance().setEnvironmentName(environmentName).setBrowser(browser).setHeadless(headless).setTestType(testType);
        System.out.println("Environment Name:" + environmentName);
        if (testType.equalsIgnoreCase("ui") || testType.equalsIgnoreCase("all")) {
            WebDriver driver = Driver.createDriver(CacheTestNgParams.getInstance());
            System.out.println("Setting driver: " + driver);
            ThreadLocalDriver.getInstance().setDriver(driver);
        }
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features", priority = 1)
    public synchronized void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
        System.out.println("tearDownClass executed");
    }

    @AfterTest
    public void after() {
        try {
            if (World.getInstance().getDriver() != null) {
                World.getInstance().getDriver().quit();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
