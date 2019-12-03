package com.hotel.mangrovehotel;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class DealsPagerAdapter extends FragmentPagerAdapter {


    public DealsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                Deals_one deals_one = new Deals_one();
                return deals_one;

            case 1:
                Deals_two deals_two = new Deals_two();
                return deals_two;


            case 2:
                Deals_three deals_three = new Deals_three();
                return deals_three;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Deals 1";
            case 1:
                return "Deals 2";
            case 2:
                return "Deals 3";
        }
        return super.getPageTitle(position);
    }
}
