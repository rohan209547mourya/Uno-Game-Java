package com.uno;

import com.uno.classes.Deck;
import com.uno.classes.Game;
import com.uno.classes.Player;

import java.util.List;

public class Main {
    /**
     * The main function creates four players and starts a game with them.
     */
    public static void main(String[] args) {

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");

        List<Player> players = List.of(player1, player2, player3, player4);

        Game game = new Game(players);

        game.start();
    }

}