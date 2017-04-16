//: c13:Faces.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Icon behavior in Jbuttons.
// <applet code=Faces
//  width=250 height=100></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class Faces extends JApplet {
  // The following path information is necessary
  // to run via an applet directly from the disk:
  static String path = 
    "C:/aaa-TIJ2-distribution/code/c13/";
  static Icon[] faces = {
    new ImageIcon(path + "face0.gif"),
    new ImageIcon(path + "face1.gif"),
    new ImageIcon(path + "face2.gif"),
    new ImageIcon(path + "face3.gif"),
    new ImageIcon(path + "face4.gif"),
  };
  JButton 
    jb = new JButton("JButton", faces[3]),
    jb2 = new JButton("Disable");
  boolean mad = false;
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    jb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        if(mad) {
          jb.setIcon(faces[3]);
          mad = false;
        } else {
          jb.setIcon(faces[0]);
          mad = true;
        }
        jb.setVerticalAlignment(JButton.TOP);
        jb.setHorizontalAlignment(JButton.LEFT);
      }
    });
    jb.setRolloverEnabled(true);
    jb.setRolloverIcon(faces[1]);
    jb.setPressedIcon(faces[2]);
    jb.setDisabledIcon(faces[4]);
    jb.setToolTipText("Yow!");
    cp.add(jb);
    jb2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        if(jb.isEnabled()) {
          jb.setEnabled(false);
          jb2.setText("Enable");
        } else {
          jb.setEnabled(true);
          jb2.setText("Disable");
        }
      }
    });
    cp.add(jb2);
  }
  public static void main(String[] args) {
    Console.run(new Faces(), 400, 200);
  }
} ///:~