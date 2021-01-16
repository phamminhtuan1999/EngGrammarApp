package com.ductho.nguphaptienganh.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ductho.nguphaptienganh.Adapter.PagerAdapter;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.RateApp;
import com.ductho.nguphaptienganh.ShareApp;
import com.ductho.nguphaptienganh.ViewModel.QuestionViewModel;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static Switch switchDarkMode;
    ViewPager pager;
    //    TabLayout tabLayout;
    public static BubbleNavigationLinearView mBubbleNavigationLinearView;
    DrawerLayout drawer;
    NavigationView navigationView;
    LinearLayout menuCau, menuTu, menuChiaSe, menuDanhGia;
    //    AdView adView;
    TextView tvUsername;
    Button mBtnSignOut;
    String name;
    public static Toolbar mToolbar;
    QuestionViewModel mQuestionViewModel;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        mQuestionViewModel.fetchLocalQuestions();
        mQuestionViewModel.getLocalQuestions().observe(this,results -> {
            Log.d("ABC",results.toString());
        });

        navigationView = findViewById(R.id.nav_view);
        switchDarkMode = navigationView.findViewById(R.id.switch_dark_mode);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (AppCompatDelegate.getDefaultNightMode() ==
                AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkMode);
            mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.dm_background));
            switchDarkMode.setChecked(true);
        } else setTheme(R.style.LightMode);




//        Common.interstitialAd = new AdFull(getApplicationContext()).getInterstitialAd();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        tvUsername = navigationView.findViewById(R.id.tv_username);
//        Intent intent = getIntent();
//        name = intent.getStringExtra("name");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        if(user!=null){
            tvUsername.setText("Xin chào " + user.getDisplayName());
            mEditor.putString("name",user.getDisplayName());
            mEditor.commit();
        }


        mBtnSignOut = navigationView.findViewById(R.id.btn_sign_out);
        mBtnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Đăng xuất")
                        .setMessage("Bạn chắc chắn muốn đăng xuất?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAuth.signOut();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Không", null).show();
            }
        });

        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchDarkMode.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate
                            .MODE_NIGHT_YES);
                    activate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate
                            .MODE_NIGHT_NO);
                    activate();
                }

            }
        });

        //navigationView.setNavigationItemSelectedListener(this);
        List<Fragment> fragList = new ArrayList<>();
        mBubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
        mBubbleNavigationLinearView.setTypeface(ResourcesCompat.getFont(this, R.font.utm_bryantlg));
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
                mBubbleNavigationLinearView.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                pager.setCurrentItem(position, true);
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

    private void activate() {
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Thoát ứng dụng")
//                    .setMessage("Bạn chắc chắn muốn thoát ứng dụng?")
//                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            System.exit(0);
//                        }
//                    }).setNegativeButton("Không", null).show();


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
