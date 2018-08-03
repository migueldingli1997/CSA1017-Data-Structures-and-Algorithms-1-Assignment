import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack
{
    /*Storage for the stack*/
    private ArrayList<Double> items = new ArrayList<Double>();

    public void push(final double itemToPush)
    {
        items.add(itemToPush); //add the item
        printStackContents(items); //print stack
        System.out.println("("+itemToPush+" pushed)");
    }

    public double pop() throws EmptyStackException
    {
        double poppedItem; //stores popped double
        
        /*If stack not empty, pop item*/
        if (items.size() > 0) {
            poppedItem = items.get(items.size()-1); //get last item
            items.remove(items.size() - 1); //remove last item from list
            
            /*If stack is not empty, print status*/
            return poppedItem; //return popped item
        }
        /*throw exception if stack is empty*/
        throw new EmptyStackException();
    }

    private void printStackContents(final ArrayList<Double> val)
    {
        int maxWidth=0; //stores maximum width of stack
        int length=0; //length of values to be output
        
        /*
        Calculate the maximum width of the output by rounding
        each operand in the stack to six decimal places
        */
        for(int i = 0; i < val.size(); i++){
            length = String.format("%.6f", val.get(i)).length();
            if (length > maxWidth)
                maxWidth = length;
        }
        
        /*Output stack contents*/
        System.out.println("\nStack contents: ");
        for (int i=val.size()-1; i>=0; i--){
            /*minimum field width set to maxWidth and operands rounded*/
            System.out.printf(("|%"+maxWidth+".6f|\n"), val.get(i));
        }
        /*Output a base for the the stack*/
        for (int i = 0; i < maxWidth + 2; i++)
            System.out.print("-");
    }
}