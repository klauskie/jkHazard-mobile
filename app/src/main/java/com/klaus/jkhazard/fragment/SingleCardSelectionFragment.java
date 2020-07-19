package com.klaus.jkhazard.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.model.Card;

public class SingleCardSelectionFragment extends Fragment {

    public static final String TAG = SingleCardSelectionFragment.class.getName();

    public interface TableSetDeckListener {
        Card getFirstTableCard();
        Card getSecondTableCard();
    }

    private ImageView mImageCardOne;
    private ImageView mImageCardTwo;
    private ImageView mImageCardEditable;
    private Button mDoneButton;

    private TableSetDeckListener mTableDeckListener;
    private UIListener mUIListener;

    public static SingleCardSelectionFragment newInstance() {
        return new SingleCardSelectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_single_card_selection, container, false);

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
            mUIListener = (UIListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Owner must implement UIListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTableDeckListener = null;
        mUIListener = null;
    }

    public void paintFirstCard(Card card) {
        mImageCardOne.setImageResource(card.getImageResourceID());
    }

    public void paintSecondCard(Card card) {
        mImageCardTwo.setImageResource(card.getImageResourceID());
    }

    public void paintEditableCard(Card card) {
        mImageCardEditable.setImageResource(card.getImageResourceID());
    }
}
