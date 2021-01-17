package com.ductho.nguphaptienganh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.ductho.nguphaptienganh.Model.ApiResponse;
import com.ductho.nguphaptienganh.Model.Result;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.ViewModel.QuestionViewModel;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    public static final int duration = 3000;
    SharedPreferences mSharedPreferences;
    QuestionViewModel mQuestionViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        splashScreenStart();

    }

    public void splashScreenStart() {
        fetchQuestionsFromAPI();
        mQuestionViewModel.getInsertedQuestion().observeForever(aLong -> {
            Log.d("ABC",aLong+"");
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPreferences.getBoolean("isFirstTime", true)) {
                    ;
                    startActivity(new Intent(SplashScreenActivity
                            .this, OnBoardActivity.class));

                }
                else if(mSharedPreferences.getString("name", null)!=null){
                    startActivity(new Intent(SplashScreenActivity
                            .this, MainActivity.class));
                }
                else{
                    startActivity(new Intent(SplashScreenActivity
                            .this, LoginActivity.class));
                }
                finish();
            }
        }, duration);

    }

    private void fetchQuestionsFromAPI() {
        mQuestionViewModel.fetchQuestions(50);
        mQuestionViewModel.getQuestions().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                List<Result> results = apiResponse.getResults();
                //add meal
                for (Result result : results) {
                    mQuestionViewModel.insertQuestion(result);
                }
            }
        });
    }
}