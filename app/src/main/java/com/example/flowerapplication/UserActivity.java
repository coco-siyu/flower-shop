package com.example.flowerapplication;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.flowerapplication.UserFlowersFragment;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    List<String> tabStrings = new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView mIvBack;
    private UserFlowersFragment flowerFragment;

    private BroadcastReceiver broadcastReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        flowerFragment = new UserFlowersFragment();
        mIvBack = (ImageView) findViewById(R.id.iv_back);

        viewPager = (ViewPager) findViewById(R.id.view_pager_user);
        tabLayout = (TabLayout) findViewById(R.id.tl_title_user);

        String user = getIntent().getStringExtra("mine_user");
        Bundle bundle = new Bundle();
        bundle.putString("mine_user", user);
        flowerFragment.setArguments(bundle);

        fragments.add(flowerFragment);

        tabStrings.add("Flower Types");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
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
