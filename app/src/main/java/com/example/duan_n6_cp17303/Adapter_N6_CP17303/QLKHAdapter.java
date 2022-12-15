package com.example.duan_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.QLKHDTO;
import com.example.duan_n6_cp17303.R;

import java.text.DecimalFormat;
import java.util.List;

public class QLKHAdapter extends BaseAdapter {
    List<QLKHDTO> list;
    Context context;

    public QLKHAdapter(List<QLKHDTO> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        QLKHDTO qlkhdto = list.get(position);
        return qlkhdto;
    }

    @Override
    public long getItemId(int position) {
        QLKHDTO qlkhdto = list.get(position);
        return qlkhdto.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_qlkh, null);
        } else {
            view = convertView;
        }
//        ImageView imgItemsp = view.findViewById(R.id.item_hoadon_img);
        TextView tvItemtenkh = view.findViewById(R.id.tv_item_hoadon_tenkh);
        TextView tvngaymua = view.findViewById(R.id.tv_item_hoadon_ngaymua);
        TextView tvItemsoluong = view.findViewById(R.id.tv_item_hoadon_sl);
        TextView tvItemtongtien = view.findViewById(R.id.tv_item_hoadon_tongtien);
        TextView tvItemtrangthai = view.findViewById(R.id.tv_item_hoadon_trangthai);


        QLKHDTO qlkhdto = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        Glide.with(view.getContext()).load(Uri.parse(qlkhdto.getAnhsanpham())).into(imgItemsp);
        tvItemtenkh.setText("Họ Tên: "+ qlkhdto.getTenkhachhang());
        tvItemsoluong.setText("Số Lượng:"+qlkhdto.getSoluong()+"");
        tvItemtongtien.setText(decimalFormat.format(qlkhdto.getTongtien()));
        tvItemtrangthai.setText(qlkhdto.getTrangthai());
        tvngaymua.setText("Ngày Mua: "+qlkhdto.getNgaymua());


        return view;
    }
}
