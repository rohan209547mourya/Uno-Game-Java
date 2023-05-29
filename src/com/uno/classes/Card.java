package com.uno.classes;

import com.uno.enums.Color;
import com.uno.enums.NumberValue;
import com.uno.enums.SpacialAction;

public class Card {

    private Color color;
    private final NumberValue number;
    private final SpacialAction spacialAction;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_GREEN = "\u001B[32m";


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
    public void setColor(Color color) {
        this.color = color;
    }


    public SpacialAction getSpecialAction() {
        return spacialAction;
    }

    public boolean isSpecialCard() {
        return spacialAction != null;
    }


    public String getAnsiColor(Color color) {

        switch (color){
            case RED -> {
                return ANSI_RED;
            }
            case BLUE -> {
                return ANSI_BLUE;
            }
            case GREEN -> {
                return ANSI_GREEN;
            }
            case YELLOW -> {
                return ANSI_YELLOW;
            }
            default -> {
                return ANSI_RESET;
            }
        }


    }

    @Override
    public String toString() {
        if(isSpecialCard()) {
            return  getAnsiColor(color) + "{"+ spacialAction + " of " + color  +"}" + ANSI_RESET;
        }

        return getAnsiColor(color)  + "{"+ number + " of " + color  +"}" + ANSI_RESET ;
    }
}
