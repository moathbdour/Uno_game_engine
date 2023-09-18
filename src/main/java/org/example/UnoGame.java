package org.example;

import org.example.Cards.Card;


import java.util.Scanner;
public class UnoGame extends Game {

    private RuleEngine ruleEngine = new RuleEngine();
    private Deck deck = Deck.getInstance();
    private PlayerList playerList = PlayerList.getInstance();

    public UnoGame(int numCardsToDeal, int numPlayers) {
        setupGame(numCardsToDeal, numPlayers);
    }

    private void setupGame(int numCardsToDeal, int numPlayers) {
        playerList.addPlayers(numPlayers);
        deck.dealCards(playerList.getPlayers(), numCardsToDeal);
        setFirstPlayer();
        setCurrentCard(setStartingCard());
        setGameRules();
    }



    @Override
    protected void setGameRules() {
        Rule matchRule = new MatchRule();
        ruleEngine.addRule(matchRule);
    }

    @Override
    void play() {

        while (!isGameOver()) {
            if (hasLegalMove()) {
                playTurn();
            } else {
               drawCardIfNoLegalMove();
               switchToNextPlayer();
            }
        }
    }


    @Override
    public boolean isGameOver() {
        return getCurrentPlayer().getHand().isEmpty();
    }

    @Override
    public boolean hasLegalMove() {
        return getCurrentPlayer().getHand().stream()
                .anyMatch(card -> ruleEngine.isLegalMove(this, card, getCurrentCard()));
    }
    @Override
    public void playTurn() {
        System.out.println("Current card: " + getCurrentCard());

        if (hasLegalMove()) {
            System.out.println(getCurrentPlayer().getName() + "'s hand:");
            getCurrentPlayer().displayHand();

            Scanner scanner = new Scanner(System.in);
            int index = -1;
            boolean validMove = false;

            while (!validMove) {
                System.out.print("Enter the index of the card you want to play: ");
                index = scanner.nextInt();

                if (index >= 1 && index <= getCurrentPlayer().getHand().size()) {
                    Card cardToPlay = getCurrentPlayer().getHand().get(index - 1);

                    if (ruleEngine.isLegalMove(this, cardToPlay, getCurrentCard())) {
                        System.out.println(getCurrentPlayer().getName() + " plays: " + cardToPlay);
                        setCurrentCard(cardToPlay); ;
                        getCurrentPlayer().getHand().remove(cardToPlay);
                        validMove = true;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                } else {
                    System.out.println("Invalid choice. Please choose a valid card index.");
                }
            }
        } else {
            System.out.println(getCurrentPlayer().getName() + " has no legal moves. Drawing a card.");
            Card drawnCard = deck.drawCard();

            if (drawnCard != null) {
                getCurrentPlayer().addCardToHand(drawnCard);
                System.out.println(getCurrentPlayer().getName() + " draws: " + drawnCard);

                if (ruleEngine.isLegalMove(this, drawnCard, getCurrentCard())) {
                    System.out.println(getCurrentPlayer().getName() + " plays: " + drawnCard);
                    setCurrentCard(drawnCard); ;
                    getCurrentPlayer().getHand().remove(drawnCard);
                } else {
                    System.out.println("Drawn card is not a legal move.");
                }
            } else {
                System.out.println("No more cards in the deck. Game over.");
            }
        }

        switchToNextPlayer();
    }

    @Override
    public void drawCardIfNoLegalMove() {
        if (!hasLegalMove()) {
            System.out.println(getCurrentPlayer().getName() + " has no legal moves. Drawing a card.");
            Card drawnCard = deck.drawCard();

            if (drawnCard != null) {
                getCurrentPlayer().addCardToHand(drawnCard);
                System.out.println(getCurrentPlayer().getName() + " draws: " + drawnCard);

                if (ruleEngine.isLegalMove(this, drawnCard, getCurrentCard())) {
                    System.out.println(getCurrentPlayer().getName() + " plays: " + drawnCard);
                    setCurrentCard(drawnCard); ;
                    getCurrentPlayer().getHand().remove(drawnCard);
                } else {
                    System.out.println("Drawn card is not a legal move.");
                }
            } else {
                System.out.println("No more cards in the deck. Game over.");
            }
        }
    }

    @Override
    public void switchToNextPlayer() {
        int currentIndex = this.getPlayers().indexOf(getCurrentPlayer());
        int nextIndex = (currentIndex + 1) % this.getPlayers().size();
        setCurrentPlayer(this.getPlayers().get(nextIndex));
        setCurrentColor(getCurrentCard().getColor());
    }
}

