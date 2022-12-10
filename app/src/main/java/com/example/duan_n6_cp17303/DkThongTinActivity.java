package com.example.duan_n6_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.CuaHangDAO;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

public class DkThongTinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dk_thong_tin);

        Button btn_dk = findViewById(R.id.dangky_btn_dangkythongtin);
        EditText ed_tch = findViewById(R.id.dangky_ed_tencuahang);
        EditText ed_sdt = findViewById(R.id.dangky_ed_sdt);
        EditText ed_dc = findViewById(R.id.dangky_ed_diachi);

        CuaHangDAO cuaHangDAO = new CuaHangDAO();
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuaHangDTO cuaHangDTO = new CuaHangDTO();
                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                String user = sharedPreferences.getString("key_TKdk","");


                String tch = ed_tch.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String dc = ed_dc.getText().toString();

                if (tch.equals("") || sdt.equalsIgnoreCase("") || dc.equalsIgnoreCase("")) {
                    Toast.makeText(DkThongTinActivity.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                } else {
                    cuaHangDTO.setDiachi(dc);
                    cuaHangDTO.setPhone(sdt);
                    cuaHangDTO.setTencuahang(tch);
                    cuaHangDTO.setUsername(user);

                    if (cuaHangDAO.insertRow(cuaHangDTO)==true){
                        Toast.makeText(DkThongTinActivity.this, "Thêm Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DkThongTinActivity.this,DangNhapActivity.class));
                    }else{
                        Toast.makeText(DkThongTinActivity.this, "Thêm Thông Tin Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}