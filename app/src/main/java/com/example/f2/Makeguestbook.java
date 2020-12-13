package com.example.f2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Makeguestbook extends AppCompatActivity {

    EditText qrvalueThema,qrvalueAttribute; //QR코드에 넣기위한 테마 edittext와 속성 edittext 변수
    Button generate_Btn; //qr코드 생성 버튼 변수
    ImageView qrImage; //QR코드의 이미지 변수

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_guestbook);//방명록 생성화면 지정

        qrvalueThema = findViewById(R.id.qr_input1);//xml의 qr_input1와 매칭
        qrvalueAttribute = findViewById(R.id.qr_input2);//xml의 qr_input2와 매칭
        generate_Btn= findViewById(R.id.generatebtn);//xml의 generatebtn와 매칭
        qrImage=findViewById(R.id.qrplaceHolder);//xml의 qrplaceHolder와 매칭

        generate_Btn.setOnClickListener(new View.OnClickListener() { //QR코드 생성 버튼 클릭 기능
            @Override
            public void onClick(View v) {

                String data1 = qrvalueThema.getText().toString();
                String data2=qrvalueAttribute.getText().toString();
                String totaldata=data1+"/"+data2; //모든 입력값들응 합쳐 QR코드 인코더에 넣는다.
                QRGEncoder qrgEncoder = new QRGEncoder(totaldata,null, QRGContents.Type.TEXT,100);//QR 인코더 객체 생성
                Bitmap qrBits = qrgEncoder.getBitmap();// 인코더 정보가 담긴 QR코드 비트 정보 객체 생성
                qrImage.setImageBitmap(qrBits);//이미지 view에 QR코드 세팅
                Toast.makeText(getApplicationContext(), "방명록 QR코드 생성이 완료되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
