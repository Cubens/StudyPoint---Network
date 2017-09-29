
 
public class turnstileHandler {

  int count = 0;

    public turnstileHandler()
    {
    }



  public long getCount() {
    return count;
  }
   
  public synchronized void increment() {
    count++;
  } 
}
