/*
Enigma Cipher Project
Author: Andrique Liu

Rotor acts as the superclass for all three derivative rotors, which
are used in the final encryption.

Notes:
position 0 == A

Future: add support to create your own rotor
*/
import java.util.*;
import java.io.*;

public class Rotor {
   public int position;
   public Map<String, Integer> ref;
   public Map<Integer, String> ref2;
   public String ring1;
   public String alphabet;
   
   public Rotor() {
      this(0);
   }
   
   // ASCII from A to Z is 65 through 90 (inclusive)
   // This will be zero-based
   
   public Rotor(int position) {
      this.set(position);
      
      alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      
      ref = new TreeMap<String, Integer>();
      ref2 = new TreeMap<Integer, String>();
      int count = -1;
      for (int i = 65; i <= 90; i++) {
         count++;
         ref.put(((char) i) + "", count);
         ref2.put(count, ((char) i) + "");
      }
   }
   
   // set sets the position of the rotor
   // Inputs:
   // int position is used to find the position to set to
   public void set(int position) {
      this.position = position;
      ring1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      for (int i = 0; i < position; i++) {
         String temp = ring1.substring(0, 1);
         ring1 = ring1.substring(1);
         ring1 = ring1 + temp;
      }
   }
   
   // turn will step the rotor to its next position
   public void turn() {
      if (position == 26) {
         position = 0;
         ring1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      } else {
         position++;
         String temp = ring1.substring(0, 1);
         ring1 = ring1.substring(1);
         ring1 = ring1 + temp;
      }
   }
   
   // getPosition returns the current position of the rotor
   public int getPosition() {
      return this.position;
   }
   
   // toString1 returns the string version of the rotor
   public String toString1() {
      return ring1;
   }
}
