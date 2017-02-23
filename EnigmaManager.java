/*
Enigma Cipher Project
Author: Andrique Liu

EnigmaManager functions as the top-level program for the Enigma Cipher module.
This program operates the program as a whole, prompting the user for input,
and eventually using the RotorManager to implement encryption.
*/
import java.util.*;
import java.io.*;

public class EnigmaManager {
   public static void main(String[] args) throws FileNotFoundException {
      // Declare objects: Scanner for user input, rotors for program implementation
      Scanner console = new Scanner(System.in);
      RotorManager rotors = new RotorManager();
      
      printIntro();
      
      // Set initial conditions (plugboard, rotor settings)
      setPlugs(console, rotors);
      // rotors.setAllToAAA(); // Set all to position 0 (for debugging)
      setRotors(console, rotors);
      
      // Declare input and output filenames
      Scanner fileScan = new Scanner(new File(getInputFile(console)));
      PrintStream output = new PrintStream(new File(getOutputFile(console)));
      
      // Encryption!
      rotors.encryptFile(fileScan, output);
   }
   
   // printIntro prints out an introductory message for the user
   public static void printIntro() {
      System.out.println("Welcome to the Enigma Cipher.");
      System.out.println("I can encrypt or decrypt files for you.");
      System.out.println("Please enter what settings you would like.");
   }
   
   // setPlugs prompts the user for their desired plugboard settings, and
   // implements these settings in the encryption
   // Inputs:
   // Scanner console is used to take user input
   // RotorManager rotors is used to set the plugboard settings
   public static void setPlugs(Scanner console, RotorManager rotors) {
      String reply = "";
      
      while (!reply.equals("Q")) {
         System.out.print("Plugs? (Format is XY. Enter Q to end) ");
         reply = console.next();
         
         if (!reply.equals("Q")) {
            String first = reply.substring(0, 1);
            String second = reply.substring(1);
            rotors.addPlug(first, second); 
         }
      }
   }
   
   // setRotors prompts the user for their desired initial rotor positions,
   // and implements these settings in the encryption
   // Inputs:
   // Scanner console is used to take user input
   // RotorManager rotors is used to set the initial rotor positions
   public static void setRotors(Scanner console, RotorManager rotors) {
      String reply = "";
      
      System.out.print("Starting rotor positions? (Format is 123) ");
      reply = console.next();
      
      int[] p = new int[3];
      for (int i = 0; i < 3; i++) {
         p[i] = Integer.parseInt(reply.substring(i, i + 1));
      }
      rotors.setPosition(p[0], p[1], p[2]);
   }
   
   // encryptLetter encrypts a single letter
   // This method is top-level; implementation is found in RotorManager
   // Inputs:
   // Scanner console is used to take user input
   // RotorManager rotors is used to encrypt user input
   public static void encryptLetter(Scanner console, RotorManager rotors) {
      System.out.print("Enter a letter to be encrypted ");
      String letter = console.next();
      System.out.print(rotors.encryptLetter(letter));
   }
   
   // encryptWord encrypts a single word
   // This method is top-level; implementation is found in RotorManager
   // Inputs:
   // Scanner console is used to take user input
   // RotorManager rotors is used to encrypt user input
   public static void encryptWord(Scanner console, RotorManager rotors) {
      System.out.print("Enter a word to be encrypted (no spaces): ");
      String word = console.next();
      System.out.print(rotors.encryptWord(word));
   }
   
   // getInputFile looks for an existing file to encrypt
   // Inputs:
   // Scanner console is used to take user input
   // Returns name of input file
   public static String getInputFile(Scanner console) {
      System.out.print("Enter the name of the file to be encrypted: ");
      String fileName = console.next();
      File file = new File(fileName);
      
      // Continue prompting user if file not found
      while (!(file.exists())) {
         System.out.print("File not found. File name?: ");
         fileName = console.nextLine();
         file = new File(fileName);
      }
      
      return fileName;
   }
   
   // getOutputFile looks for a file to place encrypted content into
   // Inputs:
   // Scanner console is used to take user input
   // Returns name of output file
   public static String getOutputFile(Scanner console) {
      System.out.print("Enter the name of the output file: ");
      String fileName = console.next();
      
      return fileName;
   }
}
