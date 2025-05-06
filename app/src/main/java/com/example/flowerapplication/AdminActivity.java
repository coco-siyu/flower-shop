package com.example.flowerapplication;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.flowerapplication.AdminFlowersFragment;
import com.example.flowerapplication.BaseActivity;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class AdminActivity extends BaseActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    List<String> tabStrings = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        AdminFlowersFragment flowersFragment = new AdminFlowersFragment();


        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tl_title);

        fragments.add(flowersFragment);


        tabStrings.add("Manage Flower");

        if (viewPager != null) {
            viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getCount() {
                    return fragments.size();
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    super.destroyItem(container, position, object);
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {

                    return tabStrings.get(position);
                }
            });
        } else {
            throw new NullPointerException();
        }

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    private long firstTime;



}
