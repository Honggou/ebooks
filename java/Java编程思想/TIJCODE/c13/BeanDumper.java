//: c13:BeanDumper.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Introspecting a Bean.
// <applet code=BeanDumper width=600 height=500>
// </applet>
import java.beans.*;
import java.lang.reflect.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class BeanDumper extends JApplet {
  JTextField query = 
    new JTextField(20);
  JTextArea results = new JTextArea();
  public void prt(String s) {
    results.append(s + "\n");
  }
  public void dump(Class bean){
    results.setText("");
    BeanInfo bi = null;
    try {
      bi = Introspector.getBeanInfo(
        bean, java.lang.Object.class);
    } catch(IntrospectionException e) {
      prt("Couldn't introspect " + 
        bean.getName());
      return;
    }
    PropertyDescriptor[] properties = 
      bi.getPropertyDescriptors();
    for(int i = 0; i < properties.length; i++) {
      Class p = properties[i].getPropertyType();
      prt("Property type:\n  " + p.getName() +
        "Property name:\n  " + 
        properties[i].getName());
      Method readMethod = 
        properties[i].getReadMethod();
      if(readMethod != null)
        prt("Read method:\n  " + readMethod);
      Method writeMethod = 
        properties[i].getWriteMethod();
      if(writeMethod != null)
        prt("Write method:\n  " + writeMethod);
      prt("====================");
    }
    prt("Public methods:");
    MethodDescriptor[] methods =
      bi.getMethodDescriptors();
    for(int i = 0; i < methods.length; i++)
      prt(methods[i].getMethod().toString());
    prt("======================");
    prt("Event support:");
    EventSetDescriptor[] events = 
      bi.getEventSetDescriptors();
    for(int i = 0; i < events.length; i++) {
      prt("Listener type:\n  " +
        events[i].getListenerType().getName());
      Method[] lm = 
        events[i].getListenerMethods();
      for(int j = 0; j < lm.length; j++)
        prt("Listener method:\n  " +
          lm[j].getName());
      MethodDescriptor[] lmd = 
        events[i].getListenerMethodDescriptors();
      for(int j = 0; j < lmd.length; j++)
        prt("Method descriptor:\n  " +
          lmd[j].getMethod());
      Method addListener = 
        events[i].getAddListenerMethod();
      prt("Add Listener Method:\n  " +
          addListener);
      Method removeListener =
        events[i].getRemoveListenerMethod();
      prt("Remove Listener Method:\n  " +
        removeListener);
      prt("====================");
    }
  }
  class Dumper implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String name = query.getText();
      Class c = null;
      try {
        c = Class.forName(name);
      } catch(ClassNotFoundException ex) {
        results.setText("Couldn't find " + name);
        return;
      }
      dump(c);
    }
  }      
  public void init() {
    Container cp = getContentPane();
    JPanel p = new JPanel();
    p.setLayout(new FlowLayout());
    p.add(new JLabel("Qualified bean name:"));
    p.add(query);
    cp.add(BorderLayout.NORTH, p);
    cp.add(new JScrollPane(results));
    Dumper dmpr = new Dumper();
    query.addActionListener(dmpr);
    query.setText("frogbean.Frog");
    // Force evaluation
    dmpr.actionPerformed(
      new ActionEvent(dmpr, 0, ""));
  }
  public static void main(String[] args) {
    Console.run(new BeanDumper(), 600, 500);
  }
} ///:~