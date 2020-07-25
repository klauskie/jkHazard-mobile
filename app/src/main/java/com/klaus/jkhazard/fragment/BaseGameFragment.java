package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.Constant;
import com.klaus.jkhazard.common.UIListener;

import java.util.Locale;


public class BaseGameFragment extends Fragment {
    public static final String TAG = BaseGameFragment.class.getName();

    protected TextView mPlayerInTurn;
    protected TextView mMyScore;
    protected TextView mCountdownText;

    protected UIListener mUIListener;

    public static BaseGameFragment newInstance(String playerInTurn, String score) {
        Bundle args = new Bundle();
        args.putString(Constant.KEY_PLAYER_IN_TURN, playerInTurn);
        args.putString(Constant.KEY_CURRENT_SCORE, score);

        BaseGameFragment fragment = new BaseGameFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(getResourceLayout(), container, false);

        Bundle args = getArguments();
        mPlayerInTurn = (TextView) view.findViewById(R.id.player_in_turn);
        mMyScore = (TextView) view.findViewById(R.id.score_number);
        mCountdownText = (TextView) view.findViewById(R.id.count_down_timer);
        mPlayerInTurn.setText(args.getString(Constant.KEY_PLAYER_IN_TURN, ""));
        mMyScore.setText(args.getString(Constant.KEY_CURRENT_SCORE, "0"));

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

    public void startCountDown(int seconds) {
        CountDownTimer countDownTimer = new CountDownTimer(seconds*1000, 1000) {

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

    public int getResourceLayout() {
        return R.layout.fragment_base_game;
    }

}
