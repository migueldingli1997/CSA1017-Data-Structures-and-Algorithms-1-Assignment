import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigInteger;
    
public class Question9_MainClass
{   
    public static void main(String args[])
    {   
        Scanner sc = new Scanner(System.in); //handles input
        int terms=1; //stores input amount of terms
        
        /*Loop until terms is zero or negative*/
        do{
            try{
                /*Input of amount of terms*/
                System.out.print("Insert a number (0 or -ve to quit): ");
                terms = sc.nextInt();
            }catch(InputMismatchException ime){
                System.out.println("Invalid input.\n");
                sc.next(); //clear invalid input
                continue; //go to start of loop
            }
            
            /*If terms > 0, calculate and output sum of terms*/
            if(terms > 0)
                System.out.println("Sum is: "+fibSum(terms)+"\n");
        }while(terms > 0);
        
        System.out.println("Program terminated."); //final output
        sc.close(); //close scanner
    }
    
    private static String fibSum(final int n)
    {   
        BigInteger fib0 = BigInteger.ZERO; //zeroth term
        BigInteger fib1 = BigInteger.ONE;  //first term
        BigInteger temp; //temporarily stores value of fib1
        
        /*Compute up to the (n+2)nd term*/
        for(int i=2; i <= n+2; i++){
            temp = fib1;           //store current fib1
            fib1 = fib1.add(fib0); //calculate next term
            fib0 = temp;           //set fib0 to previous value of fib1
            /* 
            fib1 is now the ith     Fibonacci sequence term.
            fib0 is now the (i-1)th Fibonacci sequence term.
            */
        }
        /*Subtract 1 from the (n+2)nd term and return in string form*/
        return fib1.subtract(BigInteger.ONE).toString(); 
    }
}   