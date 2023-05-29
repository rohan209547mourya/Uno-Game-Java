package org.uno.classes;

import org.uno.enums.Color;
import org.uno.enums.SpacialAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private final String playerId;
    private final String playerName;
    private List<Card> cardsInHand;

    public Player(String playerName) {
        this.playerId = UUID.randomUUID().toString();
        this.playerName = playerName;
        this.cardsInHand = new ArrayList<>();
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * Checks if the current card is a valid card to play based on the cards in hand.
     *
     * @param currentCard The current card to be checked.
     * @return {@code true} if the current card is valid to play, {@code false} otherwise.
     */
    public boolean isValidCardToPlay(Card currentCard) {
        for(Card card: cardsInHand){
            if(card.getColor() == Color.BLACK) {
                return true;
            }
            if (card.getColor() == currentCard.getColor()) {
                return true;
            }
            else if(!currentCard.isSpecialCard() && card.getNumber() == currentCard.getNumber()) {
                return true;
            }
            else if(card.getNumber() == null && currentCard.isSpecialCard() && card.getSpecialAction() == currentCard.getSpecialAction()) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function adds a list of cards to the current hand of a player.
     * 
     * @param cards The parameter "cards" is a List of Card objects that are being passed as an
     * argument to the method "addStartingCards". The method adds all the cards in the list to the
     * "cardsInHand" list of the object on which the method is called.
     */
    public void addStartingCards(List<Card> cards) {
        this.cardsInHand.addAll(cards);
    }

   /**
    * This Java function prints out the cards in a player's hand.
    */
    public void showCards() {
        System.out.println(this.cardsInHand);
    }

   /**
    * The function checks if a given card can be played based on the color, number, and special action
    * of the current card in a game.
    * 
    * @param card The card that the player wants to play.
    * @param currentCard The current card in play on the game board.
    * @return The method returns a boolean value, which is true if the card can be played and false if
    * it cannot be played.
    */
    public boolean playCard(Card card, Card currentCard) {
        if (card.getColor() == currentCard.getColor() ||
                card.getNumber() == currentCard.getNumber()) {
            cardsInHand.remove(card);
            return true;
        }
        else if (card.isSpecialCard()) {
            if(card.getSpecialAction() != SpacialAction.WILD && card.getSpecialAction() != SpacialAction.WILD_DRAW_FOUR) {
                if(card.getColor() == currentCard.getColor()) {
                    cardsInHand.remove(card);
                    return true;
                }
            }
            else {
                cardsInHand.remove(card);
                return true;
            }
        }
        return false;
    }
}
