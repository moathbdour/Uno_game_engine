package org.example.Cards;

public class NumberCard implements Card {
    private String color;
    private String value;

    public NumberCard(String color, String value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + color + " " + value + "]";
    }
}

