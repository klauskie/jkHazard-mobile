package com.klaus.jkhazard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.DeckListener;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.fragment.JudgeInputCardsFragment;
import com.klaus.jkhazard.fragment.PreviewSetFragment;
import com.klaus.jkhazard.fragment.TopBarFragment;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public class JudgeActivity extends AppCompatActivity implements DeckListener, UIListener {

    private static final String TAG = JudgeActivity.class.getName();

    TopBarFragment mTopBarFragment;
    JudgeInputCardsFragment mJudgeCardsFragment;
    PreviewSetFragment mPreviewSetFragment;
    Card mSelectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        FragmentManager fragmentManager = getSupportFragmentManager();

        mTopBarFragment = (TopBarFragment) fragmentManager.findFragmentByTag(TopBarFragment.TAG);
        if (mTopBarFragment == null) {
            mTopBarFragment = TopBarFragment.newInstance("BlueDude", "2");
            fragmentManager.beginTransaction().add(R.id.top_info_panel, mTopBarFragment, TopBarFragment.TAG).commit();
        }

        mJudgeCardsFragment = (JudgeInputCardsFragment) fragmentManager.findFragmentByTag(JudgeInputCardsFragment.TAG);
        if (mJudgeCardsFragment == null) {
            mJudgeCardsFragment = JudgeInputCardsFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.frag_judge_card_input, mJudgeCardsFragment, JudgeInputCardsFragment.TAG).commit();
        }

        mPreviewSetFragment = (PreviewSetFragment) fragmentManager.findFragmentByTag(PreviewSetFragment.TAG);
        if (mPreviewSetFragment == null) {
            mPreviewSetFragment = PreviewSetFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.frag_preview, mPreviewSetFragment, PreviewSetFragment.TAG).commit();
        }
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
        mSelectedCard = selectedCard;
    }

    @Override
    public Card getSubmittedCard() {
        if (mSelectedCard == null) {
            mSelectedCard = new Card(-1, -1, true, false);
        }

        return mSelectedCard;
    }

    @Override
    public void onDoneClicked() {
        Toast.makeText(this, "Done Clicked!", Toast.LENGTH_SHORT).show();
    }
}
