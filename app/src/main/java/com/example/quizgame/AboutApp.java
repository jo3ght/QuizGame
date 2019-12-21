package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.quizgame.MVP.AboutPresenter;
import com.example.quizgame.MVP.AboutView;

public class AboutApp extends AppCompatActivity implements AboutView {

    AboutPresenter aboutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        aboutPresenter = new AboutPresenter(this);
    }

    public void openMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openTW(View view) {
        aboutPresenter.tutorial();
    }

    public void openGoogle(View view) {
        aboutPresenter.feedback();
    }

    public void openIns(View view) {
        aboutPresenter.instagram();
    }

    public void openFacebook(View view) {
        aboutPresenter.about();
    }

    @Override
    public void shareFacebook() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://facebook.com/jo3ght"));
        startActivity(intent);
    }

    @Override
    public void contributeQuestion() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://forms.gle/W5YMVMdNBziVnWGx9"));
        startActivity(intent);

    }

    @Override
    public void watchTutorial() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://youtube.com"));
        startActivity(intent);
    }

    @Override
    public void openTW() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void backMain() {
        finish();

    }

    @Override
    public void openInstagram() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.instagram.com/jo3ght/"));
        startActivity(intent);
    }
}
