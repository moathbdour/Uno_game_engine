package org.example;
import org.example.Cards.Card;
import org.example.Cards.CardFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class Deck implements Iterable<Card>, CardProvider {
    private static Deck instance; // Singleton instance
    private List<Card> cards;
    private List<Card> discardPile;
    private Card currentCard;

    private Deck() {
        cards = new ArrayList<>();
        discardPile = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    private void initializeDeck() {
        CardFactory cardFactory = new CardFactory();
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw Two"};

        for (String color : colors) {
            // Add number cards
            for (String value : values) {
                cards.add(cardFactory.createCard(color, value));
                if (!value.equals("0")) {
                    cards.add(cardFactory.createCard(color, value));
                }
            }
        }

        // Add wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(cardFactory.createCard("Colorless", "Wild"));
            cards.add(cardFactory.createCard("Colorless", "Wild Draw Four"));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(card);
        System.out.println("Card " + card.getValue() + " added to discard pile");
    }

    public void dealCards(List<Player> players, int numCards) {
        for (int i = 0; i < numCards; i++) {
            for (Player player : players) {
                Card card = drawCard();
                player.addCardToHand(card);
            }
        }
    }

    @Override
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public boolean isDrawPileEmpty() {
        return cards.isEmpty();
    }

    public boolean isDiscardPileEmpty() {
        return discardPile.isEmpty();
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}

