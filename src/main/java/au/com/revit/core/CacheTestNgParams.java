package au.com.revit.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CacheTestNgParams {

    private String environmentName;
    private String browser;
    private String cmUrl;
    private String headless;
    private String browser_version;



    private String testType;
    private static String parallelSuiteStartTime = getFormattedDateTime("ddMM_HHmm");

    private static ThreadLocal<CacheTestNgParams> thread_local = new ThreadLocal<CacheTestNgParams>() {
        @Override
        protected CacheTestNgParams initialValue() {
            return new CacheTestNgParams();
        }
    };

    public static CacheTestNgParams getInstance() {
        return thread_local.get();
    }

    public static String getParallelTestSuiteStartTime() {
        return parallelSuiteStartTime;
    }

    public static synchronized String getFormattedDateTime(String format) {
        //Get current date time
		/*LocalDateTime currentLocalDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formattedDateTime = currentLocalDateTime.format(formatter);
		return formattedDateTime;*/
        return new SimpleDateFormat(format).format(new Date());
    }

    public String getEnvironmentName() {
        return this.environmentName;
    }

    public CacheTestNgParams setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
        return this;
    }

    public String getBrowser() {
        return this.browser;
    }

    public CacheTestNgParams setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public String getCmUrl() {
        return this.cmUrl;
    }

    public CacheTestNgParams setCmUrl(String cmUrl) {
        this.cmUrl = cmUrl;
        return this;
    }

    public String getHeadless() {
        return this.headless;
    }

    public CacheTestNgParams setHeadless(String headless) {
        this.headless = headless;
        return this;
    }

    public CacheTestNgParams setbrowser_version(String browser_version) {
        this.browser_version = browser_version;
        return this;
    }

    public String getBrowserVersion() {
        return this.browser_version;
    }

    public CacheTestNgParams setTestType(String testType) {
        this.testType = testType;
        return this;
    }
    public String getTestType() {
        return testType;
    }

}
