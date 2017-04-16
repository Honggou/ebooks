//: c11:DirList3.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Building the anonymous inner class "in-place."
import java.io.*;
import java.util.*;
import com.bruceeckel.util.*;

public class DirList3 {
  public static void main(final String[] args) {
    File path = new File(".");
    String[] list;
    if(args.length == 0)
      list = path.list();
    else 
      list = path.list(new FilenameFilter() {
        public boolean 
        accept(File dir, String n) {
          String f = new File(n).getName();
          return f.indexOf(args[0]) != -1;
        }
      });
    Arrays.sort(list,
      new AlphabeticComparator());
    for(int i = 0; i < list.length; i++)
      System.out.println(list[i]);
  }
} ///:~