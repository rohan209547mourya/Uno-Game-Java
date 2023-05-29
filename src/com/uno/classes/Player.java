package com.uno.classes;

import com.uno.enums.SpacialAction;

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

    public boolean isValidCardToPlay(Card currentCard) {
        for (Card card : cardsInHand) {
            if (card.getColor() == currentCard.getColor() ||
                    card.getNumber() == currentCard.getNumber()) {
                return true;
            }
            else if (card.isSpecialCard()) {
                if(card.getSpecialAction() != SpacialAction.WILD && card.getSpecialAction() != SpacialAction.WILD_DRAW_FOUR) {
                    if(card.getColor() == currentCard.getColor()) {
                        return true;
                    }
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }

    public void addStartingCards(List<Card> cards) {
        this.cardsInHand.addAll(cards);
    }

    public void showCards() {
        System.out.println(this.cardsInHand);
    }

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
