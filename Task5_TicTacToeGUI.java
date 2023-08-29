import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task5_TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private JButton startButton;
    private JButton restartButton;
    private JLabel currentPlayerLabel;
    private JLabel scoreLabel;
    private char currentPlayer;
    private int roundsPlayed;
    private int playerXScore;
    private int playerOScore;

    public Task5_TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        roundsPlayed = 0;
        playerXScore = 0;
        playerOScore = 0;

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                buttonPanel.add(buttons[row][col]);
            }
        }

        startButton = new JButton("Start Game");
        startButton.addActionListener(new StartButtonListener());

        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new RestartButtonListener());
        restartButton.setEnabled(false);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(startButton);
        controlPanel.add(restartButton);

        currentPlayerLabel = new JLabel("Current Player: X");
        scoreLabel = new JLabel("Score - Player 1: " + playerXScore + "  Player 2: " + playerOScore);

        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.add(currentPlayerLabel);
        statusPanel.add(scoreLabel);

        add(buttonPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statusPanel, BorderLayout.NORTH);
    }

    private void checkWin() {
         // Check rows, columns, and diagonals for a win
         for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
                return;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
                return;
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetGame();
            return;
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetGame();
            return;
        }

        // Check for a draw
        boolean isDraw = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private void updateStatus() {
        currentPlayerLabel.setText("Current Player: " + currentPlayer);
        scoreLabel.setText("Score - Player 1: " + playerXScore + "  Player 2: " + playerOScore);
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] symbols = {"X", "O"};
            String selectedSymbol = (String) JOptionPane.showInputDialog(
                    Task5_TicTacToeGUI.this,
                    "Choose your symbol:",
                    "Symbol Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    symbols,
                    symbols[0]);

            if (selectedSymbol != null) {
                currentPlayer = selectedSymbol.charAt(0);
                startButton.setEnabled(false);
                restartButton.setEnabled(true);
                updateStatus();
            }
        }
    }

    private class RestartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame();
            startButton.setEnabled(true);
            restartButton.setEnabled(false);
            roundsPlayed = 0;
            playerXScore = 0;
            playerOScore = 0;
            updateStatus();
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().isEmpty()) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                checkWin();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task5_TicTacToeGUI game = new Task5_TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
