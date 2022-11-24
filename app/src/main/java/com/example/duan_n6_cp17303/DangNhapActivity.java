package com.example.duan_n6_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

public class DangNhapActivity extends AppCompatActivity {
    String a;
    EditText ed_user,ed_pass;
    CheckBox cbo_luumk;
    Button btn_dangnhap;
    TextView tv_quenmk,tv_chuadangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
    ed_user = findViewById(R.id.dangnhap_ed_taikhoan);
    ed_pass = findViewById(R.id.dangnhap_ed_matkhau);
    cbo_luumk = findViewById(R.id.dangnhap_cbo_luumatkhau);
    btn_dangnhap = findViewById(R.id.dangnhap_btn_dangnhap);
    tv_quenmk = findViewById(R.id.dangnhap_tv_quenmatkhau);
    tv_chuadangky = findViewById(R.id.dangnhap_tv_chuadangky);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();

            }
        });

        tv_chuadangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}