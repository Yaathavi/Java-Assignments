/* Yaathavi Theevannan, Date: April 12, 2021

This program reads 2 files, compares each line and prints the different lines.

Input: Asks user for the name of 2 files
Output: display the different lines with the corresponding line number */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class CompareFiles {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        fileInfo(in); // pass Scanner to fileInfo method
    }

    // method to ask for file names and display some lines of output
    public static void fileInfo(Scanner in) throws FileNotFoundException { 
        
        System.out.println("This program compares two files for differences.");
        System.out.println();

        //asks user to enter the file names and saves the names to inputFile1 and inputFile 2 variables 
        System.out.print("Enter a first file name: ");
        String inputFile1 = in.nextLine(); 

        System.out.print("Enter a second file name: ");
        String inputFile2 = in.nextLine();

        System.out.println();
        System.out.println("Differences found:");

        // read both files
        Scanner input = new Scanner(new File(inputFile1)); 
        Scanner input2 = new Scanner(new File(inputFile2)); 

        compareFiles(input, input2); // call compareFiles method
    }

    // method to compare Files
    public static void compareFiles(Scanner input, Scanner input2) {

        int countLines=0; // counter to keep track of the number of lines in the files

        // while loop that will run as long as there's another line in both files
        while (input.hasNextLine() && input2.hasNextLine()) {
            
            countLines++; // add 1 to counter each time loop is ran
     
            String line = input.nextLine(); // reads entire line of inputFile1
            String line2 = input2.nextLine(); // reads entire line of inputFile2

            // if lines are equal don't print anything, if lines are not equal print the following in the else statement
            if(line.equals(line2)){
                System.out.print("");
            } else {
                System.out.println("Line " + countLines + ":");
                System.out.println("< "+ line);
                System.out.println("> " + line2);
                System.out.println();
            }  
        }

        // this if statement will run if one of the files has more lines than the other
        if (input.hasNextLine()) {
            countLines++; 
            String line = input.nextLine(); // reads entire line of inputFile1
            System.out.println("Line " + countLines + ":");
            System.out.println("< " + line);
            System.out.println("> no such line (end-of-file)"); // inputFile2 has come to an end
            System.out.println(); 
        } else {
            countLines++;
            String line2 = input2.nextLine(); // reads entire line of inputFile2
            System.out.println("Line " + countLines + ":");
            System.out.println("> no such line (end-of-file)"); // inputFile1 has come to an end
            System.out.println("< " + line2);
            System.out.println(); 
        }
    }
}