package org.example;
import org.example.Cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private List<Card> hand;
    private CardProvider cardProvider;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        // CardProvider instance should be injected through the constructor or setter method

    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Card drawCard() {
        Card card = cardProvider.drawCard();
        if (card != null) {
            hand.add(card);
        }
        return card;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + '}';
    }

    public Card chooseCardToPlay(Game game, Card currentCard, RuleEngine ruleEngine) {
        List<Card> hand = this.getHand();

        System.out.println(this.name + ", it's your turn to play!");
        System.out.println("Your hand:");
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            System.out.println("Card " + (i + 1) + ": Color: " + card.getColor() + ", Number: " + card.getValue());
        }

        Scanner scanner = new Scanner(System.in);
        int index = -1;
        while (index < 1 || index > hand.size() || !ruleEngine.isLegalMove(game, hand.get(index - 1), currentCard)) {
            System.out.print("Enter the index of the card you want to play: ");
            index = scanner.nextInt();

            if (index < 1 || index > hand.size()) {
                System.out.println("Invalid choice. Please choose a valid card index.");
            } else if (!ruleEngine.isLegalMove(game, hand.get(index - 1), currentCard)) {
                System.out.println("Illegal move. Please choose a legal card.");
            }
        }

        Card chosenCard = hand.get(index - 1);
        hand.remove(chosenCard);

        // Returning the rest of the cards with a fixed index
        if (!hand.isEmpty()) {
            Card restOfCards = hand.get(0);
            hand.subList(1, hand.size()).clear();
            hand.add(0, restOfCards);
        }

        return chosenCard;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void displayHand() {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            System.out.println((i + 1) + ". " + card.getColor() + " " + card.getValue());
        }
    }

}

