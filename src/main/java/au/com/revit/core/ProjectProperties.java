package au.com.revit.core;

import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {

    private volatile static Properties _properties;

    private ProjectProperties(String env) {
        try {
            _properties = new Properties();
            String projectPropertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/config";
            _properties.load(new FileReader(projectPropertiesFilePath + getApplicationPropertiesFilename(env)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(" Properties file could not be loaded. Please check the stacktrace");
        }
    }

    private static String getApplicationPropertiesFilename(String applicationFileName) {
        if (StringUtils.containsIgnoreCase(applicationFileName, "dev")) {
            return "/application-dev.properties";
        }
        if (StringUtils.containsIgnoreCase(applicationFileName, "sit")) {
            return "/application-sit.properties";
        }
        if (StringUtils.containsIgnoreCase(applicationFileName, "stg")) {
            return "/application-stg.properties";
        }
        throw new RuntimeException("application properties -> " + applicationFileName + " is not configured in ProjectProperties.Java class");
    }

    public static synchronized ProjectProperties getInstance(String environmentName) {
        return new ProjectProperties(environmentName);
    }

    public String getDriverFilePath(String chrome) {
        return _properties.getProperty("webdriver.chrome.driver");
    }

    public String getApplicationUrl() {
        return _properties.getProperty("applicationUrl");
    }


    public String getURI() {
        return _properties.getProperty("current_uri");
    }

    public String getAPIKey() {
        return _properties.getProperty("key");
    }

    public String getForecastURI() {
        return _properties.getProperty("forecast_uri");
    }

}
