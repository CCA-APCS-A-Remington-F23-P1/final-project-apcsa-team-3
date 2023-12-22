import java.util.List;
import java.util.ArrayList;


public class Deck {

  private List<Card> cards;
  private int size;

  public Deck(int[] num, String[] fill, String[] color, String[] shape) {
    cards = new ArrayList<>();
    for (int i = 0; i < num.length; i++) {
      for (int s = 0; s < fill.length; s++) {
        for (int x = 0; x < color.length; x++) {
          for (int j = 0; j< shape.length; j++) {
            cards.add(new Card(num[i], shape[j], fill[s], color[x]));
          }
        }
      }
    }
    size = cards.size();
    shuffle();
  }

  public boolean empty() {
    return size==0;
  }

  public int size() {
    return size;
  }

  public void shuffle() {
    for (int k = cards.size()-1; k >= 0; k--) {
      int j = (int) (Math.random()*cards.size());
      Card temp = cards.get(k);
      cards.set(k, cards.get(j));
      cards.set(j, temp);
    }
    size = cards.size();
  }

  public Card deal() {
    if (isEmpty()) {
      return null;
    }
    size--;
    Card c = cards.get(size);
    cards.remove(size);
    return c;

  }

  @Override
  public String toString() {
    String rtn = "size = " + size + "\nUndealt cards: \n";

    for (int k = size - 1; k >= 0; k--) {
      rtn = rtn + cards.get(k);
      if (k != 0) {
        rtn = rtn + ", ";
      }
      if ((size - k) % 2 == 0) {
        rtn = rtn + "\n";
      }
    }

    rtn = rtn + "\nDealt cards: \n";
    for (int k = cards.size() - 1; k >= size; k--) {
      rtn = rtn + cards.get(k);
      if (k != size) {
        rtn = rtn + ", ";
      }
      if ((k - cards.size()) % 2 == 0) {
        rtn = rtn + "\n";
      }
    }

    rtn = rtn + "\n";
    return rtn;
  }
}

