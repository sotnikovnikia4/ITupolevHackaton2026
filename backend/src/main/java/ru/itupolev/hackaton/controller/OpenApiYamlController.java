package ru.itupolev.hackaton.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.*;

@RestController
public class OpenApiYamlController {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.description}")
    private String serverDescription;

    @Value("classpath:/openapi/openapi.yaml")
    private Resource openApiResource;

    @GetMapping(value = "${springdoc.swagger-ui.url}", produces = "application/yaml")
    public String getOpenApiYaml() throws IOException {
        String yaml = new String(openApiResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        return yaml.replace("SERVER_URL_PLACEHOLDER", serverUrl)
                .replace("SERVER_DESCRIPTION_PLACEHOLDER", serverDescription);
    }
}