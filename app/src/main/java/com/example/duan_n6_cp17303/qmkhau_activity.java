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

public class qmkhau_activity extends AppCompatActivity {
    Button btn_llmk;
    EditText ed_tendn;
    ImageView btn_huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmkhau);

        btn_llmk = findViewById(R.id.qmk_btn_llmk);
        btn_huy = findViewById(R.id.qmk_btn_huy);

        ed_tendn = findViewById(R.id.qmk_ed_ten);

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

        btn_llmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taiKhoanDAO.checkUser(ed_tendn.getText().toString())==1){
                    Toast.makeText(qmkhau_activity.this, "Nhập Mật Khẩu Mới", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(qmkhau_activity.this,QuenmatkhauActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(qmkhau_activity.this, "Tài Khoản Không Tồn Tại", Toast.LENGTH_SHORT).show();
                }
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