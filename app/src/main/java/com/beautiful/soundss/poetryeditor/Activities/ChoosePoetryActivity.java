package com.beautiful.soundss.poetryeditor.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.beautiful.soundss.poetryeditor.Fragments.AllamaIqbalFragment;
import com.beautiful.soundss.poetryeditor.Fragments.BarishRainFragment;
import com.beautiful.soundss.poetryeditor.Fragments.BeWafaFragment;
import com.beautiful.soundss.poetryeditor.Fragments.DostiFragment;
import com.beautiful.soundss.poetryeditor.Fragments.GazalFragment;
import com.beautiful.soundss.poetryeditor.Fragments.IshqFragment;
import com.beautiful.soundss.poetryeditor.Fragments.KhushiFragment;
import com.beautiful.soundss.poetryeditor.R;

public class ChoosePoetryActivity extends AppCompatActivity {

    TabLayout tabLayout;
    private ChoosePoetryActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public String poetry_text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_poetry);

        mSectionsPagerAdapter = new ChoosePoetryActivity.SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.containerEvents);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = findViewById(R.id.tabsEvents);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        poetry_text = getIntent().getStringExtra("poetry_text_current");
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch(position){
                case 0:
                    return new IshqFragment();
                case 1:
                    return new BeWafaFragment();
                case 2:
                    return new DostiFragment();
                case 3:
                    return new KhushiFragment();
                case 4:
                    return new BarishRainFragment();
                case 5:
                    return new AllamaIqbalFragment();
//                case 6:
//                    //return new AllamaIqbalFragment();
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 7 total pages.
            return 6;
        }

    }
}
