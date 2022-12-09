package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.HoaDonDAO;
import com.example.duan_n6_cp17303.R;

import java.util.Calendar;


public class FragmentTkDh extends Fragment {
    ImageView img_dateStart, img_dateEnd;
    TextView tv_dateStart, tv_dateEnd, tv_sldonhang, tv_tongdoanhthu;
    Button btn_thongke;
    HoaDonDAO hoaDonDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tk_dh, container, false);

        img_dateStart = view.findViewById(R.id.img_ngaybatdau);
        img_dateEnd = view.findViewById(R.id.img_ngaykethuc);
        tv_dateStart = view.findViewById(R.id.tv_ngaybatdau);
        tv_dateEnd = view.findViewById(R.id.tv_ngayketthuc);
        tv_sldonhang = view.findViewById(R.id.tv_sldonhang1);
        btn_thongke = view.findViewById(R.id.btn_thongke);
        tv_tongdoanhthu = view.findViewById(R.id.tv_tongdoanhthu1);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hoaDonDAO = new HoaDonDAO();


        img_dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDatePickerStart();
            }
        });
        img_dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDatePickerEnd();
            }
        });
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                String user = sharedPreferences.getString("key_TK1","");

                int soluong = hoaDonDAO.getSLDH(tv_dateStart.getText().toString(), tv_dateEnd.getText().toString(),user);

                int doanhthu = hoaDonDAO.getDTTK(tv_dateStart.getText().toString(), tv_dateEnd.getText().toString(),user);

                tv_tongdoanhthu.setText(doanhthu + "đ");
                tv_sldonhang.setText(soluong + "");
            }
        });


    }

    void showDialogDatePickerStart() {
        // viết lệnh hiển thị dialog
        // sử dụng đối tượng lịch để cài đặt
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // nguyên mẫu hàm khởi tạo DatePickerDialgo như sau:
        //DatePickerDialog(@NonNull Context context, @Nullable DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth)
        DatePickerDialog dialog;
        dialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // xử lý sự kiện
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        tv_dateStart.setText(nam + "-" + (thang + 1) + "-" + ngay);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );

        dialog.show();
    }

    void showDialogDatePickerEnd() {
        // viết lệnh hiển thị dialog
        // sử dụng đối tượng lịch để cài đặt
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // nguyên mẫu hàm khởi tạo DatePickerDialgo như sau:
        //DatePickerDialog(@NonNull Context context, @Nullable DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth)
        DatePickerDialog dialog;
        dialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // xử lý sự kiện
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        tv_dateEnd.setText(nam + "-" + (thang + 1) + "-" + ngay);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );

        dialog.show();
    }
}