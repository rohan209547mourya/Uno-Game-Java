package com.uno.classes;

import com.uno.enums.Color;
import com.uno.enums.SpacialAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Deck deck;
    private final List<Player> players;
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

    /**
     * The start function initializes the game by shuffling the deck, dealing starting cards to
     * players, drawing the first card, and starting the first player's turn.
     */
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

    /**
     * This function allows a player to take their turn in a game of Uno, including drawing a card,
     * playing a card, and checking for a winner.
     * 
     * @param player The current player who is taking their turn.
     */
    public void playTurn(Player player) {

        System.out.println("\n\n\nCurrent Player: " + player.getPlayerName());

        if(currentCard.isSpecialCard()) {
            System.out.println("Current Card: " + currentCard.getSpecialAction() + " of " + currentCard.getColor());
        }
        else{
            System.out.println("Current Card: " + currentCard.getNumber() + " of " + currentCard.getColor());
        }


        
        /*This code block is checking if it is the first turn of the game. If it is, it sets the
        `isFirstTurn` flag to false and checks if the current card is a reverse card. If it is, it
        changes the direction of play by toggling the `isClockwise` flag. Additionally, if the
        current card is a special card other than a reverse card, it performs the special action by
        calling the `doSpacialActions` method.
        */
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

        /*This code block is checking if the current player has a valid card to play based on the
        current card in play. If the player does not have a valid card to play, the player draws a
        card from the deck, adds it to their hand, and then moves on to the next player's turn by
        calling the `nextPlayerTurn()` method.
        */
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


        /*This code block is checking if the card the player wants to play is a valid card to play
        based on the current card in play. If it is a valid card, it removes the card from the
        player's hand, sets it as the current card, and checks if the player has won the game by
        calling the `checkForWinner` method. If the player has won, the method returns. Otherwise,
        it prints the number of cards remaining in the player's hand and moves on to the next
        player's turn by calling the `nextPlayerTurn()` method. If the card is not valid, it prints
        an error message and prompts the player to try again by calling the `playTurn(player)`
        method.
        */
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

   /**
    * This function determines the next player's turn based on the current card's special action and
    * the direction of play.
    */
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

   /**
    * This function changes the color of a card based on user input and returns a boolean indicating if
    * the operation was successful.
    * 
    * @return The method is returning a boolean value. If the color choice is invalid, it returns true.
    * Otherwise, it returns false.
    */
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


    /**
     * This function checks if a player has won the game by checking if they have no cards in their
     * hand.
     * 
     * @param player The player object for whom we are checking if they have won the game.
     * @return The method is returning a boolean value. It returns true if the player has no cards in
     * their hand and has won the game, and false otherwise.
     */
    public boolean checkForWinner(Player player) {
       if(player.getCardsInHand().size() == 0) {
              System.out.println(player.getPlayerName() + " has won the game!");
              return true;
       }
         return false;
    }

    /**
     * The function performs special actions based on the type of card played, such as changing the
     * current card color, drawing cards for the next player, or skipping the next player's turn.
     * 
     * @param card The card that was just played by the current player.
     * @param nextPlayer The next player who will take their turn in the game.
     */
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
