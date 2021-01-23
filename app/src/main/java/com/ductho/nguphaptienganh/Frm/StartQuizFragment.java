package com.ductho.nguphaptienganh.Frm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ductho.nguphaptienganh.Activity.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.ViewModel.QuestionViewModel;
import com.ductho.nguphaptienganh.databinding.FragmentStartQuizBinding;

import static android.content.Context.MODE_PRIVATE;

public class StartQuizFragment extends Fragment {
    public final static int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "MyPref";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    static SharedPreferences prefs;
    static int highscore;
    public static FragmentActivity mContext;
    QuestionViewModel questionViewModel;
    static FragmentStartQuizBinding mBinding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStartQuizBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        prefs = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        loadHighscore();
        mBinding.buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    public static void startQuiz() {
        MainActivity.mBubbleNavigationLinearView.setVisibility(View.GONE);
        MainActivity.mToolbar.setVisibility(View.GONE);
        NewQuizFragment newQuizFragment = new NewQuizFragment();
        mContext.getSupportFragmentManager().beginTransaction()
                .replace(R.id.llMain,newQuizFragment)
        .addToBackStack(null)
        .commit();
    }

    public static void loadHighscore() {
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        mBinding.textViewHighscore.setText("Highscore: " + highscore);
    }
}