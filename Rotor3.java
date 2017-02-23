/*
Enigma Cipher Project
Author: Andrique Liu

Rotor1 functions as the "first" rotor of the encryption sequence
*/
import java.util.*;
import java.io.*;

public class Rotor3 extends Rotor{
   // Notes: position, ref, and ring1 are already written in superclass
   private String ring2;
   
   // Constructor: creates an instance of the rotor at position 0
   public Rotor3() {
      this(0);
   }
   
   // Rotor letter order:
   // BDFHJ LCPRT XVZNY EIWGA KMUSQ O
   public Rotor3(int position) {
      super(position); // !!! Note.
      ring2 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
   }
   
   // encrypt accounts for a letter's initial journey through the rotors. First,
   // a letter is converted to a position. Referring to ring1, the unique rotor
   // property is used to change the signal at ring2
   // Inputs:
   // String input, which will undergo encryption
   // Returns encrypted input
   public String encrypt(String inputx) {
      int place = ref.get(input);
      String ring1Letter = ring1.charAt(place) + ""; // if using A, at this point you get B
      // then, you turn B into something using ring2
      place = ref.get(ring1Letter);
      String output = ring2.charAt(place) + "";
      // it is now D. D becomes "C" due to its position
      place = ring1.indexOf(output);
      output = ref2.get(place);
      return output; // returns C
   }
   
   // mirrorEncrypt accounts for a letter's return journey through the rotors
   public String mirrorEncrypt(String input) {
      int place = ref.get(input);
      String output = ring1.charAt(place) + "";
      place = ring2.indexOf(output);
      output = alphabet.charAt(place) + "";
      place = ring1.indexOf(output);
      output = alphabet.charAt(place) + "";
      return output;
   }
   
   // toString2 returns a string version of this rotor's ring2
   public String toString2() {
      return ring2;
   }
}
