package au.com.revit.utilities;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {


    private Map<String, Object> _scenarioContext;

    public ScenarioContext() {
        _scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        _scenarioContext.put(key, value);
    }

    public Object getContext(String key) {
        if (!isContains(key)) {
            return "";
        }
        return _scenarioContext.get(key);
    }

    public Boolean isContains(String key) {
        return _scenarioContext.containsKey(key);
    }

}