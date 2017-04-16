//: CutAndPaste.java
// Using the clipboard from Java 1.1
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class CutAndPaste extends Frame {
  MenuBar mb = new MenuBar();
  Menu edit = new Menu("Edit");
  MenuItem
    cut = new MenuItem("Cut"),
    copy = new MenuItem("Copy"),
    paste = new MenuItem("Paste");
  TextArea text = new TextArea(20,20);
  Clipboard clipbd = 
    getToolkit().getSystemClipboard();
  public CutAndPaste() {
    cut.addActionListener(new CutL());
    copy.addActionListener(new CopyL());
    paste.addActionListener(new PasteL());
    edit.add(cut);
    edit.add(copy);
    edit.add(paste);
    mb.add(edit);
    setMenuBar(mb);
    add(text, BorderLayout.CENTER);
  }
  class CopyL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String selection = text.getSelectedText();
      StringSelection clipString = 
        new StringSelection(selection);
      clipbd.setContents(clipString, clipString);
    }
  }
  class CutL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String selection = text.getSelectedText();
      StringSelection clipString = 
        new StringSelection(selection);
      clipbd.setContents(clipString, clipString);
      text.replaceRange("", 
        text.getSelectionStart(), 
        text.getSelectionEnd());
    }
  }
  class PasteL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Transferable clipData =
        clipbd.getContents(CutAndPaste.this);
      try {
        String clipString = 
          (String)clipData.
            getTransferData(
              DataFlavor.stringFlavor);
        text.replaceRange(clipString, 
          text.getSelectionStart(), 
          text.getSelectionEnd());
      } catch(Exception ex) {
        System.out.println("not String flavor");
      }
    }
  }
  public static void main(String[] args) {
    CutAndPaste cp = new CutAndPaste();
    cp.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    cp.setSize(300,200);
    cp.setVisible(true);
  }
} ///:~