package com.learning.ailearninganalysisplatform.extractor;

import org.springframework.web.multipart.MultipartFile;

public interface FileExtractor {

    boolean supports(String contentType);

    String extract(MultipartFile file);

}