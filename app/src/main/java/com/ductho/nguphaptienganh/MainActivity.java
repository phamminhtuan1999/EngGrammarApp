package com.ductho.nguphaptienganh;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.LinearLayout;

import com.ductho.nguphaptienganh.Ads.AdBanner;
import com.ductho.nguphaptienganh.Ads.AdFull;
import com.ductho.nguphaptienganh.Ads.Common;
import com.ductho.nguphaptienganh.Ads.CountAds;
import com.ductho.nguphaptienganh.Frm.PagerAdapter;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    ViewPager pager;
    TabLayout tabLayout;
    BubbleNavigationLinearView bubbleNavigationLinearView;
    DrawerLayout drawer;
    NavigationView navigationView;
    LinearLayout menuCau, menuTu, menuChiaSe, menuDanhGia;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Common.interstitialAd = new AdFull(getApplicationContext()).getInterstitialAd();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        List<Fragment> fragList = new ArrayList<>();
        bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
        bubbleNavigationLinearView.setTypeface(ResourcesCompat.getFont(this, R.font.utm_bryantlg));
        pager = (ViewPager) findViewById(R.id.view_pager);
//        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        pager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bubbleNavigationLinearView.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                pager.setCurrentItem(position,true);
            }
        });
//        tabLayout.setTabsFromPagerAdapter(adapter);

        menuCau = findViewById(R.id.menu_cau);
        menuTu = findViewById(R.id.menu_tu);
        menuChiaSe = findViewById(R.id.menu_chia_se);
        menuDanhGia = findViewById(R.id.menu_danh_gia);
        menuCau.setOnClickListener(this);
        menuTu.setOnClickListener(this);
        menuDanhGia.setOnClickListener(this);
        menuChiaSe.setOnClickListener(this);

//        adView = findViewById(R.id.ad_main);
//        new AdBanner(adView);
//        new CountAds(this).reset();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            System.exit(0);
                        }
                    }).setNegativeButton("No", null).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_cau: {
                drawer.closeDrawers();
                pager.setCurrentItem(0);
            }
            break;
            case R.id.menu_tu: {
                drawer.closeDrawers();
                pager.setCurrentItem(1);
            }
            break;
            case R.id.menu_chia_se: {
                new ShareApp(this);
                drawer.closeDrawers();
            }
            break;
            case R.id.menu_danh_gia: {
                new RateApp(this);
                drawer.closeDrawers();
            }
            break;
        }

    }
}
