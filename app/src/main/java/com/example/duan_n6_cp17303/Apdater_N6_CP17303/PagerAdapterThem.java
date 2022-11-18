package com.example.duan_n6_cp17303.Apdater_N6_CP17303;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentDanhgia;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentLienhe;

public class PagerAdapterThem extends FragmentStateAdapter {

    int soluongPage = 3;
    public PagerAdapterThem(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new FragmentDanhgia();
            case 1:
                return new FragmentLienhe();
            default:
                return new FragmentDanhgia();
        }

    }

    @Override
    public int getItemCount() {
        return soluongPage;
    }
}
