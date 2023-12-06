public class Card{

  private int number;
  private String shape;
  private String fill;

  public Card(int n, String s, String f){
    number = n;
    shape = s;
    fill = f;
  }

  public int getNumber(){
    return number;
  }
  public String getShape(){
    return shape;
  }
  public String getFill(){
    return fill;
  }

  public void setNumber(int n){
    number = n;
  }
  public void setShape(String s){
    shape = s;
  }
  public void setFill(String f){
    fill = f;
  }

  public boolean equals(Card other){
    return(number == other.getNumber() && shape.equals(other.getShape()) && fill.equals(other.getFill()));
  }

  public String toString(){
    return(number + " " + shape + "s filled with" + fill);
  }
  
}
