package org.example;

import org.example.Cards.Card;
import org.example.Cards.NumberCard;

public class MatchRule implements Rule {
    @Override
    public String getDescription() {
        return "Play a card with the same color as the current card.";
    }

    @Override
    public boolean isLegalMove(Game game, Card card, Card currentCard) {
        return checkForMatchingColor(card, currentCard) || checkForMatchingNumber(card, currentCard);
    }

    private boolean checkForMatchingColor(Card card, Card currentCard) {
        if (card instanceof NumberCard && currentCard instanceof NumberCard) {
            NumberCard numberCard = (NumberCard) card;
            NumberCard currentNumberCard = (NumberCard) currentCard;
            return numberCard.getColor().equals(currentNumberCard.getColor());
        }
        return false;
    }

    private boolean checkForMatchingNumber(Card card, Card currentCard) {
        if (card instanceof NumberCard && currentCard instanceof NumberCard) {
            NumberCard numberCard = (NumberCard) card;
            NumberCard currentNumberCard = (NumberCard) currentCard;
            return numberCard.getValue().equals(currentNumberCard.getValue());
        }
        return false;
    }
}

