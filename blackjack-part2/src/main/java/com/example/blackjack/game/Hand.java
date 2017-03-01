package com.example.blackjack.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

  private final List<Card> cards = new ArrayList<>();

  public void deal(Card card) {
    cards.add(card);
  }

  public int getValue() {
    int val = cards.stream().filter(c -> !c.isAce()).mapToInt(c -> c.getPoints(0)).sum();
    List<Card> aces = cards.stream().filter(Card::isAce).collect(Collectors.toList());
    for (Card c : aces) {
      val += c.getPoints(val);
    }
    return val;
  }

  public List<Card> getCards() {
    return cards;
  }

}
