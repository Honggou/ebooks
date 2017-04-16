//: CodePackager.java
// "Packs" and "unpacks" the code in "Thinking 
// in Java" for cross-platform distribution.
/* Commented so CodePackager sees it and starts
   a new chapter directory, but so you don't 
   have to worry about the directory where this
   program lives:
package c17;
*/
import java.util.*;
import java.io.*;

class Pr {
  static void error(String e) {
    System.err.println("ERROR: " + e);
    System.exit(1);
  }
}

class IO {
  static BufferedReader disOpen(File f) {
    BufferedReader in = null;
    try {
      in = new BufferedReader(
        new FileReader(f));
    } catch(IOException e) {
      Pr.error("could not open " + f);
    }
    return in;
  }
  static BufferedReader disOpen(String fname) {
    return disOpen(new File(fname));
  }
  static DataOutputStream dosOpen(File f) {
    DataOutputStream in = null;
    try {
      in = new DataOutputStream(
        new BufferedOutputStream(
          new FileOutputStream(f)));
    } catch(IOException e) {
      Pr.error("could not open " + f);
    }
    return in;
  }
  static DataOutputStream dosOpen(String fname) {
    return dosOpen(new File(fname));
  }
  static PrintWriter psOpen(File f) {
    PrintWriter in = null;
    try {
      in = new PrintWriter(
        new BufferedWriter(
          new FileWriter(f)));
    } catch(IOException e) {
      Pr.error("could not open " + f);
    }
    return in;
  }
  static PrintWriter psOpen(String fname) {
    return psOpen(new File(fname));
  }
  static void close(Writer os) {
    try {
      os.close();
    } catch(IOException e) {
      Pr.error("closing " + os);
    }
  }
  static void close(DataOutputStream os) {
    try {
      os.close();
    } catch(IOException e) {
      Pr.error("closing " + os);
    }
  }
  static void close(Reader os) {
    try {
      os.close();
    } catch(IOException e) {
      Pr.error("closing " + os);
    }
  }
}

class SourceCodeFile {
  public static final String 
    startMarker = "//:", // Start of source file
    endMarker = "} ///:~", // End of source
    endMarker2 = "}; ///:~", // C++ file end
    beginContinue = "} ///:Continued",
    endContinue = "///:Continuing",
    packMarker = "###", // Packed file header tag
    eol = // Line separator on current system
      System.getProperty("line.separator"),
    filesep = // System's file path separator
      System.getProperty("file.separator");
  public static String copyright = "";
  static {
    try {
      BufferedReader cr =
        new BufferedReader(
          new FileReader("Copyright.txt"));
      String crin;
      while((crin = cr.readLine()) != null)
        copyright += crin + "\n";
      cr.close();
    } catch(Exception e) {
      copyright = "";
    }
  }
  private String filename, dirname,
    contents = new String();
  private static String chapter = "c02";
  // The file name separator from the old system:
  public static String oldsep;
  public String toString() {
    return dirname + filesep + filename;
  }
  // Constructor for parsing from document file:
  public SourceCodeFile(String firstLine, 
      BufferedReader in) {
    dirname = chapter;
    // Skip past marker:
    filename = firstLine.substring(
        startMarker.length()).trim();
    // Find space that terminates file name:
    if(filename.indexOf(' ') != -1)
      filename = filename.substring(
          0, filename.indexOf(' '));
    System.out.println("found: " + filename);
    contents = firstLine + eol;
    if(copyright.length() != 0)
      contents += copyright + eol;
    String s;
    boolean foundEndMarker = false;
    try {
      while((s = in.readLine()) != null) {
        if(s.startsWith(startMarker))
          Pr.error("No end of file marker for " +
            filename);
        // For this program, no spaces before 
        // the "package" keyword are allowed
        // in the input source code:
        else if(s.startsWith("package")) {
          // Extract package name:
          String pdir = s.substring(
            s.indexOf(' ')).trim();
          pdir = pdir.substring(
            0, pdir.indexOf(';')).trim();
          // Capture the chapter from the package
          // ignoring the 'com' subdirectories:
          if(!pdir.startsWith("com")) {
            int firstDot = pdir.indexOf('.');
            if(firstDot != -1)
              chapter = 
                pdir.substring(0,firstDot);
            else
              chapter = pdir;
          }
          // Convert package name to path name:
          pdir = pdir.replace(
            '.', filesep.charAt(0));
          System.out.println("package " + pdir);
          dirname = pdir;
        }
        contents += s + eol;
        // Move past continuations:
        if(s.startsWith(beginContinue))
          while((s = in.readLine()) != null)
            if(s.startsWith(endContinue)) {
              contents += s + eol;
              break;
            }
        // Watch for end of code listing:
        if(s.startsWith(endMarker) ||
           s.startsWith(endMarker2)) {
          foundEndMarker = true;
          break;
        }
      }
      if(!foundEndMarker)
        Pr.error(
          "End marker not found before EOF");
      System.out.println("Chapter: " + chapter);
    } catch(IOException e) {
      Pr.error("Error reading line");
    }
  }
  // For recovering from a packed file:
  public SourceCodeFile(BufferedReader pFile) {
    try {
      String s = pFile.readLine();
      if(s == null) return;
      if(!s.startsWith(packMarker))
        Pr.error("Can't find " + packMarker
          + " in " + s);
      s = s.substring(
        packMarker.length()).trim();
      dirname = s.substring(0, s.indexOf('#'));
      filename = s.substring(s.indexOf('#') + 1);
      dirname = dirname.replace(
        oldsep.charAt(0), filesep.charAt(0));
      filename = filename.replace(
        oldsep.charAt(0), filesep.charAt(0));
      System.out.println("listing: " + dirname 
        + filesep + filename);
      while((s = pFile.readLine()) != null) {
        // Watch for end of code listing:
        if(s.startsWith(endMarker) ||
           s.startsWith(endMarker2)) {
          contents += s;
          break;
        }
        contents += s + eol;
      }
    } catch(IOException e) {
      System.err.println("Error reading line");
    }
  }
  public boolean hasFile() { 
    return filename != null; 
  }
  public String directory() { return dirname; }
  public String filename() { return filename; }
  public String contents() { return contents; }
  // To write to a packed file:
  public void writePacked(DataOutputStream out) {
    try {
      out.writeBytes(
        packMarker + dirname + "#" 
        + filename + eol);
      out.writeBytes(contents);
    } catch(IOException e) {
      Pr.error("writing " + dirname + 
        filesep + filename);
    }
  }
  // To generate the actual file:
  public void writeFile(String rootpath) {
    File path = new File(rootpath, dirname);
    path.mkdirs();
    PrintWriter p =
      IO.psOpen(new File(path, filename));
    p.print(contents);
    IO.close(p);
  }
}

class DirMap {
  private Hashtable t = new Hashtable();
  private String rootpath;
  DirMap() {
    rootpath = System.getProperty("user.dir");
  }
  DirMap(String alternateDir) {
    rootpath = alternateDir;
  }
  public void add(SourceCodeFile f){
    String path = f.directory();
    if(!t.containsKey(path))
      t.put(path, new Vector());
    ((Vector)t.get(path)).addElement(f);
  }
  public void writePackedFile(String fname) {
    DataOutputStream packed = IO.dosOpen(fname);
    try {
      packed.writeBytes("###Old Separator:" +
        SourceCodeFile.filesep + "###\n");
    } catch(IOException e) {
      Pr.error("Writing separator to " + fname);
    }
    Enumeration e = t.keys();
    while(e.hasMoreElements()) {
      String dir = (String)e.nextElement();
      System.out.println(
        "Writing directory " + dir);
      Vector v = (Vector)t.get(dir);
      for(int i = 0; i < v.size(); i++) {
        SourceCodeFile f = 
          (SourceCodeFile)v.elementAt(i);
        f.writePacked(packed);
      }
    }
    IO.close(packed);
  }
  // Write all the files in their directories:
  public void write() {
    Enumeration e = t.keys();
    while(e.hasMoreElements()) {
      String dir = (String)e.nextElement();
      Vector v = (Vector)t.get(dir);
      for(int i = 0; i < v.size(); i++) {
        SourceCodeFile f = 
          (SourceCodeFile)v.elementAt(i);
        f.writeFile(rootpath);
      }
      // Add file indicating file quantity
      // written to this directory as a check:
      IO.close(IO.dosOpen(
        new File(new File(rootpath, dir),
          Integer.toString(v.size())+".files")));
    }
  }
}

public class CodePackager {
  private static final String usageString =
  "usage: java CodePackager packedFileName" +
  "\nExtracts source code files from packed \n" +
  "version of Tjava.doc sources into " +
  "directories off current directory\n" +
  "java CodePackager packedFileName newDir\n" +
  "Extracts into directories off newDir\n" +
  "java CodePackager -p source.txt packedFile" +
  "\nCreates packed version of source files" +
  "\nfrom text version of Tjava.doc";
  private static void usage() {
    System.err.println(usageString);
    System.exit(1);
  }
  public static void main(String[] args) {
    if(args.length == 0) usage();
    if(args[0].equals("-p")) {
      if(args.length != 3)
        usage();
      createPackedFile(args);
    }
    else {
      if(args.length > 2)
        usage();
      extractPackedFile(args);
    }
  }
  private static String currentLine; 
  private static BufferedReader in;
  private static DirMap dm;
  private static void 
  createPackedFile(String[] args) {
    dm = new DirMap();
    in = IO.disOpen(args[1]);
    try {
      while((currentLine = in.readLine()) 
          != null) {
        if(currentLine.startsWith(
            SourceCodeFile.startMarker)) {
          dm.add(new SourceCodeFile(
                   currentLine, in));
        }
        else if(currentLine.startsWith(
            SourceCodeFile.endMarker))
          Pr.error("file has no start marker");
        // Else ignore the input line
      }
    } catch(IOException e) {
      Pr.error("Error reading " + args[1]);
    }
    IO.close(in);
    dm.writePackedFile(args[2]);
  }
  private static void 
  extractPackedFile(String[] args) {
    if(args.length == 2) // Alternate directory
      dm = new DirMap(args[1]);
    else // Current directory
      dm = new DirMap();
    in = IO.disOpen(args[0]);
    String s = null;
    try {
       s = in.readLine();
    } catch(IOException e) {
      Pr.error("Cannot read from " + in);
    }
    // Capture the separator used in the system
    // that packed the file:
    if(s.indexOf("###Old Separator:") != -1 ) {
      String oldsep = s.substring(
        "###Old Separator:".length());
      oldsep = oldsep.substring(
        0, oldsep. indexOf('#'));
      SourceCodeFile.oldsep = oldsep;
    }
    SourceCodeFile sf = new SourceCodeFile(in);
    while(sf.hasFile()) {
      dm.add(sf);
      sf = new SourceCodeFile(in);
    }
    dm.write();
  }
} ///:~