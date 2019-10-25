package com.beautiful.soundss.poetryeditor.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Fragments.AllamaIqbalFragment;
import com.beautiful.soundss.poetryeditor.Fragments.AllamaIqbalFragmentPoetry;
import com.beautiful.soundss.poetryeditor.Fragments.BarishRainFragment;
import com.beautiful.soundss.poetryeditor.Fragments.BarishRainFragmentPoetry;
import com.beautiful.soundss.poetryeditor.Fragments.BeWafaFragment;
import com.beautiful.soundss.poetryeditor.Fragments.BeWafaFragmentPoetry;
import com.beautiful.soundss.poetryeditor.Fragments.DostiFragment;
import com.beautiful.soundss.poetryeditor.Fragments.DostiFragmentPoetry;
import com.beautiful.soundss.poetryeditor.Fragments.GazalFragment;
import com.beautiful.soundss.poetryeditor.Fragments.GazalFragmentPoetry;
import com.beautiful.soundss.poetryeditor.Fragments.IshqFragment;
import com.beautiful.soundss.poetryeditor.Fragments.IshqFragmentPoetry;
import com.beautiful.soundss.poetryeditor.Fragments.KhushiFragment;
import com.beautiful.soundss.poetryeditor.Fragments.KhushiFragmentPoetry;
import com.beautiful.soundss.poetryeditor.R;

public class PoetryChooseFragmentsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    private PoetryChooseFragmentsActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_poetry);


        mSectionsPagerAdapter = new PoetryChooseFragmentsActivity.SectionsPagerAdapter(getSupportFragmentManager());

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


    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch(position){
                case 0:
                    return new IshqFragmentPoetry();
                case 1:
                    return new BeWafaFragmentPoetry();
                case 2:
                    return new DostiFragmentPoetry();
                case 3:
                    return new KhushiFragmentPoetry();
                case 4:
                    return new BarishRainFragmentPoetry();
                case 5:
                    //return new GazalFragmentPoetry();
                    return new AllamaIqbalFragmentPoetry();
//                case 6:
//                    return new AllamaIqbalFragmentPoetry();
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


