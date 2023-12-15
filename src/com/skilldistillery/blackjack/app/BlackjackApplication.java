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
		boolean keepPlaying = true;
		System.out.println("Welcome to Blackjack!\n");

		while (keepPlaying) {
			// Reset hands for a new game
			player.getHand().clear();
			dealer.getHand().clear();

			// Reset the dealer's isFirstCardFaceDown flag
			dealer.setFirstCardFaceDown(true);

			// Deal initial hands
			dealer.dealInitialHands(player, dealer);

			// Display initial cards
			System.out.println("Player's Hand: " + player.getHand() + " (" + player.getHand().getHandValue() + ")");
			dealer.displayHand(); // This will show first card face down initially

			// Start player's turn
			player.playersTurn(dealer, scanner);
			System.out.println();

			// Reveal dealer's full hand
			dealer.setFirstCardFaceDown(false); // Ensure first card is no longer face down
			System.out.println("Dealer's Hand: " + dealer.getHand() + " (" + dealer.getHand().getHandValue() + ")");

			// Dealer's turn
			dealer.dealersTurn();

			// determine and display winner
			determineAndDisplayWinner();

			// Ask the player if they want to play again
			keepPlaying = askToPlayAgain();
		}
		System.out.println("Thanks for playing Blackjack!");
	}

	private void determineAndDisplayWinner() {
		int playerValue = player.getHand().getHandValue();
		int dealerValue = dealer.getHand().getHandValue();

		if (playerValue > 21) {
			System.out.println("Dealer wins!");
		} else if (dealerValue > 21) {
			System.out.println("Player wins!");
		} else if (playerValue > dealerValue) {
			System.out.println("Player wins!");
		} else if (dealerValue > playerValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private boolean askToPlayAgain() {
		System.out.println("\nDo you want to play again? 'y' or 'n': ");
		String response = scanner.nextLine();
		return response.equalsIgnoreCase("y");
	}
}
