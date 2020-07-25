package com.klaus.jkhazard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.DeckListener;
import com.klaus.jkhazard.fragment.SingleCardSelectionFragment;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    private static final String TAG = CardAdapter.class.getName();

    private HashMap<Integer, Card> mMyDeck;
    protected Context mContext;
    private DeckListener mDeckListener;

    public CardAdapter(Context context, DeckListener deckListener) {
        mMyDeck = deckListener.getCardDeck();
        mContext = context;
        mDeckListener = deckListener;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_card, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        if (mMyDeck.containsKey(position)) {
            holder.bind(mMyDeck.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mMyDeck.size();
    }

    /* CardHolder Class */
    public class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "com.klaus.jkhazard.adapter.CardHolder";

        ImageView mCardImageView;

        CardHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mCardImageView = (ImageView) itemView.findViewById(R.id.card_image);
        }

        void bind(Card card) {
            mCardImageView.setImageResource(card.getImageResourceID());

            if (mDeckListener.getSubmittedCard().getId() == card.getId()) {
                mCardImageView.setBackgroundColor(Color.rgb(0, 204, 0));
            } else {
                mCardImageView.setBackgroundColor(Color.rgb(255, 255, 255));
            }
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick!");

            final int position = this.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (mMyDeck.containsKey(position)) {
                    mDeckListener.submitCard(mMyDeck.get(position));
                    notifyDataSetChanged();
                }
            }

        }
    }

}
