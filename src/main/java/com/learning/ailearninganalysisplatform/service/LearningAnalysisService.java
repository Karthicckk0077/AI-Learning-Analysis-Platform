package com.learning.ailearninganalysisplatform.service;

import com.learning.ailearninganalysisplatform.dto.LearningAnalysis;
import org.springframework.stereotype.Service;

@Service
public class LearningAnalysisService {

    private final AIAnalysisService aiAnalysisService;

    public LearningAnalysisService(AIAnalysisService aiAnalysisService) {
        this.aiAnalysisService = aiAnalysisService;
    }

    public LearningAnalysis analyze(String teacherText,
                                    String studentText,
                                    String prompt) {

        String aiResponse = aiAnalysisService.compareLearning(
                teacherText,
                studentText,
                prompt
        );

        LearningAnalysis analysis = new LearningAnalysis();

        analysis.setTeacherSummary(
                extractSection(aiResponse,
                        "TEACHER SUMMARY",
                        "STUDENT SUMMARY"));

        analysis.setStudentSummary(
                extractSection(aiResponse,
                        "STUDENT SUMMARY",
                        "TOPICS COVERED"));

        analysis.setTopicsCovered(
                extractSection(aiResponse,
                        "TOPICS COVERED",
                        "TOPICS UNDERSTOOD"));

        analysis.setTopicsUnderstood(
                extractSection(aiResponse,
                        "TOPICS UNDERSTOOD",
                        "KNOWLEDGE GAPS"));

        analysis.setKnowledgeGaps(
                extractSection(aiResponse,
                        "KNOWLEDGE GAPS",
                        "COVERAGE SCORE"));

        analysis.setRecommendations(
                extractSection(aiResponse,
                        "RECOMMENDATIONS",
                        "OVERALL EVALUATION"));

        analysis.setOverallEvaluation(
                extractSection(aiResponse,
                        "OVERALL EVALUATION",
                        null));

        analysis.setCoverageScore(
                extractPercentage(
                        extractSection(aiResponse,
                                "COVERAGE SCORE",
                                "UNDERSTANDING SCORE")
                ));

        analysis.setUnderstandingScore(
                extractPercentage(
                        extractSection(aiResponse,
                                "UNDERSTANDING SCORE",
                                "RECOMMENDATIONS")
                ));

        return analysis;
    }

    private String extractSection(String text,
                                  String start,
                                  String end) {

        int startIndex = text.indexOf(start);

        if (startIndex == -1) {
            return "";
        }

        startIndex += start.length();

        int endIndex;

        if (end == null) {

            endIndex = text.length();

        } else {

            endIndex = text.indexOf(end, startIndex);

            if (endIndex == -1) {

                endIndex = text.length();

            }

        }

        String section = text.substring(startIndex, endIndex);

        section = section.replace("========================================", "");
        section = section.replace("----------------------------------------", "");

        return section.trim();
    }

    private int extractPercentage(String value) {

        try {

            value = value.replace("%", "").trim();

            StringBuilder number = new StringBuilder();

            for (char c : value.toCharArray()) {

                if (Character.isDigit(c)) {

                    number.append(c);

                }

            }

            if (number.isEmpty()) {

                return 0;

            }

            return Integer.parseInt(number.toString());

        }

        catch (Exception e) {

            return 0;

        }
    }

}