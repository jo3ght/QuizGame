package com.example.quizgame;

public class TextMenu {
        private String playQuiz;
        private String about;
        private String logo;
        private String rule;
        private String exit;
        private String highScore;
        private String bestScore;


    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }

    public TextMenu(){

        }

        public TextMenu(String playQuiz, String about, String logo, String rule, String exit,
                        String highScore,String bestScore) {
            this.playQuiz = playQuiz;
            this.about = about;
            this.logo = logo;
            this.rule = rule;
            this.exit = exit;
            this.highScore = highScore;
            this.bestScore = bestScore;
        }

        public String getPlayQuiz() {
            return playQuiz;
        }

        public void setPlayQuiz(String playQuiz) {
            this.playQuiz = playQuiz;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getExit() {
            return exit;
        }

        public void setExit(String exit) {
            this.exit = exit;
        }

        public String getHighScore() {
            return highScore;
        }

    public String getBestScore() {
        return bestScore;
    }

    public void setHighScore(String highScore) {
            this.highScore = highScore;
        }
    }

