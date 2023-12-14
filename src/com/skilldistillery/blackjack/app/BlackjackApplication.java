package com.skilldistillery.blackjack.app;

import java.util.Scanner;

import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApplication {
	private Player player = new Player();
	private Dealer dealer = new Dealer();
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		BlackjackApplication app = new BlackjackApplication();
		app.run();
	}

	public void run() {
		System.out.println("Welcome to Blackjack!\n");

		// Deal initial hands
		dealer.dealInitialHands(player, dealer);

		// Display initial cards
		System.out.println("Player's Hand: " + player.getHand() + " (" + player.getHand().getHandValue() + ")");
		dealer.displayHand(); // This will show first card face down initially

		// Start player's turn
	    player.playersTurn(dealer, scanner);

	    // Reveal dealer's full hand
	    dealer.setFirstCardFaceDown(false); // Ensure first card is no longer face down
	    System.out.println("Dealer's Hand: " + dealer.getHand());
	    
	    // Dealer's turn
	    dealer.dealersTurn();

		// determine and display winner
		determineAndDisplayWinner();

	}

	private void determineAndDisplayWinner() {
		int playerValue = player.getHand().getHandValue();
		int dealerValue = dealer.getHand().getHandValue();

		if (playerValue > 21) {
			System.out.println("Player busts! Dealer wins!");
		} else if (dealerValue > 21) {
			System.out.println("Dealer busts! Player wins!");
		} else if (playerValue > dealerValue) {
			System.out.println("Player wins!");
		} else if (dealerValue > playerValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}
