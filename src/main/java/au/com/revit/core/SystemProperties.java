package au.com.revit.core;

public class SystemProperties {


    private static ThreadLocal<SystemProperties> thread_local = new ThreadLocal<SystemProperties>() {

        @Override
        protected SystemProperties initialValue() {
            return new SystemProperties();
        }
    };

    public static SystemProperties getInstance() {
        return thread_local.get();
    }

    public String getEnvironment() {
        return System.getProperty("environment");
    }
}
