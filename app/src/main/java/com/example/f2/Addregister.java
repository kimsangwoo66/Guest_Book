package com.example.f2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Addregister extends AppCompatActivity {
     Intent intent;  // 정보전달을 위한 변수
     TextView attribute_text; // 속성 text에 값을 넣기 위한 변수
     EditText thema_edit; // 테마 edittext에 값을 넣기 위한 변수

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_register); //추가속성 등록화면을 지정
        attribute_text=findViewById(R.id.addregister_attribute_text);//xml의 addregister_attribute_text와 매칭
        thema_edit=findViewById(R.id.addregister_edit_thema); // xml의 테마 addregister_edit_thema와 매칭

        intent=getIntent(); //getIntent()에 담긴값을 intent에 저장
        String thema= intent.getExtras().getString("테마");//테마key의 을 저장
        String attribute= intent.getExtras().getString("속성");//속성key의 값을 저장
        thema_edit.setText(thema);//테마 에디트텍스트에 값을 세팅
        attribute_text.setText(attribute);// 속성 텍스트에 값을 세팅





    }
}
