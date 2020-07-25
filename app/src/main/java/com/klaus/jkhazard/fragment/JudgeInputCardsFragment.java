package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.adapter.JudgeCardAdapter;
import com.klaus.jkhazard.common.Constant;
import com.klaus.jkhazard.common.DeckListener;


public class JudgeInputCardsFragment extends BaseGameFragment {
    public static final String TAG = JudgeInputCardsFragment.class.getName();

    private ImageView mImageCardOne;
    private ImageView mImageCardTwo;
    private RecyclerView mCardRecyclerView;
    private JudgeCardAdapter mAdapter;
    private Button mDoneButton;

    private DeckListener mDeckListener;

    public static JudgeInputCardsFragment newInstance(String playerInTurn, String score) {
        Bundle args = new Bundle();
        args.putString(Constant.KEY_PLAYER_IN_TURN, playerInTurn);
        args.putString(Constant.KEY_CURRENT_SCORE, score);

        JudgeInputCardsFragment fragment = new JudgeInputCardsFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mDoneButton = (Button) view.findViewById(R.id.done_button);
        mImageCardOne = (ImageView) view.findViewById(R.id.card_one);
        mImageCardTwo = (ImageView) view.findViewById(R.id.card_two);
        mImageCardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchCards();
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUIListener.onDoneClicked();
            }
        });

        mCardRecyclerView = (RecyclerView) view.findViewById(R.id.card_vertical_recycler_view);
        mCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new JudgeCardAdapter(getContext(), mDeckListener);
        mCardRecyclerView.setAdapter(mAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mCardRecyclerView);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mDeckListener = (DeckListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement DeckListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDeckListener = null;
    }

    private void switchCards() {
        Drawable tempDrawableForCardOne = mImageCardOne.getDrawable();
        mImageCardOne.setImageDrawable(mImageCardTwo.getDrawable());
        mImageCardTwo.setImageDrawable(tempDrawableForCardOne);
    }

    public int getResourceLayout() {
        return R.layout.fragment_judge_input_cards;
    }

}
