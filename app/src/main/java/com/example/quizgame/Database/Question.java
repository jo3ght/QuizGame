package com.example.quizgame.Database;

public class Question {

    public static final String LEVEL_EASY = "Easy";
    public static final String LEVEL_MEDIUM = "Medium";
    public static final String LEVEL_HARD = "Hard";

    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int answerNr;
    private String level;

    public Question(){

    }

    public Question(String question, String optionA, String optionB, String optionC, String optionD, int answerNr,String level) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerNr = answerNr;
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static String[] getAllLevel(){
        return new String[]{
                LEVEL_EASY,
                LEVEL_MEDIUM,
                LEVEL_HARD
        };
    }
}
