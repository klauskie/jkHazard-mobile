package com.klaus.jkhazard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.DeckListener;
import com.klaus.jkhazard.common.StateGame;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.fragment.BaseGameFragment;
import com.klaus.jkhazard.fragment.JudgeInputCardsFragment;
import com.klaus.jkhazard.fragment.SingleCardSelectionFragment;
import com.klaus.jkhazard.model.ApiMock;
import com.klaus.jkhazard.model.Card;
import com.klaus.jkhazard.model.Player;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity implements DeckListener, SingleCardSelectionFragment.TableSetDeckListener, UIListener {
    private static final String TAG = GameActivity.class.getName();

    BaseGameFragment mCurrentFragment;
    Player mPlayer;

    HashMap<Integer, Card> mTableDeck;
    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ApiMock.getInstance().nextRound();

        mPlayer = ApiMock.getInstance().myPlayer();
        mTableDeck = ApiMock.getInstance().getTableCards();
        mPlayer.setStateGame(StateGame.SELECT);

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
        switch (mPlayer.getStateGame()) {
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
        return mPlayer.getDeck();
    }

    @Override
    public void submitCard(Card selectedCard) {
        Log.d(TAG, "card submitted: " + selectedCard.getId());
        currentCard = selectedCard;
        if (mPlayer.getStateGame() == StateGame.SELECT) {
            ((SingleCardSelectionFragment)mCurrentFragment).paintEditableCard(selectedCard);
        }
    }

    @Override
    public Card getSubmittedCard() {
        return currentCard;
    }

    @Override
    public void onDoneClicked() {

        ApiMock.getInstance().removeCardFromPlayer(mPlayer, currentCard);
        ApiMock.getInstance().feedCardToPlayer(mPlayer);
        currentCard = null;

        switch (mPlayer.getStateGame()) {
            case SELECT:
                mPlayer.setStateGame(StateGame.JUDGE);
                loadFragment(JudgeInputCardsFragment.newInstance("BlueDude", mPlayer.getScoreString()));
                break;
        }
    }

    @Override
    public void onNextClicked() {

    }

    private BaseGameFragment getFragmentByState() {
        switch (mPlayer.getStateGame()) {
            case SELECT:
                return SingleCardSelectionFragment.newInstance("BlueDog", mPlayer.getScoreString());
            case JUDGE:
                return JudgeInputCardsFragment.newInstance("BlueDog", mPlayer.getScoreString());
        }
        return null;
    }
}
