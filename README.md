# 🤖 AI Learning Analysis Platform

An AI-powered web application that compares **Teacher Study Material** with **Student Learning Material** to evaluate learning outcomes using Artificial Intelligence.

---

## 📖 Project Overview

The AI Learning Analysis Platform helps teachers and students understand learning progress by comparing uploaded documents. It extracts text from uploaded files, analyzes them using the Groq AI model, and generates a detailed learning analysis report.

The generated report includes:

- 📘 Teacher Summary
- 👨‍🎓 Student Summary
- 📚 Topics Covered
- ✅ Topics Understood
- ❌ Knowledge Gaps
- 💡 Personalized Recommendations
- 📊 Coverage Score
- 🎯 Understanding Score
- 📝 Overall Evaluation
- 📄 Extracted Teacher & Student Content

---

## ✨ Features

- Upload Teacher Notes
- Upload Student Notes
- Supports **PDF**, **DOCX**, and **TXT**
- AI-powered document comparison
- Automatic topic identification
- Knowledge gap detection
- Personalized learning recommendations
- Coverage & Understanding scores
- Responsive and modern dashboard
- Error handling for invalid files

---

## 🛠 Technology Stack

### Frontend
- HTML5
- CSS3
- JavaScript

### Backend
- Java 21
- Spring Boot
- Maven

### AI Integration
- Groq API (LLM)

### Libraries Used
- Apache PDFBox (PDF extraction)
- Apache POI (DOCX extraction)

---

## 📂 Project Structure

```text
ai-learning-analysis-platform
│
├── docs
│   ├── AI_Learning_Analysis_HLD.pdf
│   ├── AI_Learning_Analysis_Test_Cases.xlsx
│   ├── AI_Learning_Analysis_Test_Evidence.docx
│   ├── Teacher_Notes.docx
│   └── Student_Notes.docx
│
├── src
│   ├── main
│   │   ├── java
│   │   ├── resources
│   │   └── static
│   │       ├── css
│   │       ├── js
│   │       └── index.html
│
├── README.md
├── pom.xml
├── .gitignore
└── mvnw
```

---

## 📁 Documentation

The **docs/** folder contains the project documentation:

- 📄 High Level Design (HLD)
- 📊 Test Cases
- 📑 Test Evidence
- 📘 Teacher Notes
- 📗 Student Notes

These documents describe the project architecture, testing approach, validation process, and sample learning materials used for testing.

---

## 🎥 Project Demo

Watch the complete project demonstration here:

**Google Drive:**

https://drive.google.com/file/d/1UiFMiCH9uTQAsaQAyZQLpou_MbwUqCy8/view?usp=sharing

---

## 📑 Supported File Types

| File Type | Supported |
|-----------|-----------|
| PDF | ✅ |
| DOCX | ✅ |
| TXT | ✅ |

---

## ⚙️ Installation

### 1. Clone the repository

```bash
git clone https://github.com/Karthicckk0077/AI-Learning-Analysis-Platform.git
```

### 2. Open the project

Open the project using IntelliJ IDEA (or your preferred Java IDE).

### 3. Configure the API Key

Open:

```text
src/main/resources/application.properties
```

Replace:

```properties
groq.api.key=YOUR_GROQ_API_KEY
```

with your actual Groq API key:

```properties
groq.api.key=YOUR_ACTUAL_GROQ_API_KEY
```

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

or run the Spring Boot application directly from IntelliJ.

Open your browser and visit:

```text
http://localhost:8080
```

---

## 🚀 How to Use

1. Launch the application.
2. Upload the Teacher document.
3. Upload the Student document.
4. (Optional) Enter additional AI instructions.
5. Click **Analyze Learning**.
6. View the AI-generated learning analysis report.

---

## 📊 AI Analysis Output

The application generates:

- Teacher Summary
- Student Summary
- Topics Covered
- Topics Understood
- Knowledge Gaps
- Personalized Recommendations
- Coverage Score
- Understanding Score
- Overall Evaluation

---

## 🧪 Testing

The project includes:

- Functional Test Cases
- Negative Test Cases
- Long Document Testing
- Corrupted File Testing
- Responsive UI Testing

---

## 📈 Future Enhancements

- User Authentication
- Download Report as PDF
- Analysis History
- Charts & Analytics
- AI Study Plan Generator
- Video Lecture Analysis
- Speech-to-Text Support

---

## 👨‍💻 Developer

**Karthik**

---

## 📄 License

This project was developed for educational and learning purposes.

---

## ⭐ Acknowledgements

- Spring Boot
- Apache PDFBox
- Apache POI
- Groq API
- Java 21