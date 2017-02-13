

public class KalahGame {
    private int[] board;
    private int playerToMoveNum;
    private KalahPlayer[] players;      // Array of the two players
    private KalahView view;         // Holds the view, so can update display

    public final static int INITSTONES = 6;        // Initial number of stones
    public final static int NUMPITS = 14;          // Number of pits total
    public final static int SIDEPITS = 6;          // Pits on one side of the board
    public final static int HALFPITS = 7;          // Half of the total number of pits


    public KalahGame(int playerNum, KalahPlayer[] thePlayers, KalahView aView) {
        int[] initBoard = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6};  // Original pit contents
        initialize(playerNum, thePlayers, initBoard);
        view = aView;
    }

    public KalahGame(int playerNum, KalahPlayer[] thePlayers, int[] initBoard) {
        initialize(playerNum, thePlayers, initBoard);
    }


    private void initialize(int playerNum, KalahPlayer[] thePlayers, int[] initBoard) {
        board = new int[NUMPITS];

        for (int i = 0; i < NUMPITS; i++) {
            board[i] = initBoard[i];
        }

        playerToMoveNum = playerNum;
        players = thePlayers;
        pitToHighlight = -1;  // Indicates no pit highlighted.
    }

    public int[] getBoard() {
        return board;
    }

    public KalahPlayer[] getPlayers() {
        return players;
    }

    public int getPlayerNum() {
        return playerToMoveNum;
    }

    public KalahPlayer getPlayerToMove() {
        return players[playerToMoveNum];
    }


    public void makeMove(int pit) {
        int playerKalah, opponentKalah;   // Hold the kalah numbers for the two players
        int stones;                       // Number of stones in pit

        // Decide which kalah belongs to the current player
        playerKalah = playerToMoveNum * HALFPITS;
        opponentKalah = HALFPITS - playerKalah;

        // Make the move
        stones = board[pit];
        board[pit] = 0;

        while (stones > 0) {
            pit--;
            if (pit == -1)                    // Handle wrapping around
                pit = NUMPITS - 1;
            if (pit != opponentKalah)         // Nothing dropped in opponent's kalah
            {
                board[pit]++;                   // Drop a stone in the pit
                stones--;
            }
        }

        // Handle move-again and captures

        if (pit != playerKalah) {           // If end in own kalah, move again
            if ((board[pit] == 1) && (board[NUMPITS - pit] > 0) &&
                    ((playerToMoveNum == 0 && (pit < HALFPITS)) ||
                            (playerToMoveNum == 1 && (pit > HALFPITS)))) {
                // Capture occurred.  (Messy test, wasn't it?)
                board[playerKalah] = board[playerKalah] + board[NUMPITS - pit] + 1;
                board[pit] = board[NUMPITS - pit] = 0;
            }
            playerToMoveNum = 1 - playerToMoveNum;    // Switch player
        }
    }
}