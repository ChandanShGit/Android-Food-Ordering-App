package com.canteen.chandan.mcafeteria.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canteen.chandan.mcafeteria.R;
import com.canteen.chandan.mcafeteria.TabbedPagerAdapter;


public class TabbedMainFragment extends Fragment {

    private View view;
    private ViewPager tabViewPager;
    private TabLayout tabLayout;
    private TabbedPagerAdapter tabPagerAdaptor;

    public TabbedMainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_home, container, false);
        tabViewPager=view.findViewById(R.id.tabViewPager);
        tabLayout=view.findViewById(R.id.home_tabLayout);
        tabPagerAdaptor=new TabbedPagerAdapter(getActivity().getSupportFragmentManager());
        tabPagerAdaptor.addValues(new TabbedInnerOneFragment(),"snacks");
        tabPagerAdaptor.addValues(new TabbedInnerTwoFragment(),"chinese");
        tabPagerAdaptor.addValues(new TabbedInnerThreeFragment(),"Lunch");
        tabViewPager.setAdapter(tabPagerAdaptor);
        tabLayout.setupWithViewPager(tabViewPager);
        return view;
    }


}
