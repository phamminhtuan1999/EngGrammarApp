package com.ductho.nguphaptienganh.Frm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ductho.nguphaptienganh.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.ViewModel.QuestionViewModel;
import com.ductho.nguphaptienganh.databinding.FragmentStartQuizBinding;

import static android.content.Context.MODE_PRIVATE;

public class StartQuizFragment extends Fragment {
    public final static int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "MyPref";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    int highscore;
    QuestionViewModel questionViewModel;
    FragmentStartQuizBinding mBinding;

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
        loadHighscore();
        mBinding.buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        MainActivity.mBubbleNavigationLinearView.setVisibility(View.GONE);
        MainActivity.mToolbar.setVisibility(View.GONE);
        NewQuizFragment newQuizFragment = new NewQuizFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.llMain,newQuizFragment)
        .addToBackStack(null)
        .commit();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        mBinding.textViewHighscore.setText("Highscore: " + highscore);
    }
}