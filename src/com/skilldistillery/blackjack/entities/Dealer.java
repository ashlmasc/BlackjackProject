package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Dealer extends Player {
	private Deck deck;
	private boolean isFirstCardFaceDown = true;

	public Dealer() {
		super();
		this.deck = new Deck(); // Initialize the deck
		this.deck.shuffle(); // Shuffle the deck
		this.hand = new BlackjackHand();
	}

	public Card dealCard(Hand hand) {
		Card card = deck.dealCard();
		hand.addCard(card);
		return card;
	}

	public void setFirstCardFaceDown(boolean isFirstCardFaceDown) {
		this.isFirstCardFaceDown = isFirstCardFaceDown;
	}

	@Override
	public void displayHand() {
		List<Card> cards = hand.getCards();
		if (isFirstCardFaceDown) {
			System.out.print("Dealer's Hand: Face Down, ");
			for (int i = 1; i < cards.size(); i++) {
				System.out.print(cards.get(i));
				if (i < cards.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
			isFirstCardFaceDown = false; // Update the flag after the first display
		} else {
			// Display all cards including the first one
			System.out.print("Dealer's Hand: ");
			for (Card card : cards) {
				System.out.print(card);
				if (!card.equals(cards.get(cards.size() - 1))) {
					System.out.print(", ");
				}
			}
			System.out.println(" (" + hand.getHandValue() + ")");
		}
	}

	public void dealersTurn() {
		isFirstCardFaceDown = false;
		while (getHand().getHandValue() < 17) {
			Card newCard = deck.dealCard();
			System.out.println("Dealer hits.");
			hit(newCard); // This adds the new card to the dealer's hand and should update hand value
			displayHand(); // Display the updated hand with the new card
		}

		if (isBust()) {
			System.out.println("Dealer busts!");
		} else {
			System.out.println("Dealer stands.");
		}
	}

	public void dealInitialHands(Player player, Dealer dealer) {
		dealCard(player.getHand());
		dealCard(dealer.getHand());
		dealCard(player.getHand());
		dealCard(dealer.getHand());
	}
}