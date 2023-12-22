import java.util.List;
import java.util.ArrayList;

public abstract class Board 
{
  private Card[] cards;
  private Deck deck;

  public Board(int size, int[] num, String[] fill, String[] color, String[] shape) 
  {
    cards = new Card[size];
    deck = new Deck(num, fill, color, shape);
   
    dealMyCards();
  }

  public void newGame() 
  {
    deck.shuffle();
    dealMyCards();
  }

  public int size() 
  {
    return cards.length;
  }

  public boolean empty() 
  {
    for (int k = 0; k < cards.length; k++) {
      if (cards[k] != null) {
        return false;
      }
    }
    return true;
  }

  public void deal(int k) 
  {
    cards[k] = deck.deal();
  }

  public int deckSize() 
  {
    return deck.size();
  }

  public Card cardAt(int k) 
  {
    return cards[k];
  }

  public void replaceSelectedCards(List<Integer> selectedCards) 
  {
    for (Integer k : selectedCards) {
      deal(k.intValue());
    }
  }

  public List<Integer> cardIndexes() 
  {
    List<Integer> selected = new ArrayList<Integer>();
    for (int k = 0; k < cards.length; k++) {
      if (cards[k] != null) {
        selected.add(k);
      }
    }
    return selected;
  }

  public String toString() 
  {
    String s = "";
    for (int k = 0; k < cards.length; k++) {
      s = s + k + ": " + cards[k] + "\n";
    }
    return s;
  }

  public boolean gameIsWon() 
  {
    if (deck.empty()) {
      for (Card c : cards) {
        if (c != null) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  public abstract boolean valid(List<Integer> selectedCards);
  public abstract boolean another();


  private void dealMyCards() 
  {
    for (int k = 0; k < cards.length; k++) {
      cards[k] = deck.deal();
    }
  }
}

