package com.klaus.jkhazard.common;

import com.klaus.jkhazard.model.Card;

import java.util.HashMap;

public interface DeckListener {
    HashMap<Integer, Card> getCardDeck();
    void submitCard(Card selectedCard);
    Card getSubmittedCard();
}
