package com.example.quizgame.Database;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){

    }

    public static class CategoryTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_cate";
        public static final String COLUMN_NAME = "name";
    }

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";

        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTIONA = "option_a";
        public static final String COLUMN_OPTIONB = "option_b";
        public static final String COLUMN_OPTIONC = "option_c";
        public static final String COLUMN_OPTIOND = "option_d";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_LEVEL = "level";

        public static final String COLUMN_CATE_ID = "cate_id";
    }
}
