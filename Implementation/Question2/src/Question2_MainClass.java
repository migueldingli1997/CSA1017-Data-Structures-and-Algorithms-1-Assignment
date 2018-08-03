import java.util.EmptyStackException;
import java.util.Scanner;
import java.lang.NumberFormatException;

public class Question2_MainClass
{
    private static ValidationAndChecks valAndCh
        = new ValidationAndChecks();

    public static void main(String args[])
    {       
        Scanner sc = new Scanner(System.in); //handles input
        String exp = ""; //stores user input expression

        /*
        User input and validation checks...
        Loop until input starts with a 'q' (indicating a quit)
        */
        mainLoop: do{
            /*Loop until expression is valid*/
            do{
                System.out.print("Insert a postfix expression (q to quit): ");
                exp = sc.nextLine(); //input postfix expression

                /*If expression starts with 'q', stop outer loop*/
                if(exp.startsWith("q"))
                    break mainLoop;
            }while(!valAndCh.isExpressionValid(exp));
            
            evaluateExpress(exp); //evaluate expression
        }while(true);
        
        sc.close(); //close scanner
        System.out.println("Program terminated."); //final output
    }

    private static void evaluateExpress(final String exp)
    {
        Stack opStack = new Stack(); //for operands
        int endOfNum = 0; //stores index of end of a number
        double op1 = 0.0, op2 = 0.0; //stored popped operands
        double answer = 0.0; //stores sub-answers

        /*Traverse expression's characters*/
        for(int i=0; i < exp.length(); i++)
        {
            /*
            If the start of a number was found, push.
            If an operators was found, pop two operands,
            perform the operation, and push the result.
            */
            if(valAndCh.isNumPart(i,exp)){
                /*
                Since each operand must be followed by a
                space, if a space is not found, then the
                expression is invalid.
                */
                endOfNum = exp.indexOf(" ", i);
                
                /*If a space was not found after operand*/
                if (endOfNum == -1){
                    try{
                        /*Try to convert exp to a double and push*/
                        opStack.push(Double.parseDouble(exp));
                        break; //exit main loop
                    }catch(NumberFormatException e){
                        /*Expression was invalid after all*/
                        errorInEvaluation(1);
                        return; //dummy return
                    }
                }
                else{
                    try{
                        /*Push the extracted double*/
                        opStack.push( Double.parseDouble(
                                exp.substring(i,endOfNum)
                        ) );
                    }catch (NumberFormatException nfe){
                        errorInEvaluation(2); //operand was not a double
                        return; //dummy return
                    }
                }
                i = endOfNum; //index of end of operand
            }
            else if(valAndCh.isOperator(exp.charAt(i)))
            { 
                /*Pop two operands; if stack is empty, error occurred*/
                try{
                    op2 = opStack.pop();
                    op1 = opStack.pop();
                }catch(EmptyStackException e){
                    errorInEvaluation(3);
                    return;
                }
                System.out.println("\nTwo operands popped; "
                    +"Operation performed: "+exp.charAt(i));

                /*Perform operation according to operator*/
                switch(exp.charAt(i)){
                case '+':
                    answer = op1 + op2;
                    break;
                case '-':
                    answer = op1 - op2;
                    break;
                case '*':
                    answer = op1 * op2;
                    break;
                case '/':
                    answer = op1 / op2;
                    break;
                case '^':
                    answer = Math.pow(op1, op2);
                    break;
                }
                opStack.push(answer); //Push result
            }
        }
        /*Print final result to 5 decimal places*/
        System.out.println("\nAnswer: "+opStack.pop()+"\n");
    }

    private static void errorInEvaluation(final int type)
    {
        /*Prints a custom error message based on 'type'*/
        System.out.println("Error of type "+type
            +" occurred in evaluation.\n");
    }
}
