package com.example.duan_n6_cp17303.Apdater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_n6_cp17303.Fragment.FragmentDanhgia;
import com.example.duan_n6_cp17303.Fragment.FragmentLienhe;

public class PagerAdapterKh extends FragmentStateAdapter {

    int soluongPage = 3;
    public PagerAdapterKh(@NonNull Fragment fragment) {
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
