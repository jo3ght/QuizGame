package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.quizgame.Database.Question;

public class Mode extends AppCompatActivity {

    public Spinner spinnerLevel;

    public static final String EXTRA_LEVEL = "extraLevel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        spinnerLevel = findViewById(R.id.spinner_Level);


        String [] level = Question.getAllLevel();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,level);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(arrayAdapter);

        String getLevel = spinnerLevel.getSelectedItem().toString();
        Intent intent = new Intent(Mode.this, QuizActivity.class);
        intent.putExtra(EXTRA_LEVEL, getLevel);
        startActivity(intent);


    }

}
