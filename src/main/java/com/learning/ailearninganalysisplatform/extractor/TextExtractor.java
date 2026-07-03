package com.learning.ailearninganalysisplatform.extractor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class TextExtractor implements FileExtractor {

    @Override
    public boolean supports(String contentType) {
        return "text/plain".equals(contentType);
    }

    @Override
    public String extract(MultipartFile file) {

        try {
            return new String(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Unable to read text file.", e);
        }
    }
}