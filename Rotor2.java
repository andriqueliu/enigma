/*
Enigma Cipher Project
Author: Andrique Liu

Rotor1 functions as the "middle" rotor of the encryption sequence
*/
import java.util.*;
import java.io.*;

public class Rotor2 extends Rotor{
   private String ring2;
   
   // Constructor: creates an instance of the rotor at position 0
   public Rotor2() {
      this(0);
   }
   
   // Rotor letter order:
   // AJDKS IRUXB LHWTM CQGZN PYFVO E
   public Rotor2(int position) {
      super(position); // !!! Note.
      ring2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
   }
   
   // encrypt accounts for a letter's initial journey through the rotors. First,
   // a letter is converted to a position. Referring to ring1, the unique rotor
   // property is used to change the signal at ring2
   // Inputs:
   // String input, which will undergo encryption
   // Returns encrypted input
   public String encrypt(String input) {
      int place = ref.get(input);
      String ring1Letter = ring1.charAt(place) + "";
      place = ref.get(ring1Letter);
      String output = ring2.charAt(place) + "";
      place = ring1.indexOf(output);
      output = ref2.get(place);
      return output;
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
