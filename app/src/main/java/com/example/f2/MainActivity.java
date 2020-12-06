package com.example.f2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.zip.ZipEntry;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;

public class MainActivity extends AppCompatActivity {
 //fragmentManager 가 할수있는일
 // findfragmentbyid() 로 fragment 가져오기
 // popbackStack() 을 사용하여 fragment 를 백스택에서 꺼내기
 // beginTransaction()을 사용하여 FragmentTransaction 가져오기
    private FragmentManager fragmentManager = getSupportFragmentManager();
private FragmentGuestBook fragmentGuestBook = new FragmentGuestBook();
private FragmentMy fragmentMy = new FragmentMy();
private FragmentHome fragmentHome = new FragmentHome();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction Transaction = fragmentManager.beginTransaction();
        Transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.my:transaction.replace(R.id.frameLayout, fragmentMy).commitAllowingStateLoss();
                    break;

                    case R.id.guestbook:transaction.replace(R.id.frameLayout, fragmentGuestBook).commitAllowingStateLoss();
                    break;

                    case R.id.home:transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                }
                return true;
            }
        });
    }

    }