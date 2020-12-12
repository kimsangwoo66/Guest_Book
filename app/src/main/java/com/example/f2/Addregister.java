package com.example.f2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Addregister extends AppCompatActivity {
     Intent intent;
     TextView attribute_text;
     EditText thema_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_register);
        attribute_text=findViewById(R.id.addregister_attribute_text);
        thema_edit=findViewById(R.id.addregister_edit_thema);

        intent=getIntent();
        String thema= intent.getExtras().getString("테마");
        String attribute= intent.getExtras().getString("속성");
        thema_edit.setText(thema);
        attribute_text.setText(attribute);





    }
}
