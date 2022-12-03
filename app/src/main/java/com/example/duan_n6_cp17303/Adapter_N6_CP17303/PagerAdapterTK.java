package com.example.duan_n6_cp17303.Adapter_N6_CP17303;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentTkDh;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentTkDt;
import com.example.duan_n6_cp17303.ThongkeActivity;

public class PagerAdapterTK extends FragmentStateAdapter {

    int soluongPage = 2;
    public PagerAdapterTK(@NonNull ThongkeActivity thongkeActivity) {
        super(thongkeActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new FragmentTkDh();
            case 1:
                return new FragmentTkDt();

            default:
                return new FragmentTkDh();
        }

    }

    @Override
    public int getItemCount() {
        return soluongPage;
    }
}
