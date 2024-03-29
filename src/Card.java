import javax.swing.*;
import java.awt.*;

// Hannah - 2023
public class Card {
    // These are the instance variables.
    String rank;
    private String suit;
    private int point;
    private CardGameView card;

    private Image thisCardImage;



    // This is the card constructor that takes in each card's rank, suit, and point value.
    public Card(String mySuit, String myRank, int myPoint, Image image) {
        rank = myRank;
        suit = mySuit;
        point = myPoint;
        thisCardImage = image;

    }

    // These 3 getter methods return the variable being asked for.
    public String getRank() {
        return rank;
    }

    public Image getImage() {
        return thisCardImage;
    }

    public String getSuit() {
        return suit;
    }

    public int point() {
        return point;
    }

    // These 3 setter methods return nothing, they update the variable with the passed in input.
    public void setRank(String other) {
        rank = other;
    }

    public void setPoint(int other) {
        point = other;
    }


    public void setSuit(String other) {
        // This line of code makes sure that the suit it is being set to is a real suit.
        if (other.equals("clubs") || other.equals("spades") || other.equals("hearts") || other.equals("diamonds")) {
            suit = other;
        }
    }

    // This toString method returns a String in the form of "10 of hearts".
    public String toString() {
        return rank + " of " + suit;
    }

    public void draw(Graphics g, int x) {
        g.drawImage(thisCardImage, 100 + x, 200, 100, 130, card);
    }

}
