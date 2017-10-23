package com.dabin.sanzhou;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragments.Loginfragment;
import fragments.SignFragment;

public class ZhuActivity extends AppCompatActivity {

    @Bind(R.id.main_frame)
    FrameLayout mainFrame;
    @Bind(R.id.main_login)
    Button mainLogin;
    @Bind(R.id.main_sign)
    Button mainSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        ButterKnife.bind(this);
        //默认为登录界面
        FragmentManager manager = getSupportFragmentManager();
        Loginfragment loginfragment = new Loginfragment();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,loginfragment);
        fragmentTransaction.commit();
    }

    @OnClick({R.id.main_login, R.id.main_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_login:  //登录
                FragmentManager manager = getSupportFragmentManager();
                Loginfragment loginfragment = new Loginfragment();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,loginfragment);
                fragmentTransaction.commit();
                break;
            case R.id.main_sign:  // 注册
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                SignFragment signFragment = new SignFragment();
                FragmentTransaction fragmentTransaction1 = supportFragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.main_frame,signFragment);
                fragmentTransaction1.commit();
                break;
        }
    }
}
