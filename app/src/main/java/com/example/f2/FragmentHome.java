package com.example.f2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private ListView noticeListView; //리스트뷰에 넣을 변수
    private NoticeListAdapter adapter;//리스트뷰 형식을 적용시킬 어뎁터 변수
    private List<Notice> noticedList;// 리스트 변수
    private Button qrlistener_btn, makebook_btn; // qr코드 버튼 변수, 방명록 생성 버튼 변수
    Intent intent;// 정보 전달할 변수

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home,container,false);//방명록 홈화면 지정

        qrlistener_btn = view.findViewById(R.id.qrlistenerbtn);//xml의 qrlistenerbtn과 매칭
        makebook_btn = view.findViewById(R.id.makebookbtn);//xml의 makebookbtn과 매칭
        noticeListView = (ListView) view.findViewById(R.id.noticeListView);//xml의noticeListView와 매칭
        noticedList = new ArrayList<Notice>();
        noticedList.add(new Notice("알림", "졸업작품 전시회 방명록에 등록되었습니다.", "pm:18:47"));
        noticedList.add(new Notice("알림", "동아리 회비 방명록 기간이 5일남았습니다.", "pm:19:35"));
        noticedList.add(new Notice("알림", "컴공 학술제 방명록이 삭제되었습니다.   ", "pm:21:21"));


        adapter = new NoticeListAdapter(getActivity(), noticedList);// 리스트에 추가한 리스트값들을 적용
        noticeListView.setAdapter(adapter);//어텝터를 적용

        qrlistener_btn.setOnClickListener(new View.OnClickListener() { //QR코드 리스너 클릭 버튼 클릭 기능
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity()); //바코드 스캔을 위한 객체
                intentIntegrator.setPrompt("QR코드를 스캔해주세요!");//바코드 스캔버튼 안내 문구
                intentIntegrator.setBeepEnabled(true);//스캔시 삐 소리 작동
                intentIntegrator.setOrientationLocked(true);//가로 세로 모드 자동변환 잠금
                intentIntegrator.setCaptureActivity(Capture.class);//캡처 클래스 동작
                intentIntegrator.initiateScan();//스캐너 프로세스 시작
            }
        });

        makebook_btn.setOnClickListener(new View.OnClickListener() {//방명록 생성 버튼 클릭 기능
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), Makeguestbook.class);// intent에 방명록생성 클래스를 불러오는 정보 저장
                startActivity(intent); //방명록생성 클래스를 불러옴
            }
        });

        return view;

    }


}
