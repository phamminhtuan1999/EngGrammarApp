package com.ductho.nguphaptienganh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;

import com.ductho.nguphaptienganh.Adapter.CustomAdapter;
import com.ductho.nguphaptienganh.Ads.AdBanner;
import com.ductho.nguphaptienganh.Load.LoadBaiHoc;
import com.ductho.nguphaptienganh.Model.BaiHoc;
import com.ductho.nguphaptienganh.R;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<BaiHoc> arrayList;
    CustomAdapter adapter;
    String id, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

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

        if(id == android.R.id.home){
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
