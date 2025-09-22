package com.apirest.apirest.Controllers;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.services.S3Service;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/upload")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/presigned")
    public Map<String, String> getPresignedUrl(
            @RequestParam String filename,
            @RequestParam String contentType) {
        String bucket = bucketName;
        String key = "pages/" + filename;

        URL url = s3Service.generateUploadUrl(bucket, key, contentType);

        return Map.of(
                "uploadUrl", url.toString(),
                "publicUrl", "https://" + bucket + ".s3.amazonaws.com/" + key);
    }

    @PostMapping("/presigned/read/batch")
    public List<Map<String, String>> getPresignedReadUrl(@RequestBody List<String> keys) {
        return keys.stream()
                .map(key -> Map.of(
                        "key", key,
                        "url", s3Service.generatePresignedReadUrl(key, bucketName)))
                .toList();
    }
}
