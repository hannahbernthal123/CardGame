import java.util.ArrayList;
import java.util.Scanner;

// Hannah - 2023
public class Player {
    private ArrayList<Card> hand;
    int points;
    String name;


    public Player(String myName) {
        name = myName;
        points = 0;
        hand = new ArrayList<Card>();
    }

    // This is a secondary player constructor in case someone wants to create a new player with a specified hand.
    public Player(String myName, ArrayList<Card> myHand) {
        name = myName;
        hand = myHand;
        points = 0;
    }
    public int getPoints() {
        return points;
    }

    public void printHand() {
        System.out.println(hand);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int otherPoints) {
        points += otherPoints;
    }

    public boolean checkTrick() {
        ArrayList<Card> removers = new ArrayList<Card>();
        // This indexes through an arraylist of cards and checks to see if there are 4 cards with the same rank.
        for (Card card1 : hand) {
            // This clears the removers arrayList so that every time it is called, it checks for a fresh new set of 4 cards.
            removers.clear();
            for (Card card2 : hand) {
                if (card1.getRank().equals(card2.getRank())) {
                    removers.add(card2);
                }
            }
            // If there are four cards with the same rank, it removes those cards from the hand and adds a point to that player.
            if (removers.size() == 4) {
                for (int i = 0; i < 4; i++) {
                    hand.remove(removers.get(i));
                    points++;
                }
                return true;

            }
        }
        return false;
    }


    public int checkCards(String rank) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRank().equals(rank)) {
                return i;
            }
        }
        return -1;
    }


    public int handSize() {
        return hand.size();
    }

    public void addCard(Card otherCard) {
        hand.add(otherCard);
    }


    public String toString() {
        return name + "has" + points + "points\n" + name + "'s cards:"
                + hand;
    }

}
