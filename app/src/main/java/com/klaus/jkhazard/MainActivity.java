package com.klaus.jkhazard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.klaus.jkhazard.fragment.DeckGridFragment;
import com.klaus.jkhazard.fragment.SingleCardSelectionFragment;
import com.klaus.jkhazard.fragment.TopBarFragment;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DeckGridFragment.DeckListener, SingleCardSelectionFragment.TableSetDeckListener {

    private static final String TAG = MainActivity.class.getName();

    TopBarFragment mTopBarFragment;
    SingleCardSelectionFragment mSingleCardSelectionFragment;
    DeckGridFragment mDeckGridFragment;

    HashMap<Integer, Card> mTableDeck;
    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTableDeck = getTableDeck();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mTopBarFragment = (TopBarFragment) fragmentManager.findFragmentByTag(TopBarFragment.TAG);
        if (mTopBarFragment == null) {
            mTopBarFragment = TopBarFragment.newInstance("BlueDude", "2");
            fragmentManager.beginTransaction().add(R.id.top_info_panel, mTopBarFragment, TopBarFragment.TAG).commit();
        }

        mSingleCardSelectionFragment = (SingleCardSelectionFragment) fragmentManager.findFragmentByTag(SingleCardSelectionFragment.TAG);
        if (mSingleCardSelectionFragment == null) {
            mSingleCardSelectionFragment = SingleCardSelectionFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.frag_single_card_selection, mSingleCardSelectionFragment, SingleCardSelectionFragment.TAG).commit();
        }

        mDeckGridFragment = (DeckGridFragment) fragmentManager.findFragmentByTag(DeckGridFragment.TAG);
        if (mDeckGridFragment == null) {
            mDeckGridFragment = DeckGridFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.frag_cards, mDeckGridFragment, DeckGridFragment.TAG).commit();
        }

        // Setup
        mTopBarFragment.startCountDown(60);
    }

    public HashMap<Integer, Card> getTableDeck() {
        HashMap<Integer, Card> tempDeck = new HashMap<>();
        for(int i = 0; i < 2; i++) {
            tempDeck.put(i, new Card(i, R.color.colorPrimaryDark, true, false));
        }
        return tempDeck;
    }

    @Override
    public Card getFirstTableCard() {
        return mTableDeck.get(0);
    }

    @Override
    public Card getSecondTableCard() {
        return mTableDeck.get(1);
    }

    @Override
    public HashMap<Integer, Card> getMyDeck() {
        HashMap<Integer, Card> tempDeck = new HashMap<>();
        for(int i = 0; i < 7; i++) {
            tempDeck.put(i, new Card(i, R.drawable.jhimg1, true, false));
        }
        return tempDeck;
    }

    @Override
    public void submitCard(Card selectedCard) {
        Log.d(TAG, "card submitted: " + selectedCard.getId());
        currentCard = selectedCard;
        mSingleCardSelectionFragment.paintEditableCard(selectedCard);
    }

    @Override
    public Card getSubmittedCard() {
        if (currentCard == null) {
            currentCard = new Card(-1, -1, true, false);
        }

        return currentCard;
    }
}
