package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.duan_n6_cp17303.Adapter_N6_CP17303.QLKHAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.HoaDonDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.QLKHDTO;
import com.example.duan_n6_cp17303.R;

import java.util.List;


public class FragmentChuagiao extends Fragment {

    ListView lv;
    HoaDonDAO dao;
    QLKHAdapter adapter;
    List<QLKHDTO> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chuagiao, container, false);
        lv = view.findViewById(R.id.lv_qlkhcg);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loaddata();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_qlhoadon);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                String user = sharedPreferences.getString("key_TK1","");
                list = dao.getDonHangCG(user);

                QLKHDTO qlkhdto = list.get(position);


                RadioButton cbo_chuagiao = dialog.findViewById(R.id.cbo_chuagiao);
                RadioButton cbo_dagiao = dialog.findViewById(R.id.cbo_dagiao);
                


                Button capnhat = dialog.findViewById(R.id.btn_capnhat);
                Button huy = dialog.findViewById(R.id.btn_huy);

                final String[] a = {null};
                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (cbo_chuagiao.isChecked()){
                            a[0] = "Chưa Giao";
                        }else{
                            a[0] = "Đã Giao";
                        }

                        if (dao.updateTrangthai(qlkhdto.getId(), a[0])==true){
                            Toast.makeText(getContext(), "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loaddata();
                        }else {
                            Toast.makeText(getContext(), "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });



                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void loaddata() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");
        dao = new HoaDonDAO();
        adapter = new QLKHAdapter(dao.getDonHangCG(user), getContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}