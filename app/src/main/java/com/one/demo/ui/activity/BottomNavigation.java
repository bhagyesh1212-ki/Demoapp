package com.one.demo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.one.demo.R;
import com.one.demo.adapter.ViewPagerAdapter;
import com.one.demo.databinding.ActivityBottomNavigationBinding;
import com.one.demo.ui.fragment.HomeFragment;
import com.one.demo.ui.fragment.LanguageFragmentN;
import com.one.demo.ui.fragment.SettingFragment;

public class BottomNavigation extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frame_layout;
    ViewPagerAdapter viewPagerAdapter;
    private ActivityBottomNavigationBinding binding;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frame_layout = findViewById(R.id.frame_layout);


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.language) {
                binding.viewpager.setCurrentItem(1);
            } else if (itemId == R.id.setting) {
                binding.viewpager.setCurrentItem(2);
            } else {
                binding.viewpager.setCurrentItem(0);
            }
            return true;
        });

//        replacefragment(new HomeFragment());
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            int itemId = item.getItemId();
//            if (itemId == R.id.home){
//
//                replacefragment(new HomeFragment());
//            } else if (itemId == R.id.language) {
//                replacefragment(new LanguageFragment());
//            } else if (itemId == R.id.setting) {
//                replacefragment(new SettingFragment());
//            } else {
//                replacefragment(new HomeFragment());
//            }
//            return true;
//        });

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.add(new HomeFragment());
        viewPagerAdapter.add(new LanguageFragmentN());
        viewPagerAdapter.add(new SettingFragment());
        binding.viewpager.setAdapter(viewPagerAdapter);

        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

 //   @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
    @Override
    public void onBackPressed() {
        if (binding.viewpager.getCurrentItem() != 0) {
            binding.viewpager.setCurrentItem(0); // Navigate back to the Home tab
        } else {
            super.onBackPressed(); // Exit the app
        }
    }

//    private void replacefragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit();
//    }
}