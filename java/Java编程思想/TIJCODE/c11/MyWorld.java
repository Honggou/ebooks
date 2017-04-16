//: c11:MyWorld.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
import java.io.*;
import java.util.*;

class House implements Serializable {}

class Animal implements Serializable {
  String name;
  House preferredHouse;
  Animal(String nm, House h) { 
    name = nm; 
    preferredHouse = h;
  }
  public String toString() {
    return name + "[" + super.toString() + 
      "], " + preferredHouse + "\n";
  }
}

public class MyWorld {
  public static void main(String[] args)
  throws IOException, ClassNotFoundException {
    House house = new House();
    ArrayList  animals = new ArrayList();
    animals.add(
      new Animal("Bosco the dog", house));
    animals.add(
      new Animal("Ralph the hamster", house));
    animals.add(
      new Animal("Fronk the cat", house));
    System.out.println("animals: " + animals);

    ByteArrayOutputStream buf1 = 
      new ByteArrayOutputStream();
    ObjectOutputStream o1 =
      new ObjectOutputStream(buf1);
    o1.writeObject(animals);
    o1.writeObject(animals); // Write a 2nd set
    // Write to a different stream:
    ByteArrayOutputStream buf2 = 
      new ByteArrayOutputStream();
    ObjectOutputStream o2 =
      new ObjectOutputStream(buf2);
    o2.writeObject(animals);
    // Now get them back:
    ObjectInputStream in1 =
      new ObjectInputStream(
        new ByteArrayInputStream(
          buf1.toByteArray()));
    ObjectInputStream in2 =
      new ObjectInputStream(
        new ByteArrayInputStream(
          buf2.toByteArray()));
    ArrayList animals1 = 
      (ArrayList)in1.readObject();
    ArrayList animals2 = 
      (ArrayList)in1.readObject();
    ArrayList animals3 = 
      (ArrayList)in2.readObject();
    System.out.println("animals1: " + animals1);
    System.out.println("animals2: " + animals2);
    System.out.println("animals3: " + animals3);
  }
} ///:~