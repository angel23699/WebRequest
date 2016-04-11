package com.arce.angel.request.Activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arce.angel.request.Fragments.GETRequestFragment;
import com.arce.angel.request.Fragments.POSTRequestFragment;
import com.arce.angel.request.R;
import com.arce.angel.request.SlidingTab.SlidingTabLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SlidingTabLayout slidingTabLayout;
    ViewPager viewPager;
    SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setViewPager(viewPager);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return GETRequestFragment.newInstance();
                case 1:
                    return POSTRequestFragment.newInstance();
                case 2:
                    return POSTRequestFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.request_GET).toUpperCase(l);
                case 1:
                    return getString(R.string.request_POST).toUpperCase(l);
                case 2:
                    return getString(R.string.request_SOAP).toUpperCase(l);
            }
            return null;
        }
    }
}
