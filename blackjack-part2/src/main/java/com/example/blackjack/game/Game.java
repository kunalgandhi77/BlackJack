package com.example.blackjack.game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

  private boolean status = true;
  private Hand player;
  private Hand dealer;
  private Deck deck;

  public Game() {
    player = new Hand();
    dealer = new Hand();
    deck = new Deck();
    player.deal(deck.draw());
    dealer.deal(deck.draw());
    player.deal(deck.draw());
    dealer.deal(deck.draw());
  }

  public void stand() {
    if (status) {
      while (dealer.getValue() < 17) {
        dealer.deal(deck.draw());
      }
      status = false;
    }
  }

  public void hit() {
    if (status) {
      player.deal(deck.draw());
      if (player.getValue() > 21) {
        status = false;
      } else {
        if (dealer.getValue() < 17) {
          dealer.deal(deck.draw());
        }
      }
    }
  }

  public String getWinner() {
    if (status) {
      return null;
    } else {
      if (player.getValue() > 21) {
        return "Dealer";
      }
      if (dealer.getValue() > 21) {
        return "Player";
      }
      if (player.getValue() > dealer.getValue()) {
        return "Player";
      } else {
        return "Dealer";
      }
    }
  }

  public List<String> getPlayerHand() {
    return player.getCards().stream().map(Card::toString).collect(Collectors.toList());
  }

  public List<String> getDealerHand() {
    final List<String> cards = dealer.getCards().stream().map(Card::toString).collect(Collectors.toList());
    if (status) {
      cards.set(0, "??");
    }
    return cards;
  }

  public boolean getStatus() {
    return status;
  }

  public List<String> getOptions() {
    if (status) {
      return Arrays.asList(new String[]{"hit", "stand"});
    }else{
      return Arrays.asList(new String[]{"newgame"});
    }
  }

}
