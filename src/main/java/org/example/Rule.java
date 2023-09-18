package org.example;

import org.example.Cards.Card;

public interface Rule {
    String getDescription();
    boolean isLegalMove(Game game, Card card, Card currentCard);
}

