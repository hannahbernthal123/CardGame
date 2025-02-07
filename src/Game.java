// Hannah - 2023
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    // These are the instance variables.

    // These instance variables that control data sharing.
    private Deck deck;
    private Game myGame;
    private CardGameView window;
    private Player currentPlayer;
    private Player opposingPlayer;
    private Player p1;
    private Player p2;

    // These are the instance variables with types I did not create (used to track different things).
    private int turn;
    private String winner;
    private String currentState;
    private Scanner newObject;
    private boolean isItGoFish;
    private boolean gameOver;

    public Game() {
        // This sets up two String arrays for the accepted ranks and suits.
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};


        // This initializes a new deck, passing in the two String arrays and a point value of 0.
        deck = new Deck(rank, suit, 0);

        // This sets up the tracking instance variables to be on the correct setting at the start of the game.
        turn = 1;
        gameOver = false;
        isItGoFish = false;
        winner = "";
        currentState = "instructions";
        window = new CardGameView(this);

        // These take in the two player's names.
        newObject = new Scanner(System.in);
        System.out.println("Player 1, enter your name: ");
        String name1 = newObject.nextLine();

        System.out.println("Player 2, enter your name: ");
        String name2 = newObject.nextLine();

        // This initializes the two players.
        p1 = new Player(name1);
        p2 = new Player(name2);
        currentPlayer = p1;
        opposingPlayer = p2;
    }


    // These are the getters.
    public boolean getFishingTime() {
        return isItGoFish;
    }

    public int getTurn() {
        return turn;
    }

    public String getWinner() {
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpposingPlayer() {
        return opposingPlayer;
    }
    public String getCurrentState() {
        return currentState;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getPlayerName(int turn) {
        if (turn == 1) {
            return p1.getName();
        }
        else {
            return p2.getName();
        }
    }

    // This method deals out 5 random cards to player 1 and 2.
    public void dealCards() {
        for (int i = 0; i < 5; i++)
        {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    // This method return whether or not it is fishing time based on the cards of the other opponent and the current player's request.
    public boolean fishingTime() {
        // This part of the method takes in the String that the player is requested and checks it against the other player's deck.
        String request = request();
        if (opposingPlayer.checkCards(request) != -1) {
            // If the other player does have the card (method does not return -1) the other player gives the card to the current player.
            currentPlayer.addCard(take(opposingPlayer.checkCards(request), opposingPlayer.getHand()));
            // Then, it checks the trick to see if the current player has a set of 4.
            currentPlayer.checkTrick();
            isItGoFish = false;
            return false;
        }
        else {
            // If the other player does not have the requested card, the current player is dealt a random card from the deck.
            currentPlayer.addCard(deck.deal());
            System.out.println("Go Fish!");
            // Then, it checks the trick to see if the current player has a set of 4.
            currentPlayer.checkTrick();
            isItGoFish = true;
            window.repaint();
            return true;
        }
    }

    // This method switches the turn and the current players to prepare for the next turn.
    public void switchCurrentPlayer() {
        if (turn == 1)
        {
            currentPlayer = p2;
            opposingPlayer = p1;
            turn = 2;
        }
        else {
            currentPlayer = p1;
            opposingPlayer = p2;
            turn = 1;
        }
    }

    // PlayTurn is meant to start each turn.
    // It first establishes which player's turn it is and then it displays who's turn it is and their hand.
    public void playTurn() {
        window.repaint();
        currentState = "printHand";
        System.out.print("-----------------------------------------------\n");
        System.out.print("It is " + currentPlayer.getName() + "'s turn. \n");
        System.out.print("Your current hand is:\n ");

        // This prints out the current players hand.
        currentPlayer.printHand();

        // Checks if the other player has the card.
        fishingTime();
    }

    // This is the request method, it takes in user input of what they want to request and returns it as a String.
    public String request() {
        System.out.println("What would you like to request?");
        String requestedCard = newObject.nextLine();
        return requestedCard;
    }

    // This method takes in an index and removes it from the given hand of cards.
    public Card take(int index, ArrayList<Card> hand) {
        return hand.remove(index);
    }

    // This method plays the game continuously.
    public void play() {
        // This String is created to ensure that the player is ready to move on before starting the next turn.
        String s;
        dealCards();

        // This keeps the game going while both people still have cards.
        while (p1.handSize() > 0 && p2.handSize() > 0)
        {
            s = newObject.nextLine();
            if (s != null) {
                isItGoFish = false;
                switchCurrentPlayer();
                window.repaint();
                playTurn();
            }
        }
        // After a player has 0 cards, it sets the Person winner to the player with more points and sets gameOver to true.
        winner = checkPoints(p1, p2);
        gameOver = true;
        window.repaint();
        System.out.println(checkPoints(p1, p2) + " IS THE WINNER!");
    }

    // This method checks which player has more points.
    public String checkPoints(Player person1, Player person2) {
        if (person1.getPoints() > person2.getPoints()) {
            return person1.getName();
        }
        else {
            return person2.getName();
        }
    }

    // This method prints the instructions.
    public static void printInstructions() {
        System.out.println("Instructions: \nThe goal of the game is to get a set of 4. See who can get it first!");
        System.out.println("1. Player 1 asks Player 2 for a card of their choice\n2. Player 2 gives them the card if " +
                "they have it, if not, they said 'GO FISH!'\n3. Player 1 recieves the new card (either from Player 2 or " +
                "from the deck\n4. If a player gets 4 of a kind, those are removed from their deck and they win!");
    }

    public static void main(String[] args) {
        Game.printInstructions();
        Game myGame = new Game();
        myGame.play();
    }
}
