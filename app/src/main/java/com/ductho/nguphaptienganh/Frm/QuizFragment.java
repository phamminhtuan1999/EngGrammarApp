package com.ductho.nguphaptienganh.Frm;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ductho.nguphaptienganh.Model.Result;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.ViewModel.QuestionViewModel;
import com.ductho.nguphaptienganh.databinding.FragmentQuizBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class QuizFragment extends Fragment {
    private CountDownTimer mCountDownTimer;
    private long timeLeftInMillis;
    List<String> options;

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
//    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    FragmentQuizBinding mBinding;
    QuestionViewModel mQuestionViewModel;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private List<Result> questionList;
    private int questionCountTotal;
    private int questionCounter;
    private Result currentQuestion;
    private boolean answered;
    private int score;

    private long backPressedTime;
    RadioButton radioButton4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentQuizBinding.inflate(inflater, container, false);
        radioButton4 = mBinding.getRoot().findViewById(R.id.radio_button4);
        return mBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textColorDefaultRb = mBinding.radioButton1.getTextColors();
        textColorDefaultCd = mBinding.textViewCountdown.getTextColors();
        options = new ArrayList<>();

//        Log.d("ABC","ID button 4"+radioButton4.getId());



        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        mQuestionViewModel.fetchLocalQuestions();
        mQuestionViewModel.getLocalQuestions().observe(getActivity(), results -> {
            questionList = results;
            if (savedInstanceState == null) {
                questionCountTotal = questionList.size();
                Collections.shuffle(questionList);
                showNextQuestion();
            } else {
                questionCountTotal = questionList.size();
                questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
                currentQuestion = questionList.get(questionCounter - 1);
                score = savedInstanceState.getInt(KEY_SCORE);
                timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
                answered = savedInstanceState.getBoolean(KEY_ANSWERED);

                if (!answered) {
                    startCountDown();
                } else {
                    updateCountdownText();
                    showSolution();
                }
            }
        });
    }

    private void checkAnswer() {
        answered = true;
//        RadioButton selectedRb = mBinding.radioGroup.getCheckedRadioButtonId();
        int selectedRbInt = mBinding.radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRb = (RadioButton) mBinding.radioGroup.getChildAt(selectedRbInt);
        String selectedAnswer = selectedRb.getText().toString();
//        int answerNr = selectedRbInt + 1;
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
            mBinding.textViewScore.setText(
//                    "Score: " +
                    String.valueOf(score));
        }

        showSolution();
    }

    private void showNextQuestion() {
        mBinding.radioButton1.setTextColor(textColorDefaultRb);
        mBinding.radioButton2.setTextColor(textColorDefaultRb);
        mBinding.radioButton3.setTextColor(textColorDefaultRb);
//        mBinding.radioButton4.setTextColor(textColorDefaultRb);
//        radioButton4.setTextColor(textColorDefaultRb);
        mBinding.radioGroup.clearCheck();
        options.clear();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            options.add(currentQuestion.getCorrectAnswer());
            options.addAll(currentQuestion.getIncorrectAnswers());
            Collections.shuffle(options);

            mBinding.textViewQuestion.setText(currentQuestion.getQuestion());
            mBinding.radioButton1.setText(options.get(0));
            mBinding.radioButton2.setText(options.get(1));
            mBinding.radioButton3.setText(options.get(2));
//            mBinding.radioButton4.setText(options.get(3));
//            radioButton4.setText(options.get(3));

            questionCounter++;
            mBinding.textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            mBinding.buttonConfirmNext.setText("confirm");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void showSolution() {
        mBinding.radioButton1.setTextColor(Color.RED);
        mBinding.radioButton2.setTextColor(Color.RED);
        mBinding.radioButton3.setTextColor(Color.RED);
//        mBinding.radioButton4.setTextColor(Color.RED);
//        radioButton4.setTextColor(Color.RED);

        if (options != null) {
            if (currentQuestion.getCorrectAnswer().equals(options.get(0))) {
                mBinding.radioButton1.setTextColor(Color.GREEN);
                mBinding.textViewQuestion.setText("Answer 1 is correct");
            } else if (currentQuestion.getCorrectAnswer().equals(options.get(1))) {
                mBinding.radioButton2.setTextColor(Color.GREEN);
                mBinding.textViewQuestion.setText("Answer 2 is correct");
            } else if (currentQuestion.getCorrectAnswer().equals(options.get(2))) {
                mBinding.radioButton3.setTextColor(Color.GREEN);
                mBinding.textViewQuestion.setText("Answer 3 is correct");
            } else {
//                mBinding.radioButton4.setTextColor(Color.GREEN);
//                radioButton4.setTextColor(Color.GREEN);
                mBinding.textViewQuestion.setText("Answer 4 is correct");
            }
        }

        if (questionCounter < questionCountTotal) {
            mBinding.buttonConfirmNext.setText("next");
        } else {
            mBinding.buttonConfirmNext.setText("finish");
        }

    }

    private void updateCountdownText() {
        int minutes = (int) timeLeftInMillis / 1000 / 60;
        int seconds = (int) timeLeftInMillis / 1000 % 60;
        int secondLeft = 0;
        if (Math.round((float) seconds) != secondLeft) {
            secondLeft = Math.round((float) seconds);
            String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mBinding.textViewCountdown.setText(timeFormatted);
            Log.d("Time left", String.valueOf(timeLeftInMillis));
        }
        if (timeLeftInMillis < 10000) {
            mBinding.textViewCountdown.setTextColor(Color.RED);
        } else {
            mBinding.textViewCountdown.setTextColor(textColorDefaultCd);
        }
    }

    private void startCountDown() {
        mCountDownTimer = new CountDownTimer(timeLeftInMillis, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountdownText();
                mBinding.textViewCountdown.setText("00:00");
                checkAnswer();
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
    }
}