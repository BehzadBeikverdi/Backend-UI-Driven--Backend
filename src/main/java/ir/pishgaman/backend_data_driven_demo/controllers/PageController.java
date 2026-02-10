package ir.pishgaman.backend_data_driven_demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1")
public class PageController {
    private final AtomicInteger step = new AtomicInteger(0);

    @GetMapping("/page")
    public Map<String, Object> getPage() {
        int currentStep = step.get();
        if (currentStep == 0) {
            return Map.of(
                    "responseType", "UI_SCHEMA",
                    "schema", Map.of(
                            "components", new Object[]{
                                    Map.of("type", "Header", "props", Map.of("title", "Step 1: Welcome")),
                                    Map.of("type", "ActionButton", "props", Map.of("text", "Next"), "action", Map.of(
                                            "type", "API_CALL",
                                            "endpoint", "/next-step",
                                            "method", "POST",
                                            "responseType", "UI_SCHEMA"
                                    ))
                            }
                    )
            );
        } else if (currentStep == 1) {
            int amount = (int) (Math.random() * 5000);
            return Map.of(
                    "responseType", "UI_SCHEMA",
                    "schema", Map.of(
                            "components", new Object[]{
                                    Map.of("type", "Header", "props", Map.of("title", "Step 2: Balance")),
                                    Map.of("type", "BalanceCard", "props", Map.of("responseType", "DATA",
                                            "model", "BalanceModel",
                                            "data", Map.of("amount", amount, "currency", "USD"))),
                                    Map.of("type", "ActionButton", "props", Map.of("text", "Refresh Balance"), "action", Map.of(
                                            "type", "API_CALL",
                                            "endpoint", "/data/balance",
                                            "method", "GET",
                                            "responseType", "DATA",
                                            "model", "BalanceModel"
                                    )),
                                    Map.of("type", "ActionButton", "props", Map.of("text", "Next Step"), "action", Map.of(
                                            "type", "API_CALL",
                                            "endpoint", "/next-step",
                                            "method", "POST",
                                            "responseType", "UI_SCHEMA"
                                    ))
                            }
                    )
            );
        } else if (currentStep == 2) {
            return Map.of(
                    "responseType", "UI_SCHEMA",
                    "schema", Map.of(
                            "components", new Object[]{
                                    Map.of("type", "Header", "props", Map.of("title", "Step 3: Enter Info")),
                                    Map.of("type", "DynamicForm", "props", Map.of(
                                            "fields", new Object[]{
                                                    Map.of("name", "fullName", "label", "Full Name", "required", true),
                                                    Map.of("name", "email", "label", "Email", "required", true, "format", "EMAIL")
                                            }
                                    ), "action", Map.of(
                                            "type", "API_CALL",
                                            "endpoint", "/submit-info",
                                            "method", "POST",
                                            "responseType", "UI_SCHEMA"
                                    ))
                            }
                    )
            );
        } else {
            return Map.of(
                    "responseType", "UI_SCHEMA",
                    "schema", Map.of(
                            "components", new Object[]{
                                    Map.of("type", "Header", "props", Map.of("title", "Step 3: Complete")),
                                    Map.of("type", "ActionButton", "props", Map.of("text", "Restart"), "action", Map.of(
                                            "type", "API_CALL",
                                            "endpoint", "/reset",
                                            "method", "POST",
                                            "responseType", "UI_SCHEMA"
                                    ))
                            }
                    )
            );
        }
    }

    @PostMapping("/next-step")
    public Map<String, Object> nextStep() {
        step.incrementAndGet();
        return getPage();
    }

    @PostMapping("/reset")
    public Map<String, Object> reset() {
        step.set(0);
        return getPage();
    }

    @GetMapping("/data/balance")
    public Map<String, Object> getBalance() {
        int amount = (int) (Math.random() * 5000);
        return Map.of(
                "responseType", "DATA",
                "model", "BalanceModel",
                "data", Map.of("amount", amount, "currency", "USD")
        );
    }

    @PostMapping("/submit-info")
    public Map<String, Object> submitInfo(@RequestBody Map<String, Object> payload) {
        System.out.println("Received form: " + payload);
        step.incrementAndGet();
        return getPage();
    }

}
