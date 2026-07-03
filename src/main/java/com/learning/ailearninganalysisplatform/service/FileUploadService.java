package com.learning.ailearninganalysisplatform.service;

import com.learning.ailearninganalysisplatform.dto.LearningAnalysis;
import com.learning.ailearninganalysisplatform.dto.UploadResponse;
import com.learning.ailearninganalysisplatform.extractor.PdfExtractor;
import com.learning.ailearninganalysisplatform.extractor.TextExtractor;
import com.learning.ailearninganalysisplatform.extractor.WordExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    private final PdfExtractor pdfExtractor;
    private final TextExtractor textExtractor;
    private final WordExtractor wordExtractor;
    private final AIAnalysisService aiAnalysisService;
    private final LearningAnalysisService learningAnalysisService;

    public FileUploadService(PdfExtractor pdfExtractor,
                             TextExtractor textExtractor,
                             WordExtractor wordExtractor,
                             AIAnalysisService aiAnalysisService,
                             LearningAnalysisService learningAnalysisService) {

        this.pdfExtractor = pdfExtractor;
        this.textExtractor = textExtractor;
        this.wordExtractor = wordExtractor;
        this.aiAnalysisService = aiAnalysisService;
        this.learningAnalysisService = learningAnalysisService;
    }

    // ----------------------------
    // Single File Upload
    // ----------------------------

    public UploadResponse uploadFile(MultipartFile file) {

        UploadResponse response = new UploadResponse();

        response.setFileName(file.getOriginalFilename());
        response.setFileType(file.getContentType());
        response.setFileSize(file.getSize());

        String extractedText = extractText(file);

        response.setExtractedText(extractedText);

        if (!"Unsupported file type.".equals(extractedText)) {

            String summary = aiAnalysisService.generateSummary(extractedText);
            response.setAiSummary(summary);

        }

        response.setMessage("File uploaded successfully.");

        return response;
    }

    // ----------------------------
    // Teacher vs Student Analysis
    // ----------------------------

    public UploadResponse analyzeLearning(
            MultipartFile teacherFile,
            MultipartFile studentFile,
            String prompt) {

        UploadResponse response = new UploadResponse();

        response.setFileName(
                teacherFile.getOriginalFilename()
                        + " vs "
                        + studentFile.getOriginalFilename());

        response.setFileType("Learning Analysis");
        response.setFileSize(
                teacherFile.getSize() + studentFile.getSize());

        String teacherText = extractText(teacherFile);
        String studentText = extractText(studentFile);

        response.setExtractedText(
                "===== TEACHER MATERIAL =====\n\n"
                        + teacherText
                        + "\n\n==============================\n\n"
                        + "===== STUDENT MATERIAL =====\n\n"
                        + studentText);

        // Get structured learning analysis
        LearningAnalysis analysis =
                learningAnalysisService.analyze(
                        teacherText,
                        studentText,
                        prompt
                );

        response.setLearningAnalysis(analysis);

        response.setMessage("Teacher and Student documents processed successfully.");

        return response;
    }

    // ----------------------------
    // Common Text Extraction
    // ----------------------------

    private String extractText(MultipartFile file) {

        String contentType = file.getContentType();

        if ("application/pdf".equals(contentType)) {

            return pdfExtractor.extract(file);

        }

        if ("text/plain".equals(contentType)) {

            return textExtractor.extract(file);

        }

        if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                .equals(contentType)) {

            return wordExtractor.extract(file);

        }

        return "Unsupported file type.";

    }

}