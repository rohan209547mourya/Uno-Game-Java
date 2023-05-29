package com.uno.classes;

import com.uno.enums.Color;
import com.uno.enums.SpacialAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private List<Player> players;
    private Card currentCard;
    private int currentPlayerIndex;
    private boolean isClockwise;
    private boolean isFirstTurn ;

    private final Scanner sc = new Scanner(System.in);

    public Game(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.currentCard = null;
        this.currentPlayerIndex = (int) (Math.random() * 3 + 0);
        this.isClockwise = true;
        this.isFirstTurn = true;
    }

    public void start(){
        System.out.println("Started the game...");
        deck.shuffleDeck();

        for(Player player : players) {
            List<Card> startingCards = new ArrayList<>();

            for(int i = 0; i < 7; i++) {
                startingCards.add(deck.drawCard());
            }

            player.addStartingCards(startingCards);
        }

        currentCard = deck.drawCard();

        Player firstPlayer = players.get(currentPlayerIndex);

        playTurn(firstPlayer);
    }

    public void playTurn(Player player) {

        System.out.println("\n\n\nCurrent Player: " + player.getPlayerName());

        if(currentCard.isSpecialCard()) {
            System.out.println("Current Card: " + currentCard.getSpecialAction() + " of " + currentCard.getColor());
        }
        else{
            System.out.println("Current Card: " + currentCard.getNumber() + " of " + currentCard.getColor());
        }

        if(isFirstTurn) {
            isFirstTurn = false;
            if(currentCard != null && currentCard.getSpecialAction() == SpacialAction.REVERSE) {
                isClockwise = !isClockwise;
            }

            if(currentCard != null && currentCard.isSpecialCard() && currentCard.getSpecialAction() != SpacialAction.REVERSE) {
                doSpacialActions(currentCard, player);
            }

        }


        player.showCards();

        if(!player.isValidCardToPlay(currentCard)) {
            System.out.println("No valid card to play, drawing a card...");
            Card drawnCard = deck.drawCard();

            if(drawnCard.isSpecialCard()) {
                System.out.println("Drew: " + drawnCard.getSpecialAction() + " of " + drawnCard.getColor());
            }else {
                System.out.println("Drew: " + drawnCard.getNumber() + " of " + drawnCard.getColor());
            }

            player.getCardsInHand().add(drawnCard);
            nextPlayerTurn();
            return;
        }


        System.out.print("Enter the index of the card you want to play: ");
        int cardIndex = sc.nextInt();

        if(cardIndex < 0 || cardIndex >= player.getCardsInHand().size()) {
            System.out.println("Invalid card index, try again...");
            playTurn(player);
            return;
        }

        Card cardToPlay = player.getCardsInHand().get(cardIndex);

        if(cardToPlay.isSpecialCard()) {
            System.out.println("Playing: " + cardToPlay.getSpecialAction() + " of " + cardToPlay.getColor());
        }else {
            System.out.println("Playing: " + cardToPlay.getNumber() + " of " + cardToPlay.getColor());
        }


        if(player.playCard(cardToPlay, currentCard)) {
            currentCard = cardToPlay;
            if(checkForWinner(player)) {
                return;
            }
            else{
                System.out.println(player.getCardsInHand().size() + " cards remaining");
                nextPlayerTurn();
            }
        }
        else{
            System.out.println("Invalid card, try again...");
            playTurn(player);
        }

    }

    public void nextPlayerTurn() {

        if(currentCard != null && currentCard.getSpecialAction() == SpacialAction.REVERSE) {
            isClockwise = !isClockwise;
        }
        if(currentCard != null && currentCard.getSpecialAction() == SpacialAction.SKIP) {
            if(isClockwise) {
                currentPlayerIndex += 2;
                if(currentPlayerIndex >= players.size()) {
                    currentPlayerIndex = 0;
                }
            } else {
                currentPlayerIndex -= 2;
                if(currentPlayerIndex < 0) {
                    currentPlayerIndex = players.size() - 1;
                }
            }
        }

        if(isClockwise) {
            currentPlayerIndex++;
            if(currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        } else {
            currentPlayerIndex--;
            if(currentPlayerIndex < 0) {
                currentPlayerIndex = players.size() - 1;
            }
        }


        Player nextPlayer = players.get(currentPlayerIndex);

        doSpacialActions(currentCard, nextPlayer);


        playTurn(nextPlayer);
    }

    private boolean changeCurrentCardColor() {
        System.out.println("Changing color to: ");
        System.out.println("1. Red");
        System.out.println("2. Blue");
        System.out.println("3. Green");
        System.out.println("4. Yellow");
        System.out.print("Enter your choice: ");
        int colorChoice = sc.nextInt();

        Color newColor = null;

        switch (colorChoice) {
            case 1 -> newColor = Color.RED;
            case 2 -> newColor = Color.BLUE;
            case 3 -> newColor = Color.GREEN;
            case 4 -> newColor = Color.YELLOW;
            default -> {
                System.out.println("Invalid choice, try again...");
                nextPlayerTurn();
                return true;
            }
        }

        currentCard.setColor(newColor);
        return false;
    }


    public boolean checkForWinner(Player player) {
       if(player.getCardsInHand().size() == 0) {
              System.out.println(player.getPlayerName() + " has won the game!");
              return true;
       }
         return false;
    }

    public void doSpacialActions(Card card, Player nextPlayer) {
        if(card != null && card.getSpecialAction() == SpacialAction.WILD) {
            if (changeCurrentCardColor()) return;
            playTurn(nextPlayer);
        }
        else if(card != null && card.getSpecialAction() == SpacialAction.WILD_DRAW_FOUR) {
            if (changeCurrentCardColor()) return;

            System.out.println("Drawing four cards for " + nextPlayer.getPlayerName());
            for(int i = 0; i < 4; i++) {
                Card drawnCard = deck.drawCard();
                nextPlayer.getCardsInHand().add(drawnCard);
            }
            playTurn(nextPlayer);
        }
        else if(card != null && card.getSpecialAction() == SpacialAction.DRAW_TWO) {
            System.out.println("Drawing two cards for " + nextPlayer.getPlayerName());
            Card drawnCard = deck.drawCard();
            nextPlayer.getCardsInHand().add(drawnCard);
            drawnCard = deck.drawCard();
            nextPlayer.getCardsInHand().add(drawnCard);
            playTurn(nextPlayer);
        }
    }
}
