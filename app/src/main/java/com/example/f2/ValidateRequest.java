package com.example.f2;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {


        final static private String URL = "https://mrk211.cafe24.com/UserValidate.php";
        private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener) //아이디가 중복되는지 확인하기위해 DB 서버에 연결하는 메소드
        {
            super(Request.Method.POST, URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("userID", userID);



        }
        @Override
        public Map<String, String> getParams()
        {
            return parameters;
        }


}
