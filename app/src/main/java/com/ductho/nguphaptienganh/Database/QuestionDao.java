package com.ductho.nguphaptienganh.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ductho.nguphaptienganh.Model.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public interface QuestionDao {
    @Insert
    Maybe<Long> insert(Result result);

    @Update
    Maybe<Integer> update(Result result);

    @Delete
    Maybe<Void> delete(Result result);

    @Query("SELECT * FROM question_table ORDER BY question")
    Maybe<List<Result>> getAllQuestions();
}
