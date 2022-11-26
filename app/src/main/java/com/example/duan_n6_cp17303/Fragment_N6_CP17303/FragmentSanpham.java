package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_n6_cp17303.Apdater_N6_CP17303.SanPhamAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class FragmentSanpham extends Fragment {

    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    ListView lv;
    List<SanPhamDTO> list;

    public static FragmentSanpham newInstance() {
        FragmentSanpham fragment = new FragmentSanpham();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham, container, false);

        lv = view.findViewById(R.id.sanpham_lv_donHang);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.sanpham_fab_themsanpham);
        sanPhamDAO = new SanPhamDAO();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd(view.getContext());

            }
        });
        loaddata();


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_suaxoa);

                EditText tensp = dialog.findViewById(R.id.suasp_ed_tensp);
                EditText soluong = dialog.findViewById(R.id.suasp_ed_soluong);
                EditText thongtin = dialog.findViewById(R.id.suasp_ed_thongtin);
                EditText giatien = dialog.findViewById(R.id.suasp_ed_giatien);
                EditText img = dialog.findViewById(R.id.suasp_ed_anh);

                Button them = dialog.findViewById(R.id.suasp_btn_dangky);
                Button huy = dialog.findViewById(R.id.suasp_btn_huy);
                Button xoa = dialog.findViewById(R.id.suasp_btn_xoa);
                SanPhamDTO sanPhamDTO1 = list.get(position);

                tensp.setText(sanPhamDTO1.getTensanpham());
                soluong.setText(String.valueOf(sanPhamDTO1.getSoluong()));
                thongtin.setText(sanPhamDTO1.getThongtin());
                giatien.setText(String.valueOf(sanPhamDTO1.getGiatien()));
                img.setText(sanPhamDTO1.getAnhsanpham());

                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String tsp = tensp.getText().toString();
                            String tt = thongtin.getText().toString();
                            String gt = giatien.getText().toString();
                            String sl = soluong.getText().toString();


                            sanPhamDTO1.setTensanpham(String.valueOf(tsp));
                            sanPhamDTO1.setGiatien(Float.parseFloat(gt));
                            sanPhamDTO1.setSoluong(Integer.parseInt(sl));
                            sanPhamDTO1.setAnhsanpham(img.getText().toString());
                            sanPhamDTO1.setThongtin(tt);


                            sanPhamDAO.updateRow(sanPhamDTO1);
                            Toast.makeText(getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            loaddata();
                            dialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Số lượng và giá tiền phải là số", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            sanPhamDAO.deleteRow(list.get(position));
                            Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            loaddata();
                            dialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
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
                return false;
            }

        });

    }

    public void loaddata() {

        list = sanPhamDAO.getAll();
        adapter = new SanPhamAdapter(sanPhamDAO.getAll(), getContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void showDialogAdd(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_sanpham);

        EditText tensp = dialog.findViewById(R.id.themsp_ed_tensp);
        EditText soluong = dialog.findViewById(R.id.themsp_ed_soluong);
        EditText thongtin = dialog.findViewById(R.id.themsp_ed_thongtin);
        EditText giatien = dialog.findViewById(R.id.themsp_ed_giatien);
        EditText img = dialog.findViewById(R.id.themsp_ed_anh);

        Button them = dialog.findViewById(R.id.themsp_btn_dangky);
        Button huy = dialog.findViewById(R.id.themsp_btn_huy);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String tsp = tensp.getText().toString();
                    String tt = thongtin.getText().toString();


                    SanPhamDTO sanPhamDTO = new SanPhamDTO();
                    sanPhamDTO.setTensanpham(String.valueOf(tsp));
                    sanPhamDTO.setGiatien(Float.parseFloat(giatien.getText().toString()));
                    sanPhamDTO.setSoluong(Integer.parseInt(soluong.getText().toString()));
                    sanPhamDTO.setAnhsanpham(img.getText().toString());
                    sanPhamDTO.setThongtin(tt);


                    sanPhamDAO.insertRow(sanPhamDTO);
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Số Lượng và Giá tiền phải số", Toast.LENGTH_SHORT).show();
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

}