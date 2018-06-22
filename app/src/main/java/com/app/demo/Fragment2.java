package com.app.demo;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class Fragment2 extends Fragment {
    private CustomScrollViewPager viewPager;
    public Fragment2(CustomScrollViewPager viewPager) {
        this.viewPager = viewPager;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_2, null);
        viewPager.setObjectForPosition(view,1);
        return view;
    }
}
