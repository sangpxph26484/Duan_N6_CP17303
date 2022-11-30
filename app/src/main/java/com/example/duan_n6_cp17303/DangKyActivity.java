package com.example.duan_n6_cp17303;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.CuaHangDAO;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky);

        Button btn_dk = findViewById(R.id.dangky_btn_dangky);
        EditText ed_dktk = findViewById(R.id.dangky_ed_taikhoan);
        EditText ed_dkmk = findViewById(R.id.dangky_ed_matkhau);
        EditText ed_nlmk = findViewById(R.id.dangky_ed_nhaplaimatkhau);
        EditText ed_tenshop = findViewById(R.id.dangky_ed_ten);
        EditText ed_diachi = findViewById(R.id.dangky_ed_diachi);
        EditText ed_sdt = findViewById(R.id.dangky_ed_sdt);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        CuaHangDAO cuaHangDAO = new CuaHangDAO();
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
                CuaHangDTO cuaHangDTO = new CuaHangDTO();

                String dktk = ed_dktk.getText().toString();
                String dkmk = ed_dkmk.getText().toString();
                String nlmk = ed_nlmk.getText().toString();
                String tenshop = ed_tenshop.getText().toString();
                String diachi = ed_diachi.getText().toString();
                String sdt = ed_sdt.getText().toString();

                if (dktk.equals("") || dkmk.equalsIgnoreCase("") || nlmk.equalsIgnoreCase("") || tenshop.equalsIgnoreCase("") || diachi.equalsIgnoreCase("") || sdt.equalsIgnoreCase("")) {
                    Toast.makeText(DangKyActivity.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (nlmk.equals(dkmk)) {
                        int a = taiKhoanDAO.checkUser(dktk);
                        if (a == -1) {
                            taiKhoanDTO.setUsername(dktk);
                            taiKhoanDTO.setPassword(dkmk);
                            cuaHangDTO.setDiachi(diachi);
                            cuaHangDTO.setPhone(sdt);
                            cuaHangDTO.setTencuahang(tenshop);

                            if (taiKhoanDAO.insertRow(taiKhoanDTO) == true || cuaHangDAO.insertRow(cuaHangDTO) == true) {
                                Toast.makeText(DangKyActivity.this, "Đăng Ký Thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                Toast.makeText(DangKyActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(DangKyActivity.this, "Tài Khoản Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(DangKyActivity.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}