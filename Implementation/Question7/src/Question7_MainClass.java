import java.util.Arrays;

public class Question7_MainClass 
{   
    public static void main(String args[])
    {
        /*Size of array from 10 to 20 items*/
        final int size = (int) (Math.random()*11)+10;
        final int nums[] = new int[size];
        
        /*Fill array with random integers from 0 to 9999*/
        for(int i=0; i<size; i++)
            nums[i] = (int) (Math.random()*10000);
        
        /*Print array and largest number*/
        System.out.println("Array of numbers: "
            + Arrays.toString(nums));
        System.out.println("Largest number: "
            + largest(nums, nums.length-1));
    }
    
    private static int largest(int nums[], int upto)
    {
        /*If list is empty, the largest cannot be found*/
        if(nums.length == 0){
            System.out.println("Error: array size is zero");
            return -1;
        }
        
        /*Largest up to index 0 is the first value, nums[0]*/
        if(upto == 0)
            return nums[0];
        else{
            /*Largest so far is the largest of the array tail*/         
            int soFar = largest(nums, upto-1);
            
            /*
            If current value larger than largest value so far,
            then current value is new largest value so far.
            */
            if(nums[upto] > soFar)
                return nums[upto]; //current value is new largest
            else
                return soFar; //largest so far remained largest
        }
    }
}