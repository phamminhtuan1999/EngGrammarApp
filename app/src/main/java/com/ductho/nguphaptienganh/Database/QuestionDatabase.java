package com.ductho.nguphaptienganh.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ductho.nguphaptienganh.Model.Result;

@Database(entities = {Result.class}, version = 1,exportSchema = false)
public abstract class QuestionDatabase extends RoomDatabase {
    private static QuestionDatabase mInstance;
    public abstract QuestionDao getQuestionDao();

    public static QuestionDatabase getInstance(Context context){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionDatabase.class,"question_database").build();
        }
        return mInstance;
    }
}