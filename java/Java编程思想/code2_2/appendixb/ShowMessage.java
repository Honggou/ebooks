//: appendixb:ShowMessage.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
public class ShowMessage {
  private native void ShowMessage(String msg);
  static {
    System.loadLibrary("MsgImpl");
    // Linux hack, if you can't get your library
    // path set in your environment:
    // System.load(
    //  "/home/bruce/tij2/appendixb/MsgImpl.so");
  }
  public static void main(String[] args) {
    ShowMessage app = new ShowMessage();
    app.ShowMessage("Generated with JNI");
  }
} ///:~