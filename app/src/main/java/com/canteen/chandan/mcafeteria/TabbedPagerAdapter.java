package com.canteen.chandan.mcafeteria;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandan on 2/22/2018.
 */

public class TabbedPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> allFragments=new ArrayList<>();
    private  List<String> allTitles=new ArrayList<>();

    public TabbedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addValues(Fragment fm, String title){
        allFragments.add(fm);
        allTitles.add(title);
    }


    @Override
    public Fragment getItem(int position) {return allFragments.get(position);
    }

    @Override
    public int getCount() {
        return allTitles.size();
    }

    public CharSequence getPageTitle(int position){
        return allTitles.get(position);
    }
}
