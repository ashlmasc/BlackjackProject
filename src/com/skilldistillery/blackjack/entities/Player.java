package com.skilldistillery.blackjack.entities;

import java.util.Scanner;

public class Player {
	protected Hand hand;
	private int cardNumber = 1;

	public Player() {
		this.hand = new BlackjackHand();
	}

	public Hand getHand() {
		return hand;
	}

	public void hit(Dealer dealer) {
		Card card = dealer.dealCard();
		hand.addCard(card);
		System.out.println("Player's Card " + cardNumber + ": " + card);
		if (((BlackjackHand) hand).isBust()) {
			System.out.println("Player busts!");
		}
		// Increment cardNumber after adding the card to the hand for the correct card
		// number
		cardNumber++;
	}

	public void stand() {
		System.out.println("Player stands.");
	}

	public boolean isBust() {
		return ((BlackjackHand) hand).isBust();
	}

	public void displayHand() {
		System.out.println("Player's Hand: " + hand.getCards());
	}

	@Override
	public String toString() {
		return "Player hand: " + hand;
	}

	public void resetCardNumber() {
		cardNumber = 1; // Reset cardNumber to 1 for a new round
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void play(Dealer dealer) {
		Scanner userInput = new Scanner(System.in);

		while (!isBust()) {
			System.out.println("Does player want to Hit or Stand (H or S)?");
			String choice = userInput.nextLine();

			if (choice.equalsIgnoreCase("H")) {
				hit(dealer);
				displayHand();
			} else if (choice.equalsIgnoreCase("S")) {
				stand();
				break;
			} else {
				System.out.println("Invalid choice. Please enter 'H' or 'S'.");
			}
		}
	}
}