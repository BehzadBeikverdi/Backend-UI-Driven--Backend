package ir.pishgaman.backend_data_driven_demo.models;

import java.util.Map;

public record ComponentModel(String type, Map<String, Object> props, Map<String, Object> action) {
}
