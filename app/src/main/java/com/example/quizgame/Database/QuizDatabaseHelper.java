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
    private static final int DATABASE_VER = 10;


    private static QuizDatabaseHelper instance;

    private SQLiteDatabase db;

    private QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public static synchronized QuizDatabaseHelper getInstance(Context context) {
        if (instance == null) {
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
                " ) ";

        final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTIONA + " TEXT, " +
                QuestionTable.COLUMN_OPTIONB + " TEXT, " +
                QuestionTable.COLUMN_OPTIONC + " TEXT, " +
                QuestionTable.COLUMN_OPTIOND + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER,  " +
                QuestionTable.COLUMN_LEVEL + " TEXT ," +
                QuestionTable.COLUMN_CATE_ID + " INTEGER,  " +
                " FOREIGN KEY (" + QuestionTable.COLUMN_CATE_ID + " ) REFERENCES " +
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

    private void fillCategoriesTable() {
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

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoryTable.COLUMN_NAME, category.getName());
        db.insert(CategoryTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionToTable() {
        Question q1 = new Question("Easy: Which programming language for Android App",
                "Java", "PHP", "HTML", "Python",
                1, Question.LEVEL_EASY, Category.TECHNOLOGY);
        addQuestion(q1);

        Question q2 = new Question("Medium: Who sings Old Town Road",
                "Lil Nas", "Ed Sheeran ", "Harry Style", "Adam Levi",
                1, Question.LEVEL_MEDIUM, Category.MUSIC);
        addQuestion(q2);

        Question q3 = new Question("Who is the coach of Liverpool",
                "Jurgen Klopp", "Jose", "Son Heung min", "Messi",
                1, Question.LEVEL_EASY, Category.SPORT);
        addQuestion(q3);

        Question q4 = new Question("Which country has a lot of deaths in WW2 ?",
                "Soviet Onion", "USA", "Germany", "China",
                1, Question.LEVEL_EASY, Category.HISTORY);
        addQuestion(q4);

        Question q5 = new Question("Where's bomb code in CS:GO?",
                "7355608", "9111911", "123457", "6969696",
                1, Question.LEVEL_EASY, Category.HISTORY);
        addQuestion(q5);

        Question q6 = new Question("Earth is located in which galaxy?",
                "Milky Way", "Asgardian", "CykaBlyat", "China",
                1, Question.LEVEL_HARD, Category.SCIENCE);
        addQuestion(q6);



        //SCIENCE LEVEL EASY

        Question SCI1 = new Question("Which planet is nearest the sun?",
                "Mercury", "Moon", "Earth", "China",
                1, Question.LEVEL_EASY, Category.SCIENCE);
        addQuestion(SCI1);

        Question SCI2 = new Question("How many eyes does a honeybee have? ",
                "1", "2", "4", "5",
                4, Question.LEVEL_EASY, Category.SCIENCE);
        addQuestion(SCI2);

        Question SCI3 = new Question("Who discovered the law of gravity?",
                "Sir Isaac Newton", "Ngo Ba Kha", "Anderson Berlus", "Cyka Blyat",
                1, Question.LEVEL_EASY, Category.SCIENCE);
        addQuestion(SCI3);

        Question SCI4 = new Question("What is the largest flower in the world?",
                "Rafflesia", "Sunflower", "Rose", "Violet",
                1, Question.LEVEL_EASY, Category.SCIENCE);
        addQuestion(SCI4);

        Question SCI5 = new Question("What do the letters DNA stand for?",
                "Rush B", "Moon", "Deoxyribonucleic acid", "China",
                3, Question.LEVEL_EASY, Category.SCIENCE);
        addQuestion(SCI5);

        // SCIENCE LEVEL MEDIUM

        Question SCIM1 = new Question("What are the three most important types of fuel?",
                "Coal, oil, gas", "P90 Rush B", "Vinahouse", "China",
                1, Question.LEVEL_MEDIUM, Category.SCIENCE);
        addQuestion(SCIM1);

        Question SCIM2 = new Question("What is the strongest muscle in the human body?",
                "Hands", "Tongue", "Eyes", "Heart",
                2, Question.LEVEL_MEDIUM, Category.SCIENCE);
        addQuestion(SCIM2);

        Question SCIM3 = new Question("Which element is graphite composed of?",
                "Oxi", "Carbon", "Canxi", "Cyka Blyat",
                2, Question.LEVEL_MEDIUM, Category.SCIENCE);
        addQuestion(SCIM3);

        Question SCIM4 = new Question("Who invented the hovercraft?",
                "Rafflesia", "Sunflower", "Christopher Cockerell", "Violet",
                3, Question.LEVEL_MEDIUM, Category.SCIENCE);
        addQuestion(SCIM4);

        Question SCIM5 = new Question("Who invented the telephone?",
                "Alexander Graham Bell", "Lenin", "Deoxyribonucleic acid", "China",
                1, Question.LEVEL_MEDIUM, Category.SCIENCE);
        addQuestion(SCIM5);

        // SCIENCE LEVEL HARD

        Question SCIH1 = new Question("Who invented Coca-Cola?",
                "John Pemberton", "P90 Rush B", "Vinahouse", "China",
                1, Question.LEVEL_HARD, Category.SCIENCE);
        addQuestion(SCIH1);

        Question SCIH2 = new Question("Which planet was the Galileo spacecraft sent to study?",
                "Mars", "Moon", "Jupiter", "Sun",
                3, Question.LEVEL_HARD, Category.SCIENCE);
        addQuestion(SCIH2);

        Question SCIH3 = new Question("How many incisor teeth are there in a full set of adult teeth?",
                "2 incisor teeth", "8 incisor teeth", "5 incisor teeth", "9 incisor teeth",
                2, Question.LEVEL_HARD, Category.SCIENCE);
        addQuestion(SCIH3);

        Question SCIH4 = new Question("128 ounces are equivalent to one what?",
                "Oz", "Kg", "Gallon", "Violet",
                3, Question.LEVEL_HARD, Category.SCIENCE);
        addQuestion(SCIH4);

        Question SCIH5 = new Question("What was the name of the space mission that landed the first humans on the moon?",
                "Apollo 11", "Apollo 12", "Cyka Blyat 91", "China",
                1, Question.LEVEL_HARD, Category.SCIENCE);
        addQuestion(SCIH5);



        Question q7 = new Question("Earth is located in which galaxy?",
                "Milky Way", "Asgardian", "CykaBlyat", "China",
                1, Question.LEVEL_EASY, 7);
        addQuestion(q7);

        Question q8 = new Question("Who's the best handsome man still alive in the world?",
                "Anh Tu", "Anh Tu", "Anh Tu", "Anh Tu",
                1, Question.LEVEL_EASY, 8);
        addQuestion(q8);

        Question q9 = new Question("What's your name?",
                "Huy", "PHP", "HTML", "Python",
                1, Question.LEVEL_HARD, Category.MUSIC);
        addQuestion(q9);

        // SPORT EASY

        Question QSE1 = new Question("In which country were the first Olympic Games held?",
                "Greece", "Viet Nam", "Japan", "China",
                1, Question.LEVEL_EASY, Category.SPORT);
        addQuestion(QSE1);

        Question QSE2 = new Question("How long is an Olympic swimming pool?",
                "Ten meters", "Fifty meters", "Eleven meters", "20 meters",
                2, Question.LEVEL_EASY, Category.SPORT);
        addQuestion(QSE2);

        Question QSE3 = new Question("What is the national sport in Japan?",
                "Sumo Wrestling", "Soccer", "Volleyball", "Swimming",
                1, Question.LEVEL_EASY, Category.SPORT);
        addQuestion(QSE3);

        Question QSE4 = new Question("How many players are on each side of the net in beach volleyball?",
                "Two players", "Five players", "Four players", "Nine players",
                1, Question.LEVEL_EASY, Category.SPORT);
        addQuestion(QSE4);

        Question QSE5 = new Question("What was the Olympic city of 1992 ?",
                "Hanoi", "New York", "Tokyo", "Barcelona",
                4, Question.LEVEL_EASY, Category.SPORT);
        addQuestion(QSE5);

        // SPORT MEDIUM

        Question QSM1 = new Question("What is called a yoga posture?",
                "Greece", "Dance", "Asana", "Cyka",
                3, Question.LEVEL_MEDIUM, Category.SPORT);
        addQuestion(QSM1);

        Question QSM2 = new Question("In which sport can you win the Davis Cup?",
                "Soccer", "Tennis", "Marathon", "Volleyball",
                2, Question.LEVEL_MEDIUM, Category.SPORT);
        addQuestion(QSM2);

        Question QSM3 = new Question("What is the national sport in Japan?",
                "Sumo Wrestling", "Soccer", "Volleyball", "Swimming",
                1, Question.LEVEL_MEDIUM, Category.SPORT);
        addQuestion(QSM3);

        Question QSM4 = new Question("Which popular fitness method was invented by a German?",
                "Street workout", "Pilates", "GYM", "Kick boxing",
                2, Question.LEVEL_MEDIUM, Category.SPORT);
        addQuestion(QSM4);

        Question QSM5 = new Question("How many players has a hockey team got on the ice?",
                "Two players", "Four players", "Five players", "Six players",
                4, Question.LEVEL_MEDIUM, Category.SPORT);
        addQuestion(QSM5);

        // SPORT HARD

        Question QSH1 = new Question("Who are the top two scorers in the history of the SPL?",
                "Kris Boyd, Henrik Larsson", "Ramsey", "Cyka Blyat", "Mr Bean",
                1, Question.LEVEL_HARD, Category.SPORT);
        addQuestion(QSH1);

        Question QSH2 = new Question("Who did Wales appoint as their manager in January 2018?",
                "Your mom", "Ryan Giggs", "Marathon", "Volleyball",
                2, Question.LEVEL_HARD, Category.SPORT);
        addQuestion(QSH2);

        Question QSH3 = new Question("What name is shared by Birmingham, Coventry and Leicester?",
                "Sumo Wrestling", "Hola", "Volleyball", "City",
                4, Question.LEVEL_HARD, Category.SPORT);
        addQuestion(QSH3);

        Question QSH4 = new Question("Hears and Hibs come from which Scottish city?",
                "Street", "Edinburgh", "Mike", "David",
                2, Question.LEVEL_HARD, Category.SPORT);
        addQuestion(QSH4);

        Question QSH5 = new Question("Can a goalkeeper score a goal for his own team?",
                "No", "Yes", "Can not", "Six players",
                2, Question.LEVEL_HARD, Category.SPORT);
        addQuestion(QSH5);



        // MUSIC EASY

        Question QME1 = new Question("Which Scottish singer-songwriter had a 2019 hit with 'Someone You Loved'?",
                "Lewis Capaldi", "Harry Styles", "Paolo Nutini", "Mr Bean",
                1, Question.LEVEL_EASY, Category.MUSIC);
        addQuestion(QME1);

        Question QME2 = new Question("Sophie Turner appeared in the video of which Jonas Brothers single?",
                "Cool", "Ryan Giggs", "Sucker", "Volleyball",
                3, Question.LEVEL_EASY, Category.MUSIC);
        addQuestion(QME2);

        Question QME3 = new Question("Which British group featuring Tom Fletcher, Danny Jones, Dougie Poynter and Harry Judd reformed in 2019?",
                "Mariah Carey", "Pink", "Christina", "City",
                2, Question.LEVEL_EASY, Category.MUSIC);
        addQuestion(QME3);

        Question QME4 = new Question("Keith Flint passed away in March 2019. With which British electronica group did he make his name?",
                "Prodigy", "Chemical Brothers", "Fat Boy Slim", "David",
                1, Question.LEVEL_EASY, Category.MUSIC);
        addQuestion(QME4);

        Question QME5 = new Question("Which 2019 hit contains the lyrics 'I got the horses in the back / Horse tack is attached / Hat is matte black / Got the boots that's black to match'?",
                "Sorry", "Old Town Road", "I love You", "7 Years",
                2, Question.LEVEL_EASY, Category.MUSIC);
        addQuestion(QME5);

        // MUSIC MEDIUM

        Question QMM1 = new Question("Ed Sheeran teamed up with which American superstar on the May hit 'I Don't Care'?",
                "Arianna Grande", "Selena Gomez", "Justin Bieber", "Mr Bean",
                3, Question.LEVEL_MEDIUM, Category.MUSIC);
        addQuestion(QMM1);

        Question QMM2 = new Question("Which UK rapper had a number one single in 2019 with Vossi Bop?",
                "Stormzy", "Ryan Giggs", "Sucker", "Volleyball",
                1, Question.LEVEL_MEDIUM, Category.MUSIC);
        addQuestion(QMM2);

        Question QMM3 = new Question("In which year did Yazz and the Plastic Population top the UK single chart with The Only Way Is Up?",
                "One Direction", "Westlife", "The wall", "Cyka Blyat",
                2, Question.LEVEL_MEDIUM, Category.MUSIC);
        addQuestion(QMM3);

        Question QMM4 = new Question("Rihanna's chart topping single 'We Found Love' featured which Scottish DJ?",
                "Calvin Harris", "Chemical Brothers", "Fat Boy Slim", "David",
                1, Question.LEVEL_MEDIUM, Category.MUSIC);
        addQuestion(QMM4);

        Question QMM5 = new Question("In 2001, Heidi Range replaced Siobhán Donaghy as a member in which British girl group?",
                "Sorry", "Old Town Road", "I love You", "Sugababes",
                4, Question.LEVEL_MEDIUM, Category.MUSIC);
        addQuestion(QMM5);


        // MUSIC HARD

        Question QMH1 = new Question("Shape of You was a 2017 hit for which British artist?",
                "Arianna Grande", "Ed Sheeran", "Justin Bieber", "Mr Bean",
                2, Question.LEVEL_HARD, Category.MUSIC);
        addQuestion(QMH1);

        Question QMH2 = new Question("The most successful single of 2007 was 'Bleeding Love'. Which British singer recorded it?",
                "Stormzy", "Leona Lewis", "Sucker", "Volleyball",
                2, Question.LEVEL_HARD, Category.MUSIC);
        addQuestion(QMH2);

        Question QMH3 = new Question("David Bowie retired which stage persona in 1973?",
                "One Direction", "Westlife", "Ziggy Stardust", "Cyka Blyat",
                3, Question.LEVEL_HARD, Category.MUSIC);
        addQuestion(QMH3);

        Question QMH4 = new Question("The biggest selling album of 2007 was 'Back to Black', recorded by which British female soul singer?",
                "Calvin Harris", "Amy Winehouse", "Taylor Swift", "David",
                2, Question.LEVEL_HARD, Category.MUSIC);
        addQuestion(QMH4);

        Question QMH5 = new Question("American singer Stefani Joanne Angelina Germanotta is best known by which stagename?",
                "Sorry", "Old Town Road", "I love You", "Lady Gaga",
                4, Question.LEVEL_HARD, Category.MUSIC);
        addQuestion(QMH5);


        // TECHNOLOGY EASY

        Question QTE1 = new Question("How many bits make a byte?",
                "8 bytes", "16 bytes", "9 bytes", "10 bytes",
                1, Question.LEVEL_EASY, Category.TECHNOLOGY);
        addQuestion(QTE1);

        Question QTE2 = new Question("Which of the following is hardware? ",
                "Monitor", "Program", "Operating System", "Volleyball",
                1, Question.LEVEL_EASY, Category.TECHNOLOGY);
        addQuestion(QTE2);

        Question QTE3 = new Question("Which company will be assembling iPhones in India in 2019 on behalf of Apple?",
                "One Direction", "Foxconn in Tamil Nadu", "Ziggy Stardust", "Cyka Blyat",
                2, Question.LEVEL_EASY, Category.TECHNOLOGY);
        addQuestion(QTE3);

        Question QTE4 = new Question("Which of the following is software?",
                "Calvin Harris", "Keyboard", "Microsoft Word", "David",
                3, Question.LEVEL_EASY, Category.TECHNOLOGY);
        addQuestion(QTE4);

        Question QTE5 = new Question("What do you call the “brain” of the computer?",
                "Hard Drive", "Central Processing unit", "Database", "System Software",
                2, Question.LEVEL_EASY, Category.TECHNOLOGY);
        addQuestion(QTE5);


        // TECHNOLOGY MEDIUM

        Question QTM1 = new Question("Who invented Java?    ",
                "Anderson Berlus", "James A Gosling", "Mr Bean", "David Buck",
                2, Question.LEVEL_MEDIUM, Category.TECHNOLOGY);
        addQuestion(QTM1);

        Question QTM2 = new Question("Which of the following is hardware? ",
                "Monitor", "Program", "Operating System", "Volleyball",
                1, Question.LEVEL_MEDIUM, Category.TECHNOLOGY);
        addQuestion(QTM2);

        Question QTM3 = new Question("Which company will be assembling iPhones in India in 2019 on behalf of Apple?",
                "One Direction", "Foxconn in Tamil Nadu", "Ziggy Stardust", "Cyka Blyat",
                2, Question.LEVEL_MEDIUM, Category.TECHNOLOGY);
        addQuestion(QTM3);

        Question QTM4 = new Question("Which of the following is software?",
                "Calvin Harris", "Keyboard", "Microsoft Word", "David",
                3, Question.LEVEL_MEDIUM, Category.TECHNOLOGY);
        addQuestion(QTM4);

        Question QTM5 = new Question("Longhorn was the code name of ?",
                "Hard Drive", "Central Processing unit", "Database", "System Software",
                2, Question.LEVEL_MEDIUM, Category.TECHNOLOGY);
        addQuestion(QTM5);











    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTIONA, question.getOptionA());
        cv.put(QuestionTable.COLUMN_OPTIONB, question.getOptionB());
        cv.put(QuestionTable.COLUMN_OPTIONC, question.getOptionC());
        cv.put(QuestionTable.COLUMN_OPTIOND, question.getOptionD());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_LEVEL, question.getLevel());


        cv.put(QuestionTable.COLUMN_CATE_ID, question.getCategoryID());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CategoryTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(CategoryTable._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(CategoryTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
    }

/*    public List<Question> getAllQuestion() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
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
    }*/

    public List<Question> getQuestions(int categoryID, String level) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATE_ID + " =  ? " +
                " AND " + QuestionTable.COLUMN_LEVEL + " = ? ";

        String[] selectArgs = new String[]{String.valueOf(categoryID), level};

        Cursor cursor = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectArgs,
                null, null, null
        );

        if (cursor.moveToFirst()) {
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

























