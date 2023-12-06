import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Set{
  private int score;
  private int highScore;
  private Board board;
  private Deck deck;
  
  public Set() {
    
  }


  public boolean isSet(Card a, Card b, Card c) {
    //determines whether the three cards make a set
  }
  
  public int highScore(int score, int highScore) {
    highScore = (Math.max(score, highScore));
    return highScore;
  }



  
} 
