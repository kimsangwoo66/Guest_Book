package com.example.f2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest
{

    final static private String URL = "https://mrk211.cafe24.com/UserLogin.php";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) //로그인 요청을 하기위해 DB서버와 연결을 시켜주는 메소드
    {
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);



    }
    @Override
    public Map<String, String> getParams()
    {
        return parameters;
    }
}
