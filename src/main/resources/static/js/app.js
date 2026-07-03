// =====================================================
// AI Learning Analysis Platform
// app.js
// =====================================================

const form = document.getElementById("uploadForm");

const teacherFile = document.getElementById("teacherFile");

const studentFile = document.getElementById("studentFile");

const prompt = document.getElementById("prompt");

const result = document.getElementById("result");

// =====================================================
// Submit
// =====================================================

form.addEventListener("submit", async function (e) {

    e.preventDefault();

    // -----------------------------
    // Validation
    // -----------------------------

    if (teacherFile.files.length === 0) {

        alert("Please select Teacher Material.");

        return;

    }

    if (studentFile.files.length === 0) {

        alert("Please select Student Material.");

        return;

    }

    // -----------------------------
    // Prepare Form Data
    // -----------------------------

    const formData = new FormData();

    formData.append("teacherFile", teacherFile.files[0]);

    formData.append("studentFile", studentFile.files[0]);

    formData.append("prompt", prompt.value);

    // -----------------------------
    // Loading Screen
    // -----------------------------

    result.innerHTML = `

        <div class="loading">

            🤖 AI is analyzing your documents...

            <br><br>

            <small>

                Comparing teacher material with student understanding

            </small>

        </div>

    `;

    window.scrollTo({

        top: result.offsetTop - 30,

        behavior: "smooth"

    });

    try {

        const response = await fetch(

            "/api/files/analyze-learning",

            {

                method: "POST",

                body: formData

            }

        );

        if (!response.ok) {

            throw new Error("Unable to analyze the uploaded files.");

        }

        const data = await response.json();

        const analysis = data.learningAnalysis;
                result.innerHTML = `

                <!-- ==============================
                        REPORT HEADER
                =============================== -->

                <div class="report-header">

                    <h2>📊 AI Learning Analysis Report</h2>

                    <div class="report-info">

                        <div class="info-item">

                            <span>Status</span>

                            <strong>${data.message}</strong>

                        </div>

                        <div class="info-item">

                            <span>Files</span>

                            <strong>${data.fileName}</strong>

                        </div>

                        <div class="info-item">

                            <span>Total Size</span>

                            <strong>${data.fileSize} Bytes</strong>

                        </div>

                    </div>

                </div>

                <!-- ==============================
                        Teacher Summary
                =============================== -->

                <div class="summary-card">

                    <h3>📘 Teacher Summary</h3>

                    <div class="analysis-box">

                        ${(analysis.teacherSummary || "Not Available").replace(/\n/g,"<br>")}

                    </div>

                </div>

                <!-- ==============================
                        Student Summary
                =============================== -->

                <div class="summary-card">

                    <h3>👨‍🎓 Student Summary</h3>

                    <div class="analysis-box">

                        ${(analysis.studentSummary || "Not Available").replace(/\n/g,"<br>")}

                    </div>

                </div>

                <!-- ==============================
                        Dashboard
                =============================== -->

                <div class="dashboard-grid">

                    <div class="card">

                        <h3>📚 Topics Covered</h3>

                        <div class="analysis-box">

                            ${(analysis.topicsCovered || "Not Available").replace(/\n/g,"<br>")}

                        </div>

                    </div>

                    <div class="card">

                        <h3>✅ Topics Understood</h3>

                        <div class="analysis-box">

                            ${(analysis.topicsUnderstood || "Not Available").replace(/\n/g,"<br>")}

                        </div>

                    </div>

                    <div class="card">

                        <h3>❌ Knowledge Gaps</h3>

                        <div class="analysis-box">

                            ${(analysis.knowledgeGaps || "Not Available").replace(/\n/g,"<br>")}

                        </div>

                    </div>

                    <div class="card">

                        <h3>💡 Recommendations</h3>

                        <div class="analysis-box">

                            ${(analysis.recommendations || "Not Available").replace(/\n/g,"<br>")}

                        </div>

                    </div>

                </div>

                <!-- ==============================
                        Coverage Score
                =============================== -->

                <div class="score">

                    <h3>

                        📊 Coverage Score (${analysis.coverageScore}%)

                    </h3>

                    <div class="progress">

                        <div

                            class="progress-fill"

                            style="width:${analysis.coverageScore}%">

                        </div>

                    </div>

                </div>

                <!-- ==============================
                        Understanding Score
                =============================== -->

                <div class="score">

                    <h3>

                        🎯 Understanding Score (${analysis.understandingScore}%)

                    </h3>

                    <div class="progress">

                        <div

                            class="progress-fill"

                            style="width:${analysis.understandingScore}%">

                        </div>

                    </div>

                </div>
                        <!-- ==============================
                                Overall Evaluation
                        =============================== -->

                        <div class="overall-card">

                            <h3>📝 Overall Evaluation</h3>

                            <div class="analysis-box">

                                ${(analysis.overallEvaluation || "Not Available").replace(/\n/g,"<br>")}

                            </div>

                        </div>

                        <!-- ==============================
                                Extracted Content
                        =============================== -->

                        <details>

                            <summary>

                                📄 View Extracted Teacher & Student Content

                            </summary>

                            <div class="analysis-box">

                                ${(data.extractedText || "No extracted content available.").replace(/\n/g,"<br>")}

                            </div>

                        </details>

                        `;

                    }

                    catch(error){

                        console.error(error);

                        result.innerHTML = `

                            <div class="error">

                                <h2>

                                    ❌ Analysis Failed

                                </h2>

                                <br>

                                <p>

                                    ${error.message}

                                </p>

                            </div>

                        `;

                    }

                });