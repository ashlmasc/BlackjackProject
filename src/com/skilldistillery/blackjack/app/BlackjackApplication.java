package com.skilldistillery.blackjack.app;

import java.util.Scanner;

import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.Card;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApplication {
    private Player player = new Player();
    private Dealer dealer = new Dealer();

    public static void main(String[] args) {
        BlackjackApplication app = new BlackjackApplication();
        app.run();
    }

    public void run() {
        // Deal initial cards
        dealer.resetFaceDown(); // Reset face-down status for the dealer
        dealInitialCards();

        // Display initial hands
        displayHands();

        // Player's turn
        takeTurn(player);

        // Check if player busted
        if (!player.isBust()) {
            // Dealer's turn only if the player hasn't busted
            dealer.play();
        }

        // Determine and display the winner
        determineAndDisplayWinner();
    }

    private void dealInitialCards() {
        // Deal the 1st card face-up to the player
        player.hit(dealer);

        // Deal the 1st card face-down to the dealer
        dealer.hitFaceDown();

        // Deal the 2nd card face-up to the player
        player.hit(dealer);

        // Deal the 2nd card face-up to the dealer
        dealer.hit();

        // Reset cardNumber for a new round
        player.resetCardNumber();
        dealer.resetCardNumber(); // Reset cardNumber for the dealer
    }

    private void displayHands() {
        System.out.println("Player's Hand: " + player.getHand().getCards());
        dealer.displayHand(); // Display dealer's hand with face-down logic
        System.out.println();
    }

    private void takeTurn(Player currentPlayer) {
        while (!currentPlayer.isBust()) {
            currentPlayer.play(dealer);
            displayHands();
        }
    }

    private void determineAndDisplayWinner() {
        int playerScore = player.getHand().getHandValue();
        int dealerScore = dealer.getHand().getHandValue();

        System.out.println("Player's Hand Value: " + playerScore);
        System.out.println("Dealer's Hand Value: " + dealerScore);

        if (playerScore > 21 && dealerScore > 21) {
            System.out.println("It's a tie! Both Player and Dealer bust.");
        } else if (playerScore > 21) {
            System.out.println("Dealer wins! Player busted.");
        } else if (dealerScore > 21) {
            System.out.println("Player wins! Dealer busted.");
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie!");
        } else if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
}