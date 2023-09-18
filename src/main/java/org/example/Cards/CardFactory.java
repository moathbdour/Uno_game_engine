package org.example.Cards;

public class CardFactory {
    public Card createCard(String color, String value) {
        switch (value) {
            case "Reverse":
                return new ReverseCard(color);
            case "Skip":
                return new SkipCard(color);
            case "Wild":
                return new WildCard();
            case "Wild Draw Four":
                return new WildDrawFourCard();
            default:
                return new NumberCard(color, value);
        }
    }
}

