package com.ductho.nguphaptienganh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebView;

import com.ductho.nguphaptienganh.Ads.AdBanner;
import com.ductho.nguphaptienganh.Load.LoadNoiDung;
import com.ductho.nguphaptienganh.R;
import com.google.android.gms.ads.AdView;

public class NoiDungActivity extends AppCompatActivity {

    String id, title;
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pdf);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
//        getSupportActionBar().setTitle(title);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + title + "</font>")));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        wv = findViewById(R.id.wv);
        new LoadNoiDung(id, wv);
//        AdView adView = findViewById(R.id.ad_nd);
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
