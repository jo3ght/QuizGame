package com.example.quizgame.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.quizgame.Database.QuizContract.*;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizGame.db";
    private static final int DATABASE_VER = 8;


    private static  QuizDatabaseHelper instance;

    private SQLiteDatabase db;

    private QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public static synchronized QuizDatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new QuizDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoryTable.TABLE_NAME + " ( " +
                CategoryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoryTable.COLUMN_NAME + " TEXT " +
                " ) " ;

        final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTIONA + " TEXT, " +
                QuestionTable.COLUMN_OPTIONB + " TEXT, " +
                QuestionTable.COLUMN_OPTIONC + " TEXT, " +
                QuestionTable.COLUMN_OPTIOND + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER,  " +
                QuestionTable.COLUMN_LEVEL + " TEXT ," +
                QuestionTable.COLUMN_CATE_ID + " INTEGER,  " +
                " FOREIGN KEY (" +QuestionTable.COLUMN_CATE_ID + " ) REFERENCES " +
                CategoryTable.TABLE_NAME + " ( " + CategoryTable._ID + ")" + " ON DELETE CASCADE " +
                ")";

        db.execSQL(CREATE_CATEGORIES_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionToTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("Technology");
        addCategory(c1);
        Category c2 = new Category("Music");
        addCategory(c2);
        Category c3 = new Category("Sport");
        addCategory(c3);
        Category c4 = new Category("History");
        addCategory(c4);
        Category c5 = new Category("Game");
        addCategory(c5);
        Category c6 = new Category("Science");
        addCategory(c6);
    }

    private void addCategory(Category category){
        ContentValues cv  = new ContentValues();
        cv.put(CategoryTable.COLUMN_NAME,category.getName());
        db.insert(CategoryTable.TABLE_NAME,null,cv);
    }

    private void fillQuestionToTable() {
        Question q1 = new Question("Easy: Which programming language for Android App",
                "Java","PHP","HTML","Python",
                1,Question.LEVEL_EASY,Category.TECHNOLOGY);
        addQuestion(q1);

        Question q2 = new Question("Medium: Who sings Old Town Road",
                "Lil Nas","Ed Sheeran ","Harry Style","Adam Levi",
                1,Question.LEVEL_MEDIUM,Category.MUSIC);
        addQuestion(q2);

        Question q3 = new Question("Who is the coach of Manchester United",
                "Jurgen Klopp","Jose","Son Heung min","Messi",
                1,Question.LEVEL_EASY,Category.SPORT);
        addQuestion(q3);

        Question q4 = new Question("Which country has a lot of deaths in WW2 ?",
                "Soviet Onion","USA","Germany","China",
                1,Question.LEVEL_EASY,Category.HISTORY);
        addQuestion(q4);

        Question q5 = new Question("Where's bomb code in CS:GO?",
                "7355608","9111911","123457","6969696",
                1,Question.LEVEL_EASY,Category.HISTORY);
        addQuestion(q5);

        Question q6 = new Question("Earth is located in which galaxy?",
                "Milky Way","Asgardian","CykaBlyat","China",
                1,Question.LEVEL_HARD,Category.SCIENCE);
        addQuestion(q6);

        Question q7 = new Question("Earth is located in which galaxy?",
                "Milky Way","Asgardian","CykaBlyat","China",
                1,Question.LEVEL_EASY,7);
        addQuestion(q7);

        Question q8 = new Question("Who's the best handsome man still alive in the world?",
                "Anh Tu","Anh Tu","Anh Tu","Anh Tu",
                1,Question.LEVEL_EASY,8);
        addQuestion(q8);

        Question q9 = new Question("What's your name?",
                "Huy","PHP","HTML","Python",
                1,Question.LEVEL_HARD,Category.MUSIC);
        addQuestion(q9);





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


        cv.put(QuestionTable.COLUMN_CATE_ID, question.getCategoryID());
        db.insert(QuestionTable.TABLE_NAME,null,cv);
    }

    public  List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CategoryTable.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(CategoryTable._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(CategoryTable.COLUMN_NAME)));
                categoryList.add(category);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
    }

    public List<Question> getAllQuestion(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor  = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME,null);

        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOptionA(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONA)));
                question.setOptionB(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONB)));
                question.setOptionC(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONC)));
                question.setOptionD(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIOND)));

                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setLevel(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_LEVEL)));

                question.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CATE_ID)));

                questionList.add(question);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public List<Question> getQuestions(int categoryID, String level){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATE_ID + " =  ? " +
                " AND " + QuestionTable.COLUMN_LEVEL + " = ? ";

        String[] selectArgs = new String[]{String.valueOf(categoryID),level};

        Cursor cursor = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectArgs,
                null,null,null
        );

        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOptionA(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONA)));
                question.setOptionB(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONB)));
                question.setOptionC(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIONC)));
                question.setOptionD(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTIOND)));

                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setLevel(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_LEVEL)));

                question.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CATE_ID)));

                questionList.add(question);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }


}

























