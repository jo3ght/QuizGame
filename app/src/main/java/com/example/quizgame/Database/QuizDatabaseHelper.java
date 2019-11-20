package com.example.quizgame.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizgame.Database.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizGame.db";
    private static final int DATABASE_VER = 5;

    private SQLiteDatabase db;

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTIONA + " TEXT, " +
                QuestionTable.COLUMN_OPTIONB + " TEXT, " +
                QuestionTable.COLUMN_OPTIONC + " TEXT, " +
                QuestionTable.COLUMN_OPTIOND + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER,  " +
                QuestionTable.COLUMN_LEVEL + " TEXT " +
                ")";

        db.execSQL(CREATE_QUESTIONS_TABLE);
        fillQuestionToTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionToTable() {
        Question q1 = new Question("Easy A is correct ",
                "A","B","C","D",1,Question.LEVEL_EASY);
        addQuestion(q1);

        Question q2 = new Question("Easy B is correct ",
                "A","B","C","D",2,Question.LEVEL_EASY);
        addQuestion(q2);

        Question q3 = new Question("M C is correct ",
                "A","B","C","D",3,Question.LEVEL_MEDIUM);
        addQuestion(q3);

        Question q4 = new Question("M D is correct ",
                "A","B","C","D",4,Question.LEVEL_MEDIUM);
        addQuestion(q4);

        Question q5 = new Question("H A is correct ",
                "A","B","C","D",5,Question.LEVEL_HARD);
        addQuestion(q5);

        Question q6 = new Question("H A is correct ",
                "A","B","C","D",5,Question.LEVEL_HARD);
        addQuestion(q6);

    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTIONA,question.getOptionA());
        cv.put(QuestionTable.COLUMN_OPTIONB,question.getOptionB());
        cv.put(QuestionTable.COLUMN_OPTIONC,question.getOptionC());
        cv.put(QuestionTable.COLUMN_OPTIOND,question.getOptionD());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_LEVEL,question.getLevel());

        db.insert(QuestionTable.TABLE_NAME,null,cv);
    }

    public List<Question> getAllQuestion(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor  = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME,null);

        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOptionA(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONA)));
                question.setOptionB(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONB)));
                question.setOptionC(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONC)));
                question.setOptionD(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIOND)));

                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setLevel(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_LEVEL)));


                questionList.add(question);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public List<Question> getQuestions(String level){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectArgs = new String[]{level};
        Cursor cursor  = db.rawQuery(" SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE  " + QuestionTable.COLUMN_LEVEL
                + " = ? " ,selectArgs);

        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOptionA(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONA)));
                question.setOptionB(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONB)));
                question.setOptionC(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONC)));
                question.setOptionD(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIOND)));

                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setLevel(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_LEVEL)));

                questionList.add(question);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }


}

























