package com.ductho.nguphaptienganh.Repository;

import android.app.Application;

import com.ductho.nguphaptienganh.API.ApiRequest;
import com.ductho.nguphaptienganh.API.RetrofitInit;
import com.ductho.nguphaptienganh.Database.QuestionDao;
import com.ductho.nguphaptienganh.Database.QuestionDatabase;
import com.ductho.nguphaptienganh.Model.ApiResponse;
import com.ductho.nguphaptienganh.Model.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public class QuestionRepository {
    private ApiRequest mApiRequest;
    private static QuestionRepository mInstance;
    private QuestionDao mQuestionDao;

    public QuestionRepository(Application application) {
        mQuestionDao = QuestionDatabase.getInstance(application).getQuestionDao();
        mApiRequest = RetrofitInit.getInstance();
    }

    public static QuestionRepository getInstance(Application application){
        if(mInstance==null){
            mInstance = new QuestionRepository(application);
        }
        return mInstance;
    }

    public Maybe<ApiResponse> fetchQuestions(int i){
        return mApiRequest.fetchQuestions(i);
    }
    public Maybe<List<Result>> fetchLocalQuestions(){
        return mQuestionDao.getAllQuestions();
    }
    public Maybe<List<Result>> fetchSelectedLocalQuestions(String category,String difficulty){
        return mQuestionDao.getSelectedQuestions(category, difficulty);
    }

    public Maybe<Long> insertQuestion(Result result){
        return  mQuestionDao.insert(result);
    }

}