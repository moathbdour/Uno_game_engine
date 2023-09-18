package org.example.Cards;

import org.example.Game;
import org.example.Player;

import java.util.Collections;
import java.util.List;

public class ReverseCard implements ActionCard {
    private String color;

    public ReverseCard(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return "Reverse";
    }

    @Override
    public void performAction(Game game) {
        // Reverse the direction of play
        List<Player> players = game.getPlayers();
        Collections.reverse(players);
        System.out.println("The direction of play is reversed!");
    }

    @Override
    public String toString() {
        return "[" + color + " Reverse]";
    }
}

