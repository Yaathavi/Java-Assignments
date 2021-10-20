
/* Yaathavi Theevannan, Date: March 15, 2021

This program reads a file and translates the file's English text to pigLatin and prints it.

Input: A text file called sample.txt containing English words
Output: All of the words in the sample.txt file converted to PigLatin */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PigLatin {
    public static void main (String [] args) throws FileNotFoundException{
        
        Scanner input = new Scanner(new File("sample.txt")); // reads sample.txt file

        // while loop that runs for each line in the sample.txt file.
        while (input.hasNextLine()){

            String line = input.nextLine().toLowerCase(); //reads entire line and changes all the words to lowercase
            Scanner lineScan = new Scanner(line); // reads token-by-token
            
            // while loop that runs for every word in the current line
            while (lineScan.hasNext()){
                String word = lineScan.next(); // gets the next word
                char first = firstLetter(word); // gets the first letter of each word and saves it to a char type variable
                pigLatin(first, word); // converts each word to pigLatin format 
            }
            System.out.println(); //prints a new line for the next line in the file
        }
    }

    // method to return first letter  
    public static char firstLetter(String word){
        char first = word.charAt(0);
        return first;
    }

    // method to check if the first letter of the word is a vowel
    public static boolean vowelCheck(char first){
        return (first == 'a' || first == 'e' ||first == 'i' ||first == 'o' ||first == 'u');
    }

    // method that converts each word to pigLatin based on if it starts with a vowel or consonant
    public static void pigLatin(char first, String word){
        if (vowelCheck(first)){
            System.out.print(word + "yay ");
        } else {
            String newWord = word.substring(1);
            System.out.print(newWord + first + "ay ");
        }
    }
}