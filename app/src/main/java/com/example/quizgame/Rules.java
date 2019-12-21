package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.quizgame.MVP.RulePresenter;
import com.example.quizgame.MVP.RuleView;

public class Rules extends AppCompatActivity implements RuleView {
    RulePresenter rulePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        rulePresenter = new RulePresenter(this);
    }

    public void playQuiz(View view) {
        rulePresenter.backMainfRule();
//
    }

    @Override
    public void backMainfRule() {
        finish();
    }
}
