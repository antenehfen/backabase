
import java.util.Scanner;
import java.util.InputMismatchException;

public class KalahViewText implements KalahView {

    private Scanner input;

    public KalahViewText (){
        input = new Scanner(System.in);
    }

    @Override
    public void display (KalahGame state)
    // Postcondition:  Displays the current board
    {
        int [] board = state.getBoard();
        KalahPlayer [] players = state.getPlayers();

        System.out.println();
        System.out.println("            " + players[1].getName());
        System.out.println();
        System.out.print("     ");
        for (int i = KalahGame.NUMPITS - 1; i > KalahGame.HALFPITS; i--)
            System.out.print(CSJavaLib.padIntRight(board[i], 4));
        System.out.println();

        System.out.println(CSJavaLib.padIntRight(board[0], 5) +
                "                          " + CSJavaLib.padIntRight(board[KalahGame.HALFPITS], 5));

        System.out.print("     ");
        for (int i = 1; i <= KalahGame.SIDEPITS; i++)
            System.out.print(CSJavaLib.padIntRight(board[i], 4));
        System.out.println();

        System.out.println();
        System.out.println("            " + players[0].getName());
    }

    @Override
    public int getUserMove(KalahGame state) {
        int pit;  // The pit under consideration
        int [] board = state.getBoard();

        System.out.println();

        pit = getIntAnswer("Pit to empty, " + state.getPlayerToMove().getName() + "? ");

        while ((pit < 1) || (pit > KalahGame.SIDEPITS) ||
                (board[pit + state.getPlayerNum()*KalahGame.HALFPITS] == 0))
        {
            System.out.println("Illegal move.  Try again.");
            pit = getIntAnswer("Pit to empty? ");
        }

        return pit + state.getPlayerNum()*KalahGame.HALFPITS; // Adjust to player's side

    }

    @Override
    public void reportMove (int bestMove, String name) {
        System.out.println("\n" + name + " empties pit " + bestMove);
    }


    @Override
    public int getIntAnswer(String question) {
        int answer = 0;
        boolean valid = false;

        // Ask for a number
        System.out.print(question + " ");
        while(!valid) try {
            answer = input.nextInt();
            ;
            valid = true;   // If got to here we have a valid integer
        } catch (InputMismatchException ex) {
            reportToUser("That was not a valid integer");
            valid = false;
            input.nextLine();  // Throw away the rest of the line
            System.out.print(question + " ");
        }
        input.nextLine();  // Throw away the rest of the line

        return answer;
    }

    @Override
    public void reportToUser(String message) {
        // Reports something to the user
        System.out.println(message);
    }

    @Override
    public String getAnswer(String message) {
        System.out.print(message);
        return input.nextLine();
    }
}
