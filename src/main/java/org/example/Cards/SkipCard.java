package org.example.Cards;

import org.example.Game;
import org.example.Player;

public class SkipCard implements ActionCard {
    private String color;

    public SkipCard(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return "Skip";
    }

    @Override
    public void performAction(Game game) {
        // Skip the next player's turn
        Player nextPlayer = game.getNextPlayer(game.getCurrentPlayer());
        game.setCurrentPlayer(nextPlayer);
        System.out.println(nextPlayer.getName() + "'s turn is skipped!");
    }

    @Override
    public String toString() {
        return "[" + color + " Skip]";
    }
}

