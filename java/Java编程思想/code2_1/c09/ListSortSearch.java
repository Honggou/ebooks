//: c09:ListSortSearch.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Sorting and searching Lists with 'Collections.'
import com.bruceeckel.util.*;
import java.util.*;

public class ListSortSearch {
  public static void main(String[] args) {
    List list = new ArrayList();
    Collections2.fill(list, 
      Collections2.capitals, 25);
    System.out.println(list + "\n");
    Collections.shuffle(list);
    System.out.println("After shuffling: "+list);
    Collections.sort(list);
    System.out.println(list + "\n");
    Object key = list.get(12);
    int index = 
      Collections.binarySearch(list, key);
    System.out.println("Location of " + key + 
      " is " + index + ", list.get(" + 
      index + ") = " + list.get(index));
    AlphabeticComparator comp =
      new AlphabeticComparator();
    Collections.sort(list, comp);
    System.out.println(list + "\n");
    key = list.get(12);
    index = 
      Collections.binarySearch(list, key, comp);
    System.out.println("Location of " + key + 
      " is " + index + ", list.get(" + 
      index + ") = " + list.get(index));
  }
} ///:~