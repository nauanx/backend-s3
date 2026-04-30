package com.epam.edp.demo.controller;

import com.epam.edp.demo.service.S3DownloadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author Pavlo_Yemelianov
 */
@RestController
public class HelloEdpController {

    HelloEdpController(S3DownloadService s3DownloadService) {
        this.s3DownloadService = s3DownloadService;
    }

    private final S3DownloadService s3DownloadService;

    @GetMapping(value = "/api/hello")
    public String hello() throws IOException {
        String fileName = "data.txt";
        return s3DownloadService.getTextFileContent(fileName);
    }

}
