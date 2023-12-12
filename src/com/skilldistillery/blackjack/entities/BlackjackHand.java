package com.skilldistillery.blackjack.entities;

public class BlackjackHand extends Hand {

	public int getHandValue;

	public boolean isBlackjack() {
		return getHandValue() == 21;
	}

	public boolean isBust() {
		return getHandValue() > 21;
	}
   

	@Override
	public int getHandValue() {
	    int handValue = 0;

	    for (Card card : getCards()) {
	        handValue += card.getValue();
	    }

	    return handValue;
	}
}
