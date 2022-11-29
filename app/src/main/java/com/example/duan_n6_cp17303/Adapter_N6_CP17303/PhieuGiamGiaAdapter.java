package com.example.duan_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.PhieuGiamGiaDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.PhieuGiamGiaDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.R;

import java.util.List;

public class PhieuGiamGiaAdapter extends BaseAdapter {
    List<PhieuGiamGiaDTO> list;
    Context context;
    PhieuGiamGiaDAO phieuGiamGiaDAO;
    OnclickVoucher onclickVoucher;
    public PhieuGiamGiaAdapter(List<PhieuGiamGiaDTO> list, Context context,OnclickVoucher onclickVoucher) {
        this.list = list;
        this.context = context;
        this.onclickVoucher = onclickVoucher;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        PhieuGiamGiaDTO phieuGiamGiaDTO = new PhieuGiamGiaDTO();
        return phieuGiamGiaDTO;
    }

    @Override
    public long getItemId(int position) {
        PhieuGiamGiaDTO phieuGiamGiaDTO = new PhieuGiamGiaDTO();

        return phieuGiamGiaDTO.getIdphieugiamgia();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_voucher, null);
        } else {
            view = convertView;
        }

      TextView tv_ma = view.findViewById(R.id.item_voucher_tv_ma);
      TextView tv_phanTram = view.findViewById(R.id.item_voucher_tv_phamtram);
      Button btn_them = view.findViewById(R.id.item_voucher_btn_xoa);
      PhieuGiamGiaDTO phieuGiamGiaDTO = list.get(position);

      tv_ma.setText("Mã: "+phieuGiamGiaDTO.getMagiamgia());
      tv_phanTram.setText("Giảm Giá: "+phieuGiamGiaDTO.getPhantram()+"%");

      btn_them.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onclickVoucher.hihi(phieuGiamGiaDTO);

          }
      });
        return view;
    }
    public  interface OnclickVoucher {
        void hihi(PhieuGiamGiaDTO phieuGiamGiaDTO);
    }
}
