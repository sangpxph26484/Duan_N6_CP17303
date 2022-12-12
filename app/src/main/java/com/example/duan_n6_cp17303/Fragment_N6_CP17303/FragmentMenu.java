package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.CuaHangDAO;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.HoaDonDAO;
import com.example.duan_n6_cp17303.DangNhapActivity;
import com.example.duan_n6_cp17303.MainActivity;
import com.example.duan_n6_cp17303.PhieuGiamGiaActivity;
import com.example.duan_n6_cp17303.R;
import com.example.duan_n6_cp17303.ThongkeActivity;

import java.text.DecimalFormat;


public class FragmentMenu extends Fragment {
    TextView tv_xemchitiet, tv_doanhthu, tv_soluongdonhang,tv_soluongdagiao,tv_soluongchuagiao,tv_tenshop;
    ImageView img_info,img_dangxuat;
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
        img_info = view.findViewById(R.id.trangchu_img_quanlybl);
        tv_soluongdagiao = view.findViewById(R.id.trangchu_tv_dagiao);
        tv_soluongchuagiao = view.findViewById(R.id.trangchu_tv_chuagiao);
        img_dangxuat = view.findViewById(R.id.img_dangxuat);
        tv_tenshop = view.findViewById(R.id.tv_tenshop);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        CuaHangDAO cuaHangDAO = new CuaHangDAO();

        dao = new HoaDonDAO();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");

        long a = dao.getDT(user);
        int b = dao.getSLDHMenu(user);
        int c = dao.getSLDHcg(user);
        int d = dao.getSLDHdg(user);
        String tenshop = cuaHangDAO.getTenshop(user);


        tv_doanhthu.setText(decimalFormat.format(a) + "đ");
        tv_soluongdonhang.setText(b+"");
        tv_soluongchuagiao.setText(c+"");
        tv_soluongdagiao.setText(d+"");
        tv_tenshop.setText(tenshop+"!");

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
        img_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangxuat();
                Intent intent = new Intent(getContext(), DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
    void dangxuat() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")) {
            Log.d("TAG", "dang nhap");
            Intent intent = new Intent(getContext(), DangNhapActivity.class);
            startActivity(intent);
        } else {
            Log.d("TAG", "dang xuat");
            Toast.makeText(getContext(), "Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}