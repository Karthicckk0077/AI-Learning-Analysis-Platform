package com.learning.ailearninganalysisplatform.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AIAnalysisService {

    @Value("${groq.api.key}")
    private String apiKey;

    private static final String API_URL =
            "https://api.groq.com/openai/v1/chat/completions";

    private static final String MODEL =
            "llama-3.3-70b-versatile";

    private final OkHttpClient client = new OkHttpClient();

    // ----------------------------------------------------
    // Generate Summary
    // ----------------------------------------------------

    public String generateSummary(String text) {

        String prompt = """
                Summarize the following study material in simple bullet points.

                """ + text;

        return callGroq(prompt);

    }

    // ----------------------------------------------------
    // Teacher vs Student Comparison
    // ----------------------------------------------------

    public String compareLearning(String teacherText,
                                  String studentText,
                                  String userPrompt) {

        String prompt = """
You are an AI Learning Analysis Assistant.

Compare the teacher material with the student material.

Return your response in the following format.

========================================
TEACHER SUMMARY
========================================

Summarize what the teacher taught.

========================================
STUDENT SUMMARY
========================================

Summarize what the student understood.

========================================
TOPICS COVERED
========================================

List all topics taught.

========================================
TOPICS UNDERSTOOD
========================================

List the concepts correctly understood.

========================================
KNOWLEDGE GAPS
========================================

List missing concepts.

========================================
COVERAGE SCORE
========================================

Percentage only.

========================================
UNDERSTANDING SCORE
========================================

Percentage only.

========================================
RECOMMENDATIONS
========================================

Provide recommendations.

========================================
OVERALL EVALUATION
========================================

Final evaluation.

""";

        if (userPrompt != null && !userPrompt.isBlank()) {

            prompt += "\nAdditional Instructions:\n"
                    + userPrompt
                    + "\n";

        }

        prompt += """

==========================
TEACHER MATERIAL
==========================

""" + teacherText +

                """
                
                ==========================
                STUDENT MATERIAL
                ==========================
                
                """ + studentText;

        return callGroq(prompt);

    }

    // ----------------------------------------------------
    // Common Groq Call
    // ----------------------------------------------------

    private String callGroq(String prompt) {

        try {

            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", prompt);

            JSONArray messages = new JSONArray();
            messages.put(message);

            JSONObject body = new JSONObject();
            body.put("model", MODEL);
            body.put("messages", messages);
            body.put("temperature", 0.3);

            RequestBody requestBody = RequestBody.create(
                    body.toString(),
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            String responseBody = response.body() != null
                    ? response.body().string()
                    : "";

            if (!response.isSuccessful()) {

                return "Groq API Error "
                        + response.code()
                        + "\n\n"
                        + responseBody;

            }

            JSONObject json = new JSONObject(responseBody);

            return json.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        }

        catch (IOException e) {

            return "Error calling Groq API : " + e.getMessage();

        }

    }

}