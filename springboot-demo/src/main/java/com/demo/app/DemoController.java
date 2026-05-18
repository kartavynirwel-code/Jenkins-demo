package com.demo.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.env:local}")
    private String appEnv;

    // GET /api/hello  – basic smoke test
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
            "message", "Hello from Spring Boot!",
            "status",  "UP"
        );
    }

    // GET /api/info  – deployment info
    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
            "app",       "springboot-demo",
            "version",   appVersion,
            "env",       appEnv,
            "timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    // GET /  – root endpoint
    @GetMapping("/")
    public String root() {
        return "Spring Boot Demo is running! Try /api/hello or /api/info";
    }
}
