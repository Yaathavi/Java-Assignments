/* Yaathavi Theevannan, Date: March 30, 2021

This program reads a file with numbers and prints the sum of the numbers in each line and the total lines in the file.

Input: A text file called sum.txt 
Output: Addition statement with sum of the numbers in each line and the total number of lines in the file */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Sum {

    final static int TOTAL_DIGITS = 25; // max # of digits in each number in input file is 25
    public static void main (String [] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("sum.txt")); // reads sum.txt file
        readLines(input); // calls readLines method while passing input as parameter
    }

    // method to count total number of lines 
    public static void readLines(Scanner input){
        int count=0; // counter to keep track of number of lines 

        // while loop that runs for each line in the sum.txt file.
        while (input.hasNextLine()){
            readNum(input); // calls readNum method for each line in sum.txt file 
            count++;
        }
        System.out.println("\nTotal lines = " + count); // prints total number of lines
    }

  
    // method that reads all the numbers in a line and outputs the addition statement with correct sum
    public static void readNum (Scanner input){
        Scanner lineScan = new Scanner(input.nextLine()); // reads token-by-token

        // initialize variables and arrays 
        String num1="";
        String num2="";
        int[] arr1 =  new int[TOTAL_DIGITS]; 
        int[] arr2 =  new int[TOTAL_DIGITS];

        num1 = lineScan.next(); // gets the next token (number)
        System.out.print(num1); 
        arr1 = convertToArr(num1); // converts num1 to an array 

        // while loop that runs if there's another token on the line
        while (lineScan.hasNext()){
            num2 = lineScan.next(); // gets the next number to add to num1
            System.out.print(" + " + num2); 

            arr2 = convertToArr(num2); // converts num2 to an array 
            arr1 = add(arr1, arr2); // passes the 2 arrays to the add method and assigns the sum to arr1 
        }
        System.out.println(" = " + arrToString(arr1)); // prints final sum of each line
    }
    
    // method to put each number's digits into an array with leading zeros
    public static int[] convertToArr(String word){
        
        int[] numArr = new int[TOTAL_DIGITS]; 
        int zeros = TOTAL_DIGITS - word.length(); // calculate number of leading zeros needed

        // for loop to add the digits of a number to an array starting from the end
        for(int i=0; i < word.length(); i++){
            numArr[zeros+i] = Character.getNumericValue(word.charAt(i)); 
        }
        return numArr;  
    }

    //method to add the 2 numbers 
    public static int[] add(int[] arr1, int[] arr2){
        int[] sumArr = new int[TOTAL_DIGITS];
        int carry = 0; // variable to keep track of when you need to carry a 1 to the previous digit

        for(int i=24; i >= 0; i--){
            int sum = arr1[i] + arr2[i] + carry; 
            carry=0; // reset to 0 so you don't carry more than 1 the next time

            // Need to carry 1 forward if sum > 9 
            if(sum > 9){
                sum = sum-10; 
                carry++;
            } else{
                carry=0;
            }
            sumArr[i] = sum; 
        }
        return sumArr;
    }

    // method to convert the array containing the sum of each line to a string with no leading zeros
    public static String arrToString(int[] arr1) {
        
        boolean started = true; // will be true if number has started and there are no more leading zeros
        String sum = "";

        // Run for each digit in the array
        for (int i = 0; i < arr1.length; i++) {

            if (started) {
                started = false;
                sum += arr1[i]; 
         /* set started to true once it encounters the first non-zero digit so that even if there’s a zero in the digit, it will be concatenated to the string */
            }
        }
        // If the array only has zeros, make the string equal 0.
        if (started) {
            sum = "0";
        }
        return sum;
    }
}