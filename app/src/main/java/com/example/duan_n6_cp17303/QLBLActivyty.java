package com.example.duan_n6_cp17303;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan_n6_cp17303.Adapter_N6_CP17303.BinhLuanAdapter;
import com.example.duan_n6_cp17303.Adapter_N6_CP17303.SanPhamAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.BinhLuanDAO;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentThem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QLBLActivyty extends AppCompatActivity {
        TextView tensp, giasp, mota, txtsoluongsp;
        ImageView imgsanpham, imgback;
        SanPhamDAO dao;
        int count = 0;
        SanPhamDTO sanPhamDTO;
        ListView lv_binhluan;
        BinhLuanAdapter binhLuanAdapter;
        BinhLuanDAO binhLuanDAO;
        List<BinhLuanDTO> list = new ArrayList<>();

    public static QLBLActivyty newInstance() {
        QLBLActivyty fragment = new QLBLActivyty();

        return fragment;
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_qlblactivyty);


            tensp = findViewById(R.id.binhluan_tv_ten);
            imgsanpham = findViewById(R.id.binhluan_img);
            lv_binhluan = findViewById(R.id.binhluan_lv_list);
            Intent intent1 = getIntent();
            String id_sanpham = intent1.getStringExtra("IDSANPHAM");

            SharedPreferences sharedPreferences = this.getSharedPreferences("Login", this.MODE_PRIVATE);
            String u = sharedPreferences.getString("name", "");
            Log.e("u", u);

            showdatabinhluan();
            binhLuanDAO = new BinhLuanDAO();
            BinhLuanDTO bl = new BinhLuanDTO();
            KhachHangDAO khachHangDAO = new KhachHangDAO();
            binhLuanDAO = new BinhLuanDAO();
            dao = new SanPhamDAO();
            sanPhamDTO = (SanPhamDTO) getIntent().getSerializableExtra("chitiet");
            SharedPreferences sharedPreferences1 = getBaseContext().getSharedPreferences("Mypref",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String anh = sharedPreferences1.getString("key_anh","");
            String ten = sharedPreferences1.getString("key_ten","");

           Glide.with(QLBLActivyty.this).load(Uri.parse(anh)).into(imgsanpham);
           tensp.setText(ten);
//            SharedPreferences sharedPreferences3 = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
//            String binhluan = sharedPreferences3.getString("key_TK1", "");
//            int idkh = khachHangDAO.getidKh(binhluan);
//            SharedPreferences sharedPreferences4 = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
//            int idsp = sharedPreferences4.getInt("key_SP", 0);
//           bl.setBinhluan(String.valueOf(binhLuanDAO.getAll1(idkh)));
            showdatabinhluan();

            lv_binhluan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(QLBLActivyty.this);

                    builder.setTitle("Xóa Sản Phẩm");
                    builder.setMessage("Bạn có chắc chắn muốn xóa không");

                    builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                list = binhLuanDAO.getAll();
                                BinhLuanDTO binhLuanDTO = list.get(position);
                                binhLuanDAO.deleteRow(binhLuanDTO.getIdbinhluan());
                                Toast.makeText(QLBLActivyty.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                showdatabinhluan();
                                dialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;
                }
            });
        }
        private void showdatabinhluan() {
            SharedPreferences sharedPreferences1 = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
            int idsp = sharedPreferences1.getInt("key_SP", 0);
            Log.e("idsanpham", "showdatabinhluan:"+idsp);
            binhLuanDAO = new BinhLuanDAO();
            list = binhLuanDAO.getAll1(idsp);
            binhLuanAdapter = new BinhLuanAdapter(list, getBaseContext());
            lv_binhluan.setAdapter(binhLuanAdapter);
        }
}