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
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticedList;
    private Button qrlistenerbtn, makebookbtn;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home,container,false);

        qrlistenerbtn = view.findViewById(R.id.qrlistenerbtn);
        makebookbtn = view.findViewById(R.id.makebookbtn);
        noticeListView = (ListView) view.findViewById(R.id.noticeListView);
        noticedList = new ArrayList<Notice>();
        noticedList.add(new Notice("알림", "졸업작품 전시회 방명록에 등록되었습니다.", "pm:18:47"));
        noticedList.add(new Notice("알림", "동아리 회비 방명록 기간이 5일남았습니다.", "pm:19:35"));
        noticedList.add(new Notice("알림", "컴공 학술제 방명록이 삭제되었습니다.   ", "pm:21:21"));


        adapter = new NoticeListAdapter(getActivity(), noticedList);
        noticeListView.setAdapter(adapter);

        qrlistenerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
                intentIntegrator.setPrompt("QR코드를 스캔해주세요!");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

        makebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), Makeguestbook.class);
                startActivity(intent);
            }
        });

        return view;

    }
/*
이건 메인엑티비티에 써야된다.
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );

        if(intentResult.getContents()!=null)
        {
            //결과가 null 값이 아니면
            //다이얼로그 시작
           AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Result");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

        }
        else
        {
            Toast.makeText(getContext(), "스캔이 안됬습니다.",Toast.LENGTH_SHORT).show();
        }
*/

}
