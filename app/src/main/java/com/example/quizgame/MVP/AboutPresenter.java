package com.example.quizgame.MVP;

public class AboutPresenter {

    AboutView aboutView;

    public AboutPresenter(AboutView aboutView) {
        this.aboutView = aboutView;
    }

    public void about() {
        aboutView.shareFacebook();
    }

    public void tutorial() {
        aboutView.watchTutorial();
    }

    public void feedback() {
        aboutView.contributeQuestion();
    }

    public void instagram() {
        aboutView.openInstagram();
    }

    public void backMain() {
        aboutView.backMain();
    }
}
