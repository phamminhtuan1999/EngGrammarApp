package com.ductho.nguphaptienganh.Repository;

import android.app.Application;

import com.ductho.nguphaptienganh.API.ApiRequest;
import com.ductho.nguphaptienganh.API.RetrofitInit;
import com.ductho.nguphaptienganh.Model.ApiResponse;

import io.reactivex.rxjava3.core.Maybe;

public class QuestionRepository {
    private ApiRequest mApiRequest;
    private static QuestionRepository mInstance;

    public QuestionRepository(Application application) {
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
}