package com.example.quizgame.MVP;

public class MainPresenter {
    MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void about() {
        mainView.openAbout();
    }

    public void rule() {
        mainView.openRule();
    }

    public void exit() {
        mainView.exitApp();
    }

    public void level(){
        mainView.choseLevel();
    }


}
