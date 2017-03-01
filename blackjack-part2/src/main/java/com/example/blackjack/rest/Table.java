package com.example.blackjack.rest;

import java.util.List;

public class Table {

  private List<String> myHand;
  private List<String> dealerHand;
  private List<String> options;
  private String message;
  private String title;

  public List<String> getMyHand() {
    return myHand;
  }

  public void setMyHand(List<String> myHand) {
    this.myHand = myHand;
  }

  public List<String> getDealerHand() {
    return dealerHand;
  }

  public void setDealerHand(List<String> dealerHand) {
    this.dealerHand = dealerHand;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
