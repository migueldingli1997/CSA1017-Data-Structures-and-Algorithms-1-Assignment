import java.util.Scanner;
import java.util.InputMismatchException;

public class Question1_MainClass
{
    public static void main(String args[])
    {
        int input = 0; //stores input decimal number
        Scanner sc = new Scanner(System.in); //handles input

        /*Loop until input is negative (indicating a quit)*/
        while (input >= 0)
        {
            System.out.print("Insert decimal number (-ve to quit): ");
            try {
                input = sc.nextInt(); //user input of decimal number

                /*Check if input out of range; else calculate Roman num*/
                if(input == 0 || input > 1024)
                    System.out.println("Input out of range; insert a number from 1 to 1024.\n");
                else if(input > 0)
                    System.out.println("Roman number: " + numToRom(input) + "\n");
            }
            catch(InputMismatchException ime) //detects non-integer inputs
            {
                System.out.println("Invalid input; insert an integer from 1 to 1024.\n");
                sc.next(); //clear invalid input
            }
        }
        sc.close(); //close scanner
        System.out.println("Program terminated."); //final output
    }

    private static String numToRom(int num)
    {
        String finalRom = ""; //stores resultant Roman numeral
        //All possible letter combinations
        final String ROM[] = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        //All corresponding decimal values
        final int DEC[] = {1,4,5,9,10,40,50,90,100,400,500,900,1000};

        /*Greedy algorithm; starting from last decimal value*/
        for(int i=DEC.length-1; i >= 0; i--)
        {
            /*Subtracting until num is smaller than current decimal*/
            while(num >= DEC[i])
            {
                num -= DEC[i]; //subtract decimal
                finalRom += ROM[i]; //add respective numeral to output
            }
        }
        return finalRom; //return resultant Roman numeral
    }
}
