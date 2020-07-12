package com.klaus.jkhazard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.klaus.jkhazard.fragment.DeckGridFragment;
import com.klaus.jkhazard.fragment.SingleCardSelectionFragment;
import com.klaus.jkhazard.fragment.TopBarFragment;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DeckGridFragment.DeckListener {

    private static final String TAG = "MainActivity";

    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new TopBarFragment(), R.id.top_info_panel);
        loadFragment(new SingleCardSelectionFragment(), R.id.frag_single_card_selection);
        loadFragment(new DeckGridFragment(), R.id.frag_cards);
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
    }

    @Override
    public Card getSubmittedCard() {
        if (currentCard == null) {
            currentCard = new Card(-1, -1, true, false);
        }

        return currentCard;
    }

    protected void loadFragment(Fragment fragment, int layout) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(layout, fragment);
        ft.commit();
    }
}
