package com.projectcloud.domain.member.controller;

import com.projectcloud.domain.member.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    /**
     * 프로필 이미지 업로드
     **/
    @PostMapping("/profile-image")
    public ResponseEntity<String>
    uploadProfileImage(
            @RequestParam("file") MultipartFile file) throws IOException {

        String fileName = s3Service.uploadFile(file);

        return ResponseEntity.ok("파일 업로드 성공! 파일명: " + fileName);
    }

    /**
     * Presigned URL 조회
     */
    @GetMapping("/profile-image")
    public ResponseEntity<String> getProfileImage(
            @RequestParam("fileName") String fileName) {

        String presignedUrl = s3Service.getPresignedUrl(fileName);

        return ResponseEntity.ok(presignedUrl);
    }
}