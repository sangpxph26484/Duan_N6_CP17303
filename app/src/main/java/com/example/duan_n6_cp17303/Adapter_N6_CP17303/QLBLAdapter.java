package com.example.duan_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.R;

import java.util.List;

public class QLBLAdapter extends BaseAdapter {
    List<BinhLuanDTO> list;
    Context context;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
        return binhLuanDTO;
    }

    @Override
    public long getItemId(int position) {
        BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
        return binhLuanDTO.getIdbinhluan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(), R.layout.item_qlbl, null);
        }else{
            view = convertView;
        }
        ImageView imageView =view.findViewById(R.id.qlbl_img);
        TextView tv_ten = view.findViewById(R.id.qlbl_tv_ten);

        BinhLuanDTO binhLuanDTO =list.get(position);
 //       Glide.with(view.getContext()).load(Uri.parse(sanPhamDTO.getAnhsanpham())).into(imgItemsp);
        return null;

    }
}
