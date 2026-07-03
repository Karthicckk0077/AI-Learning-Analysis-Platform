package com.learning.ailearninganalysisplatform.dto;

public class LearningAnalysis {

    private String teacherSummary;
    private String studentSummary;
    private String topicsCovered;
    private String topicsUnderstood;
    private String knowledgeGaps;
    private String recommendations;
    private int coverageScore;
    private int understandingScore;
    private String overallEvaluation;

    public LearningAnalysis() {
    }

    public String getTeacherSummary() {
        return teacherSummary;
    }

    public void setTeacherSummary(String teacherSummary) {
        this.teacherSummary = teacherSummary;
    }

    public String getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(String studentSummary) {
        this.studentSummary = studentSummary;
    }

    public String getTopicsCovered() {
        return topicsCovered;
    }

    public void setTopicsCovered(String topicsCovered) {
        this.topicsCovered = topicsCovered;
    }

    public String getTopicsUnderstood() {
        return topicsUnderstood;
    }

    public void setTopicsUnderstood(String topicsUnderstood) {
        this.topicsUnderstood = topicsUnderstood;
    }

    public String getKnowledgeGaps() {
        return knowledgeGaps;
    }

    public void setKnowledgeGaps(String knowledgeGaps) {
        this.knowledgeGaps = knowledgeGaps;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public int getCoverageScore() {
        return coverageScore;
    }

    public void setCoverageScore(int coverageScore) {
        this.coverageScore = coverageScore;
    }

    public int getUnderstandingScore() {
        return understandingScore;
    }

    public void setUnderstandingScore(int understandingScore) {
        this.understandingScore = understandingScore;
    }

    public String getOverallEvaluation() {
        return overallEvaluation;
    }

    public void setOverallEvaluation(String overallEvaluation) {
        this.overallEvaluation = overallEvaluation;
    }

    @Override
    public String toString() {
        return "LearningAnalysis{" +
                "teacherSummary='" + teacherSummary + '\'' +
                ", studentSummary='" + studentSummary + '\'' +
                ", topicsCovered='" + topicsCovered + '\'' +
                ", topicsUnderstood='" + topicsUnderstood + '\'' +
                ", knowledgeGaps='" + knowledgeGaps + '\'' +
                ", recommendations='" + recommendations + '\'' +
                ", coverageScore=" + coverageScore +
                ", understandingScore=" + understandingScore +
                ", overallEvaluation='" + overallEvaluation + '\'' +
                '}';
    }
}