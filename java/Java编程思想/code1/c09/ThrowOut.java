//: ThrowOut.java
public class ThrowOut {
  public static void
  main(String[] args) throws Throwable {
    try {
      throw new Throwable(); 
    } catch(Exception e) {
      System.out.println("Caught in main()");
    }
  }
} ///:~