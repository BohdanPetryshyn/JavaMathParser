package MathParser;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class MathParser {
    public static double evaluate(@NotNull String expression){

        // TODO: 15.12.2018 Create expression format checker

        Stack<Double> valueStack = new Stack<>();
        Stack<Operator> operatorStack = new Stack<Operator>();

        Token token;
        Value value;
        Operator operator;
        double firstOperand;
        double secondOperand;
        String localExpression = expression;


        endlessCycle: while(true){
            token = firstToken(localExpression);

            if(token.getType() == TokenType.VALUE){                                // token is a value
                value = (Value) token;
                valueStack.push(value.getValue());
                localExpression = localExpression.substring(value.getLength());  // shifting start of expression to get another token next time
            }
            else{                                                                  // token is a operator
                operator = (Operator) token;
                switch (operator.getType()){
                    case OPERATOR_PLUS:                 // TODO: 15.12.2018 refactor this fucking shit
                    case OPERATOR_MINUS:
                    case OPERATOR_MUL:
                    case OPERATOR_DIV:
                    case OPERATOR_POW:
                        while(!operatorStack.empty() && operator.getType().getPriority() <= operatorStack.peek().getType().getPriority()){
                            secondOperand = valueStack.pop();
                            firstOperand = valueStack.pop();
                            valueStack.push(calculate(operatorStack.pop(), firstOperand, secondOperand));
                        }
                        operatorStack.push(operator);
                        break;
                    case OPERATOR_LEFT_BRACKET:
                        operatorStack.push(operator);
                        break;
                    case OPERATOR_RIGHT_BRACKET:
                        while (operatorStack.peek().getType() != TokenType.OPERATOR_LEFT_BRACKET){
                            secondOperand = valueStack.pop();
                            firstOperand = valueStack.pop();
                            valueStack.push(calculate(operatorStack.pop(), firstOperand, secondOperand));
                        }
                        operatorStack.pop();
                        break;
                    case OPERATOR_END:
                        while (!operatorStack.empty()){
                            secondOperand = valueStack.pop();
                            firstOperand = valueStack.pop();
                            valueStack.push(calculate(operatorStack.pop(), firstOperand, secondOperand));
                        }
                        break endlessCycle;
                }
                localExpression = localExpression.substring(1);
            }
        }

        return valueStack.pop();
    }


    private static Token firstToken(@NotNull String expression){
        if(expression.length() == 0){
            return new Operator(TokenType.OPERATOR_END);
        }
        char firstChar = expression.charAt(0);
        int index = 1;
        double value;
        switch(firstChar){
            case '+':
                return new Operator(TokenType.OPERATOR_PLUS);
            case '-':
                return new Operator(TokenType.OPERATOR_MINUS);
            case '*':
                return new Operator(TokenType.OPERATOR_MUL);
            case '/':
                return new Operator(TokenType.OPERATOR_DIV);
            case '^':
                return new Operator(TokenType.OPERATOR_POW);
            case '(':
                return  new Operator(TokenType.OPERATOR_LEFT_BRACKET);
            case ')':
                return new Operator(TokenType.OPERATOR_RIGHT_BRACKET);
            default:
                if(Character.isDigit(firstChar)){
                    while(index < expression.length() &&
                            (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')){
                        index++;
                    }
                    value = Double.parseDouble(expression.substring(0, index));
                    return new Value(value, index);
                }
                else return null;
        }

    }

    private static double calculate(Operator operator, double firstOperand, double secondOperand){
        switch (operator.getType()){
            case OPERATOR_PLUS:
                return firstOperand + secondOperand;
            case OPERATOR_MINUS:
                return firstOperand - secondOperand;
            case OPERATOR_MUL:
                return firstOperand * secondOperand;
            case OPERATOR_DIV:
                return firstOperand / secondOperand;
            case OPERATOR_POW:
                return Math.pow(firstOperand, secondOperand);
            default:
                return 0;
        }
    }
}
