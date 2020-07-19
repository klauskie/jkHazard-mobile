package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.adapter.CardAdapter;
import com.klaus.jkhazard.adapter.JudgeCardAdapter;
import com.klaus.jkhazard.common.DeckListener;


public class JudgeInputCardsFragment extends Fragment {

    public static final String TAG = JudgeInputCardsFragment.class.getName();

    private ImageView mImageCardOne;
    private ImageView mImageCardTwo;
    private RecyclerView mCardRecyclerView;
    private JudgeCardAdapter mAdapter;

    private DeckListener mDeckListener;

    public static JudgeInputCardsFragment newInstance() {
        return new JudgeInputCardsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_judge_input_cards, container, false);

        mImageCardOne = (ImageView) view.findViewById(R.id.card_one);
        mImageCardTwo = (ImageView) view.findViewById(R.id.card_two);

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

}
