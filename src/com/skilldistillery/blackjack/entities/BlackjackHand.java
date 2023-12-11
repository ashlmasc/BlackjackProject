package com.skilldistillery.blackjack.entities;

public class BlackjackHand extends Hand {

    // Existing methods...

    @Override
    public int getHandValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : getCards()) {
            value += card.getValue();

            if (card.getValue() == 11) { // Check for Ace
                numAces++;
            }
        }

        // Adjust the value of Aces to prevent busting
        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    public boolean isBlackjack() {
        return getHandValue() == 21 && getCards().size() == 2;
    }

    public boolean isBust() {
        return getHandValue() > 21;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : getCards()) {
            sb.append(card).append(", ");
        }
        // Remove the trailing comma and space if there are cards
        if (!getCards().isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}