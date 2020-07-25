package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.UIListener;


public abstract class BaseInitialSetupFragment extends Fragment {

    protected UIListener mUIListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mUIListener = (UIListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement UIListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUIListener = null;
    }

}
