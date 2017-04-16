//: FileDialogNew.java
// Demonstration of File dialog boxes
import java.awt.*;
import java.awt.event.*;

public class FileDialogNew extends Frame {
  TextField filename = new TextField();
  TextField directory = new TextField();
  Button open = new Button("Open");
  Button save = new Button("Save");
  public FileDialogNew() {
    setTitle("File Dialog Test");
    Panel p = new Panel();
    p.setLayout(new FlowLayout());
    open.addActionListener(new OpenL());
    p.add(open);
    save.addActionListener(new SaveL());
    p.add(save);
    add(p, BorderLayout.SOUTH);
    directory.setEditable(false);
    filename.setEditable(false);
    p = new Panel();
    p.setLayout(new GridLayout(2,1));
    p.add(filename);
    p.add(directory);
    add(p, BorderLayout.NORTH);
  }
  class OpenL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      // Two arguments, defaults to open file:
      FileDialog d = new FileDialog(
        FileDialogNew.this,
        "What file do you want to open?");
      d.setFile("*.java");
      d.setDirectory("."); // Current directory
      d.show();
      String yourFile = "*.*";
      if((yourFile = d.getFile()) != null) {
        filename.setText(yourFile);
        directory.setText(d.getDirectory());
      } else {
        filename.setText("You pressed cancel");
        directory.setText("");
      }
    }
  }
  class SaveL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      FileDialog d = new FileDialog(
        FileDialogNew.this,
        "What file do you want to save?",
        FileDialog.SAVE);
      d.setFile("*.java");
      d.setDirectory(".");
      d.show();
      String saveFile;
      if((saveFile = d.getFile()) != null) {
        filename.setText(saveFile);
        directory.setText(d.getDirectory());
      } else {
        filename.setText("You pressed cancel");
        directory.setText("");
      }
    }
  }
  public static void main(String[] args) {
    Frame f = new FileDialogNew();
    f.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    f.setSize(250,110);
    f.setVisible(true);
  }
} ///:~