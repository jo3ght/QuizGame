package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
    }

    public void openMain(View view) {
        startActivity(new Intent(AboutApp.this,MainActivity.class));
    }

    public void openTW(View view) {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://twitter.com/jo3ght"));
        startActivity(intent);
    }

    public void openGoogle(View view) {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://gmail.com"));
        startActivity(intent);
    }

    public void openIns(View view) {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/jo3ght"));
        startActivity(intent);
    }

    public void openFacebook(View view) {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://facebook.com/jo3ght"));
        startActivity(intent);
    }
}
