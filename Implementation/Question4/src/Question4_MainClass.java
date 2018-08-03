import java.util.Arrays;

public class Question4_MainClass
{
    public static void main(String args[])
    {
        int nums[] = new int[16384]; //numbers to be sorted
        
        /*Fill array with random values*/
        for(int i=0; i<nums.length; i++)
            nums[i] = (int) (Math.random()*100000); //0 to 99999
        
        /*Outputs and sort*/
        System.out.println("Before shell sort: " + Arrays.toString(nums).substring(0, 120));
        shellSort(nums); //sort
        System.out.println("After shell sort:  " + Arrays.toString(nums).substring(0, 120));
        
        /*Checks if array was successfully sorted*/
        if(sorted(nums))
            System.out.println("Sort succeded.");
        else
            System.out.println("Sort failed.");
    }
    
    private static void shellSort(int nums[])
    {
        boolean swapped = true; //indicates that a swap occurred
        
        int g = 1;  //gap
        while(g < nums.length)
            g*=2;   //up to (2^k)
        g--;        //g is now (2^k)-1
        
        while(g!=1 || swapped){
            if(g >= 2) //if interval is not 1
                g/=2;  //divide interval by 2
            
            swapped = false; //no values swapped yet
            
            /*Traverse the array*/
            for(int i=0; i+g < nums.length; i++){
                if(nums[i] > nums[i+g]){
                    swap(nums, i, i+g); //swap values
                    swapped = true; //swap occurred
                }
            }
        }
    }
    
    private static void swap(int nums[], int i1, int i2)
    {
        /*Swap two values of nums[] found at i1 and i2*/
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
    
    private static boolean sorted(final int nums[])
    {
        /*Detect values that are out of order*/
        for(int i=0; i<nums.length-1; i++)
            if(nums[i] > nums[i+1])
                return false; //list is not sorted
        return true; //list is sorted
    }
}
