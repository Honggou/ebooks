//: c07:Transmogrify.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Dynamically changing the behavior of
// an object via composition.

abstract class Actor {
  abstract void act();
}

class HappyActor extends Actor {
  public void act() { 
    System.out.println("HappyActor"); 
  }
}

class SadActor extends Actor {
  public void act() { 
    System.out.println("SadActor");
  }
}

class Stage {
  Actor a = new HappyActor();
  void change() { a = new SadActor(); }
  void go() { a.act(); }
}

public class Transmogrify {
  public static void main(String[] args) {
    Stage s = new Stage();
    s.go(); // Prints "HappyActor"
    s.change();
    s.go(); // Prints "SadActor"
  }
} ///:~