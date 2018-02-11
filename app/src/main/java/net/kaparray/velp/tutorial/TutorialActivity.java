package net.kaparray.velp.tutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import net.kaparray.velp.Auth.AuthActivity;
import net.kaparray.velp.R;


public class TutorialActivity extends AppCompatActivity {



   public void startAuthActivity(View view){
       Intent intent = new Intent(TutorialActivity.this, AuthActivity.class);
       startActivity(intent);
       finish();
   }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tutorial);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            SharedPreferences preferences = getSharedPreferences("TUTORIAL", Context.MODE_PRIVATE);
            if (preferences.getBoolean("activity_executed", false)) {
                Intent intent = new Intent(TutorialActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            } else {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("activity_executed", true);
                editor.apply();
            }
        }

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());



        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }



    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fr_tutorial1, container, false);
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0: return TutorialFragment1.newInstance();
                case 1: return TutorialFragment2.newInstance();
                case 2: return TutorialFragment3.newInstance();
                case 3: return TutorialFragment4.newInstance();
                default: return PlaceholderFragment.newInstance(position + 1);
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }



    }
}
