package com.example.f2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {


        final static private String URL = "https://mrk211.cafe24.com/UserRegister.php";
        private Map<String, String> parameters;

    public SignUpRequest(String userID, String userPassword, String userAddress, String userName, String userPhone, Response.Listener<String> listener)
        {                                                               //회원가입 정보를 입력해 서버 DB에 보내기 위한 메소드
            super(Method.POST, URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("userID", userID);
            parameters.put("userPassword", userPassword);
            parameters.put("userAddress", userAddress);
            parameters.put("userName", userName);
            parameters.put("userPhone", userPhone);


        }
        @Override
        public Map<String, String> getParams()
        {
            return parameters;
        }

}
