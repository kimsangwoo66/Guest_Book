package com.example.f2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
    Intent intent;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //String[] totaldata = data.getDataString().split("/");
        //String first=totaldata[0];
        //String second=totaldata[1];

        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data


        );






        if (intentResult.getContents() != null) {
            String totaldata[]=intentResult.getContents().split("/");
            //String first=totaldata[0];
            //String second=totaldata[1];




            intent = new Intent(getApplication(), Addregister.class);
            intent.putExtra("테마",totaldata[0]);
            intent.putExtra("속성",totaldata[1]);
            startActivity(intent);

            //결과가 null 값이 아니면
            //다이얼로그 시작
            /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("추가사항을 입력해주세요");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("등록완료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

             */

        } else {
            Toast.makeText(MainActivity.this, "스캔이 안됬습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    }