package com.klaus.jkhazard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.DeckListener;


public class JudgeCardAdapter extends CardAdapter {

    private static final String TAG = "JudgeCardAdapter";

    public JudgeCardAdapter(Context context, DeckListener deckListener) {
        super(context, deckListener);
    }

    @NonNull
    @Override
    public CardAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_card_large, parent, false);
        return new CardAdapter.CardHolder(view);
    }
}
