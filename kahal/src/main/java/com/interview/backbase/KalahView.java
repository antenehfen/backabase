
public interface KalahView {


    public void display (KalahGame state);

    public int getUserMove(KalahGame state);

    public void reportMove (int chosenMove, String name);

    public int getIntAnswer (String question);


    public void reportToUser(String message);

    public String getAnswer(String question);
}
