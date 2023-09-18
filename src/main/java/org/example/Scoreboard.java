package org.example;

import org.example.Cards.Card;

public class Scoreboard implements GameObserver {
    private int playerScore;

    public Scoreboard() {
        playerScore = 0;
    }

    @Override
    public void onGameStarted(Game game) {
        // Reset the scoreboard for a new game
        playerScore = 0;
    }

    @Override
    public void onCardPlayed(Game game, Player player, Card card) {
        // Update the scoreboard based on the card played
        if (card.getValue() == "10") {
            playerScore += 10;
        } else {
            playerScore += 1;
        }
        System.out.println("Score: " + playerScore);
    }

    @Override
    public void onPlayerWon(Game game, Player player) {
        // Display the winning player and final score
        System.out.println("Player " + player.getName() + " won with a score of " + playerScore);
    }
}
