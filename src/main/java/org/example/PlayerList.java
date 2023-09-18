package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerList {
    private static PlayerList instance;
    private List<Player> players;

    private PlayerList() {
        players = new ArrayList<>();
    }

    public static PlayerList getInstance() {
        if (instance == null) {
            instance = new PlayerList();
        }
        return instance;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addPlayers(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 10) {
            throw new IllegalArgumentException("Invalid number of players. Must be between 2 and 10.");
        }

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            players.add(player);
        }
    }
}

