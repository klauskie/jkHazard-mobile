package com.klaus.jkhazard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.klaus.jkhazard.fragment.DeckGridFragment;
import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DeckGridFragment.DeckListener {
    private static final String TAG = "MainActivity";

    Button mToggleDeckBtn;
    DrawerLayout mDrawerLayout;

    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawerDeck);
        mToggleDeckBtn = (Button) this.findViewById(R.id.smallSideBtn);

        mToggleDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                    mDrawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frag_cards, new DeckGridFragment());
        ft.commit();
    }

    @Override
    public HashMap<Integer, Card> getMyDeck() {
        HashMap<Integer, Card> tempDeck = new HashMap<>();
        for(int i = 0; i < 9; i++) {
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
}
