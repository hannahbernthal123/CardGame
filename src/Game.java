// Hannah - 2023
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private Deck deck;
    private Player p1;
    private Player p2;
    private int turn;
    private boolean isItGoFish;
    private String winner;
    private CardGameView window;
    private Player currentPlayer;
    private Player opposingPlayer;
    private String currentState;
    private Scanner newObject;
    private boolean gameOver;

    public Game() {
        // This sets up two String arrays for the accepted ranks and suits.
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};

        // This sets the turn to Player one's turn.
        turn = 1;
        gameOver = false;

        // This initializes a new deck, passing in the two String arrays and a point value of 0.
        deck = new Deck(rank, suit, 0);

        isItGoFish = false;
        winner = "";
        currentState = "instructions";
        window = new CardGameView(this);

        newObject = new Scanner(System.in);
        System.out.println("Player 1, enter your name: ");
        String name1 = newObject.nextLine();

        System.out.println("Player 2, enter your name: ");
        String name2 = newObject.nextLine();

        // These are the instance variables.
        /// This initializes the two players.
//        p1 = new Player(name1);
//        p2 = new Player(name2);
//        currentPlayer = p1;
//        opposingPlayer = p2;

//        Use this to test a win.
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        Card hello = new Card( "hearts","3", 0, new ImageIcon("Resources/Cards/10.png").getImage());
        Card hi = new Card( "spades","3", 0, new ImageIcon("Resources/Cards/9.png").getImage());
        Card what = new Card( "clubs","3", 0, new ImageIcon("Resources/Cards/12.png").getImage());
        Card you = new Card( "diamonds","3", 0, new ImageIcon("Resources/Cards/11.png").getImage());

        hand1.add(hello);
        hand1.add(hi);
        hand1.add(what);
        hand2.add(you);

        p1 = new Player(name1, hand1);
        p2 = new Player(name2, hand2);
        currentPlayer = p1;
        opposingPlayer = p2;
    }

    public boolean getFishingTime() {
        return isItGoFish;
    }

    public int getTurn() {
        return turn;
    }

    public String getWinner() {
        return winner;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpposingPlayer() {
        return opposingPlayer;
    }
    public String getCurrentState() {
        return currentState;
    }

    public boolean fishingTime() {
        // Then, this part of the method takes in the String that the player is requested and checks it against the other player's deck.
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
            currentPlayer.checkTrick();
            // If the other player does not have the requested card, they are dealt a random card from the deck.
            currentPlayer.addCard(deck.deal());
            System.out.println("Go Fish!");
            isItGoFish = true;
            window.repaint();
            return true;
        }
    }

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

    public String request() {
        System.out.println("What would you like to request?");
        String requestedCard = newObject.nextLine();
        return requestedCard;
    }

    public Card take(int other, ArrayList<Card> hand) {
        return hand.remove(other);
    }

    public void play() {
        String s;
        //dealCards();

        // Runs a turn while both players still have cards.
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

    public void dealCards() {
        for (int i = 0; i < 5; i++)
        {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    public static void printInstructions() {
        System.out.println("Instructions: \nThe goal of the game is to get the most points. You get points by getting a " +
                "4 of a kind. Keep playing until someone runs out of cards!");
        System.out.println("1. Player 1 asks Player 2 for a card of their choice\n2. Player 2 gives them the card if " +
                "they have it, if not, they said 'GO FISH!'\n3. Player 1 recieves the new card (either from Player 2 or " +
                "from the deck\n4. If a player gets 4 of a kind, those are removed from their deck and gets a point\n5. " +
                "When someone runs out of cards, whoever has the most points wins!");
    }

    public static void main(String[] args) {
        Game.printInstructions();
        Game myGame = new Game();
        myGame.play();
    }
}
