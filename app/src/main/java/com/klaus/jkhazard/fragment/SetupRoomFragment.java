package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.UIListener;


public class SetupRoomFragment extends Fragment {


    UIListener mUIListener;

    public static SetupRoomFragment newInstance() {
        return new SetupRoomFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_room, container, false);

        EditText numRoundEditText = (EditText) view.findViewById(R.id.edit_num_rounds);
        EditText numPlayersEditText = (EditText) view.findViewById(R.id.edit_num_players);

        Button nextButton = (Button) view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUIListener.onNextClicked();
            }
        });

        return view;
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
