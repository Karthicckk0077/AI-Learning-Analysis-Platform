package com.learning.ailearninganalysisplatform.dto;

public class UploadResponse {

    private String fileName;
    private String fileType;
    private long fileSize;
    private String message;
    private String extractedText;
    private String aiSummary;

    // NEW
    private LearningAnalysis learningAnalysis;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }

    // NEW Getter
    public LearningAnalysis getLearningAnalysis() {
        return learningAnalysis;
    }

    // NEW Setter
    public void setLearningAnalysis(LearningAnalysis learningAnalysis) {
        this.learningAnalysis = learningAnalysis;
    }
}