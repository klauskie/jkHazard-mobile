package com.klaus.jkhazard.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ApiMock {

    private static ApiMock mInstance;

    private Stack<Card> mDeckStack;
    private HashMap<Integer, Card> mTableCards; // TODO: change to list
    private ArrayList<Player> mPlayerList;

    private ApiMock() {
        initDeck();
        initPlayers(3);
    }

    public static ApiMock getInstance() {
        if (mInstance == null) {
            mInstance = new ApiMock();
        }
        return mInstance;
    }

    public void nextRound() {
        mTableCards = new HashMap<>();
        addCardToTable(mDeckStack.pop());
        addCardToTable(mDeckStack.pop());
    }

    public HashMap<Integer, Card> getTableCards() {
        return mTableCards;
    }

    public void addCardToTable(Card card) {
        if (mTableCards.size() > 2) {
            return;
        }
        mTableCards.put(mTableCards.size(), card);
    }

    public void feedCardToPlayer(Player player) {
        player.addCard(mDeckStack.pop());
    }

    public void removeCardFromPlayer(Player player, Card card) {
        player.removeCard(card);
    }

    public HashMap<Integer, Card> popNumCards(int n) {
        HashMap<Integer, Card> cardMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Card card = mDeckStack.pop();
            cardMap.put(card.getId(), card);
        }

        return cardMap;
    }

    public Player myPlayer() {
        return mPlayerList.get(0);
    }

    private void initDeck() {
        mDeckStack = new Stack<>();
        for(int i = 0; i < 50; i++) {
            mDeckStack.push(new Card(i, 0, true, false));
        }
    }

    private void initPlayers(int qty) {
        mPlayerList = new ArrayList<>();
        for (int i = 1; i <= qty; i++) {
            mPlayerList.add(new Player("player_" + i, popNumCards(7)));
        }
    }
}
