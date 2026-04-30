package com.epam.edp.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;

@Service
public class S3DownloadService {

    private static final Log LOGGER = LogFactory.getLog(S3DownloadService.class);

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3DownloadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String getTextFileContent(String key) throws IOException {
        LOGGER.debug("Attempting to download file from S3: " + key + " from bucket: " + bucketName);
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
        String content = new String(response.readAllBytes());
        LOGGER.debug("Content of the file from S3: " + content);
        return content;
    }

}
