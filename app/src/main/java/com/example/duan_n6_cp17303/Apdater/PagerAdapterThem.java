package com.example.duan_n6_cp17303.Apdater;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_n6_cp17303.Fragment.FragmentAll;
import com.example.duan_n6_cp17303.Fragment.FragmentChuagiao;
import com.example.duan_n6_cp17303.Fragment.FragmentDagiao;
import com.example.duan_n6_cp17303.Fragment.FragmentDanhgia;
import com.example.duan_n6_cp17303.Fragment.FragmentLienhe;


public class PagerAdapterThem extends FragmentStateAdapter {

    int soluongPage = 2;
    public PagerAdapterThem(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new FragmentAll();
            case 1:
                return new FragmentChuagiao();
            case 2:
                return new FragmentDagiao();
            default:
                return new FragmentAll();
        }

    }

    @Override
    public int getItemCount() {
        return soluongPage;
    }
}
