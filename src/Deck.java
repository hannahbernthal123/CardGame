// Hannah - 2023
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int cardsLeft;

    // This is the deck constructor taking in an array of ranks, array of suits, and a point value.
    public Deck(String[] rank, String[] suit, int point) {
        int count = 1;
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < suit.length; j++) {
                Card tempCard = new Card(suit[j], rank[i], 0, new ImageIcon("Resources/Cards/" + Integer.toString(count) + ".png").getImage());
                cards.add(tempCard);
                count++;
            }
        }

        int a = 1;
        shuffle();
        cardsLeft = cards.size();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    // This deals the array list "cards" out to the players.
    // It also checks to make sure the deck isn't empty before dealing.
    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.remove(cardsLeft);
    }

    // This shuffles the array list "cards".
    public void shuffle() {
        cardsLeft = cards.size();
        for (int i = cards.size() - 1; i >= 0; i--)
        {
            int r = (int) (Math.random() * i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
    }
}
