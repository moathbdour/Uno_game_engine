package org.example.Cards;

import org.example.Game;

public interface ActionCard extends Card {
    void performAction(Game game);
}

