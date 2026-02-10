package ir.pishgaman.backend_data_driven_demo.models;

import java.util.Map;

public class ComponentModel {
    private final String type;
    private final Map<String, Object> props;
    private final Map<String, Object> action;

    public ComponentModel(String type, Map<String, Object> props, Map<String, Object> action) {
        this.type = type;
        this.props = props;
        this.action = action;
    }

    public String getType() { return type; }
    public Map<String, Object> getProps() { return props; }
    public Map<String, Object> getAction() { return action; }
}
