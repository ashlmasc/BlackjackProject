package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	private List<Card> cards = new ArrayList<>();

	public Hand() {

	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	public abstract int getHandValue();

	public void clear() {
		cards.clear();
	}

	@Override
	public String toString() {
		return "Hand [cards=" + cards + "]";
	}
}
