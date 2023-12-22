
import java.util.Scanner;

public class SetGUIRunner 
{

  public static void main(String[] args) 
  {
    // Board board = new SetBoard();
    // CardGameGUI gui = new CardGameGUI(board, "set");
    Scanner a = new Scanner(System.in);
    System.out.println("To play Normal Set, enter 1. To play Ultra Set, enter 2. To play Gravity Set, enter 3: ");
    String n = a.next();
    if (n.equals("1")) {
      Board board = new SetBoard();
      CardGameGUI gui = new CardGameGUI(board, "set");
      gui.displayGame();
    } else if (n.equals("2")) {
      Board board = new UltraSetBoard();
      CardGameGUI gui = new CardGameGUI(board, "ultra");
      gui.displayGame();
    } else if (n.equals("3")) {
       Board board = new GravitySetBoard();
       CardGameGUI gui = new CardGameGUI(board, "gravity");
     gui.displayGame();
   }
    
    
  }
}
