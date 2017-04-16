//: Table.java
// Simple demonstration of JTable
package c13.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

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
  DataModel() {
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

public class Table extends JPanel {
  public Table() {
    setLayout(new BorderLayout());
    JTable table = new JTable(new DataModel());
    JScrollPane scrollpane = 
      JTable.createScrollPaneForTable(table);
    add(scrollpane, BorderLayout.CENTER);
  }
  public static void main(String args[]) {
    Show.inFrame(new Table(),200,200);
  }
} ///:~