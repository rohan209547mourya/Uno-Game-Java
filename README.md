# UNO Game Documentation

## Introduction
This documentation provides an overview of the classes and methods used in the UNO game implementation. The UNO game is a card game where players take turns matching a card in their hand with the current card shown on top of the deck. The game includes different types of cards, such as numbered cards, special cards, and wild cards.

## Classes

### Main
- This class contains the `main` method and serves as the entry point of the game.
- The `main` method initializes players, creates a game instance, and starts the game.

### Player
- This class represents a player in the UNO game.
- Constructor:
  - `Player(String playerName)`: Initializes a player with a unique identifier, a name, and an empty hand of cards.
- Methods:
  - `getPlayerId()`: Returns the unique identifier of the player.
  - `getPlayerName()`: Returns the name of the player.
  - `getCardsInHand()`: Returns a list of cards in the player's hand.
  - `isValidCardToPlay(Card currentCard)`: Checks if a given card is a valid card to play based on the current card in play.
  - `addStartingCards(List<Card> cards)`: Adds a list of cards to the player's hand.
  - `showCards()`: Prints the cards in the player's hand.
  - `playCard(Card card, Card currentCard)`: Checks if a given card can be played based on the color, number, and special action of the current card in play.

### Deck
- This class represents a deck of cards in the UNO game.
- Constructor:
  - `Deck()`: Initializes a deck with a standard set of UNO cards.
- Methods:
  - `shuffleDeck()`: Shuffles the deck of cards.
  - `initializeDeck()`: Initializes the deck with different colored cards, numbers, and special actions.
  - `replenish()`: Replenishes the deck by creating a new deck of cards.
  - `drawCard()`: Draws a card from the deck and replenishes the deck if it is empty.
  - `toString()`: Overrides the default `toString` method to provide a string representation of the deck.

### Card
- This class represents a card in the UNO game.
- Constructors:
  - `Card(Color color, NumberValue number)`: Initializes a card with a color and a number value.
  - `Card(Color color, SpacialAction spacialAction)`: Initializes a card with a color and a special action.
- Methods:
  - `getColor()`: Returns the color of the card.
  - `getNumber()`: Returns the number value of the card.
  - `setColor(Color color)`: Sets the color of the card.
  - `getSpecialAction()`: Returns the special action of the card.
  - `isSpecialCard()`: Checks if the card is a special card.
  - `getAnsiColor(Color color)`: Returns the ANSI color code corresponding to a given color.
  - `toString()`: Overrides the default `toString` method to provide a string representation of the card.

### Game
- This class represents the UNO game.
- Constructor:
  - `Game(List<Player> players)`: Initializes a game with a list of players.
- Methods:
  - `start()`: Starts the game by shuffling the deck, dealing starting cards to players, drawing the first card, and starting the first player's turn.
  - `playTurn(Player player)`: Allows a player to take their turn in the game, including drawing a card, playing a card, and applying the card's special action if applicable.
  - `isGameOver()`: Checks if the game is over by determining if any player has an empty hand.
  - `getNextPlayer()`: Returns the next player in turn.
  - `reverseDirection()`: Reverses the direction of play.
  - `skipNextPlayer()`: Skips the next player in turn.
  - `drawCardsFromDeck(int numCards)`: Draws a specified number of cards from the deck.
  - `applyWildCardColor(Card wildCard)`: Allows the current player to choose the color for a wild card.
  - `toString()`: Overrides the default `toString` method to provide a string representation of the game state.

## Conclusion
This documentation provides an overview of the classes and methods used in the UNO game implementation. Understanding these classes and their functionalities is essential for further development and customization of the game.