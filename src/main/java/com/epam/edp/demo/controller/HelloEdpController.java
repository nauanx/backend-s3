package com.epam.edp.demo.controller;

import com.epam.edp.demo.service.S3DownloadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author Pavlo_Yemelianov
 */
@RestController
public class HelloEdpController {

    HelloEdpController(S3DownloadService s3DownloadService) {
        this.s3DownloadService = s3DownloadService;
    }

    private final S3DownloadService s3DownloadService;

    @GetMapping("/")
    public Map<String, String> root() {
        String fileName = "data.txt";
        try {
            return Map.of("content", s3DownloadService.getTextFileContent(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("content", "Error reading file from S3: " + e.getMessage());
        }
    }

    @GetMapping(value = "/api/hello")
    public String hello() {
        return "Hello, EDP!";
    }

}
