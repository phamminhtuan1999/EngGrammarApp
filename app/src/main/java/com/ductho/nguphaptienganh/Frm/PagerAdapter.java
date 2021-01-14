package com.ductho.nguphaptienganh.Frm;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
//    final static StudyFragment studyFragment = new StudyFragment();
    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
//                frag = new FrmCau();
                frag = new StudyFragment();
                break;
            case 1:
                frag = new StartQuizFragment();
                break;
//            case 2:
//                frag = new FrmLession();
//                break;
//            case 3:
//                frag = new FrmReview();
//                break;
            case 2:
                frag = new FrmNote();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Học bài";
                break;
            case 1:
                title = "Test";
                break;
//            case 2:
//                title = "Lession";
//                break;
//            case 3:
//                title = "Review";
//                break;
            case 2:
                title = "Note";
                break;
        }

        return title;
    }

}
