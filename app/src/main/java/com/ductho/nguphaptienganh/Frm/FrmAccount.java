package com.ductho.nguphaptienganh.Frm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ductho.nguphaptienganh.Activity.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.databinding.FragmentFrmAccountBinding;


public class FrmAccount extends Fragment {
    FragmentFrmAccountBinding mBinding;
    OnClickListener mOnClickListener;
    public static final String KEY_TITLE = "title";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mOnClickListener = v -> {
            Bundle bundle = new Bundle();
            String title = "";
            switch (v.getId()){
                case R.id.rlName:
                    title = "Đổi tên";
                    break;
                case R.id.rlEmail:
                    title = "Đổi email";
                    break;
                case R.id.rlAvatar:
                    title = "Đổi avatar";
                    break;
                case R.id.rlPassword:
                    title = "Đổi password";
                    break;
            }
            bundle.putString(KEY_TITLE,title);
            FrmChangeInfo frmChangeInfo = new FrmChangeInfo();
            frmChangeInfo.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.llMain,frmChangeInfo)
                    .addToBackStack(null)
                    .commit();
        };


        mBinding.rlName.setOnClickListener(mOnClickListener);
        mBinding.rlEmail.setOnClickListener(mOnClickListener);
        mBinding.rlPassword.setOnClickListener(mOnClickListener);
        mBinding.rlAvatar.setOnClickListener(mOnClickListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.mBubbleNavigationLinearView.setVisibility(View.VISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFrmAccountBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }


}