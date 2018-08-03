import java.util.Scanner;
import java.util.InputMismatchException;

public class Question3_MainClass
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in); //handles input
        
        int n=0; //stores number to check if prime
        boolean a, b; //stores result of the two functions
        
        /*Loop until input is negative*/
        do{
            try{
                System.out.print("Insert integer (0 or -ve to quit): ");
                n = sc.nextInt(); //number input
            }catch(InputMismatchException ime){
                System.out.println("Invalid input; not an integer.\n");
                sc.next(); //clear invalid input
                continue; //restart the loop
            }
            
            if(n <= 0) //If input is 0 or negative, break loop
                break;
            
            a = isPrime(n); //supports up to Long.MAX_VALUE
            b = isPrime_sieve(n); //supports up to VM limit of array size
                
            /*
            If results produced by isPrime and sieve do not match,
            an error has occurred. Otherwise, print final output.
            */
            if(a!=b){
                System.out.println("Error in one of the results.");
                break;
            }
            else{
                if(a) //if n is prime
                    System.out.println("By the two algorithms: " + n + " is a prime number.\n");
                else  //if n is not prime
                    System.out.println("By the two algorithms: " + n + " is not a prime number.\n");
            }
        }while(true);
        
        sc.close(); //close scanner
        System.out.println("Program terminated."); //final output
    }

    private static boolean isPrime(final long n)
    { 
        if(n==2)
            return true; //2 is prime
        else if(n<=1 || n%2==0) 
            return false; //values <= 1 and even numbers are not prime
        else{ //Check if n is prime
            /*Loop through odd values up to sqrt(n)+1*/
            for(long i=3; i<=1+Math.sqrt(n); i+=2)
                if(n%i == 0) //if i is a divisor of n
                    return false; //value is not prime
            return true; //value is prime
        }
    }
    
    private static boolean isPrime_sieve(final int n)
    {
        int primesCount=0; //counts amount of primes found
        
        /*1 and smaller values are not primes*/
        if(n<=1){
            System.out.println("Sieve of Eratosthenes primes: none");
            return false;
        }
        
        /*Declaration and initialization of nums array*/
        int nums[] = new int[n-1]; //from 2 to n, so n-1 length
        for(int i=2; i<=n; i++)
            nums[indOf(i)] = i; //(i-2)th element corresponds to value i
        
        /*Cancelling out non-primes*/
        for(int i=2; i<=n; i++){
            /*if already cancelled, skip*/
            if(nums[indOf(i)] == -1)
                continue;
            /*cancel all multiples of i {excluding i itself}*/
            for(int j=2; (i*j)<=n; j++)
                nums[indOf(i*j)] = -1;
        }
        
        /*Output primes found*/
        System.out.print("Sieve of Eratosthenes primes: 2");
        primesCount=1; //First prime is 2
        for(int i=1; i<nums.length; i++){
            if(nums[i] != -1){
                System.out.print("," + nums[i]);
                primesCount++;
            }
        }
        System.out.println("\nAmount of primes: "+primesCount);
        
        /*If n was cancelled, then n is not prime*/
        if(nums[indOf(n)] == -1)
            return false; //n is not prime
        else
            return true; //n is prime
    }
    
    /*Returns the index of the value i (assuming i >= 2)*/
    private static int indOf(final int i){
        return i-2;
    }
}
