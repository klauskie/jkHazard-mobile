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


public class JoinRoomFragment extends BaseInitialSetupFragment {

    public static JoinRoomFragment newInstance() {
        return new JoinRoomFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_room, container, false);

        EditText editRoomNumber = (EditText) view.findViewById(R.id.edit_room_number);
        Button nextButton = (Button) view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUIListener.onNextClicked();
            }
        });

        return view;
    }

}
