package com.example.duan_n6_cp17303;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan_n6_cp17303.Fragment.FragmentDonhang;
import com.example.duan_n6_cp17303.Fragment.FragmentMenu;
import com.example.duan_n6_cp17303.Fragment.FragmentSanpham;
import com.example.duan_n6_cp17303.Fragment.FragmentThem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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