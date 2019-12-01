package com.example.illinoistrivia;

public class Question {
    public String questionText;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public int correctAnswer;
    public Question(String qText, String a1, String a2, String a3, String a4, int correctA) {
        questionText = qText;
        answer1 = a1;
        answer2 = a2;
        answer3 = a3;
        answer4 = a4;
        correctAnswer = correctA;
    }
}
