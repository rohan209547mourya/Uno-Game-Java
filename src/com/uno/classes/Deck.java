package com.uno.classes;

import com.uno.enums.Color;
import com.uno.enums.NumberValue;
import com.uno.enums.SpacialAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> deck = new ArrayList<>();

    public Deck() {
        initializeDeck();
        shuffleDeck();
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    private void initializeDeck(){
        for (Color color : Color.values()) {
            for (NumberValue number : NumberValue.values()) {
                if (number == NumberValue.ZERO) {
                    this.deck.add(new Card(color, number));
                } else {
                    this.deck.add(new Card(color, number));
                    this.deck.add(new Card(color, number));
                }
            }
        }
        for (Color color : Color.values()) {
            this.deck.add(new Card(color, SpacialAction.WILD));
            this.deck.add(new Card(color, SpacialAction.WILD_DRAW_FOUR));
            for (SpacialAction spacialAction : SpacialAction.values()) {
                if (spacialAction != SpacialAction.WILD && spacialAction != SpacialAction.WILD_DRAW_FOUR) {
                    this.deck.add(new Card(color, spacialAction));
                    this.deck.add(new Card(color, spacialAction));
                }
            }
        }
    }

    public void replenish() {
        this.deck = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            replenish();
        }
        return this.deck.remove(deck.size() - 1);
    }

    @Override
    public String toString() {
        System.out.println(this.deck.size());
        StringBuilder stringBuilder = new StringBuilder();

        for (Card card : this.deck) {
            stringBuilder.append(card.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
