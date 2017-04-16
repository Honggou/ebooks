//: DirList3.java
// Building the anonymous inner class "in-place"
import java.io.*;

public class DirList3 {
  public static void main(final String[] args) {
    try {
      File path = new File(".");
      String[] list;
      if(args.length == 0)
        list = path.list();
      else 
        list = path.list(
          new FilenameFilter() {
            public boolean 
            accept(File dir, String n) {
              String f = new File(n).getName();
              return f.indexOf(args[0]) != -1;
            }
          });
      for(int i = 0; i < list.length; i++)
        System.out.println(list[i]);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
} ///:~