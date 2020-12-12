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

    EditText qrvalueThema,qrvalueAttribute;
    Button generate_Btn;
    ImageView qrImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_guestbook);

        qrvalueThema = findViewById(R.id.qr_input1);
        qrvalueAttribute = findViewById(R.id.qr_input2);
        generate_Btn= findViewById(R.id.generatebtn);
        qrImage=findViewById(R.id.qrplaceHolder);

        generate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String data1 = qrvalueThema.getText().toString();
                String data2=qrvalueAttribute.getText().toString();
                String totaldata=data1+"/"+data2; //모든 입력값들응 합쳐 QR코드 인코더에 넣는다.
                QRGEncoder qrgEncoder = new QRGEncoder(totaldata,null, QRGContents.Type.TEXT,100);
                Bitmap qrBits = qrgEncoder.getBitmap();
                qrImage.setImageBitmap(qrBits);
                Toast.makeText(getApplicationContext(), "방명록 QR코드 생성이 완료되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
