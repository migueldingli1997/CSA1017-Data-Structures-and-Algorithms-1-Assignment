import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Question5_MainClass
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in); //handles input
        double num = 0.0, sqrRt = 0.0; //user input and final answer
        int acc = 0; //accuracy (decimal places)

        /*Loop until an input is negative (indicating a quit)*/
        while(num>=0 && acc>=0)
        {
            try{
                System.out.print("Insert a number (-ve to quit): ");
                num = sc.nextDouble();  //input number
                if(num < 0)
                    break;
                System.out.print("Insert the accuracy (-ve to quit): ");
                acc = sc.nextInt();     //input accuracy
                if(acc < 0)
                    break;
            }catch(InputMismatchException ime){
                System.out.println("Invalid input.\n");
                sc.next(); //clear invalid input
                continue; //go to start of loop
            }
            
            /*Maximum fractional part size of 8 digits*/
            if(acc > 8)
                acc = 8;

            /*Find square root and convert to string*/
            sqrRt = findSqrRt(num, acc);
            
            /*If fractional part is zero, output an integer as answer*/
            if((int) sqrRt == sqrRt)
                System.out.println("Square root of " + num + " is " + (int)sqrRt + "\n");
            else
                System.out.println("Square root of " + num + " is " + sqrRt + "\n");
        }
        sc.close(); //close scanner
        System.out.println("Program terminated."); //final output
    }

    private static double findSqrRt(final double num, final int acc)
    {
        double sqrRt = num; //stores result
        double previous = 0.0; //stores previous value
        double latest = sqrRt; //stores latest value
        
        /*Square root of zero is zero*/
        if(num == 0.0)
            return 0.0;
        
        /*Loop until latest value is equal to previous value*/
        do{
            /*Generate next value by Newton-Raphson Method*/
            sqrRt -= (Math.pow(sqrRt,2) - num) / (2*sqrRt);
            /*Set previous to previous value*/
            previous = latest;
            /*Rounded to avoid infinite loops*/
            latest = roundNum(sqrRt,acc+2);
        }while (previous != latest);

        return roundNum(sqrRt,acc); //return rounded answer
    }
    
    private static double roundNum(final double num, final int acc)
    {
        /*Setting format of rounded number*/
        String format = "#.";
        for(int i=0; i<acc; i++)
            format += "#";
        DecimalFormat df = new DecimalFormat(format);
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        /*Round number, convert to double, and return*/
        return Double.parseDouble(df.format(num));
    }
}
