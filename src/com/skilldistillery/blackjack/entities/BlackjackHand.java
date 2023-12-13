package com.skilldistillery.blackjack.entities;

import java.util.List;

public class BlackjackHand extends Hand {

	// public int getHandValue;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<Card> cards = getCards();

		for (int i = 0; i < cards.size(); i++) {
			sb.append(cards.get(i));

			if (i < cards.size() - 1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}
}
