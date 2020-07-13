package com.klaus.jkhazard.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.Constant;

import java.util.Locale;

public class TopBarFragment extends Fragment {

    public static final String TAG = TopBarFragment.class.getName();

    public static TopBarFragment newInstance(String playerInTurn, String score) {

        Bundle args = new Bundle();
        args.putString(Constant.KEY_PLAYER_IN_TURN, playerInTurn);
        args.putString(Constant.KEY_CURRENT_SCORE, score);

        TopBarFragment fragment = new TopBarFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private TextView mPlayerInTurn;
    private TextView mMyScore;
    private TextView mCountdownText;

    private CountDownTimer mCountDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_bar, container, false);

        mPlayerInTurn = (TextView) view.findViewById(R.id.player_in_turn);
        mMyScore = (TextView) view.findViewById(R.id.score_number);
        mCountdownText = (TextView) view.findViewById(R.id.count_down_timer);

        Bundle args = getArguments();

        mPlayerInTurn.setText(args.getString(Constant.KEY_PLAYER_IN_TURN, ""));
        mMyScore.setText(args.getString(Constant.KEY_CURRENT_SCORE, "0"));

        return view;
    }

    public void startCountDown(int seconds) {
        mCountDownTimer = new CountDownTimer(seconds*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                long m = (millisUntilFinished/1000 % 3600) / 60;
                long s = millisUntilFinished/1000 % 60;

                mCountdownText.setText(String.format(Locale.ENGLISH,"%02d:%02d", m, s));
            }

            public void onFinish() {
                mCountdownText.setText("done!");
            }
        }.start();
    }

}
