
//Kalah player
public abstract class KalahPlayer {
	private String playerName;

	/**
	 * @param name player's name
	 */
	public KalahPlayer (String name) {
		playerName = name;
	}

	/**
	 * @return the player's name
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * Gets and returns the player's choice of move
	 * @param state current game state
	 * @param view the object that displays the game
	 * @return move chosen by the player
	 */
	public abstract int getMove(KalahGame state, KalahView view);
}
