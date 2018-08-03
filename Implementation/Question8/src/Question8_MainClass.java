import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Question8_MainClass
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in); //handles input
        double xDeg, xRad; //store input in degrees and radians
        int terms;   //stores input amount of terms
        String type; //shows if cos or sine will be computed
        
        /*Loop until input starts with 'q' (indicating a quit)*/
        do{
            try{
                System.out.print("Insert angle in degrees (q to quit): ");
                xDeg = sc.nextDouble(); //input angle in degrees
                System.out.print("Insert amount of terms (q to quit): ");
                terms = sc.nextInt(); //input amount of terms
            }catch(InputMismatchException ime){
                /*If input starts with 'q', then quit*/
                if(sc.next().startsWith("q"))
                    break;
                else{
                    System.out.println("Invalid input.\n");
                    continue; //go to start of loop
                }
            }
            
            if(terms < 1){ //if amount of terms less than 1, invalid
                System.out.println("Invalid amount of terms.\n");
                continue; //go to start of loop
            }
            
            System.out.print("Insert COS for cosine or SIN for sine: ");
            type = sc.next(); //input of calculation type
            /*If type is neither COS nor SIN*/
            if(!type.toLowerCase().equals("cos")
                && !type.toLowerCase().equals("sin")){
                System.out.println("Invalid calculation type.\n");
                continue; //go to start of loop
            }
            
            /*Convert xDeg to radians and store in xRad*/
            xRad = ((xDeg%360)/180)*Math.PI;
        
            /*Compute and output*/
            if(type.toLowerCase().equals("cos"))
                System.out.println("cos("+xDeg+") = " + compCorS(xRad,terms,"cos") + "\n");
            else if(type.toLowerCase().equals("sin"))
                System.out.println("sin("+xDeg+") = " + compCorS(xRad,terms,"sin") + "\n");
        }while(true);
        sc.close(); //close scanner
        System.out.println("Program terminated."); //final output
    }
    
    private static String compCorS(double xRad, int n, String type)
    {
        BigDecimal result = null;     //stores result
        BigDecimal numer = bigDec(0); //stores numerator
        BigDecimal denom = bigDec(1); //stores denominator
        BigDecimal x = bigDec(xRad);  //stores input (in radians)
        
        /*Compute cosine*/
        if(type.equals("cos"))
        {
            result = bigDec(1); //first term is '1'
            for(int i=1; i<n; i++){
                /*Computing numerator of term*/
                numer = x.pow(2*i).multiply(bigDec((Math.pow(-1,i))));
                /*Computing denominator of term*/
                denom = denom.multiply(bigDec( ((2*i)-1)*(2*i)) );
                /*Dividing numerator by denominator and rounding to 11dp*/
                result = result.add(
                		numer.divide(denom,11,RoundingMode.HALF_UP));
            }
        }
        /*Compute sine*/
        else if(type.equals("sin"))
        {
            result = bigDec(xRad); //first term is 'x' (in radians)
            for(int i=1; i<n; i++){
                /*Computing numerator and denominator of term*/
                numer = x.pow((2*i)+1).multiply(bigDec(Math.pow(-1,i)));
                /*Computing denominator of term*/
                denom = denom.multiply(bigDec( (2*i)*((2*i)+1) ));
                /*Dividing numerator by denominator and rounding to 11dp*/
                result = result.add(
                		numer.divide(denom,11,RoundingMode.HALF_UP));
            }
        }

        /*If type is a valid type, finalize and return result*/
        if(type.equals("cos") || type.equals("sin")){
            /*Round result to 10 decimal places and return result*/
            result = result.setScale(10, RoundingMode.HALF_UP);
            return result.toString();
        }
        else{
        	System.out.println("Error: Invalid calculation type.");
        	return null;
        }
    }
    
    private static BigDecimal bigDec(final double i)
    {
        /*Returns a BigDecimal of value 'i'*/
        return new BigDecimal(i);
    }
}