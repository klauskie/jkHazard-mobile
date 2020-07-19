package com.klaus.jkhazard.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klaus.jkhazard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewSetFragment extends Fragment {

    public static final String TAG = PreviewSetFragment.class.getName();

    public static PreviewSetFragment newInstance() {
        return new PreviewSetFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preview_set, container, false);
    }

}
