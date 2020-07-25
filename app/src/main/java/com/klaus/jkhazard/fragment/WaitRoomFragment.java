package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.adapter.CardAdapter;
import com.klaus.jkhazard.adapter.PlayerAdapter;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.model.Player;

import java.util.ArrayList;


public class WaitRoomFragment extends BaseInitialSetupFragment {

    public static final String KEY_IS_PLAYER_HOST = "is_player_host";

    RecyclerView mPlayerRecyclerView;
    PlayerAdapter mAdapter;

    UIListener mUIListener;
    PlayersListener mPlayerListener;

    public static WaitRoomFragment newInstance(boolean isHost) {
        Bundle args = new Bundle();
        args.putBoolean(KEY_IS_PLAYER_HOST, isHost);

        WaitRoomFragment fragment = new WaitRoomFragment();
        fragment.setArguments(args);

        return fragment;
    }

    // TODO: change interface to API interface
    public interface PlayersListener {
        ArrayList<Player> getPlayers();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_room, container, false);

        Button startButton = (Button) view.findViewById(R.id.start_game_btn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUIListener.onDoneClicked();
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            if (args.getBoolean(KEY_IS_PLAYER_HOST)) {
                startButton.setEnabled(true);
            }
        }

        mPlayerRecyclerView = (RecyclerView) view.findViewById(R.id.player_wait_list);
        mPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new PlayerAdapter(getContext(), mPlayerListener.getPlayers());
        mPlayerRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mPlayerListener = (PlayersListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement PlayersListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPlayerListener = null;
    }

}
