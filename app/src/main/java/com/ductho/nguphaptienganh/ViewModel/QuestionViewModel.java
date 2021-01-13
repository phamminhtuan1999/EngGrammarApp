package com.ductho.nguphaptienganh.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ductho.nguphaptienganh.Model.ApiResponse;
import com.ductho.nguphaptienganh.Repository.QuestionRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuestionViewModel extends AndroidViewModel {


    MutableLiveData<ApiResponse> mQuestions;
    QuestionRepository mQuestionRepository;

    public QuestionViewModel(@NonNull Application application) {
        super(application);

        mQuestionRepository = QuestionRepository.getInstance(application);
        mQuestions = new MutableLiveData<>();

    }

    public void fetchQuestions(int i) {
        mQuestionRepository.fetchQuestions(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<ApiResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ApiResponse apiResponse) {
                        mQuestions.setValue(apiResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<ApiResponse> getQuestions(){
        return mQuestions;
    }
}
