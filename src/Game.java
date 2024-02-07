//Hannah - 2023
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private Deck deck;
    private Player p1;
    private Player p2;
    int turn = 1;

    public Game() {

        String[] rank = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Hearts", "Spades", "Diamonds", "Clubs"};

        Scanner newObject = new Scanner(System.in);
        System.out.println("Player 1, enter your name: ");
        String name1 = newObject.nextLine();

        System.out.println("Player 2, enter your name: ");
        String name2 = newObject.nextLine();

        p1 = new Player(name1);
        p2 = new Player(name2);

        //value
        deck = new Deck(rank, suit, 0);
    }

    public static void main(String[] args) {
        Game.printInstructions();
        Game myGame = new Game();
        myGame.play();
    }

    public void playTurn() {
        Player currentPlayer;
        Player opp;
        if (turn == 1)
        {
            currentPlayer = p1;
            opp = p2;
            turn = 2;
        }
        else {
            currentPlayer = p2;
            opp = p1;
            turn = 1;
        }

        System.out.print("-----------------------------------------------\n");
        System.out.print("It is " + currentPlayer.getName() + "'s turn. \n");
        System.out.print("Your current hand is:\n ");
        currentPlayer.printHand();


        //execute request by taking cards
        String request = currentPlayer.request();
        if (opp.checkCards(request) != -1) {
            currentPlayer.addCard(opp.take(opp.checkCards(request)));
            currentPlayer.checkTrick();
        }
        else {
            currentPlayer.checkTrick();
            currentPlayer.addCard(deck.deal());
            System.out.println("Go Fish!");
        }

    }
    public void play() {
        deck.shuffle();
        dealCards();

        while (p1.handSize() > 0 && p2.handSize() > 0)
        {
            playTurn();
        }

        if (p1.getPoints() > p2.getPoints()) {
            System.out.println(p1.getName() + " IS THE WINNER!");
        }
        else {
            System.out.println(p2.getName() + " IS THE WINNER!");
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
}
