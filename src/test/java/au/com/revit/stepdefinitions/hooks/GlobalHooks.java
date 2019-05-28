package au.com.revit.stepdefinitions.hooks;


import au.com.revit.core.World;
import cucumber.api.java.Before;

public class GlobalHooks {


    @Before
    public void before() {
        System.out.println(" In-Before hook----------------------------  ");
        try {
            World.getInstance().loadEnvSpecificProperties();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
