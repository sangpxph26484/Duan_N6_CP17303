package com.example.duan_n6_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan_n6_cp17303.Adapter_N6_CP17303.PhieuGiamGiaAdapter;
import com.example.duan_n6_cp17303.DAO_N6_CP17303.PhieuGiamGiaDAO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.PhieuGiamGiaDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PhieuGiamGiaActivity extends AppCompatActivity implements PhieuGiamGiaAdapter.OnclickVoucher {
    PhieuGiamGiaDAO phieuGiamGiaDAO;
    PhieuGiamGiaAdapter adapter;
    ListView lv;
    List<PhieuGiamGiaDTO> list;
    Button btn_xoa;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_voucher);

        img_back = findViewById(R.id.img_back);
        FloatingActionButton fab = findViewById(R.id.voucher_fab_themvoucher);
        phieuGiamGiaDAO = new PhieuGiamGiaDAO();
        lv = findViewById(R.id.voucher_lv_voucher);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd(PhieuGiamGiaActivity.this);
            }
        });
        loaddata();

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void loaddata() {

        list = phieuGiamGiaDAO.getAll();
        adapter = new PhieuGiamGiaAdapter(phieuGiamGiaDAO.getAll(), PhieuGiamGiaActivity.this, this);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void showDialogAdd(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_voucher);

        EditText tenvoucher = dialog.findViewById(R.id.themvoucher_ed_ma);
        EditText phamtram = dialog.findViewById(R.id.themvoucher_ed_phantram);
        Button them = dialog.findViewById(R.id.themvoucher_btn_them);
        Button huy = dialog.findViewById(R.id.themvoucher_btn_huy);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ma = tenvoucher.getText().toString();
                    String pt = phamtram.getText().toString();

                    PhieuGiamGiaDTO phieuGiamGiaDTO = new PhieuGiamGiaDTO();
                    phieuGiamGiaDTO.setMagiamgia(ma);
                    phieuGiamGiaDTO.setPhantram(pt);

                    phieuGiamGiaDAO.insertRow(phieuGiamGiaDTO);
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "MÃ và phần trăm ko đúng", Toast.LENGTH_SHORT).show();
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

    @Override
    public void hihi(PhieuGiamGiaDTO phieuGiamGiaDTO) {


        AlertDialog.Builder builder = new AlertDialog.Builder(PhieuGiamGiaActivity.this);
        builder.setTitle("Xóa Thành Viên");
        builder.setMessage("Bạn có chắc chắn muốn xóa không");
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    phieuGiamGiaDAO.deleteRow(phieuGiamGiaDTO.getIdphieugiamgia());
                    Toast.makeText(PhieuGiamGiaActivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    loaddata();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}