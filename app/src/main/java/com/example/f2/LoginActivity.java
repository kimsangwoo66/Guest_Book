package com.example.f2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    int version = 1;

    private AlertDialog dialog;

    EditText editpassword;
    Button login_signup_btn,login_find_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);//로그인 페이지 화면 지정

        final  EditText editid= (EditText) findViewById(R.id.editid);
        final EditText editpassword= (EditText) findViewById(R.id.editpassword);

        final Button login_btn=findViewById(R.id.login_btn);

        login_signup_btn=findViewById(R.id.login_singup); //회원가입 화면 이동 버튼 지정

        login_find_btn=findViewById(R.id.login_find); // 아직안함






        login_btn.setOnClickListener(new View.OnClickListener()  { // 로그인 버튼 이벤트
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);//intent에 mainactivity 클래스를 불러오는 정보 저장
                LoginActivity.this.startActivity(intent);// mainactivity를 불러옴
                finish();

                /*final  String userID = editid.getText().toString();
                String userPassword = editpassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인에 성공했습니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                //intent.putExtra("userID",userID);

                                LoginActivity.this.startActivity(intent);
                                finish();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("아이디를 잘못 입력하셨습니다.")
                                        .setNegativeButton("다시시도",null)
                                        .create();
                                dialog.show();
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener); //로그인 요청 값을 받을수 있는 클래스 객체 생성
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

                 */
            }

        });


        login_signup_btn.setOnClickListener(new View.OnClickListener(){ // 회원가입 화면이동 이벤트
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(LoginActivity.this, "회원가입 화면으로 이동", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected  void onStop() //메시지 종료 메소드
    {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;


        }
    }

    }
