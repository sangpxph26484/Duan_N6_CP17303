package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan_n6_cp17303.PhieuGiamGiaActivity;
import com.example.duan_n6_cp17303.R;
import com.example.duan_n6_cp17303.ThongkeActivity;


public class FragmentMenu extends Fragment {
    TextView tv_xemchitiet;
    public static FragmentMenu newInstance() {
        FragmentMenu fragment = new FragmentMenu();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tv_xemchitiet = view.findViewById(R.id.trangchu_tv_xemchitiet);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), ThongkeActivity.class));
            }
        });

        ImageView imageView = view.findViewById(R.id.trangchu_img_voucher);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhieuGiamGiaActivity.class);
                startActivity(intent);
            }
        });
    }
}