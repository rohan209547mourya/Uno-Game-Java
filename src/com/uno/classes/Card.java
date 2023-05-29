package com.uno.classes;

import com.uno.enums.Color;
import com.uno.enums.NumberValue;
import com.uno.enums.SpacialAction;

public class Card {

    private final Color color;
    private final NumberValue number;
    private final SpacialAction spacialAction;


    public Card(Color color, NumberValue number) {
        this.color = color;
        this.number = number;
        this.spacialAction = null;
    }

    public Card(Color color, SpacialAction spacialAction) {
        this.color = color;
        this.number = null;
        this.spacialAction = spacialAction;
    }

    public Color getColor() {
        return color;
    }

    public NumberValue getNumber() {
        return number;
    }


    public SpacialAction getSpacialAction() {
        return spacialAction;
    }

    public boolean isSpacialCard() {
        return spacialAction != null;
    }


    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", number=" + number +
                ", spacialAction=" + spacialAction +
                '}';
    }
}
