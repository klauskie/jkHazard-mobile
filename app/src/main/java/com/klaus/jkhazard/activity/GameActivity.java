package com.klaus.jkhazard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.DeckListener;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.fragment.BaseGameFragment;
import com.klaus.jkhazard.fragment.JudgeInputCardsFragment;
import com.klaus.jkhazard.fragment.SingleCardSelectionFragment;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity implements DeckListener, SingleCardSelectionFragment.TableSetDeckListener, UIListener {
    private static final String TAG = GameActivity.class.getName();

    private enum StateGame {
        JUDGE,
        JUDGE_WAIT,
        SELECT,
        SELECT_WAIT
    }

    BaseGameFragment mCurrentFragment;
    StateGame mStateGame;

    HashMap<Integer, Card> mTableDeck;
    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        mTableDeck = getTableDeck();
        mStateGame = StateGame.SELECT;

        loadFragment(getFragmentByState());

        mCurrentFragment.startCountDown(60);
    }

    @Override
    public void onBackPressed() {
        // TODO: finish and go to Home. A dialog should popup warning about leaving the game.
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments > 0) {
            finish();
            return;
        }

        super.onBackPressed();
    }

    public void loadFragment(BaseGameFragment fragment) {
        switch (mStateGame) {
            case SELECT:
            case SELECT_WAIT:
                mCurrentFragment = (SingleCardSelectionFragment) fragment;
                break;
            case JUDGE:
            case JUDGE_WAIT:
                mCurrentFragment = (JudgeInputCardsFragment) fragment;
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_content_frame, mCurrentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
    public HashMap<Integer, Card> getCardDeck() {
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
        if (mStateGame == StateGame.SELECT) {
            ((SingleCardSelectionFragment)mCurrentFragment).paintEditableCard(selectedCard);
        }
    }

    @Override
    public Card getSubmittedCard() {
        if (currentCard == null) {
            currentCard = new Card(-1, -1, true, false);
        }

        return currentCard;
    }

    @Override
    public void onDoneClicked() {
        switch (mStateGame) {
            case SELECT:
                mStateGame = StateGame.JUDGE;
                loadFragment(JudgeInputCardsFragment.newInstance("BlueDude", "2"));
                break;
        }
    }

    private BaseGameFragment getFragmentByState() {
        switch (mStateGame) {
            case SELECT:
                return SingleCardSelectionFragment.newInstance("BlueDog", "2");
            case JUDGE:
                return JudgeInputCardsFragment.newInstance("BlueDog", "3");
        }
        return null;
    }
}
