package com.example.quizgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.Database.Category;
import com.example.quizgame.Database.Question;
import com.example.quizgame.Database.QuizDatabaseHelper;
import com.example.quizgame.MVP.MainPresenter;
import com.example.quizgame.MVP.MainView;
import com.example.quizgame.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    Dialog mDialog;

    private ImageView tgSound;
    private TextView tvHighScore;
    private static final int RQ_CODE = 1;

    public static final String EXTRA_LEVEL = "level";
    public static final String EXTRA_ID_CATEGORY = "extraCategoryID";
    public static final String EXTRA_NAME_CATEGORY = "extraCategory";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private Spinner spinnerLevel;
    private Spinner spinnerCategory;
    private Button btnOkLevel;
    MainPresenter mainPresenter;


    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this,R.layout.activity_main);

        TextMenu textMenu = new TextMenu();

        textMenu.setPlayQuiz("Play");
        textMenu.setLogo("Quiz Game");
        textMenu.setExit("Exit");
        textMenu.setAbout("About");
        textMenu.setHighScore("High Score");
        textMenu.setRule("Rule");

        binding.setMenu(textMenu);

        loadHighscore();

        mDialog = new Dialog(this);

        mainPresenter = new MainPresenter(this);


        tgSound = findViewById(R.id.tgSound);
        final int[] mute = {0};
        tgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mute[0] ==0){
                    tgSound.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    mute[0] =1;
                }else {
                    tgSound.setImageResource(R.drawable.ic_volume_off_black_24dp);
                    mute[0] =0;
                }
            }
        });
    }

    public void openQuiz(View view) {
        chooseLevel();
    }

    public void openAbout(View view) {
        mainPresenter.about();
    }

    public void openExit(View view) {
        mainPresenter.exit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RQ_CODE) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highScore) {
                    updateHighScore(score);
                }
            }
        }
    }


    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        TextMenu textMenu = new TextMenu();


        textMenu.setBestScore(""+highScore);
    }

    private void updateHighScore(int newHighScore) {
        highScore = newHighScore;
        tvHighScore.setText("" + highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }

    public void openLevel(View view) {
        mainPresenter.rule();

    }

    private void chooseLevel() {
        mainPresenter.level();
    }

    @Override
    public void exitApp() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Exit?");
        alertDialog.setIcon(R.drawable.ic_android_black_24dp);
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Build.VERSION.SDK_INT >= 21){
                    finishAndRemoveTask();
                }

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel exit", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();

    }

    @Override
    public void openAbout() {
        startActivity(new Intent(MainActivity.this, AboutApp.class));
        finish();
    }

    @Override
    public void openRule() {
        startActivity(new Intent(MainActivity.this, Rules.class));
        finish();
    }

    @Override
    public void choseLevel() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.level, null);

        spinnerLevel = view1.findViewById(R.id.spinnerLevel);
        spinnerCategory = view1.findViewById(R.id.spinnerCategory);
        btnOkLevel = view1.findViewById(R.id.btnOkLevel);
        Button btnCancel = view1.findViewById(R.id.btnCancel);


        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view1)
                .create();
        alertDialog.show();

        String[] level = Question.getAllLevel();

        ArrayAdapter<String> arrayAdapterLevel = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, level);


        arrayAdapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(arrayAdapterLevel);

        QuizDatabaseHelper databaseHelper = QuizDatabaseHelper.getInstance(this);
        List<Category> categoryList = databaseHelper.getAllCategories();


        ArrayAdapter<Category> arrayAdapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryList);

        arrayAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(arrayAdapterCategory);

        btnOkLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Category selectCategory = (Category) spinnerCategory.getSelectedItem();
                int categoryID = selectCategory.getId();
                String categoryName = selectCategory.getName();

                String level = spinnerLevel.getSelectedItem().toString();
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra(EXTRA_ID_CATEGORY, categoryID);
                intent.putExtra(EXTRA_NAME_CATEGORY, categoryName);
                intent.putExtra(EXTRA_LEVEL, level);
                startActivityForResult(intent, RQ_CODE);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

}
