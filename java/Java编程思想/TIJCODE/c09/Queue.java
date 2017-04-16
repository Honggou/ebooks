//: c09:Queue.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Making a queue from a LinkedList.
import java.util.*;

public class Queue {
  private LinkedList list = new LinkedList();
  public void put(Object v) { list.addFirst(v); }
  public Object get() { 
    return list.removeLast(); 
  }
  public boolean isEmpty() { 
    return list.isEmpty(); 
  }
  public static void main(String[] args) {
    Queue queue = new Queue();
    for(int i = 0; i < 10; i++)
      queue.put(Integer.toString(i));
    while(!queue.isEmpty())
      System.out.println(queue.get());
  }
} ///:~