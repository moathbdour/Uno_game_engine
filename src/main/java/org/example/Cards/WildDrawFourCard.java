package org.example.Cards;

import org.example.Game;
import org.example.Player;

public class WildDrawFourCard implements ActionCard {
    private String color;

    public WildDrawFourCard() {
        this.color = "Colorless";
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return "Wild Draw Four";
    }

    @Override
    public void performAction(Game game) {
        // Perform the action associated with the Wild Draw Four card
        // Make the next player draw four cards and change the color
        Player nextPlayer = game.getNextPlayer(game.getCurrentPlayer());
        for (int i = 0; i < 4; i++) {
            Card drawnCard = game.getCurrentPlayer().drawCard();
            nextPlayer.getHand().add(drawnCard);
        }

        // Prompt the player to choose a new color
        String chosenColor = game.promptPlayerForColor();
        game.setCurrentColor(chosenColor);

        System.out.println(nextPlayer.getName() + " draws four cards and changes the color to " + chosenColor);
    }

    @Override
    public String toString() {
        // Customize the string representation of the card
        return "[" + color + " Wild Draw Four]";
    }
}

