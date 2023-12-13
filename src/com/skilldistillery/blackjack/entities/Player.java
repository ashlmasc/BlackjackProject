package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.Scanner;

public class Player {
	protected Hand hand;

	public Player() {
		this.hand = new BlackjackHand();
	}

	public Hand getHand() {
		return hand;
	}

	public void hit(Card card) {
		System.out.println("[DEBUG] hit method called with: " + card);
		hand.addCard(card); // Directly add card to hand

		// **************************Debug****************
		System.out.println("[DEBUG] Player hits: " + card);

		if (this instanceof Dealer) {
			System.out.println("Dealer's Card: " + card);
		} else {
			System.out.println("Player's Card: " + card);
		}
	}

	public void stand() {
		System.out.println((this instanceof Dealer) ? "Dealer stands." : "Player stands.");
	}

	public boolean isBust() {
		return hand.getHandValue() > 21;
	}

	private void checkBust() {
		if (((BlackjackHand) hand).isBust()) {
			System.out.println((this instanceof Dealer) ? "Dealer" : "Player" + " busts!");
		}
	}

//	public void displayHand() {
//		List<Card> cards = hand.getCards();
//		System.out.print((this instanceof Dealer) ? "Dealer's Hand: " : "Player's Hand: ");
//
//		for (int i = 0; i < cards.size(); i++) {
//			if (this instanceof Dealer && i == 1) {
//				System.out.print("Face Down");
//			} else {
//				System.out.print(cards.get(i));
//			}
//
//			if (i < cards.size() - 1) {
//				System.out.print(", ");
//			}
//		}
//
//		if (!(this instanceof Dealer)) {
//			System.out.println(" (" + hand.getHandValue() + ")"); // Display the hand's total value for the player
//		} else {
//			System.out.println();
//		}
//	}

	// ***************DEBUG METHOD*************************
	public void displayHand() {
		List<Card> cards = hand.getCards();
		System.out.print((this instanceof Dealer) ? "Dealer's Hand: " : "Player's Hand: ");

		for (Card card : cards) {
			System.out.print(card + ", ");
		}

		// debug: Print the entire hand and its total value
		System.out.println();
		System.out.println("[DEBUG] Current hand: " + cards + " | Hand value: " + hand.getHandValue());
	}

	public void playersTurn(Dealer dealer, Scanner scanner) {

		while (!isBust()) {
			System.out.println("Does the player want to Hit or Stand (H or S)?");
			String choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("H")) {
				Card card = dealer.dealCard(hand);
				hit(card);
				displayHand(); // Display hand after hitting
				if (isBust()) {
					System.out.println("Player busts!"); // Display bust message after updating hand
					break;
				}
			} else if (choice.equalsIgnoreCase("S")) {
				stand();
				break;
			} else {
				System.out.println("Invalid choice. Please enter 'H' or 'S'.");
			}
		}
	}
}