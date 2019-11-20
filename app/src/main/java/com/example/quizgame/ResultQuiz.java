package com.example.quizgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultQuiz extends AppCompatActivity {
    private TextView tvResultScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);

        tvResultScore = findViewById(R.id.tvResultScore);

        Intent intent = getIntent();

        int scoreRS = intent.getIntExtra(QuizActivity.EXTRA_SCORES,0);

        tvResultScore.setText(""+scoreRS);

    }


    public void openMain(View view) {
        startActivity(new Intent(ResultQuiz.this, MainActivity.class));

    }
}
