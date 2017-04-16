//: TypedBinMember.java
// An interface for adding the double dispatching
// method to the trash hierarchy without 
// modifying the original hierarchy.
package c16.doubledispatch;

interface TypedBinMember {
  // The new method:
  boolean addToBin(TypedBin[] tb);
} ///:~