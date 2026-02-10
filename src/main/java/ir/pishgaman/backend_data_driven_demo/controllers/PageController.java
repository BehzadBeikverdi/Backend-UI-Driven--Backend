package ir.pishgaman.backend_data_driven_demo.controllers;

import ir.pishgaman.backend_data_driven_demo.services.PageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PageController {

    private final PageService pageService;

    public PageController(@Qualifier("PageService") PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/page")
    public Map<String, Object> getPage() {
        return pageService.getPage();
    }

    @PostMapping("/next-step")
    public Map<String, Object> nextStep() {
        return pageService.nextStep();
    }

    @PostMapping("/reset")
    public Map<String, Object> reset() {
        return pageService.reset();
    }

    @GetMapping("/data/balance")
    public Map<String, Object> getBalance() {
        return pageService.getBalance();
    }

    @PostMapping("/submit-info")
    public Map<String, Object> submitInfo(@RequestBody Map<String, Object> payload) {
        return pageService.submitInfo(payload);
    }
}
