package com.ductho.nguphaptienganh.Frm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ductho.nguphaptienganh.Activity.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.databinding.FragmentFrmSettingsBinding;


public class FrmSettings extends Fragment {
    FragmentFrmSettingsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFrmSettingsBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.rlAccount.setOnClickListener(v -> {
            MainActivity.mBubbleNavigationLinearView.setVisibility(View.GONE);
            MainActivity.mToolbar.setVisibility(View.GONE);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.llMain,new FrmAccount())
                    .addToBackStack(null)
                    .commit();
        });
    }
}