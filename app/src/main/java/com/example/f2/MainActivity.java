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



public class MainActivity extends AppCompatActivity {
 //fragmentManager 가 할수있는일
 // findfragmentbyid() 로 fragment 가져오기
 // popbackStack() 을 사용하여 fragment 를 백스택에서 꺼내기
 // beginTransaction()을 사용하여 FragmentTransaction 가져오기
    Intent intent;
    private FragmentManager fragmentManager = getSupportFragmentManager(); //fragment들과 activity간에 서로 상호작용 할 수있게 연결
private FragmentGuestBook fragmentGuestBook = new FragmentGuestBook(); // 방명록관라fragment 객체생성
private FragmentMy fragmentMy = new FragmentMy(); //내정보fragment 객체생성
private FragmentHome fragmentHome = new FragmentHome();//홈fragment 객체생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//fragment들을 호출하는 메인화면 지정

        FragmentTransaction Transaction = fragmentManager.beginTransaction(); //fragment가 작업을 수행할수있게 하는 객체
        Transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();//첫 fragment를 홈화면으로 지정

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView); //하단탭뷰 선언
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override //하단탭 클릭 버튼 기능
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.my:transaction.replace(R.id.frameLayout, fragmentMy).commitAllowingStateLoss();//내정보fragment 호출 기능
                    break;

                    case R.id.guestbook:transaction.replace(R.id.frameLayout, fragmentGuestBook).commitAllowingStateLoss();
                    break;                                                                      //방명록관리fragment 호츌 기능

                    case R.id.home:transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;                                                                  //홈fragment 호출 기능
                }
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) //QR코드 스캔값 정보를 저장하는 메소드
    {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data


        );






        if (intentResult.getContents() != null) {
            String totaldata[]=intentResult.getContents().split("/");//저장한 정보 데이터를 /에 따라 배열로 나눔





            intent = new Intent(getApplication(), Addregister.class); //추가속성 등록 클래스를 불러올 정보를 저장
            intent.putExtra("테마",totaldata[0]); //값을 전달하기 위해 0번째 배열의 값 저장
            intent.putExtra("속성",totaldata[1]); //값을 전달하기 위해 1번째 배열의 값 저장
            startActivity(intent);



        } else {
            Toast.makeText(MainActivity.this, "스캔이 안됬습니다.", Toast.LENGTH_SHORT).show();//스캔을하지않았을경우 메시지 호출
        }
    }
    }