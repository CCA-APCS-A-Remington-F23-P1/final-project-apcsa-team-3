import java.util.Timer;
import java.util.TimerTask;

public class GravitySetBoard extends SetBoard{

  Timer t;
  TimerTask task;
  private boolean timesUp;
  private final long timeLimit = 20000;

  private long startTime;
  
  public GravitySetBoard(){  
    super(); 
    t = new Timer();
    task = new TimerTask(){
      public void run(){
        timesUp = true;
      }
    }
  }
  public void startTimer(){
    startTime = System.currentTimeMillis();
    t.schedule(task, timeLimit);
  }

  public long getTimeLeft(){
    return System.currentTimeMillis() - startTime;
  }

  @Override
  public boolean another(){
    boolean n = false;
    if(getTimeLeft() > timeLimit){
      n = super.another();
    }
    return n;
  }
}
