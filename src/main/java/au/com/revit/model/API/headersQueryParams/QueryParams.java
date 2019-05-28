package au.com.revit.model.API.headersQueryParams;

import java.util.Map;


public class QueryParams {

    private Map<String, String> _queryParams;


    public Map<String, String> getQueryParams() {
        return _queryParams;
    }

    public void cacheQueryParams(Map<String, String> queryParams) {
        _queryParams = queryParams;
    }
}
