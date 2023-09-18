package org.example;

import java.util.ArrayList;
import java.util.List;

public class GameDriver {
    public static void main(String[] args) {
        UnoGame game = new UnoGame(7, 4);
        game.play();
    }
}

