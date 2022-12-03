package com.example.duan_n6_cp17303.Fragment_N6_CP17303;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.CTHDDAO;
import com.example.duan_n6_cp17303.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class FragmentTkDt extends Fragment {

    CTHDDAO cthddao;
    String nam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_tk_dt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BarChart chart = view.findViewById(R.id.barchart);
        EditText ed_nam = view.findViewById(R.id.ed_nam);
        Button btn_thongke = view.findViewById(R.id.btn_thongke);

        cthddao = new CTHDDAO();

        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nam = ed_nam.getText().toString();

                Log.d("chuongdk", "onClick: " + cthddao.getDtT12(nam));

                ArrayList NoOfEmp = new ArrayList();
                int t1 = cthddao.getDtT1(nam);
                int t2 = cthddao.getDtT2(nam);
                int t3 = cthddao.getDtT3(nam);
                int t4 = cthddao.getDtT4(nam);
                int t5 = cthddao.getDtT5(nam);
                int t6 = cthddao.getDtT6(nam);
                int t7 = cthddao.getDtT7(nam);
                int t8 = cthddao.getDtT8(nam);
                int t9 = cthddao.getDtT9(nam);
                int t10 = cthddao.getDtT10(nam);
                int t11 = cthddao.getDtT11(nam);
                int t12 = cthddao.getDtT12(nam);

                NoOfEmp.add(new BarEntry(10, t1));
                NoOfEmp.add(new BarEntry(11, t2));
                NoOfEmp.add(new BarEntry(12, t3));
                NoOfEmp.add(new BarEntry(13, t4));
                NoOfEmp.add(new BarEntry(15, t5));
                NoOfEmp.add(new BarEntry(16, t6));
                NoOfEmp.add(new BarEntry(17, t7));
                NoOfEmp.add(new BarEntry(18, t8));
                NoOfEmp.add(new BarEntry(19, t9));
                NoOfEmp.add(new BarEntry(20, t10));
                NoOfEmp.add(new BarEntry(21, t11));
                NoOfEmp.add(new BarEntry(22, t12));


                ArrayList month = new ArrayList();

                month.add("Tháng 1");
                month.add("Tháng 2");
                month.add("Tháng 3");
                month.add("Tháng 4");
                month.add("Tháng 5");
                month.add("Tháng 6");
                month.add("Tháng 7");
                month.add("Tháng 8");
                month.add("Tháng 9");
                month.add("Tháng 10");
                month.add("Tháng 11");
                month.add("Tháng 12");
                BarDataSet bardataset = new BarDataSet(NoOfEmp, "Thống Kê 12 Tháng Trong Năm");
                chart.animateY(5000);
                BarData data = new BarData(bardataset);
                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                chart.setData(data);

            }
        });

    }
}