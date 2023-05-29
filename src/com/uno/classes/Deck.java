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
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    private void initializeDeck(){
        for (Color color : Color.values()) {
            for (NumberValue number : NumberValue.values()) {
                if(color != Color.BLACK) {
                    if (number == NumberValue.ZERO) {
                        this.deck.add(new Card(color, number));
                    } else {
                        this.deck.add(new Card(color, number));
                        this.deck.add(new Card(color, number));
                    }
                }
            }
        }
        for (Color color : Color.values()) {
            for (SpacialAction spacialAction : SpacialAction.values()) {
                if (spacialAction != SpacialAction.WILD && spacialAction != SpacialAction.WILD_DRAW_FOUR && color != Color.BLACK) {
                    this.deck.add(new Card(color, spacialAction));
                    this.deck.add(new Card(color, spacialAction));
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            this.deck.add(new Card(Color.BLACK, SpacialAction.WILD));
            this.deck.add(new Card(Color.BLACK, SpacialAction.WILD_DRAW_FOUR));
        }
    }

    public void replenish() {
        this.deck = new ArrayList<>();
        initializeDeck();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            replenish();
        }
        return this.deck.remove(deck.size() - 1);
    }

    @Override
    public String toString() {
        return "["+ deck + "]";
    }
}
