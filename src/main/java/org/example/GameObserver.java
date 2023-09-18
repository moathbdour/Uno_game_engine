package org.example;

import org.example.Cards.Card;

public interface GameObserver {
    void onGameStarted(Game game);
    void onCardPlayed(Game game, Player player, Card card);
    void onPlayerWon(Game game, Player player);
    // Add more methods as needed
}
