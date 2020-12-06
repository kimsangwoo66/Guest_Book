package com.example.f2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticedList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home,container,false);

        noticeListView = (ListView) view.findViewById(R.id.noticeListView);
        noticedList = new ArrayList<Notice>();
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-09"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-10"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-11"));


        adapter = new NoticeListAdapter(getActivity(), noticedList);
        noticeListView.setAdapter(adapter);

        return view;

    }


}
