//: c08:Callbacks.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using inner classes for callbacks

interface Incrementable {
  void increment();
}

class Callee {
  private int i = 0;
  private void incr() { 
    i++;
    System.out.println(i);
  }
  private class Closure implements Incrementable {
    public void increment() { incr(); }
  }
  Incrementable getCallbackReference() {
    return new Closure();
  }
}

class Caller {
  private Incrementable callbackReference;
  Caller(Incrementable cbh) {
    callbackReference = cbh;
  }
  void go() {
    callbackReference.increment();
  }
}

public class Callbacks {
  public static void main(String[] args) {
    Callee c = new Callee();
    Caller cc = 
      new Caller(c.getCallbackReference());
    cc.go();
    cc.go();
  }
} ///:~