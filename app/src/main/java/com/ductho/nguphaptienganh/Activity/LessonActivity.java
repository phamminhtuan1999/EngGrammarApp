package com.ductho.nguphaptienganh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.MenuItem;


import com.ductho.nguphaptienganh.Adapter.CustomAdapter;
import com.ductho.nguphaptienganh.Load.LoadBaiHoc;
import com.ductho.nguphaptienganh.Model.BaiHoc;
import com.ductho.nguphaptienganh.R;

import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<BaiHoc> arrayList;
    CustomAdapter adapter;
    String id, title;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
////       getSupportActionBar().setTitle(title);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + title + "</font>")));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        if (AppCompatDelegate.getDefaultNightMode() ==
                AppCompatDelegate.MODE_NIGHT_YES) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(R.color.dm_background)));
        }

        rv = findViewById(R.id.rv_lession);
        arrayList = new ArrayList<>();
        new LoadBaiHoc(id, arrayList);
        adapter = new CustomAdapter(arrayList, this, 1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
//        AdView adView = findViewById(R.id.ad_lesson);
//        new AdBanner(adView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
