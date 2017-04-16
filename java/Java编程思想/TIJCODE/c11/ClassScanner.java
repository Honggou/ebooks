//: c11:ClassScanner.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Scans all files in directory for classes
// and identifiers, to check capitalization.
// Assumes properly compiling code listings.
// Doesn't do everything right, but is a 
// useful aid.
import java.io.*;
import java.util.*;

class MultiStringMap extends HashMap {
  public void add(String key, String value) {
    if(!containsKey(key))
      put(key, new ArrayList());
    ((ArrayList)get(key)).add(value);
  }
  public ArrayList getArrayList(String key) {
    if(!containsKey(key)) {
      System.err.println(
        "ERROR: can't find key: " + key);
      System.exit(1);
    }
    return (ArrayList)get(key);
  }
  public void printValues(PrintStream p) {
    Iterator k = keySet().iterator();
    while(k.hasNext()) {
      String oneKey = (String)k.next();
      ArrayList val = getArrayList(oneKey);
      for(int i = 0; i < val.size(); i++)
        p.println((String)val.get(i));
    }
  }
}

public class ClassScanner {
  private File path;
  private String[] fileList;
  private Properties classes = new Properties();
  private MultiStringMap 
    classMap = new MultiStringMap(),
    identMap = new MultiStringMap();
  private StreamTokenizer in;
  public ClassScanner() throws IOException {
    path = new File(".");
    fileList = path.list(new JavaFilter());
    for(int i = 0; i < fileList.length; i++) {
      System.out.println(fileList[i]);
      try {
        scanListing(fileList[i]);
      } catch(FileNotFoundException e) {
        System.err.println("Could not open " +
          fileList[i]);
      }
    }
  }
  void scanListing(String fname) 
  throws IOException {
    in = new StreamTokenizer(
        new BufferedReader(
          new FileReader(fname)));
    // Doesn't seem to work:
    // in.slashStarComments(true);
    // in.slashSlashComments(true);
    in.ordinaryChar('/');
    in.ordinaryChar('.');
    in.wordChars('_', '_');
    in.eolIsSignificant(true);
    while(in.nextToken() != 
          StreamTokenizer.TT_EOF) {
      if(in.ttype == '/')
        eatComments();
      else if(in.ttype == 
              StreamTokenizer.TT_WORD) {
        if(in.sval.equals("class") || 
           in.sval.equals("interface")) {
          // Get class name:
             while(in.nextToken() != 
                   StreamTokenizer.TT_EOF
                   && in.ttype != 
                   StreamTokenizer.TT_WORD)
               ;
             classes.put(in.sval, in.sval);
             classMap.add(fname, in.sval);
        }
        if(in.sval.equals("import") ||
           in.sval.equals("package"))
          discardLine();
        else // It's an identifier or keyword
          identMap.add(fname, in.sval);
      }
    }
  }
  void discardLine() throws IOException {
    while(in.nextToken() != 
          StreamTokenizer.TT_EOF
          && in.ttype != 
          StreamTokenizer.TT_EOL)
      ; // Throw away tokens to end of line
  }
  // StreamTokenizer's comment removal seemed
  // to be broken. This extracts them:
  void eatComments() throws IOException {
    if(in.nextToken() != 
       StreamTokenizer.TT_EOF) {
      if(in.ttype == '/')
        discardLine();
      else if(in.ttype != '*')
        in.pushBack();
      else 
        while(true) {
          if(in.nextToken() == 
            StreamTokenizer.TT_EOF)
            break;
          if(in.ttype == '*')
            if(in.nextToken() != 
              StreamTokenizer.TT_EOF
              && in.ttype == '/')
              break;
        }
    }
  }
  public String[] classNames() {
    String[] result = new String[classes.size()];
    Iterator e = classes.keySet().iterator();
    int i = 0;
    while(e.hasNext())
      result[i++] = (String)e.next();
    return result;
  }
  public void checkClassNames() {
    Iterator files = classMap.keySet().iterator();
    while(files.hasNext()) {
      String file = (String)files.next();
      ArrayList cls = classMap.getArrayList(file);
      for(int i = 0; i < cls.size(); i++) {
        String className = (String)cls.get(i);
        if(Character.isLowerCase(
             className.charAt(0)))
          System.out.println(
            "class capitalization error, file: "
            + file + ", class: " 
            + className);
      }
    }
  }
  public void checkIdentNames() {
    Iterator files = identMap.keySet().iterator();
    ArrayList reportSet = new ArrayList();
    while(files.hasNext()) {
      String file = (String)files.next();
      ArrayList ids = identMap.getArrayList(file);
      for(int i = 0; i < ids.size(); i++) {
        String id = (String)ids.get(i);
        if(!classes.contains(id)) {
          // Ignore identifiers of length 3 or
          // longer that are all uppercase
          // (probably static final values):
          if(id.length() >= 3 &&
             id.equals(
               id.toUpperCase()))
            continue;
          // Check to see if first char is upper:
          if(Character.isUpperCase(id.charAt(0))){
            if(reportSet.indexOf(file + id)
                == -1){ // Not reported yet
              reportSet.add(file + id);
              System.out.println(
                "Ident capitalization error in:"
                + file + ", ident: " + id);
            }
          }
        }
      }
    }
  }
  static final String usage =
    "Usage: \n" + 
    "ClassScanner classnames -a\n" +
    "\tAdds all the class names in this \n" +
    "\tdirectory to the repository file \n" +
    "\tcalled 'classnames'\n" +
    "ClassScanner classnames\n" +
    "\tChecks all the java files in this \n" +
    "\tdirectory for capitalization errors, \n" +
    "\tusing the repository file 'classnames'";
  private static void usage() {
    System.err.println(usage);
    System.exit(1);
  }
  public static void main(String[] args) 
  throws IOException {
    if(args.length < 1 || args.length > 2)
      usage();
    ClassScanner c = new ClassScanner();
    File old = new File(args[0]);
    if(old.exists()) {
      try {
        // Try to open an existing 
        // properties file:
        InputStream oldlist =
          new BufferedInputStream(
            new FileInputStream(old));
        c.classes.load(oldlist);
        oldlist.close();
      } catch(IOException e) {
        System.err.println("Could not open "
          + old + " for reading");
        System.exit(1);
      }
    }
    if(args.length == 1) {
      c.checkClassNames();
      c.checkIdentNames();
    }
    // Write the class names to a repository:
    if(args.length == 2) {
      if(!args[1].equals("-a"))
        usage();
      try {
        BufferedOutputStream out =
          new BufferedOutputStream(
            new FileOutputStream(args[0]));
        c.classes.store(out,
          "Classes found by ClassScanner.java");
        out.close();
      } catch(IOException e) {
        System.err.println(
          "Could not write " + args[0]);
        System.exit(1);
      }
    }
  }
}

class JavaFilter implements FilenameFilter {
  public boolean accept(File dir, String name) {
    // Strip path information:
    String f = new File(name).getName();
    return f.trim().endsWith(".java");
  }
} ///:~