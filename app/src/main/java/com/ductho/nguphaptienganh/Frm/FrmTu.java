package com.ductho.nguphaptienganh.Frm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.ductho.nguphaptienganh.Adapter.CustomAdapter;
import com.ductho.nguphaptienganh.DanhMucTu;
import com.ductho.nguphaptienganh.MainActivity;
import com.ductho.nguphaptienganh.Model.BaiHoc;
import com.ductho.nguphaptienganh.R;

import java.util.ArrayList;

public class FrmTu extends Fragment {
    ArrayList<BaiHoc> arrayList, arrayListCopy;
    CustomAdapter adapter;

    public FrmTu() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.mBubbleNavigationLinearView.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_cau, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_cau);
        setHasOptionsMenu(true);
        arrayList = new ArrayList();
        arrayListCopy = new ArrayList<>();
        new DanhMucTu(arrayList);
        arrayListCopy.addAll(arrayList);
        adapter = new CustomAdapter(arrayList, getContext(), 0);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        return view;
    }


    // set chiều cao của list view cho cân đối với giao diện
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayList.clear();
                if (newText.equals("")) {
                    arrayList.addAll(arrayListCopy);
                } else {
                    for (BaiHoc baiHoc : arrayListCopy) {
                        if (baiHoc.getTitle().toLowerCase().contains(newText)) {
                            arrayList.add(baiHoc);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
