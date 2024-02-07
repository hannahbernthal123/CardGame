//Hannah - 2023
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int cardsLeft;

    public Deck(String[] rank, String[] suit, int point) {
        for (String currentSuit : suit) {
            for (int j = 0; j < rank.length; j++) {
                Card tempCard = new Card(rank[j], currentSuit, 0);
                cards.add(tempCard);
            }
        }
        cardsLeft = cards.size();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    //This deals the array list "cards"
    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        }

        cardsLeft--;
        return cards.remove(cardsLeft);
    }

    //This shuffles the array list "cards"
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
