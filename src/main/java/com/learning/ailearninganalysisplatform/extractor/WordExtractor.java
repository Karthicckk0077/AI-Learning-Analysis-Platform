package com.learning.ailearninganalysisplatform.extractor;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class WordExtractor implements FileExtractor {

    @Override
    public boolean supports(String contentType) {
        return "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                .equals(contentType);
    }

    @Override
    public String extract(MultipartFile file) {

        try (
                XWPFDocument document = new XWPFDocument(file.getInputStream());
                XWPFWordExtractor extractor = new XWPFWordExtractor(document)
        ) {

            return extractor.getText();

        } catch (IOException e) {

            return "Unable to extract Word document.";

        }
    }
}