package com.ductho.nguphaptienganh.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ductho.nguphaptienganh.Model.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
@Dao
public interface QuestionDao {
    @Insert
    Maybe<Long> insert(Result result);

    @Update
    Maybe<Integer> update(Result result);

    @Delete
    Maybe<Void> delete(Result result);

    @Query("SELECT * FROM question_table ORDER BY question")
    Maybe<List<Result>> getAllQuestions();

    @Query("SELECT * FROM question_table WHERE category LIKE :category AND difficulty = :difficulty AND type = 'multiple' ORDER BY question LIMIT 5 ")
    Maybe<List<Result>> getSelectedQuestions(String category, String difficulty);


}
