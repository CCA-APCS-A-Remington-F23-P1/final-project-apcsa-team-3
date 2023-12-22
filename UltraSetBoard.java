import java.util.List;
import java.util.ArrayList;


public class UltraSetBoard extends Board {

  private static final int BSIZE = 12;

  private static final int[] NUM = {1, 2, 3};
  private static final String[] FILL = {"S", "E", "Z"};
  private static final String[] COLOR = {"R", "G", "P"};
  private static final String[] SHAPE = {"O", "D", "S"};

  public UltraSetBoard() 
  {
    super(BSIZE, NUM, FILL, COLOR, SHAPE);
  }

  @Override
  public boolean valid(List<Integer> selected) 
  {
    if (selected.size() == 4) {
      if (isSet(selected)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean another() 
  {
    List<Integer> indexes = cardIndexes();
    for (int i = 0; i < indexes.size()-2; i++) {
      for (int j = i+1; j < indexes.size()-1; j++) {
        for (int k = j+1; k < indexes.size(); k++) {
          for (int l = k+1; l < indexes.size(); l++) {
            List<Integer> selected = new ArrayList<Integer>(4);
            selected.add(indexes.get(i));
            selected.add(indexes.get(j));
            selected.add(indexes.get(k));
            selected.add(indexes.get(l));
            if (isSet(selected)) {
              return true;
            }
          }
        }
      }
    }
  
    return true;
  }

  private boolean isSet(List<Integer> selectedCards) 
  {

    if (selectedCards.size() != 4) {
      return false;
    }

    Card a = cardAt(selectedCards.get(0));
    Card b = cardAt(selectedCards.get(1));
    Card c = cardAt(selectedCards.get(2));
    Card d = cardAt(selectedCards.get(3));

    if (finishSet(a, b).equals(finishSet(c, d))) {
      return true;
    }

    if (finishSet(a, c).equals(finishSet(b, d))) {
      return true;
    }

    if (finishSet(a, d).equals(finishSet(b, c))) {
      return true;
    }


    System.out.println("Not an UltraSet");
    return false;
  }

  private Card finishSet(Card x, Card y) {
    Card z = new Card(x.getNumber(), x.getShape(), x.getFill(), x.getColor());
    String[] shapes = {"O", "D", "S"};
    String[] fills = {"E", "Z", "S"};
    String[] colors = {"R", "G", "P"};
    
    if (x.getNumber() == y.getNumber()) {
      z.setNumber(x.getNumber());
    }
    else if (x.getNumber() != y.getNumber()) {
      z.setNumber(6-x.getNumber()-y.getNumber());
    }

    if (x.getShape() == y.getShape()) {
      z.setShape(x.getShape());
    }
    else if (x.getShape() != y.getShape()) {
      for (String s : shapes) {
        if (s != x.getShape() && s != y.getShape()) {
          z.setShape(s);
        }
      }
    }

    if (x.getFill() == y.getFill()) {
      z.setFill(x.getFill());
    }
    else if (x.getFill() != y.getFill()) {
      for (String f : fills) {
        if (f != x.getFill() && f != y.getFill()) {
          z.setFill(f);
        }
      }
    }

    if (x.getColor() == y.getColor()) {
      z.setColor(x.getColor());
    }
    else if (x.getColor() != y.getColor()) {
      for (String c : colors) {
        if (c != x.getColor() && c != y.getColor()) {
          z.setColor(c);
        }
      }
    }

    return z;
  }

}
