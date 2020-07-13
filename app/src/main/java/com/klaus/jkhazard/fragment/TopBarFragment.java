package com.klaus.jkhazard.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klaus.jkhazard.R;

public class TopBarFragment extends Fragment {

    public static final String TAG = TopBarFragment.class.getName();

    public static TopBarFragment newInstance() {
        return new TopBarFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_bar, container, false);
    }

}
