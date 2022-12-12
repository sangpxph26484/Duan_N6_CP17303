package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan_n6_cp17303.Adapter_N6_CP17303.PagerAdapterQLKH;
import com.example.duan_n6_cp17303.Adapter_N6_CP17303.PagerAdapterThem;
import com.example.duan_n6_cp17303.Adapter_N6_CP17303.SanPhamAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.QLBLActivyty;
import com.example.duan_n6_cp17303.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.Serializable;
import java.util.List;


public class FragmentThem extends Fragment {
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    ListView lv;
    List<SanPhamDTO> list;
    SanPhamDTO sanPhamDTO;
    BinhLuanDTO binhLuanDTO;
    public static FragmentThem newInstance() {
        FragmentThem fragment = new FragmentThem();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);

        lv = view.findViewById(R.id.qldh_lv_list);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            sanPhamDAO = new SanPhamDAO();

        loaddata();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sanPhamDTO = list.get(position);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("key_SP",sanPhamDTO.getIdsanpham());
                editor.putString("key_ten",sanPhamDTO.getTensanpham());
                editor.putString("key_anh",sanPhamDTO.getAnhsanpham());
                editor.commit();
                startActivity(new Intent(getContext(),QLBLActivyty.class));
            }
        });
    }

    public void loaddata() {

        list = sanPhamDAO.getAll();
        adapter = new SanPhamAdapter(sanPhamDAO.getAll(), getContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}