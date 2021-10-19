/* Yaathavi Theevannan, Date: April 12, 2021

This program reads a file containing people's name and their answers for the Keirsey test and outputs their name, personality type, 
number of A&B answers and percentage of b answers for each dimension to an output file of their choice.

Input: Asks user for the name of 2 files; an input file to read from and an output file to output their results to
Output: outputs their name, personality type, number of A&B answers and % of b answers for each dimension to a file  */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PersonalityTest {

    public final static int NUM_OF_DIMENSIONS = 4; // The Keirsey test measures 4 dimensions of personality
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        files(input); // calls files method 
    }

    //method to accept file names and read each line in the files 
    public static void files(Scanner input) throws FileNotFoundException { 
        File inputName = inputVerify(input); // call inputVerify method to get the name of the input file 

        System.out.print("Output file name: "); // ask user for output file name and save it to outputFile variable 
		String outputName = input.next();

        Scanner inputFile = new Scanner(inputName); // reads input file

        // creates a new PrintStream object to print to the output file
        PrintStream output = new PrintStream(new File(outputName)); 

        // while loop that runs as long as the input file has another line
        while (input.hasNextLine()){
            String line = inputFile.nextLine(); // reads entire line
            output.println(line + ":"); // prints name to file
            String scoreLine = inputFile.nextLine(); // reads next line
            abCount(scoreLine, output); // call abCount method
        }
    }

    // method that prompts user for input file name until a valid input file is entered
    public static File inputVerify(Scanner input)    {
        boolean fileFound = false; // set fileFound boolean to false at first
        File inputName = new File(""); // create new file object 

        // asks user to enter an existing file name 
        System.out.print("Input file name: ");
        inputName = new File(input.next());

        // while loop that runs until valid input file name is entered
        while(fileFound == false) { 
            //if statement for when an invalid input file is entered
            if (!inputName.exists()) { 
              System.out.print("File not found. Try again: ");
              inputName = new File(input.next());
            } else {
                fileFound = true; // fileFound becomes true if valid file name is entered        
            }
        }
        return inputName; // returns the correct input file
    } 
    
    // method to create 2 arrays to keep track of A count and B count for each of the 4 dimensions
    public static void abCount(String scoreLine, PrintStream output) {
        // initialize arrays for a count and b count
        int[] a =  new int[NUM_OF_DIMENSIONS]; 
        int[] b =  new int[NUM_OF_DIMENSIONS]; 

        // 2 for loop because there's 10 groups of 7 
        for(int i=0; i<10; i++){
            for(int j=0; j<7; j++){
                int aOrB = 0; // variable to keep track of if user put A or B where A equals 0 and B equals 1
                char currChar = scoreLine.charAt(i*7+j); // gets each character's position in a line
                
                // outer if statement for when user does not enter a dash 
                if(currChar != '-'){
                    // if user enters B, assign 1 to the aOrB variable
                    if(currChar == 'B' || currChar == 'b'){
                        aOrB = 1;
                    }

                    if(j==0){  // if statement to keep track of first dimesion (E vs I)
                        // check aOrB's value to determine which array to add 1 to
                        if(aOrB==0){
                            a[0] += 1;
                        } else {
                            b[0] += 1;
                        }
                    } else if(j== 1 || j==2){ // else if statement to keep track of second dimension (S vs N)
                        // check aOrB's value to determine which array to add 1 to
                        if(aOrB==0){
                            a[1] += 1;
                        } else {
                            b[1] += 1;
                        }
                    } else if(j== 3 || j==4){ // else if statement to keep track of third dimension (T vs F)
                        // check aOrB's value to determine which array to add 1 to
                        if(aOrB==0){
                            a[2] += 1;
                        } else {
                            b[2] += 1;
                        }
                    } else { // else statement to keep track of fourth dimension (J vs P)
                        // check aOrB's value to determine which array to add 1 to
                        if(aOrB==0){
                            a[3] += 1;
                        } else {
                            b[3] += 1;
                        }
                    }
                }
            }
        }
        displayScores(a,b,output); //call displayScores method to output A & B counts to output file
        bPercent(a,b,output); // call bPercent method to output percentage of b answers to output file
        type(a,b,output); // call type method to output personality type to output file
    }

    // method to output number of A and B answers in each dimension 
    public static void displayScores(int[] a, int[] b, PrintStream output) {
        output.print(a[0] + "A-" + b[0] + "B"); 

        // for loop to print the number of A and B answers for dimensions 2-4
        for(int i=1; i< NUM_OF_DIMENSIONS; i++){
            output.print(" " + a[i] + "A-" + b[i] + "B");
        }
        output.println(); 
    }

    // method to output percentage of b answers in each dimension 
    public static void bPercent(int[] a, int[] b, PrintStream output) {
        int percent = (int) Math.round((((double) b[0])/(b[0]+a[0]))*100);
        output.print("[" + percent +"%");

        // for loop to print the percentage of b answers for dimensions 2-4
        for(int i=1; i< NUM_OF_DIMENSIONS; i++){
            percent = (int) Math.round((((double) b[i])/(b[i]+a[i]))*100);
            output.print(", " + percent + "%");
        }
        output.print("] = "); 
    }

    /* method to output personality type based on number of A and B answers.
     If there's an equal number of A and B answers, it will print an X. */
    public static void type(int[] a, int[] b, PrintStream output) {

        // if statement to compare the number of a and b answers in the first dimension (E/I)
        if(a[0]>b[0]){
            output.print("E");
        } else if(a[0]<b[0]){
            output.print("I");
        } else {
            output.print("X");
        }
        
        // if statement to compare the number of a and b answers in the second dimension (S/N)
        if(a[1]>b[1]){
            output.print("S");
        } else if(a[1]<b[1]){
            output.print("N");
        } else {
            output.print("X");
        }
        
        // if statement to compare the number of a and b answers in the third dimension (T/F)
        if(a[2]>b[2]){
            output.print("T");
        } else if(a[2]<b[2]){
            output.print("F");
        } else {
            output.print("X");
        }
        
        // if statement to compare the number of a and b answers in the fourth dimension (J/P)
        if(a[3]>b[3]){
            output.println("J");
            output.println();
        } else if(a[3]<b[3]){
            output.println("P");
            output.println();
        } else {
            output.println("X");
            output.println();
        }
    }
}