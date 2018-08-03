import java.text.DecimalFormat;

public class Question6_MainClass
{       
    final static int matrSize = 32; //Matrix size (matrSize x matrSize)
    
    public static void main(String args[])
    {
        /*Used for rounding values to 2 decimal places*/
        DecimalFormat decF = new DecimalFormat("0.00");
        
        /*Two matrices and the product matrix*/
        double matr1[][] = new double[matrSize][matrSize];
        double matr2[][] = new double[matrSize][matrSize];
        double matr3[][] = new double[matrSize][matrSize];
        
        double sumOfProd; //stores intermediary products
        
        /*
        Fill the matrices with random values from 0 to 999
        rounded to two decimal places.
        */
        for(int i=0; i<matrSize; i++){
            for(int j=0; j<matrSize; j++){
                matr1[i][j] = Double.parseDouble(
                        decF.format(Math.random()*1000));
                matr2[i][j] = Double.parseDouble(
                        decF.format(Math.random()*1000));
            }
        }
        
        /*Print matrices with max field sizes 6*/
        System.out.println("Matrix 1: ");
        printMatrix(matr1, 6);
        System.out.println("Matrix 2: ");
        printMatrix(matr2, 6);
        
        /*The product calculation*/
        for(int i=0; i<matrSize; i++){
            for(int j=0; j<matrSize; j++){
                sumOfProd = 0.0;
                /*Traverse row k of matr1 and column k of
                matr2 and add products of terms traversed*/
                for(int k=0; k<matrSize; k++)
                    sumOfProd += matr1[i][k]*matr2[k][j];
                matr3[i][j] = sumOfProd;
            }
        }
        /*Print resultant matrix with minimum field size 11*/
        System.out.println("Resultant matrix: ");
        printMatrix(matr3, 11);
    }
    
    private static void printMatrix(double matrix[][], int fieldSize)
    {
        /*Format of values: a minimum field size and round to 2 d.p.*/
        String format = "%"+fieldSize+".2f ";
        
        /*Print top for matrix*/
        for(int i=0; i<((fieldSize+1)*matrSize)-1; i++)
            System.out.print("-");
        System.out.println();
        
        /*Print matrix values*/
        for(int i=0; i<matrSize; i++){
            for(int j=0; j<matrSize; j++)
                System.out.printf(format, matrix[i][j]);
            System.out.println(); //Skip a line for a new row
        }
        
        /*Print base for matrix*/
        for(int i=0; i<((fieldSize+1)*matrSize)-1; i++)
            System.out.print("-");
        System.out.println("\n");
    }
}
