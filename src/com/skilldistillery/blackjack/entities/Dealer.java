package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Dealer extends Player {
	private Deck deck;
	private Player player;

	public Dealer() {
		super();
		this.deck = new Deck(); // Initialize the deck
		this.deck.shuffle(); // Shuffle the deck
	}
	
	public void setPlayer(Player player) {
        this.player = player;
    }


	public void dealInitialHands() {
		Card card1 = deck.dealCard();
		Card card2 = deck.dealCard();
		player.dealInitialHand(card1, card2);

		card1 = deck.dealCard();
		card2 = deck.dealCard();
		dealInitialHand(card1, card2);
	}

	public Card dealCard() {
		return deck.dealCard();
	}

	public void hit() {
		Card card = dealCard();
		super.hit(card);
		String playerType = this instanceof Dealer ? "Dealer" : "Player";

		System.out.println(playerType + "'s Card  " + card);

		if (isBust()) {
			System.out.println(playerType + " busts!");
		}
	}

//	@Override
//	public void displayHand() {
//		System.out.print("Dealer's Hand: ");
//		List<Card> cards = getHand().getCards();
//
//		for (int i = 0; i < cards.size(); i++) {
//			System.out.print(cards.get(i));
//			if (i < cards.size() - 1) {
//				System.out.print(", ");
//			}
//		}
//
//		System.out.println();
//	}
	
	public void displayDealerHand() {
        displayHand(); // Call the displayHand method from the Player class
    }

	public void play() {
		while (getHand().getHandValue() < 17) {
			hit(dealCard());
			displayHand();
		}

		if (!isBust()) {
			stand();
		}
	}

//    public void resetDeck() {
//        deck = new Deck();
//        deck.shuffle();
//    }
}