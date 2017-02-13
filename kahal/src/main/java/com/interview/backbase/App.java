package com.interview.backbase;


    public class App
    {

        public static void main(String args[])
        {
            int finalScore;
            KalahView view = new KalahViewGraphical();
            boolean updateIncrementally = true;

            KalahPlayer [] players = new KalahPlayer[2];                // Array to hold the players

            // Initialize the game

            players[0] = makePlayer(view, "first");
            players[1] = makePlayer(view, "second");

            // Hold current game state
            KalahGame state = new KalahGame(0, players, view);

            view.display(state);

            while (!state.gameIsOver())
            {
                int move = state.getPlayerToMove().getMove(state, view);
                if(updateIncrementally)
                    state.makeMoveIncrementally(move);
                else
                    state.makeMove(move);
                view.display(state);
            }

            finalScore = state.score();       // Get the score from current player's view

            if (finalScore > 0)
                view.reportToUser(players[state.getPlayerNum()].getName() + " wins by " + finalScore);
            else if (finalScore < 0)
                view.reportToUser(players[1 - state.getPlayerNum()].getName() + " wins by " + -finalScore);
            else
                view.reportToUser("It is a draw");
        }

        public static KalahPlayer makePlayer(KalahView view, String playerMsg) {
            String playerName = view.getAnswer("Enter the name of the " + playerMsg +
                    " player." + "\n(Include 'Computer' in the name of a computer player) ");
            if(playerName.contains("Computer")) {
                int depth = view.getIntAnswer("How far should I look ahead? ");
                return new ComputerKalahPlayerAB(playerName, depth);
            }
            else
                return new HumanKalahPlayer(playerName);
        }
    }


