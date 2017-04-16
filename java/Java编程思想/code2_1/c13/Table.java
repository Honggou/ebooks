//: c13:Table.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Simple demonstration of JTable.
// <applet code=Table
//  width=350 height=100></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import com.bruceeckel.swing.*;

// The TableModel controls all the data:
class DataModel extends AbstractTableModel {
  Object[][] data = {
    {"one", "two", "three", "four"},
    {"five", "six", "seven", "eight"},
    {"nine", "ten", "eleven", "twelve"},
  };
  // Prints data when table changes:
  class TML implements TableModelListener {
    public void tableChanged(TableModelEvent e) {
      for(int i = 0; i < data.length; i++) {
        for(int j = 0; j < data[0].length; j++)
          System.out.print(data[i][j] + " ");
        System.out.println();
      }
    }
  }
  public DataModel() {
    addTableModelListener(new TML());
  }
  public int getColumnCount() { 
    return data[0].length; 
  }
  public int getRowCount() { 
    return data.length;
  }
  public Object getValueAt(int row, int col) { 
    return data[row][col]; 
  }
  public void 
  setValueAt(Object val, int row, int col) {
    data[row][col] = val;
    // Indicate the change has happened:
    fireTableDataChanged();
  }
  public boolean 
  isCellEditable(int row, int col) { 
    return true; 
  }
};       

public class Table extends JApplet {
  public void init() {
    Container cp = getContentPane();
    JTable table = new JTable(new DataModel());
    JScrollPane scrollpane = 
      JTable.createScrollPaneForTable(table);
    cp.add(scrollpane, BorderLayout.CENTER);
  }
  public static void main(String[] args) {
    Console.run(new Table(), 350, 100);
  }
} ///:~