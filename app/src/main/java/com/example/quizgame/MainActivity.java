package com.example.quizgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.quizgame.Database.Question;

public class MainActivity extends AppCompatActivity {

    Dialog mDialog;

    private Button tgSound;
    private TextView tvHighScore;
    private static final int RQ_CODE = 1;

    private String getLevel;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tgSound = findViewById(R.id.tgSound);
        tvHighScore = findViewById(R.id.tvScore);

        loadHighscore();

        mDialog = new Dialog(this);


    }


    public void openQuiz(View view) {
        Intent intent = new Intent(MainActivity.this,QuizActivity.class);
        intent.putExtra("level",getLevel);
        startActivityForResult(intent,RQ_CODE);

    }

    public void openAbout(View view) {
        startActivity(new Intent(MainActivity.this,AboutApp.class));
    }

    public void openExit(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Exit?");
        alertDialog.setIcon(R.drawable.ic_android_black_24dp);
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onDestroy();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Cancel exit",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RQ_CODE){
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if(score > highScore){
                    updateHighScore(score);
                }
            }
        }
    }

    private void loadHighscore() {

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        tvHighScore.setText(""+highScore);
    }

    private void updateHighScore(int newHighScore){
        highScore = newHighScore;
        tvHighScore.setText(""+highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();

    }

   /* public void openLevel(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.level,null);
        AppCompatSpinner spinnerLevel = view1.findViewById(R.id.spinner_Level);//day nay, nay quen cai view1
        builder.setView(view1);
        String [] levels = Question.getAllLevel();
        Log.e("Levels",levels.length+"");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,levels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerLevel.setAdapter(arrayAdapter);
        AlertDialog alertDialog = builder.show();

    //qua kia di
    }*/
}
