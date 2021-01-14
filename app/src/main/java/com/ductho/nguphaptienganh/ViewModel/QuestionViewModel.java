package com.ductho.nguphaptienganh.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ductho.nguphaptienganh.Model.ApiResponse;
import com.ductho.nguphaptienganh.Model.Result;
import com.ductho.nguphaptienganh.Repository.QuestionRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuestionViewModel extends AndroidViewModel {


    MutableLiveData<ApiResponse> mQuestions;
    MutableLiveData<Long> mInsertedQuestions;
    MutableLiveData<List<Result>> mLocalQuestions;
    MutableLiveData<List<Result>> mSelectedLocalQuestions;
    QuestionRepository mQuestionRepository;

    public QuestionViewModel(@NonNull Application application) {
        super(application);

        mQuestionRepository = QuestionRepository.getInstance(application);
        mQuestions = new MutableLiveData<>();
        mLocalQuestions = new MutableLiveData<>();
        mSelectedLocalQuestions = new MutableLiveData<>();
        mInsertedQuestions = new MutableLiveData<>();

    }

    public void insertQuestion(Result result) {
        mQuestionRepository.insertQuestion(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                        mInsertedQuestions.setValue(aLong);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Long> getInsertedQuestion() {
        return mInsertedQuestions;
    }

    public void fetchSelectedLocalQuestions(String category,String difficulty) {
        mQuestionRepository.fetchSelectedLocalQuestions(category, difficulty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Result>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Result> results) {
                        mSelectedLocalQuestions.setValue(results);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Result>> getSelectedLocalQuestions() {
        return mSelectedLocalQuestions;
    }

    public void fetchLocalQuestions() {
        mQuestionRepository.fetchLocalQuestions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Result>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Result> results) {
                        mLocalQuestions.setValue(results);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Result>> getLocalQuestions() {
        return mLocalQuestions;
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

    public LiveData<ApiResponse> getQuestions() {
        return mQuestions;
    }


}
