/*
Enigma Cipher Project
Author: Andrique Liu

RotorManager functions as a top-level module which controls each individual
rotor, and is responsible for all encryption implementation of this project.
*/
import java.util.*;
import java.io.*;

public class RotorManager {
   public Rotor1 r1;
   public Rotor2 r2;
   public Rotor3 r3;
   private Map<String, String> map;
   private String alphabet;
   
   // Constructor module: creates an instance of RotorManager
   public RotorManager() {
      r1 = new Rotor1(); 
      r2 = new Rotor2();
      r3 = new Rotor3();
      map = new TreeMap<String, String>();
      alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   }
   
   // Obsolete; setting of initial positions is performed in EnigmaManager
   public RotorManager(int v1, int v2, int v3) {
      r1 = new Rotor1(v1);
      r2 = new Rotor2(v2);
      r3 = new Rotor3(v3);
      map = new TreeMap<String, String>();
   }
   
   // addPlug adds a plugboard option in the current encryption operation
   // Inputs:
   // Strings first and second are placed into a map, enabling plugboard behavior
   public void addPlug(String first, String second) {
      map.put(first, second);
      map.put(second, first);
   }
   
   // plugSwap swaps a letter for its "plugged" letter
   // Example: A and B were declared as a pair for the plugboard. Any time plugSwap is
   // called, A is swapped for B, and B is swapped for A
   // Inputs:
   // String letter is used to swap for its "plugged" letter
   // Returns the "plugged" letter
   public String plugSwap(String letter) {
      if (map.containsKey(letter)) {
         return map.get(letter);
      } else {
         return letter;
      }
   }
   
   // r1 steps from V to W
   // r2 steps from E to F
   // r3 steps from Q to R
   // step performs the machine's "stepping" mechanism, which will rotate the
   // rotors and change their positions
   // Note: "Double Stepping" is performed when r3 goes from Q to R; if r2 steps from
   // E to F, then r1 is *also* stepped
   public void step() {
      r3.turn();
      if (r3.getPosition() == 17) { // R is at 18.
         r2.turn();
      }
      if (r2.getPosition() == 5) {  // F is at 6.
         r1.turn();
      }
      if (r1.getPosition() == 22) { // W is at 23.
         // Only r1 will turn, which is already taken care of. Thus, do nothing
      }
   }
   
   // setAllToAAA sets all rotors to position 0 (A). This method is obsolete,
   // as the program prompts users for their desired initial positions
   public void setAllToAAA() {
      r1.set(0);
      r2.set(0);
      r3.set(0);
   }
   
   // setPosition sets the position of the rotors. This method is used to 
   // set the user's desired rotor positions to increase encryption complexity
   // Inputs:
   // ints v1, v2, v3 correspond to respective rotor positions
   public void setPosition(int v1, int v2, int v3) {
      r1.set(v1);
      r2.set(v2);
      r3.set(v3);
   }
   
   // encryptLetter encrypts a single letter. If input is a letter, encrypt
   // as normal. Else (punctuation, number, etc.), just return
   // Inputs:
   // String input will be encrypted using this method
   // Returns encrypted letter
   public String encryptLetter(String input) {
      if (Character.isLetter(input.charAt(0))) {
         this.step();
         String output = plugSwap(input);
         output = r1.encrypt(r2.encrypt(r3.encrypt(output)));
         output = reflect(output);
         output = r3.mirrorEncrypt(r2.mirrorEncrypt(r1.mirrorEncrypt(output)));
         output = plugSwap(output);
         
         return output;
      } else {
         return input;
      }
   }
   
   // reflect serves to "reflect" the input
   // Inputs:
   // String input, which will be reflected
   // Returns reflected String
   public String reflect(String input) {
      // Reference: "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      int place = alphabet.indexOf(input);
      int length = alphabet.length() / 2;
      if (place < length) {
         place =  place + length;
      } else {
         place =  place - length;
      }
      
      return alphabet.charAt(place) + "";
   }
   
   // encryptWord encrypts a single word
   // Inputs:
   // String input, the String to be encrypted
   // Returns encrypted string
   public String encryptWord(String input) {
      String word = "";
      
      for (int i = 0; i < input.length(); i++) {
         word = word + encryptLetter(input.charAt(i) + "");
      }
      
      return word;
   }
   
   // encryptFile encrypts an entire file
   // Inputs:
   // Scanner input is used to take user input
   // PrintStream output is used to specify an output stream which will take the
   // encrypted contents
   public void encryptFile(Scanner input, PrintStream output) {
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineScan = new Scanner(line);
         while (lineScan.hasNext()) {
            String word = lineScan.next();
            output.print(encryptWord(word) + " ");
         }
         output.println();
      }
   }
}
