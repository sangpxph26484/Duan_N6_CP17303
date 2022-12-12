package com.example.duan_n6_cp17303.Adapter_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.QLBLActivyty;
import com.example.duan_n6_cp17303.R;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    List<SanPhamDTO> list;
    Context context;

    public SanPhamAdapter(List<SanPhamDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        SanPhamDTO sanPhamDTO = list.get(position);

        return sanPhamDTO;
    }

    @Override
    public long getItemId(int position) {
        SanPhamDTO sanPhamDTO = list.get(position);

        return sanPhamDTO.getIdsanpham();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_sanpham, null);
        } else {
            view = convertView;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        TextView tv_tensp = view.findViewById(R.id.item_sanpham_tv_tensanpham);
        TextView tv_gia = view.findViewById(R.id.item_sanpham_tv_gia);
        TextView tv_thongtin = view.findViewById(R.id.item_sanpham_tv_thongtin);
        TextView tv_soluong = view.findViewById(R.id.item_sanpham_tv_soluong);
        ImageView img_sp = view.findViewById(R.id.item_sanpham_img);
        SanPhamDTO sanPhamDTO = list.get(position);


        tv_tensp.setText("Tên Sản Phẩm: " + sanPhamDTO.getTensanpham() + "");
        tv_gia.setText("Giá: " + decimalFormat.format(sanPhamDTO.getGiatien()));
        tv_thongtin.setText("Thông Tin: " + sanPhamDTO.getThongtin());
        tv_soluong.setText("Số Lượng: " + sanPhamDTO.getSoluong() + "");


        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Mypref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key_SP1",sanPhamDTO.getIdsanpham());
        editor.commit();
        Glide.with(view.getContext()).load(Uri.parse(sanPhamDTO.getAnhsanpham())).into(img_sp);

        return view;
    }
}
