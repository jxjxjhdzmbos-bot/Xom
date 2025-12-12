package com.xo.game;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private TextView tvStatus;
    private Button btnPlayAgain;
    private Button btnMainMenu;
    
    private String[] board = new String[9];
    private boolean isPlayerXTurn = true;
    private boolean gameOver = false;
    private boolean vsAI = false;
    private boolean isAIThinking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        vsAI = getIntent().getBooleanExtra("VS_AI", false);

        tvStatus = findViewById(R.id.tvStatus);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnMainMenu = findViewById(R.id.btnMainMenu);

        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        buttons[4] = findViewById(R.id.btn4);
        buttons[5] = findViewById(R.id.btn5);
        buttons[6] = findViewById(R.id.btn6);
        buttons[7] = findViewById(R.id.btn7);
        buttons[8] = findViewById(R.id.btn8);

        for (int i = 0; i < 9; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCellClicked(index);
                }
            });
        }

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resetGame();
    }

    private void onCellClicked(int index) {
        if (gameOver || board[index] != null || isAIThinking) {
            return;
        }

        makeMove(index);

        if (!gameOver && vsAI && !isPlayerXTurn) {
            isAIThinking = true;
            tvStatus.setText(R.string.ai_thinking);
            disableAllButtons();
            
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    aiMove();
                    isAIThinking = false;
                    enableAllButtons();
                }
            }, 500);
        }
    }

    private void makeMove(int index) {
        String player = isPlayerXTurn ? "X" : "O";
        board[index] = player;
        buttons[index].setText(player);
        buttons[index].setTextColor(isPlayerXTurn ? 
            getResources().getColor(R.color.player_x) : 
            getResources().getColor(R.color.player_o));

        if (checkWinner()) {
            gameOver = true;
            String winner = isPlayerXTurn ? 
                getString(R.string.player_x_wins) : 
                getString(R.string.player_o_wins);
            tvStatus.setText(winner);
            highlightWinningCells();
            disableAllButtons();
        } else if (isBoardFull()) {
            gameOver = true;
            tvStatus.setText(R.string.draw);
            disableAllButtons();
        } else {
            isPlayerXTurn = !isPlayerXTurn;
            updateStatusText();
        }
    }

    private void aiMove() {
        int bestMove = findBestMove();
        if (bestMove != -1) {
            makeMove(bestMove);
        }
    }

    private int findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == null) {
                board[i] = "O";
                int score = minimax(0, false);
                board[i] = null;

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    private int minimax(int depth, boolean isMaximizing) {
        String result = evaluateBoard();
        
        if (result != null) {
            if (result.equals("O")) return 10 - depth;
            if (result.equals("X")) return depth - 10;
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == null) {
                    board[i] = "O";
                    int score = minimax(depth + 1, false);
                    board[i] = null;
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == null) {
                    board[i] = "X";
                    int score = minimax(depth + 1, true);
                    board[i] = null;
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    private String evaluateBoard() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : winPatterns) {
            String first = board[pattern[0]];
            if (first != null && 
                first.equals(board[pattern[1]]) && 
                first.equals(board[pattern[2]])) {
                return first;
            }
        }

        boolean full = true;
        for (int i = 0; i < 9; i++) {
            if (board[i] == null) {
                full = false;
                break;
            }
        }

        return full ? "draw" : null;
    }

    private boolean checkWinner() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : winPatterns) {
            String first = board[pattern[0]];
            if (first != null && 
                first.equals(board[pattern[1]]) && 
                first.equals(board[pattern[2]])) {
                return true;
            }
        }

        return false;
    }

    private void highlightWinningCells() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : winPatterns) {
            String first = board[pattern[0]];
            if (first != null && 
                first.equals(board[pattern[1]]) && 
                first.equals(board[pattern[2]])) {
                buttons[pattern[0]].setBackgroundColor(getResources().getColor(R.color.accent));
                buttons[pattern[1]].setBackgroundColor(getResources().getColor(R.color.accent));
                buttons[pattern[2]].setBackgroundColor(getResources().getColor(R.color.accent));
                break;
            }
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == null) {
                return false;
            }
        }
        return true;
    }

    private void updateStatusText() {
        String player = isPlayerXTurn ? getString(R.string.player_x) : getString(R.string.player_o);
        tvStatus.setText(player + getString(R.string.turn));
    }

    private void disableAllButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void enableAllButtons() {
        for (int i = 0; i < buttons.length; i++) {
            if (board[i] == null) {
                buttons[i].setEnabled(true);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = null;
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackgroundResource(R.drawable.cell_bg);
        }

        isPlayerXTurn = true;
        gameOver = false;
        isAIThinking = false;
        updateStatusText();
    }
}
