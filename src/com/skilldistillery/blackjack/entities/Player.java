package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.Scanner;

public class Player {
    protected Hand hand;
    
    public void dealInitialHand(Card card1, Card card2) {
        hand.addCard(card1);
        hand.addCard(card2);
    }
  

    public Player() {
        this.hand = new BlackjackHand();
    }

    public Hand getHand() {
        return hand;
    }

    public void hit(Card card) {
        hand.addCard(card);
        if (this instanceof Dealer) {
            System.out.println("Dealer's Card: " + card);
        } else {
            System.out.println("Player's Card: " + card);
        }
        if (((BlackjackHand) hand).isBust()) {
            if (this instanceof Dealer) {
                System.out.println("Dealer busts!");
            } else {
                System.out.println("Player busts!");
            }
        }
    
    }

    public void stand() {
    	if (this instanceof Dealer) {
            System.out.println("Dealer stands.");
        } else {
            System.out.println("Player stands.");
        }
    }

    public boolean isBust() {
        return ((BlackjackHand) hand).isBust();
    }

    
    public void displayHand() {
    	System.out.print((this instanceof Dealer) ? "Player's Hand: " : "Dealer's Hand: ");
        List<Card> cards = hand.getCards();

        for (int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i)); 
            if (i < cards.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }

   

    public void play(Dealer dealer) {
        Scanner userInput = new Scanner(System.in);

        while (!isBust()) {
            System.out.println("Does the player want to Hit or Stand (H or S)?");
            String choice = userInput.nextLine();

            if (choice.equalsIgnoreCase("H")) {
                hit(dealer.dealCard());
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