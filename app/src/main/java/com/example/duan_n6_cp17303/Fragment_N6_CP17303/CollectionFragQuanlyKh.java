package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan_n6_cp17303.Apdater_N6_CP17303.PagerAdapterQLKH;
import com.example.duan_n6_cp17303.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CollectionFragQuanlyKh extends Fragment {
    PagerAdapterQLKH adapter;
    ViewPager2 viewPager2;

    public static CollectionFragQuanlyKh newInstance() {
        CollectionFragQuanlyKh fragment = new CollectionFragQuanlyKh();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection_frag_quanly_kh,container,false);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.viewPagerQLKH);
        adapter = new PagerAdapterQLKH(this);

        viewPager2.setAdapter(adapter);
        TabLayout tab = view.findViewById(R.id.tabLayoutQLKH);

        TabLayoutMediator mediator = new TabLayoutMediator(tab, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position == 0) {
                            tab.setText("Tất Cả");
                        } else if(position ==1) {
                            tab.setText("Chưa Giao");
                        }else{
                            tab.setText("Đã Giao");
                        }
                    }
                });
        mediator.attach();
    }
}