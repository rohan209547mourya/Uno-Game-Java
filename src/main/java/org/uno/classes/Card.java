package org.uno.classes;

import org.uno.enums.Color;
import org.uno.enums.NumberValue;
import org.uno.enums.SpacialAction;

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

    // These are getter and setter methods for the `color` instance variable of the `Card` class.
    public Color getColor() {
        return color;
    }

    public NumberValue getNumber() {
        return number;
    }
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * This method returns a SpacialAction object.
     * 
     * @return The method is returning an object of type `SpacialAction`.
     */
    public SpacialAction getSpecialAction() {
        return spacialAction;
    }

    /**
     * This method checks if a card has a special action.
     * 
     * @return A boolean value indicating whether the "spacialAction" variable is not null.
     */
    public boolean isSpecialCard() {
        return spacialAction != null;
    }


    /**
     * This method returns the ANSI color code corresponding to a given color.
     * 
     * @param color The parameter "color" is of type "Color", which is likely an enum that represents
     * different colors. The method "getAnsiColor" takes in a Color object and returns a corresponding
     * ANSI color code as a String.
     * @return The method is returning a string that represents the ANSI color code corresponding to
     * the input color. If the input color is RED, the method returns the ANSI code for red color, if
     * it is BLUE, it returns the ANSI code for blue color, and so on. If the input color is not
     * recognized, the method returns the ANSI code for resetting the color to default.
     */
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

    /**
     * This method returns a string representation of a card object, including its
     * color and number or special action.
     * 
     * @return The `toString()` method is returning a string representation of a playing card. If the
     * card is a special card, it returns a string with the special action and color of the card in
     * ANSI color format. If the card is not a special card, it returns a string with the number and
     * color of the card in ANSI color format.
     */
    @Override
    public String toString() {
        if(isSpecialCard()) {
            return  getAnsiColor(color) + "{"+ spacialAction + " of " + color  +"}" + ANSI_RESET;
        }

        return getAnsiColor(color)  + "{"+ number + " of " + color  +"}" + ANSI_RESET ;
    }
}
