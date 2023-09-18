package org.example;

import org.example.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
    private List<Rule> rules;

    public RuleEngine() {
        this.rules = new ArrayList<>();
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public boolean isLegalMove(Game game, Card card, Card currentCard) {
        for (Rule rule : rules) {
            if (!rule.isLegalMove(game, card, currentCard)) {
                return false;
            }
        }
        return true;
    }
}

