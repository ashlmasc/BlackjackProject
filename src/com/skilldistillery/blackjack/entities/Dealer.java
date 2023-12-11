package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Dealer extends Player {
    private Deck deck;
    private boolean isFirstCardFaceDown = true;

    public Dealer() {
        this(new Deck(), new BlackjackHand());
    }

    public Dealer(Deck deck, Hand hand) {
        this.hand=hand;
        this.deck = deck;
        deck.shuffle();
    }

    public Card dealCard() {
        return deck.dealCard();
    }

    public void hitFaceDown() {
        Card card = deck.dealCard();
        super.hit(card);
        System.out.println("Dealer's Card " + getCardNumber() + ": Face Down");
    }

    @Override
    public void hit(Card card) {
        super.hit(card);
        String playerType = this instanceof Dealer ? "Dealer" : "Player";

        if (getCardNumber() == 1 && this instanceof Dealer) {
            System.out.println(playerType + "'s Card " + getCardNumber() + ": Face Down");
        } else {
            System.out.println(playerType + "'s Card " + (getCardNumber() - (this instanceof Dealer ? 0 : 1)) + ": " + card);
        }

        if (isBust()) {
            System.out.println(playerType + " busts!");
        }
    }

    @Override
    public void displayHand() {
        System.out.print("Dealer's Hand: ");
        List<Card> cards = getHand().getCards();

        for (int i = 0; i < cards.size(); i++) {
            if (i == 0 && isFirstCardFaceDown) {
                System.out.print("Face Down");
            } else {
                System.out.print(cards.get(i));
                if (i < cards.size() - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println(); 
    }

    @Override
    public int getCardNumber() {
        return super.getCardNumber(); // Use the cardNumber from the Player class
    }

    // Add a method to reset face-down status for a new round
    public void resetFaceDown() {
        isFirstCardFaceDown = true;
    }
}