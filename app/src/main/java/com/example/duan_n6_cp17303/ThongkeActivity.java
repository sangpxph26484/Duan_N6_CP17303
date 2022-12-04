package com.example.duan_n6_cp17303;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.duan_n6_cp17303.Adapter_N6_CP17303.PagerAdapterTK;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThongkeActivity extends AppCompatActivity {
    PagerAdapterTK adapter;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        ImageView img_back = findViewById(R.id.img_backtk);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewPager2 = findViewById(R.id.viewPagerQLDH);
        adapter = new PagerAdapterTK(ThongkeActivity.this);

        viewPager2.setAdapter(adapter);
        TabLayout tab = findViewById(R.id.tabLayoutQLDH);

        TabLayoutMediator mediator = new TabLayoutMediator(tab, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position == 0) {
                            tab.setText("Đơn Hàng");
                        }else{
                            tab.setText("Doanh Thu");
                        }
                    }
                });
        mediator.attach();
    }
}