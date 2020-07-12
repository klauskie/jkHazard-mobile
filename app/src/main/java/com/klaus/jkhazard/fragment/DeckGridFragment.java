package com.klaus.jkhazard.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.adapter.CardAdapter;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;
import java.util.HashSet;

public class DeckGridFragment extends Fragment {
    private static final String TAG = "CardListFragment";

    public interface DeckListener {
        HashMap<Integer, Card> getMyDeck();
        void submitCard(Card selectedCard);
        Card getSubmittedCard();
    }

    private RecyclerView mCardRecyclerView;
    private CardAdapter mAdapter;

    private DeckListener mDeckListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deck_grid, container, false);

        Log.i(TAG, "CreateView called!");

        mCardRecyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        mCardRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //mCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new CardAdapter(getContext(), mDeckListener);
        mCardRecyclerView.setAdapter(mAdapter);

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
}
