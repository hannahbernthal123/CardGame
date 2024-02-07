//Hannah - 2023
public class Card {
    //These are the instance variables
    String rank;
    private String suit;
    private int point;

    //These are the instance variables
    public Card(String myRank, String mySuit, int myPoint) {
        rank = myRank;
        suit = mySuit;
        point = myPoint;
    }

    //These 3 getter methods return the variable being asked for
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int point() {
        return point;
    }

    //These 3 setter methods return the variable being changed
    public void setRank(String other) {
        rank = other;
    }

    public void setPoint(int other) {
        point = other;
    }


    public void setSuit(String other) {
        if (other.equals("clubs") || other.equals("spades") || other.equals("hearts") || other.equals("diamonds")) {
            suit = other;
        }
    }

    public String toString() {
        return rank + " of " + suit;
    }

}
