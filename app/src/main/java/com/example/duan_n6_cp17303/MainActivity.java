package com.example.duan_n6_cp17303;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.duan_n6_cp17303.DAO_N6_CP17303.CuaHangDAO;
import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentDonhang;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentMenu;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentSanpham;
import com.example.duan_n6_cp17303.Fragment_N6_CP17303.FragmentThem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Connection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper myDBHelper = new MyDBHelper();
        Connection conn = myDBHelper.openConnect();

        CuaHangDAO catDao = new CuaHangDAO();

        // bước 9 thì không cần phần trên, dùng DAO để lấy dữ liệu
//        String tencuahang = "Chương đẹp trai1";
//        String diachi = "Đẹp trai1";
//        String phone = "0986756541";
//
//
        CuaHangDTO cuaHangDTO = new CuaHangDTO();


        // duyệt mảng in ra danh sách
        List<CuaHangDTO> listCat = catDao.getAll(); // lấy danh sách cho vào biến
        for(int i = 0; i<listCat.size(); i++){
            CuaHangDTO objCat = listCat.get(i);

            Log.d("zzzzz", "onCreate: phần tử thứ " + i + ":  id = " + objCat.getTencuahang());

        }


        fragmentManager = getSupportFragmentManager();
        FragmentMenu fragmentMenu = new FragmentMenu();
        FragmentDonhang fragmentDonhang = new FragmentDonhang();
        FragmentThem fragmentThem = new FragmentThem();
        FragmentSanpham fragmentSanpham = new FragmentSanpham();

        fragmentManager.beginTransaction().add(R.id.container_frag,fragmentMenu).commit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_Menu:
                        replaceFragment(fragmentMenu.newInstance());
                        break;
                    case R.id.nav_donHang:
                        replaceFragment(fragmentDonhang.newInstance());
                        break;
                    case R.id.nav_sanPham:
                        replaceFragment(fragmentSanpham.newInstance());
                        break;
                    case R.id.nav_them:
                        replaceFragment(fragmentThem.newInstance());
                        break;
                    default:
                        replaceFragment(fragmentMenu.newInstance());
                        break;
                }

                return true;
            }
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frag,fragment);
        transaction.commit();
    }

    protected void reloadFragment(String TAG){
        // Reload current fragment
        Fragment frg = null;
        frg = this.getSupportFragmentManager().findFragmentByTag(TAG);
        FragmentTransaction ft =this.getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }

}