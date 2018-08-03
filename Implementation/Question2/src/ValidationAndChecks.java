public class ValidationAndChecks
{
    public boolean isExpressionValid(final String exp)
    {
        /*Validation checks; if one not satisfied, expression is invalid*/
        if (exp.length() == 0) //Check if input is empty
            System.out.println("Invalid expression; empty input.\n");
        else if (!hasValidCharacters(exp)) //...for forbidden characters
            System.out.println("Illegal characters in expression.\n");
        else if (!containsOperands(exp)) //..for a lack of operands
            System.out.println("Invalid expression; lack of operands.\n");
        else if (!hasCorrectStructure(exp)) //..for an invalid structure
            System.out.println("Invalid expression; structural issue.\n");
        else
            return true; //Expression valid
        return false; //Expression invalid
    }
    
    private boolean hasValidCharacters(final String exp)
    {
        /*Traverse the expression's characters*/
        for(int i=0; i<exp.length(); i++)
        {
            /*
            If the character is not a part of an operand, an
            operator, or a space, then expression is invalid
            */
            if(!isNumPart(i,exp)
                && !isOperator(exp.charAt(i))
                && exp.charAt(i) != ' ')
                return false; //Invalid character detected
        }
        return true; //All characters are valid
    }

    private boolean containsOperands(final String exp)
    {       
        /*Checks if expression contains any digit*/
        for (int i = 0; i < 10; i++) {
            if (exp.contains(""+i))
                return true; //Expression contains a digit
        }
        return false; //Expression does not contain digits
    }
    
    private boolean hasCorrectStructure(String exp)
    {
        exp += " ";
        
        /*Operand and operator counts*/
        int operandCount = 0, operatorCount = 0;

        /*Traverse the expression's characters*/
        for (int i = 0; i < exp.length(); i++)
        {
            /*If the start of an operand is found*/
            if (isNumPart(i,exp))
            {
                /*Skip remainder of operand*/
                do i++;
                while (i < exp.length() && isNumPart(i,exp));
                operandCount++; //increment operand count
            }
            else if (isOperator(exp.charAt(i))) //operator found
            {
                operatorCount++; //increment operator count
                i++; //Skip space after operator
                
                /*
                In postfix, there has to be as much operands
                as one plus the amount of operators
                */
                if(operatorCount + 1 > operandCount)
                    return false;
            }
        }

        /*
        In postfix notation, there has to be as much 
        operands as one plus the amount of operators.
        */
        if (operatorCount + 1 == operandCount)
            return true;
        else
            return false;
    }

    public boolean isOperator(final char ch) {
        /*True if the character is one of the allowed operators*/
        return (ch == '+'    || ch == '-' 
                || ch == '*' || ch == '/' 
                || ch == '^');
    }
    
    public boolean isNumPart(final int i, final String exp) {
        /*
        True if the character is a digit, a decimal point
        or the character is a minus and the next character 
        is a digit, indicating a negative number.
        */
        final char ch = exp.charAt(i);
        return(Character.isDigit(ch)
                || ch=='.'
                || (i+1<exp.length() && ch=='-' 
                    && Character.isDigit(exp.charAt(i+1)))
        );
    }
}