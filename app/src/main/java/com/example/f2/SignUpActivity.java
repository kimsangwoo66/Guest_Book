package com.example.f2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {


        private ArrayAdapter adapter;
        private String userID;
        private String userPassword;
        private String userAddress;
        private String userName;
        private  String userPhone;
        private AlertDialog dialog;
        private boolean validate = false;  // 사용할수 있는 아이디인지 체크 하는 변수

    @Override

        protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);

        final EditText editid = (EditText) findViewById(R.id.editid1);
        final EditText editpassword = (EditText) findViewById(R.id.editpassword);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText nickname = (EditText) findViewById(R.id.nickname);
        final EditText phone = (EditText) findViewById(R.id.phone);

        final Button checkid = (Button) findViewById(R.id.checkid);
        checkid.setOnClickListener(new View.OnClickListener() { // 아이디 중복체크 이벤트 수행
            @Override
            public void onClick(View view) {
                String userID = editid.getText().toString();
                if(validate)
                {
                    return;
                }
                if(userID.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    dialog = builder.setMessage("아이디를 입력하셔야 합니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                editid.setEnabled(false);
                                validate = true;

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다. ")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();


                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener); //아이디 중복을 확인할 수있는 클래스 객체를 생성
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this); //volly를 통해 Json data를 받을 수있는 큐 생성
                queue.add(validateRequest);
            }
        });

        Button login_singup1 = (Button) findViewById(R.id.login_singup1);
        login_singup1.setOnClickListener(new View.OnClickListener() { //가입하기 이벤트 수행
            @Override
            public void onClick(View view) {
                String userID = editid.getText().toString();
                String userPassword = editpassword.getText().toString();
                String userAddress = address.getText().toString();
                String userName = nickname.getText().toString();
                String userPhone = phone.getText().toString();

                if(!validate)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    dialog = builder.setMessage("중복체크를 해야합니다. ")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                if(userID.equals("") || userPassword.equals("") || userAddress.equals("") || userName.equals("") || userPhone.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    dialog = builder.setMessage("빈칸 없이 입력해 주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //서버 DB를 불러오는 메소드
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("회원 가입에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                finish();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("회원 가입에 실패했습니다.  ")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                                editid.setEnabled(false);
                                validate = true;

                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                SignUpRequest registerRequest = new SignUpRequest(userID, userPassword,userAddress,userName,userPhone, responseListener);//아이디를 db에 등록할 수있는 클래스 객체를 생성
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this); //volly를 통해 Json data를 받을 수있는 큐 생성
                queue.add(registerRequest);

            }
        });
    }

        @Override
        protected  void onStop() // 메시지 종료 메소드
        {
            super.onStop();
            if(dialog !=null)
            {
                dialog.dismiss();
                dialog= null;
            }
        }

    }

