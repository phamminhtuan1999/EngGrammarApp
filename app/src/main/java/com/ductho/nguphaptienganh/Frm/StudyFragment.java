package com.ductho.nguphaptienganh.Frm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ductho.nguphaptienganh.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.databinding.FragmentStudyBinding;


public class StudyFragment extends Fragment {
    FragmentStudyBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStudyBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View.OnClickListener onLayoutClickListener = v -> {
            Fragment fragment = null;
            switch (v.getId()){
                case R.id.llCau:
                    fragment = new FrmCau();
                    break;
                case R.id.llTu:
                    fragment = new FrmTu();
                    break;
                case R.id.llBaihoc:
                    fragment = new FrmLession();
                    break;
                case R.id.llOntap:
                    fragment = new FrmReview();
                    break;

            }
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.llMain,fragment)
                    .addToBackStack(null).commit();
            MainActivity.mBubbleNavigationLinearView.setVisibility(View.GONE);
        };
        mBinding.llTu.setOnClickListener(onLayoutClickListener);
        mBinding.llCau.setOnClickListener(onLayoutClickListener);
        mBinding.llBaihoc.setOnClickListener(onLayoutClickListener);
        mBinding.llOntap.setOnClickListener(onLayoutClickListener);
    }


//    @Override
//    public void onDetach() {
//        super.onDetach();
//        MainActivity.mBubbleNavigationLinearView.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        MainActivity.mBubbleNavigationLinearView.setVisibility(View.VISIBLE);
//    }
}