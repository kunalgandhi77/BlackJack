package com.example.blackjack.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards = new ArrayList<>();

    public Deck() {
        for (char suit : Card.suits) {
            for (char value : Card.values) {
                cards.add(new Card(value, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        } else {
            return cards.remove(0);
        }
    }
}
