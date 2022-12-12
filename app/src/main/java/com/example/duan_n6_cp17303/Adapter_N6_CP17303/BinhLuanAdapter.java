package com.example.duan_n6_cp17303.Adapter_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.QLBLActivyty;
import com.example.duan_n6_cp17303.R;

import java.util.List;

public class BinhLuanAdapter extends BaseAdapter {
    List<BinhLuanDTO> list;

    Context context;

    public BinhLuanAdapter(List<BinhLuanDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        BinhLuanDTO binhLuanDTO = list.get(position);

        return binhLuanDTO;
    }

    @Override
    public long getItemId(int position) {
        BinhLuanDTO binhLuanDTO = list.get(position);

        return binhLuanDTO.getIdbinhluan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_binhluan, null);
        } else {
            view = convertView;
        }

        TextView tv_binhluan = view.findViewById(R.id.item_binhluan_tv_binhluan);
        TextView tv_ten = view.findViewById(R.id.item_binhluan_tv_name);

        BinhLuanDTO binhLuanDTO = list.get(position);


        tv_binhluan.setText(binhLuanDTO.getBinhluan());
        tv_ten.setText(binhLuanDTO.getTenkhachhang());
//        BinhLuanDTO sanPhamDTO =list.get(position);
//        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Mypref",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("key_SP",sanPhamDTO.getIdsanpham());
//        editor.commit();
        // Glide.with(view.getContext()).load(Uri.parse(sanPhamDTO.getAnhsanpham())).into(imgItemsp);
        return view;
    }
}
