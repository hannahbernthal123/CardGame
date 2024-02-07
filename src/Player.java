import java.util.ArrayList;
import java.util.Scanner;

//Hannah - 2023
public class Player {
    private ArrayList<Card> hand;
    int points;
    String name;


    public Player(String myName) {
        name = myName;
        points = 0;
        hand = new ArrayList<Card>();
    }

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

    public String getName() {
        return name;
    }

    public void addPoints(int otherPoints) {
        points += otherPoints;
    }

    public boolean checkTrick() {
        ArrayList<Card> removers = new ArrayList<Card>();
        for (Card card1 : hand) {
            removers.clear();
            for (Card card2 : hand) {
                if (card1.getRank().equals(card2.getRank())) {
                    removers.add(card2);
                }
            }
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


    public int checkCards(String other) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRank().equals(other)) {
                return i;
            }
        }
        return -1;
    }

    public Card take(int other) {
        return hand.remove(other);
    }

    public int handSize() {
        return hand.size();
    }

    public void addCard(Card otherCard) {
        hand.add(otherCard);
    }

    public String request() {
        System.out.println("What would you like to request?");
        Scanner newObject = new Scanner(System.in);
        String requestedCard = newObject.nextLine();
        return requestedCard;
    }

    public String toString() {
        return name + "has" + points + "points\n" + name + "'s cards:"
                + hand;
    }

}
