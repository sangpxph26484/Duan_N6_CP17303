package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.HoaDonDAO;
import com.example.duan_n6_cp17303.PhieuGiamGiaActivity;
import com.example.duan_n6_cp17303.R;
import com.example.duan_n6_cp17303.ThongkeActivity;

import java.text.DecimalFormat;


public class FragmentMenu extends Fragment {
    TextView tv_xemchitiet, tv_doanhthu, tv_soluongdonhang,tv_soluongdagiao,tv_soluongchuagiao;
    ImageView img_info;
    HoaDonDAO dao;

    public static FragmentMenu newInstance() {
        FragmentMenu fragment = new FragmentMenu();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        tv_xemchitiet = view.findViewById(R.id.trangchu_tv_xemchitiet);
        tv_doanhthu = view.findViewById(R.id.trangchu_tv_doanhthu);
        tv_soluongdonhang = view.findViewById(R.id.trangchu_tv_donhangmoi);
        img_info = view.findViewById(R.id.trangchu_img_info);
        tv_soluongdagiao = view.findViewById(R.id.trangchu_tv_dagiao);
        tv_soluongchuagiao = view.findViewById(R.id.trangchu_tv_chuagiao);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        dao = new HoaDonDAO();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        long a = dao.getDT();
        int b = dao.getSLDH();
        int c = dao.getSLDHcg();
        int d = dao.getSLDHdg();


        tv_doanhthu.setText(decimalFormat.format(a) + "đ");
        tv_soluongdonhang.setText(b+"");
        tv_soluongchuagiao.setText(c+"");
        tv_soluongdagiao.setText(d+"");

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