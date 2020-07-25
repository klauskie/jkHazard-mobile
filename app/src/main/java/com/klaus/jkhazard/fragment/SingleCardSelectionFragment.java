package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.adapter.CardAdapter;
import com.klaus.jkhazard.common.Constant;
import com.klaus.jkhazard.common.DeckListener;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.model.Card;


public class SingleCardSelectionFragment extends BaseGameFragment {
    public static final String TAG = SingleCardSelectionFragment.class.getName();

    public interface TableSetDeckListener {
        Card getFirstTableCard();
        Card getSecondTableCard();
    }

    private ImageView mImageCardOne;
    private ImageView mImageCardTwo;
    private ImageView mImageCardEditable;
    private Button mDoneButton;
    private RecyclerView mCardRecyclerView;
    private CardAdapter mAdapter;

    private DeckListener mDeckListener;
    private TableSetDeckListener mTableDeckListener;

    public static SingleCardSelectionFragment newInstance(String playerInTurn, String score) {
        Bundle args = new Bundle();
        args.putString(Constant.KEY_PLAYER_IN_TURN, playerInTurn);
        args.putString(Constant.KEY_CURRENT_SCORE, score);

        SingleCardSelectionFragment fragment = new SingleCardSelectionFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mImageCardOne = (ImageView) view.findViewById(R.id.card_one);
        mImageCardTwo = (ImageView) view.findViewById(R.id.card_two);
        mImageCardEditable = (ImageView) view.findViewById(R.id.card_three);
        mDoneButton = (Button) view.findViewById(R.id.done_button);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: move to next Activity and commit state
                mUIListener.onDoneClicked();
            }
        });

        mCardRecyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        mCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new CardAdapter(getContext(), mDeckListener);
        mCardRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        paintFirstCard(mTableDeckListener.getFirstTableCard());
        paintSecondCard(mTableDeckListener.getSecondTableCard());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mTableDeckListener = (TableSetDeckListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement TableSetDeckListener");
        }

        try {
            mDeckListener = (DeckListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement DeckListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTableDeckListener = null;
        mDeckListener = null;
    }

    public void paintFirstCard(Card card) {
        //mImageCardOne.setImageResource(card.getImageResourceID());
        mImageCardOne.setBackgroundColor(card.getImageResourceID());
    }

    public void paintSecondCard(Card card) {
        //mImageCardTwo.setImageResource(card.getImageResourceID());
        mImageCardTwo.setBackgroundColor(card.getImageResourceID());
    }

    public void paintEditableCard(Card card) {
        //mImageCardEditable.setImageResource(card.getImageResourceID());
        mImageCardEditable.setBackgroundColor(card.getImageResourceID());
    }

    public int getResourceLayout() {
        return R.layout.fragment_single_card_selection;
    }
}
