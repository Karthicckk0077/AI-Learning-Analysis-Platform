package com.learning.ailearninganalysisplatform.controller;

import com.learning.ailearninganalysisplatform.dto.UploadResponse;
import com.learning.ailearninganalysisplatform.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class UploadController {

    private final FileUploadService fileUploadService;

    public UploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    // Existing API (Keep for testing)
    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file) {

        UploadResponse response = fileUploadService.uploadFile(file);

        return ResponseEntity.ok(response);
    }

    // New AI Learning Comparison API
    @PostMapping("/analyze-learning")
    public ResponseEntity<UploadResponse> analyzeLearning(

            @RequestParam("teacherFile") MultipartFile teacherFile,

            @RequestParam("studentFile") MultipartFile studentFile,

            @RequestParam(value = "prompt", required = false) String prompt) {

        UploadResponse response = fileUploadService.analyzeLearning(
                teacherFile,
                studentFile,
                prompt
        );

        return ResponseEntity.ok(response);
    }

}