// credits to Elevens project for method(s) code/ideas


import java.util.List;
import java.util.ArrayList;

public class SetBoard extends Board {

  private static final int BSIZE = 12;

  private static final int[] NUM = {1, 2, 3};
  private static final String[] FILL = {"S", "E", "Z"};
  private static final String[] COLOR = {"R", "G", "P"};
  private static final String[] SHAPE = {"O", "D", "S"};

  public SetBoard() 
  {
    super(BSIZE, NUM, FILL, COLOR, SHAPE);
  }

  @Override
  public boolean valid(List<Integer> selected) 
  {
    if (selected.size() == 3) {
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
          List<Integer> selected = new ArrayList<Integer>(3);
          selected.add(indexes.get(i));
          selected.add(indexes.get(j));
          selected.add(indexes.get(k));
          if (isSet(selected)) {
            return true;
          }
        }
      }
    }
  
    return false;
  }

  private boolean isSet(List<Integer> selectedCards) 
  {

    if (selectedCards.size() != 3) {
      return false;
    }

    Card a = cardAt(selectedCards.get(0));
    Card b = cardAt(selectedCards.get(1));
    Card c = cardAt(selectedCards.get(2));

    if (!((a.getNumber() == b.getNumber() && a.getNumber() == c.getNumber()) || (a.getNumber() != b.getNumber() && a.getNumber() != c.getNumber() && b.getNumber() != c.getNumber()))) {
      System.out.println("Not a set");
      return false;
    }

    if (!((a.getShape() == b.getShape() && a.getShape() == c.getShape()) || (a.getShape() != b.getShape() && a.getShape() != c.getShape() && b.getShape() != c.getShape()))) {
      return false;
    }

    if (!((a.getFill() == b.getFill() && a.getFill() == c.getFill()) || (a.getFill() != b.getFill() && a.getFill() != c.getFill() && b.getFill() != c.getFill()))) {
      return false;
    }

    if (!((a.getColor() == b.getColor() && a.getColor() == c.getColor()) || (a.getColor() != b.getColor() && a.getColor() != c.getColor() && b.getColor() != c.getColor()))) {
      return false;
    }

    return true;
  }

}
