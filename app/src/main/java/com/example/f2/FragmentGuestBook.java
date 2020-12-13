package com.example.f2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentGuestBook extends Fragment {//Fragment 에서 상속
    @Nullable
    @Override
    //onCreateView 는 Fragment가 자신의 UI를 그릴때 호출
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guestbook,container,false);// 방명록 관리화면 지정
    }
}
