package com.example.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openQuiz(View view) {
        startActivity(new Intent(MainActivity.this,Rules.class));
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
                finish();
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
}
