package com.example.blackjack.game;

public class Card {

    public static final char[] suits = {'C', 'D', 'S', 'H'};
    public static final char[] values = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K'};

    private final char value;
    private final char suit;
    private final int points;
    private final boolean ace;

    public Card(char value, char suit) {
        this.value = value;
        this.suit = suit;
        ace = value == 'A';
        switch (value) {
            case 'A':
                points = 11;
                break;
            case 'J':
            case 'Q':
            case 'K':
            case 'X':
                points = 10;
                break;
            default:
                points = Character.getNumericValue(value);
        }
    }

    public int getPoints(int handValue) {
        if (ace) {
            return (handValue < 11) ? 11 : 1;
        } else {
            return points;
        }
    }

    public boolean isAce() {
        return ace;
    }
    
    

    @Override
    public String toString() {
        return "" + value + suit;
    }

}
