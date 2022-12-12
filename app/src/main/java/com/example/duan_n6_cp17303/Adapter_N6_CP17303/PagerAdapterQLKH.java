package com.example.duan_n6_cp17303.Adapter_N6_CP17303;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentAll;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentChuagiao;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentDagiao;


public class PagerAdapterQLKH extends FragmentStateAdapter {

    int soluongPage = 3;
    public PagerAdapterQLKH(@NonNull Fragment fragment) {
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
