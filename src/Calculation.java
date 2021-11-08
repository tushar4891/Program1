import java.math.BigDecimal;
import java.util.Stack;


public class Calculation {
    
    public static BigDecimal calculate(String expression, BigDecimal arr1, BigDecimal arr2,BigDecimal arr3,BigDecimal arr4,
    String country, String region)
{
char[] tokens = expression.toCharArray();

// Stack for numbers: 'values'
Stack<BigDecimal> values = new Stack<BigDecimal>();

// Stack for Operators: 'ops'
Stack<Character> ops = new Stack<Character>();

// Running a loop till length of the token
for (int j = 0; j < tokens.length; j++)
{
  // Current token is a whitespace or 'a' skip it
   if (tokens[j] == ' ' || tokens[j] == 'a')
      continue;
   
   // if 1 appears, it means it is a1, so push arr1 into values stack.
   if(tokens[j] == '1')
       values.push(arr1);
   else
   {
       // if 2 appears, it means it is a2, so push arr2 into values stack.
       if(tokens[j] == '2')
           values.push(arr2);
       else
       {
           // if 3 appears, it means it is a3, so push arr3 into values stack.
           if(tokens[j] == '3')
               values.push(arr3);
           else
           {
               // if 4 appears, it means it is a4, so push arr4 into values stack.
               if(tokens[j] == '4')
                   values.push(arr4);
               else
               {
                   if (tokens[j] == '+' || tokens[j] == '-' ||
                       tokens[j] == '*' || tokens[j] == '/')
                   {
                       // While top of 'ops' has same or greater precedence to current
                       // token, which is an operator. Apply operator on top of 'ops'
                       // to top two elements in values stack
                       while (!ops.empty() && hasPrecedence(tokens[j], ops.peek()))
                                   values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                               // Push current token to 'ops'.
                               ops.push(tokens[j]);
                   }
               }
           }
       }
   }
}    
   
// Entire expression has been  parsed at this point, apply remaining
// ops to remaining values
while (!ops.empty()) 
values.push(applyOp(ops.pop(), values.pop(), values.pop()));

// Top of 'values' contains  result, return it
return values.pop();  
}


// Returns true if 'op2' has higher or same precedence as 'op1', otherwise returns false.
public static boolean hasPrecedence(char op1, char op2)
{
if ((op1 == '*' || op1 == '/') &&
(op2 == '+' || op2 == '-'))
return false;
else
return true;
}

// A utility method to apply an operator 'op' on operands 'a' and 'b'. Return the result.
public static BigDecimal applyOp(char op, BigDecimal b, BigDecimal a)
{
switch (op)
{
case '+':
return a.add(b);
case '-':
return a.subtract(b);
case '*':
return a.multiply(b);
case '/':
if (b.equals(new BigDecimal("0.00")))
throw new UnsupportedOperationException("Cannot divide by zero");
return a.divide(b);
}
return new BigDecimal("0.00");
}
}
