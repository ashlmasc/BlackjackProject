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
		if (this instanceof Dealer) {
			hand.addCard(card);
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

	public void displayHand() {
		List<Card> cards = hand.getCards();
		System.out.print("Player's Hand: ");

		for (int i = 0; i < cards.size(); i++) {
			System.out.print(cards.get(i));
			if (i < cards.size() - 1) {
				System.out.print(", ");
			}
		}

		System.out.println(" (" + hand.getHandValue() + ")"); // Display the hand's total value
	}

	public void playersTurn(Dealer dealer, Scanner scanner) {

		while (!isBust()) {
			System.out.println("Does the player want to Hit or Stand (H or S)? ");
			String choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("H")) {
				Card card = dealer.dealCard(this.getHand()); // Pass the player's hand as an argument
				hit(card); // hit method will add the card to player's hand
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