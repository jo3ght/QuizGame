package com.example.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.Database.Question;
import com.example.quizgame.Database.QuizDatabaseHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.crypto.Cipher;

public class QuizActivity extends AppCompatActivity {


    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_SCORES = "extraScores";
    private static final long COUNT_DOWN = 30000;

    private TextView tvQuestion,tvScoreQuiz,tvTimeQuiz,tvQuizNumber;
    private RadioGroup radioGroup;
    private RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    private Button btnNext;
    private ColorStateList textColorDefaultRb;

    private ColorStateList colorStateListDF;
    private ColorStateList textColorDefaultCd;


    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView tvCategory;
    private TextView tvLevel;
    private List<Question> questionList;
    private int questionCounter;
    private int questionTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvScoreQuiz = findViewById(R.id.tvScoreQuiz);
        tvTimeQuiz = findViewById(R.id.tvTimeQuiz);
        tvQuizNumber = findViewById(R.id.tvQuizNumber);
        tvCategory = findViewById(R.id.tvCategory);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);
        radioButtonD = findViewById(R.id.radioButtonD);
        btnNext = findViewById(R.id.btnNext);
        tvLevel = findViewById(R.id.tvLevel);

        textColorDefaultRb = radioButtonA.getTextColors();
        textColorDefaultCd = tvTimeQuiz.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(MainActivity.EXTRA_ID_CATEGORY,0);
        String categoryName = intent.getStringExtra(MainActivity.EXTRA_NAME_CATEGORY);
        String level = intent.getStringExtra(MainActivity.EXTRA_LEVEL);


        tvLevel.setText("Level: "+ level);
        tvCategory.setText(categoryName);


        QuizDatabaseHelper quizDatabaseHelper = QuizDatabaseHelper.getInstance(this);
        questionList = quizDatabaseHelper.getQuestions(categoryID,level);

        questionTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(radioButtonA.isChecked() || radioButtonB.isChecked() || radioButtonC.isChecked() || radioButtonD.isChecked()){
                        checkAnswer();
                    }
                    else {
                        Toast.makeText(QuizActivity.this,"Choose an answer first!",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });


    }

    private void showNextQuestion(){
        radioButtonA.setTextColor(textColorDefaultRb);
        radioButtonB.setTextColor(textColorDefaultRb);
        radioButtonC.setTextColor(textColorDefaultRb);
        radioButtonD.setTextColor(textColorDefaultRb);

        radioGroup.clearCheck();


        if(questionCounter < questionTotal){
            currentQuestion = questionList.get(questionCounter);

            tvQuestion.setText(currentQuestion.getQuestion());
            radioButtonA.setText(currentQuestion.getOptionA());
            radioButtonB.setText(currentQuestion.getOptionB());
            radioButtonC.setText(currentQuestion.getOptionC());
            radioButtonD.setText(currentQuestion.getOptionD());

            questionCounter++;
            tvQuizNumber.setText("Question: "+ questionCounter + "/" + questionTotal);
            answered = false;

            btnNext.setText("Next");

            timeLeftInMillis = COUNT_DOWN;
            startCountDown();

        }else {
            showResult();
        }

    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTextTime();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateTextTime();
                checkAnswer();
                showResult();


            }
        }.start();
    }

    private void showResult() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.result_dialog,null);

        Button button = view.findViewById(R.id.acceptButton);
        Button exit = view.findViewById(R.id.cancelButton);
        TextView tvScore = view.findViewById(R.id.tvNumberScore);

        tvScore.setText("" + score);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finishQuiz();
                startActivity(new Intent(QuizActivity.this,MainActivity.class));

            }

            private void openMain() {
                finishQuiz();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();


    }

    private void updateTextTime(){
        int minutes = (int)(timeLeftInMillis/1000)/60;
        int seconds = (int)(timeLeftInMillis/1000)%60;


        String time = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);


        tvTimeQuiz.setText(time);


        if(timeLeftInMillis < 5000){
            tvTimeQuiz.setTextColor(Color.RED);
        }
        else {
            tvTimeQuiz.setTextColor(Color.WHITE);
        }
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbSelected) + 1;


        if(answerNr == currentQuestion.getAnswerNr()){
            score+=10;
            tvScoreQuiz.setText("Score: " + score);

            Intent rsIntent = new Intent();
            rsIntent.putExtra(EXTRA_SCORES,score);
            setResult(RESULT_OK,rsIntent);
        }
        showAnswer();

    }

    private void showAnswer(){
        radioButtonA.setTextColor(Color.RED);
        radioButtonB.setTextColor(Color.RED);
        radioButtonC.setTextColor(Color.RED);
        radioButtonD.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                radioButtonA.setTextColor(Color.GREEN);
                break;
            case 2:
                radioButtonB.setTextColor(Color.GREEN);
                break;
            case 3:
                radioButtonC.setTextColor(Color.GREEN);
                break;
            case 4:
                radioButtonD.setTextColor(Color.GREEN);
                break;
        }

        if(questionCounter < questionTotal){
            btnNext.setText("Next");

        }else {
            btnNext.setText("Finish");
        }



    }
    private void finishQuiz(){
        Intent rsIntent = new Intent();
        rsIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,rsIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }
        else {
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}
