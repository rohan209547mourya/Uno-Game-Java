package org.uno.classes;

import org.uno.enums.Color;
import org.uno.enums.NumberValue;
import org.uno.enums.SpacialAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> deck = new ArrayList<>();

    public Deck() {
        initializeDeck();
    }

    /**
     * This function shuffles the deck of cards using the shuffle method from the Collections class in
     * Java.
     */
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    /**
     * The function initializes a deck of cards for a game of UNO, with different colors, numbers, and
     * special actions.
     */
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

    /**
     * The function replenish initializes a new deck of cards.
     */
    public void replenish() {
        this.deck = new ArrayList<>();
        initializeDeck();
    }

    /**
     * This function draws a card from a deck and replenishes the deck if it is empty.
     * 
     * @return The method `drawCard()` returns a `Card` object.
     */
    public Card drawCard() {
        if (deck.isEmpty()) {
            replenish();
        }
        return this.deck.remove(deck.size() - 1);
    }

    /**
     * This function overrides the default toString() method to return a string representation of an
     * object, which includes the deck attribute enclosed in square brackets.
     * 
     * @return A string representation of the object, which includes the contents of the "deck"
     * variable enclosed in square brackets.
     */
    @Override
    public String toString() {
        return "["+ deck + "]";
    }
}
