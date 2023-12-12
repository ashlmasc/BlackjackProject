package com.skilldistillery.blackjack.app;

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
        
        dealInitialCards();

        // Display initial hands
        displayHands();

        // Player's turn
        takeTurn(player);

        // Check if player busted
        if (!player.isBust()) {
            // Dealer's turn only if the player hasn't busted
            dealer.play(dealer);
        }

        // Determine and display the winner
        determineAndDisplayWinner();
    }

    private void dealInitialCards() {
        // Set the player for the dealer
        dealer.setPlayer(player);

        // Deal two cards to both player and dealer
        dealer.dealInitialHands();
    }


    private void displayHands() {
        System.out.println("Player's Hand: " + player.getHand().getCards());
        dealer.displayHand(); 
        System.out.println();
    }

    private void takeTurn(Player currentPlayer) {
        currentPlayer.play(dealer);

        // If the current player is not the dealer and hasn't busted, initiate the dealer's turn
        if (!(currentPlayer instanceof Dealer) && !currentPlayer.isBust()) {
            dealer.play();
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