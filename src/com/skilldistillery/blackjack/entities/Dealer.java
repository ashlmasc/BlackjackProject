package com.skilldistillery.blackjack.entities;

public class Dealer extends Player {
	private Deck deck;

	public Dealer() {
		super();
		this.deck = new Deck(); // Initialize the deck
		this.deck.shuffle(); // Shuffle the deck
		this.hand = new BlackjackHand();
	}

	public Card dealCard(Hand hand) {
		Card card = deck.dealCard();
		hand.addCard(card);

		// *********************Debug*****************
		System.out.println("[DEBUG] Dealing " + card + " to " + (hand instanceof BlackjackHand ? "Player" : "Dealer"));

		return card;
	}

	public void faceDown() {
		System.out.println("Dealer's Hand: Face down card, " + hand.getCards().get(0));
	}

	public void dealersTurn() {
		// Reveal hidden card
		// *********************Debug*****************
		System.out.println("[DEBUG] Dealer reveals hand: " + getHand());

		// Dealer hits while hand value is less than 17
		while (getHand().getHandValue() < 17) {
			Card newCard = deck.dealCard();
			hit(newCard);
			System.out.println("Dealer hits: " + newCard);
			displayHand(); // Display updated hand
		}

		// Check for bust
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