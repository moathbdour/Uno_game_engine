package org.example.Cards;

import org.example.Game;

import java.util.Scanner;

public class WildCard implements ActionCard {
    private String color;

    public WildCard() {
        this.color = "Colorless";
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return "Wild";
    }

    private String getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a color (Red, Green, Blue, Yellow): ");
        String chosenColor = scanner.nextLine().trim();
        scanner.close();
        return chosenColor;
    }

    @Override
    public void performAction(Game game) {

        String chosenColor = getPlayerInput();
        game.setCurrentColor(chosenColor);

        System.out.println("Wild Card action: The color has been changed to " + chosenColor);
    }

    @Override
    public String toString() {
        return "[" + color + " Wild]";
    }
}

