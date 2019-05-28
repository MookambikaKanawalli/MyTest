package au.com.revit.stepdefinitions.API;

import au.com.revit.core.RestServices;
import au.com.revit.core.World;
import au.com.revit.utilities.ScenarioContext;
import com.jayway.restassured.response.Response;
import cucumber.api.java8.En;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIStepDefinitions implements En {


    private World world;
    private ScenarioContext m_scenarioContext;

    public APIStepDefinitions(World world, ScenarioContext scenarioContext) {
        this.world = world;
        this.m_scenarioContext = scenarioContext;
        Given("^I make a GET request with \"([^\"]*)\" and \"([^\"]*)\"$", (String lat, String longt) -> {
            try {
                String uri = this.world.getURI();
                uri = processURI(uri, lat, longt);
                Response getResponse = new RestServices().GET(uri, null, null);

            this.m_scenarioContext.setContext("response", getResponse);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Then("^I verify that state code$", () -> {

            Response response = (Response)this.m_scenarioContext.getContext("response");
            String state_code = processResponse(response.prettyPrint());
            System.out.println("------------------------------");
            System.out.println("------------------------------");
            System.out.println("");
            System.out.println("The value of State code : "+state_code);
            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("------------------------------");
        });
        Given("^I make a GET request with \"([^\"]*)\"$", (String postCode) -> {
            try {
                String uri = this.world.getForecastURI();
                uri = processForecastURI(uri, postCode);
                Response getResponse = new RestServices().GET(uri, null, null);

                this.m_scenarioContext.setContext("responseForecast", getResponse);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Then("^I verify forecast response$", () -> {
            Response response = (Response)this.m_scenarioContext.getContext("responseForecast");
            String resp = processResponse(response.prettyPrint());
            System.out.println("------------------------------");
            System.out.println("------------------------------");
            System.out.println("");
            System.out.println(resp);
            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("------------------------------");
        });
    }

    private String processForecastURI(String uri, String postCode) {

        uri = uri.replace("{pcode}",postCode);
        String apiKey = null;
        try {
            apiKey = this.world.getAPIKey();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        uri = uri + "&key=" + apiKey;
        System.out.println(uri);
        return uri;

    }

    private String processURI(String uri, String lat, String longt) {
        uri = uri.replace("{latitude}", lat);
        uri = uri.replace("{longitude}", longt);
        String apiKey = null;
        try {
            apiKey = this.world.getAPIKey();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        uri = uri + "&key=" + apiKey;
        System.out.println(uri);
        return uri;
    }

    private String processResponse(String body) {
        String state_code = "";
        try {
            JSONObject jsonObject = new JSONObject(body);
            if (hasKey(jsonObject, "data")) {
                JSONArray data = jsonObject.getJSONArray("data");
                if (data != null && data.length() > 0) {
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject dataJSONObject = data.getJSONObject(i);
                        if (hasKey(dataJSONObject, "state_code")) {
                            state_code = dataJSONObject.optString("state_code");
                            if (!state_code.isEmpty()) {
                                break;
                            }
                        }

                    }
                }
            }
        } catch (JSONException e) {
        }
        return state_code;
    }

    private boolean hasKey(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key);
    }


}
