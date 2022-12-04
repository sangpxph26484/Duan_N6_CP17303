package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan_n6_cp17303.Adapter_N6_CP17303.QLKHAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.HoaDonDAO;
import com.example.duan_n6_cp17303.R;


public class FragmentAll extends Fragment {

    ListView lv;
    HoaDonDAO dao;
    QLKHAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        lv = view.findViewById(R.id.lv_qlkh);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loaddata();
    }

    public void loaddata() {
        dao = new HoaDonDAO();


        adapter = new QLKHAdapter(dao.getDonHang(), getContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}