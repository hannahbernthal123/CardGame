import javax.swing.*;
import java.awt.*;

public class CardGameView extends JFrame {

    private Game game;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 500;

    private Card card;
    public CardGameView(Game g) {
        this.game = g;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Card Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        if (game.getCurrentState().equals("instructions")) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("font1", Font.LAYOUT_RIGHT_TO_LEFT, 10));
            g.drawString("Instructions: \nThe goal of the game is to get the most points. You get points by getting a " +
                    "4 of a kind. Keep playing until someone runs out of cards!", 90, 100);
            g.drawString("1. Player 1 asks Player 2 for a card of their choice", 90, 150);
            g.drawString("2. Player 2 gives them the card if they have it, if not, they said 'GO FISH!", 90, 200);
            g.drawString("3. Player 1 recieves the new card (either from Player 2 or from the deck", 90, 250);
            g.drawString("4. If a player gets 4 of a kind, those are removed from their deck and gets a point", 90, 300);
            g.drawString("5. When someone runs out of cards, whoever has the most points wins!", 90, 350);

        }
        // Step 2: print current hand with name
        if (game.getCurrentState().equals("printHand")) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("font1", Font.ITALIC, 20));
            g.drawString(game.getPlayerName(game.getTurn()) + "'s current hand is: ", 90, 100);
            for (int i = 0; i < game.getCurrentPlayer().getHand().size(); i++) {
                card = game.getCurrentPlayer().getHand().get(i);
                card.draw(g, 100*i);
            }

        // Step 3: go fish
            if (game.getFishingTime() == true) {
                g.setColor(Color.GREEN);
                g.setFont(new Font("font1", Font.ITALIC, 60));
                g.drawString("Go Fish!", 100, 450);
            }
        }

        // Step 4: if there is a winner, print who wins
        if (game.getCurrentState().equals("win")) {
            if ((game.getWinner().equals(game.getPlayerName(1))) || (game.getWinner().equals(game.getPlayerName(2)))) {
                g.setColor(Color.PINK);
                g.setFont(new Font("font1", Font.ITALIC, 40));
                g.drawString(game.getWinner() + " wins!", 200, 200);
            }
        }
    }
}
