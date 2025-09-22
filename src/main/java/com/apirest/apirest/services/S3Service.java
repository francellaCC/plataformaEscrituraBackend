package com.apirest.apirest.services;

import java.net.URL;
import java.time.Duration;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
public class S3Service {

        private final S3Presigner presigner;

        public S3Service(S3Presigner presigner) {
                this.presigner = presigner;
        }

        public URL generateUploadUrl(String bucket, String key, String contentType) {
                PutObjectRequest objectRequest = PutObjectRequest.builder()
                                .bucket(bucket)
                                .key(key)
                                .contentType(contentType)
                                .build();

                PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                                .signatureDuration(Duration.ofMinutes(10))
                                .putObjectRequest(objectRequest)
                                .build();

                return presigner.presignPutObject(presignRequest).url();
        }

        public String generatePresignedReadUrl(String key, String bucket) {
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                .bucket(bucket)
                                .key(key)
                                .build();

                GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                                .signatureDuration(Duration.ofMinutes(10)) // URL válida por 10 min
                                .getObjectRequest(getObjectRequest)
                                .build();

                return presigner.presignGetObject(presignRequest).url().toString();
        }
}
