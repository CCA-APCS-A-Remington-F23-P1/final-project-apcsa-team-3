public class Card{

  private int number;
  private String shape;
  private String fill;
  private String color;

  public Card(int n, String s, String f, String c){
    number = n;
    shape = s;
    fill = f;
    color = c;
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
  public String getColor(){
    return color;
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
  public void setColor(String c){
    color = c;
  }

  public boolean equals(Card other){
    return(number == other.getNumber() && shape.equals(other.getShape()) && fill.equals(other.getFill()) && color.equals(other.getColor()));
  }

  public String toString(){
    return(number + " " + color + " " + shape + "s filled with" + fill);
  }

}
