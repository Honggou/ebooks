//: SortedDirList.java
// Displays sorted directory listing
import java.io.*;
import c08.*;

public class SortedDirList {
  private File path;
  private String[] list;
  public SortedDirList(final String afn) {
    path = new File(".");
    if(afn == null)
      list = path.list();
    else
      list = path.list(
          new FilenameFilter() {
            public boolean 
            accept(File dir, String n) {
              String f = new File(n).getName();
              return f.indexOf(afn) != -1;
            }
          });
    sort();
  }
  void print() {
    for(int i = 0; i < list.length; i++)
      System.out.println(list[i]);
  }
  private void sort() {
    StrSortVector sv = new StrSortVector();
    for(int i = 0; i < list.length; i++)
      sv.addElement(list[i]);
    // The first time an element is pulled from
    // the StrSortVector the list is sorted:
    for(int i = 0; i < list.length; i++)
      list[i] = sv.elementAt(i);
  }
  // Test it:
  public static void main(String[] args) {
    SortedDirList sd;
    if(args.length == 0)
      sd = new SortedDirList(null);
    else
      sd = new SortedDirList(args[0]);
    sd.print();
  }
} ///:~