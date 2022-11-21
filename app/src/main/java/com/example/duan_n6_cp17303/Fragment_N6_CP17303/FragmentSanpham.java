package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.Apdater_N6_CP17303.SanPhamAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class FragmentSanpham extends Fragment {

    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    ListView lv;
    List<SanPhamDTO> list;
    public static FragmentSanpham newInstance() {
        FragmentSanpham fragment = new FragmentSanpham();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham, container, false);

        lv = view.findViewById(R.id.sanpham_lv_donHang);





        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.sanpham_fab_themsanpham);
        sanPhamDAO = new SanPhamDAO();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialogAdd(view.getContext());

            }
        });
        loaddata();

    }
    public  void loaddata(){

        list = sanPhamDAO.getAll();
        adapter = new SanPhamAdapter( sanPhamDAO.getAll(),getContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    public void showDialogAdd(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_sanpham);

        EditText tensp = dialog.findViewById(R.id.themsp_ed_tensp);
        EditText soluong = dialog.findViewById(R.id.themsp_ed_soluong);
        EditText thongtin = dialog.findViewById(R.id.themsp_ed_thongtin);
        EditText giatien = dialog.findViewById(R.id.themsp_ed_giatien);
        EditText img = dialog.findViewById(R.id.themsp_ed_anh);

        Button them = dialog.findViewById(R.id.themsp_btn_dangky);
        Button huy = dialog.findViewById(R.id.themsp_btn_huy);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tsp = tensp.getText().toString();

                String tt = thongtin.getText().toString();


                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setTensanpham(String.valueOf(tsp));
                sanPhamDTO.setGiatien(Float.parseFloat(giatien.getText().toString()));
                sanPhamDTO.setSoluong(Integer.parseInt(soluong.getText().toString()));
                sanPhamDTO.setAnhsanpham(img.getText().toString());
                sanPhamDTO.setThongtin(tt);

                sanPhamDAO.insertRow(sanPhamDTO);
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                loaddata();
                dialog.dismiss();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}