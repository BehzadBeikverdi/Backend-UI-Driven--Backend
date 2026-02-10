package ir.pishgaman.backend_data_driven_demo.services;

import ir.pishgaman.backend_data_driven_demo.models.BalanceModel;
import ir.pishgaman.backend_data_driven_demo.models.ComponentModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service("PageService")
public class PageService {

    private final AtomicInteger step = new AtomicInteger(0);

    public Map<String, Object> getPage() {
        int currentStep = step.get();
        return switch (currentStep) {
            case 0 -> step0();
            case 1 -> step1();
            case 2 -> step2();
            default -> completeStep();
        };
    }

    public Map<String, Object> nextStep() {
        step.incrementAndGet();
        return getPage();
    }

    public Map<String, Object> reset() {
        step.set(0);
        return getPage();
    }

    public Map<String, Object> getBalance() {
        int amount = (int) (Math.random() * 5000);
        BalanceModel balance = new BalanceModel(amount, "USD");

        return Map.of(
                "responseType", "DATA",
                "model", "BalanceModel",
                "data", Map.of("amount", balance.getAmount(), "currency", balance.getCurrency())
        );
    }

    public Map<String, Object> submitInfo(Map<String, Object> payload) {
        System.out.println("Received form: " + payload);
        step.incrementAndGet();
        return getPage();
    }

    // --- Step 0 ---
    private Map<String, Object> step0() {
        List<ComponentModel> components = List.of(
                new ComponentModel("Header", Map.of("title", "Step 1: Welcome"), null),
                new ComponentModel(
                        "ActionButton",
                        Map.of("text", "Next"),
                        Map.of(
                                "type", "API_CALL",
                                "endpoint", "/next-step",
                                "method", "POST",
                                "responseType", "UI_SCHEMA"
                        )
                )
        );

        return Map.of(
                "responseType", "UI_SCHEMA",
                "schema", Map.of("components", components)
        );
    }

    // --- Step 1 ---
    private Map<String, Object> step1() {
        int amount = (int) (Math.random() * 5000);

        List<ComponentModel> components = List.of(
                new ComponentModel("Header", Map.of("title", "Step 2: Balance"), null),
                new ComponentModel(
                        "BalanceCard",
                        Map.of(
                                "responseType", "DATA",
                                "model", "BalanceModel",
                                "data", Map.of("amount", amount, "currency", "USD")
                        ),
                        null
                ),
                new ComponentModel(
                        "ActionButton",
                        Map.of("text", "Refresh Balance"),
                        Map.of(
                                "type", "API_CALL",
                                "endpoint", "/data/balance",
                                "method", "GET",
                                "responseType", "DATA",
                                "model", "BalanceModel"
                        )
                ),
                new ComponentModel(
                        "ActionButton",
                        Map.of("text", "Next Step"),
                        Map.of(
                                "type", "API_CALL",
                                "endpoint", "/next-step",
                                "method", "POST",
                                "responseType", "UI_SCHEMA"
                        )
                )
        );

        return Map.of(
                "responseType", "UI_SCHEMA",
                "schema", Map.of("components", components)
        );
    }

    // --- Step 2 ---
    private Map<String, Object> step2() {
        List<ComponentModel> components = List.of(
                new ComponentModel("Header", Map.of("title", "Step 3: Enter Info"), null),
                new ComponentModel(
                        "DynamicForm",
                        Map.of(
                                "fields", List.of(
                                        Map.of("name", "fullName", "label", "Full Name", "required", true),
                                        Map.of("name", "email", "label", "Email", "required", true, "format", "EMAIL")
                                )
                        ),
                        Map.of(
                                "type", "API_CALL",
                                "endpoint", "/submit-info",
                                "method", "POST",
                                "responseType", "UI_SCHEMA"
                        )
                )
        );

        return Map.of(
                "responseType", "UI_SCHEMA",
                "schema", Map.of("components", components)
        );
    }

    // --- Complete Step ---
    private Map<String, Object> completeStep() {
        List<ComponentModel> components = List.of(
                new ComponentModel("Header", Map.of("title", "Step 3: Complete"), null),
                new ComponentModel(
                        "ActionButton",
                        Map.of("text", "Restart"),
                        Map.of(
                                "type", "API_CALL",
                                "endpoint", "/reset",
                                "method", "POST",
                                "responseType", "UI_SCHEMA"
                        )
                )
        );

        return Map.of(
                "responseType", "UI_SCHEMA",
                "schema", Map.of("components", components)
        );
    }
}
