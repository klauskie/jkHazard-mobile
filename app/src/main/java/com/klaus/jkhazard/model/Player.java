package com.klaus.jkhazard.model;


import com.klaus.jkhazard.common.StateGame;

import java.util.HashMap;

public class Player {
    String mUsername;
    String mPassword;
    int mScore;
    HashMap<Integer, Card> mDeck;
    StateGame mStateGame;

    public Player(String username, HashMap<Integer, Card> deck) {
        mUsername = username;
        mPassword = "";
        mScore = 0;
        mDeck = deck;
        mStateGame = StateGame.BLOCKED;
    }


    public String getUsername() {
        return mUsername;
    }

    public HashMap<Integer, Card> getDeck() {
        return mDeck;
    }

    public void setDeck(HashMap<Integer, Card> deck) {
        mDeck = deck;
    }

    public void addCard(Card card) {
        mDeck.put(card.getId(), card);
    }

    public void removeCard(Card card) {
        mDeck.remove(card.getId());
    }

    public int getScore() {
        return mScore;
    }

    public String getScoreString() {
        return mScore + "";
    }

    public void incrementScore(int points) {
        mScore += points;
    }

    public StateGame getStateGame() {
        return mStateGame;
    }

    public void setStateGame(StateGame state) {
        mStateGame = state;
    }
}
