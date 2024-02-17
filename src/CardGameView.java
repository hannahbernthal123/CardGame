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
        // First, this makes the screen white each time you repaint.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // If the game mode is set for instructions, this prints the instructions.
        if (game.getCurrentState().equals("instructions")) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("font1", Font.LAYOUT_RIGHT_TO_LEFT, 10));
            g.drawString("Instructions: \nThe goal of the game is to get a set of 4. See who can get it first!", 90, 100);
            g.drawString("1. Player 1 asks Player 2 for a card of their choice", 90, 150);
            g.drawString("2. Player 2 gives them the card if they have it, if not, they said 'GO FISH!", 90, 200);
            g.drawString("3. Player 1 recieves the new card (either from Player 2 or from the deck", 90, 250);
            g.drawString("4. If a player gets 4 of a kind, those are removed from their deck and they win!", 90, 300);

        }
        // This prints the current player's hand.
        if (game.getCurrentState().equals("printHand")) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("font1", Font.ITALIC, 20));
            g.drawString(game.getPlayerName(game.getTurn()) + "'s current hand is: ", 90, 100);
            for (int i = 0; i < game.getCurrentPlayer().getHand().size(); i++) {
                card = game.getCurrentPlayer().getHand().get(i);
                card.draw(g, 100*i);
            }

            // If it is fishing time, this prints go fish.
            if (game.getFishingTime() == true) {
                g.setColor(Color.GREEN);
                g.setFont(new Font("font1", Font.ITALIC, 60));
                g.drawString("Go Fish!", 100, 450);
            }

            // If the game is over, this prints who wins.
            if (game.isGameOver() == true) {
                if ((game.getWinner().equals(game.getPlayerName(1))) || (game.getWinner().equals(game.getPlayerName(2)))) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                    g.setColor(Color.PINK);
                    g.setFont(new Font("font1", Font.ITALIC, 40));
                    g.drawString(game.getWinner() + " wins!", 200, 200);
                }
            }
        }
    }
}
