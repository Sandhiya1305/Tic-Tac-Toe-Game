import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    // Create a 3x3 grid for buttons (the tic-tac-toe board)
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true; // True = X's turn, False = O's turn
    private int movesCount = 0;

    public TicTacToeGame() {
        // Set the frame properties
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the panel for the buttons (the board)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Initialize the 3x3 grid of buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                panel.add(buttons[row][col]);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
            }
        }

        // Add the panel to the frame
        add(panel);
    }

    // Inner class for handling button clicks
    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if the button is already clicked
            if (!buttons[row][col].getText().equals("")) {
                return;
            }

            // Set the button text to X or O based on whose turn it is
            if (playerXTurn) {
                buttons[row][col].setText("X");
            } else {
                buttons[row][col].setText("O");
            }

            // Increase the move count
            movesCount++;

            // Check for a win or draw
            if (checkWinner()) {
                JOptionPane.showMessageDialog(null, (playerXTurn ? "X" : "O") + " wins!");
                resetBoard();
            } else if (movesCount == 9) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                resetBoard();
            }

            // Switch turns
            playerXTurn = !playerXTurn;
        }
    }

    // Check if there is a winner
    private boolean checkWinner() {
        // Check rows and columns for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().equals("")) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        // Check diagonals for a win
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }

    // Reset the board for a new game
    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        movesCount = 0;
        playerXTurn = true;
    }

    public static void main(String[] args) {
        // Run the game UI
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}
