package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.UIListener;


public class HomeFragment extends Fragment {

    private InitialSetupHomeListener mCallback;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public interface InitialSetupHomeListener {
        void goToCreateRoom();
        void goToJoinRoom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button createGameButton = (Button) view.findViewById(R.id.create_game_button);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.goToCreateRoom();
            }
        });

        Button joinGameButton = (Button) view.findViewById(R.id.join_game_button);
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.goToJoinRoom();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mCallback = (InitialSetupHomeListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement InitialSetupHomeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

}
