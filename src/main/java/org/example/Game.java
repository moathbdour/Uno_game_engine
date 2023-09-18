package org.example;
import org.example.Cards.ActionCard;
import org.example.Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public abstract class Game {
    private List<Player> players;
    private Deck deck;
    private List<Rule> rules;
    private Player startingPlayer;
    private Card startingCard;
    private List<GameObserver> observers;
    private Player currentPlayer;
    private String CurrentColor;
    private Card currentCard;

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void setStartingPlayer(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public void setObservers(List<GameObserver> observers) {
        this.observers = observers;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Player getStartingPlayer() {
        return startingPlayer;
    }

    public Card getStartingCard() {
        return startingCard;
    }

    public List<GameObserver> getObservers() {
        return observers;
    }

    public String getCurrentColor() {
        return CurrentColor;
    }

    public Game() {
        players = PlayerList.getInstance().getPlayers();
        deck = Deck.getInstance();
        observers = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    protected void setFirstPlayer() {
        Collections.shuffle(players);
        startingPlayer = players.get(0);
        System.out.println("Current player is " + startingPlayer.getName());
        setCurrentPlayer(startingPlayer);

    }
    protected abstract void setGameRules(); // Abstract setter method for setting game rules

    protected Card setStartingCard() {
        startingCard = deck.drawCard();
        deck.getDiscardPile().add(startingCard);
        while (startingCard instanceof ActionCard) {
            startingCard = deck.drawCard();
            deck.getDiscardPile().add(startingCard);
        }
        return startingCard;
    }

    public Player getNextPlayer(Player currentPlayer) {
        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();
        return players.get(nextIndex);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String promptPlayerForColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the desired color: ");
        String color = scanner.nextLine();
        return color;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    protected void notifyGameStarted() {
        for (GameObserver observer : observers) {
            observer.onGameStarted(this);
        }
    }

    protected void notifyCardPlayed(Player player, Card card) {
        for (GameObserver observer : observers) {
            observer.onCardPlayed(this, player, card);
        }
    }

    protected void notifyPlayerWon(Player player) {
        for (GameObserver observer : observers) {
            observer.onPlayerWon(this, player);
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }



    abstract void play();
    public abstract boolean isGameOver(); // Abstract method to check if the game is over

    public abstract boolean hasLegalMove(); // Abstract method to check if the current player has a legal move

    public abstract void drawCardIfNoLegalMove(); // Abstract method to handle drawing a card if the current player has no legal move

    public abstract void switchToNextPlayer(); // Abstract method to switch to the next player
    public abstract void playTurn();
    public void setCurrentColor(String chosenColor) {
        CurrentColor=chosenColor;
    }
}

