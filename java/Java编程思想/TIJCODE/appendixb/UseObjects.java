//: appendixb:UseObjects.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
class MyJavaClass {
  public int aValue;
  public void divByTwo() { aValue /= 2; }
}

public class UseObjects {
  private native void 
    changeObject(MyJavaClass obj);
  static {
    System.loadLibrary("UseObjImpl");
    // Linux hack, if you can't get your library
    // path set in your environment:
    // System.load(
    //"/home/bruce/tij2/appendixb/UseObjImpl.so");
  }
  public static void main(String[] args) {
    UseObjects app = new UseObjects();
    MyJavaClass anObj = new MyJavaClass();
    anObj.aValue = 2;
    app.changeObject(anObj);
    System.out.println("Java: " + anObj.aValue);
  }
} ///:~