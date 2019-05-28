package au.com.revit.utilities;

import au.com.revit.core.World;

public class UrlLocator {


    private static ThreadLocal<UrlLocator> thread_local = new ThreadLocal<UrlLocator>() {

        @Override
        protected UrlLocator initialValue() {
            return new UrlLocator();
        }
    };

    private String applicationUrl;

    public UrlLocator() {
        try {
            applicationUrl = World.getInstance().getApplicationUrl();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static UrlLocator getInstance() {
        return thread_local.get();
    }


    public String getApplicationURL() {
        return applicationUrl;
    }

}
