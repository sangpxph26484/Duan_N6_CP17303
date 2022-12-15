package com.example.duan_n6_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

public class QuenmatkhauActivity extends AppCompatActivity {
    TaiKhoanDTO taiKhoanDTO;
    TaiKhoanDAO taiKhoanDAO;
    EditText ed_mkm,ed_nlmkm;
    Button btn_dongy;
    ImageView btn_huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau);

        ed_mkm = findViewById(R.id.ed_mkm);
        ed_nlmkm = findViewById(R.id.ed_nlmk);
        btn_dongy = findViewById(R.id.llmk_btn_dongy);
        btn_huy = findViewById(R.id.llmk_btn_huy_2);
        taiKhoanDTO = new TaiKhoanDTO();
        taiKhoanDAO = new TaiKhoanDAO();

        ed_mkm.getText().toString();
        ed_nlmkm.getText().toString();
        btn_dongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                String user = sharedPreferences.getString("key_TK1","");

                        String mkm = ed_mkm.getText().toString();
                        String nlmk = ed_nlmkm.getText().toString();

                        if ( mkm.equals("") || nlmk.equals("")) {
                            Toast.makeText(getBaseContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                        } else {
                            if (nlmk.equals(mkm)) {
                                    if (taiKhoanDAO.updateMatKhau(mkm,user) == true) {
                                        Toast.makeText(QuenmatkhauActivity.this, "Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                                        onBackPressed();

                                    } else {
                                        Toast.makeText(QuenmatkhauActivity.this, "Đổi Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(QuenmatkhauActivity.this, "Mật Khẩu Hiện Tại Không Đúng", Toast.LENGTH_SHORT).show();
                                }
                            }
                Intent intent = new Intent(QuenmatkhauActivity.this,DangNhapActivity.class);
                        startActivity(intent);

            }


        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}