package au.com.revit.core;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestServices {
    //<editor-fold desc="Fields">

    private static ThreadLocal<RestServices> _threadLocal = new ThreadLocal<>();
    private Response _response;

    public static synchronized void set(RestServices restServices) {
        _threadLocal.set(restServices);
    }

    public static synchronized RestServices getInstance() {
        return _threadLocal.get();
    }
    //</editor-fold>
    //<editor-fold desc="CRUD">

    public Response POST(String URI, Map<String, String> headers, Map<String, String> params, Object requestPayload) {
        RequestSpecification requestSpecification = RestAssured.given();
        if (headers != null) {
//            DataMigrationLogger.LOG.info("{{ Post Headers --- " + headers.keySet() + " - " + headers.values() + " }}");
            requestSpecification.headers(headers);
        }
        if (params != null && params.size() > 0) {
//            DataMigrationLogger.LOG.info("{{ Post parameters --- " + params.keySet() + " - " + params.values() + " }}");
            requestSpecification.queryParams(params);
        }
        if (requestPayload != null) {
            requestSpecification.body(requestPayload.toString());
        }
        requestSpecification.contentType(ContentType.JSON);
        try {
            _response = requestSpecification.post(URI);
        } catch (Exception e) {
        }
        return _response;
    }

    public synchronized Response GET(String URI, Map<String, String> headers, Map<String, String> params) {
        RequestSpecification requestSpecification = RestAssured.given();
        if (headers != null) {
            requestSpecification.headers(headers);
        }
        if (params != null && params.size() > 0) {
            requestSpecification.queryParams(params);
        }
        requestSpecification.contentType(ContentType.JSON);
        try {
            _response = requestSpecification.get(URI);
        } catch (Exception e) {
            System.out.println("The 5 day hourly forecast is not available on the free plan. Please see our pricing.");
        }
        return _response;
    }
    //</editor-fold>

}
