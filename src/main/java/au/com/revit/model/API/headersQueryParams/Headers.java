package au.com.revit.model.API.headersQueryParams;

import java.util.Map;

/**
 * The type Headers.
 */
public class Headers {

    private Map<String, String> _headers;

    /**
     * Instantiates a new Headers.
     */
    public Headers() {
    }

    /**
     * Cache headers.
     *
     * @param headers the headers
     */
    public void cacheHeaders(Map<String, String> headers) {
        this._headers = headers;
    }

    /**
     * Gets headers.
     *
     * @return the headers
     */
    public Map<String, String> getHeaders() {
        return _headers;
    }
}
