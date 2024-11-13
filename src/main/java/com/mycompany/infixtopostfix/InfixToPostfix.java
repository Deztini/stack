/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.infixtopostfix;
import java.util.Stack;
/**
 *
 * @author HP
 */
public class InfixToPostfix {
    
     // Stack for characters in expression
    static Stack<Character> stack = new Stack<>();
    
    // Push character to stack
    static void push(char item) {
        stack.push(item);
    }

    // Pop character from stack
    static char pop() {
        return stack.pop();
    }

    // Returns precedence of operators
    static int precedence(char symbol) {
        switch(symbol) {
            case '+': 
            case '-': return 2;
            case '*': 
            case '/': return 3;
            case '^': return 4;
            case '(': 
            case ')': 
            case '#': return 1;
        }
        return -1;
    }

    // Checks whether the symbol is an operator
    static boolean isOperator(char symbol) {
        switch(symbol) {
            case '+': 
            case '-': 
            case '*': 
            case '/': 
            case '^': 
            case '(': 
            case ')': return true;
            default: return false;
        }
    }

    // Converts infix expression to postfix
    static String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        stack.push('#'); // Marker for the bottom of the stack

        for (int i = 0; i < infix.length(); i++) {
            char symbol = infix.charAt(i);

            if (!isOperator(symbol)) {
                // If the character is an operand, add it to postfix
                postfix.append(symbol);
            } else if (symbol == '(') {
                push(symbol);
            } else if (symbol == ')') {
                while (stack.peek() != '(') {
                    postfix.append(pop());
                }
                pop(); // Pop '(' from stack
            } else {
                // Operator encountered
                while (precedence(symbol) <= precedence(stack.peek())) {
                    postfix.append(pop());
                }
                push(symbol);
            }
        }

        // Pop all remaining operators
        while (stack.peek() != '#') {
            postfix.append(pop());
        }

        return postfix.toString();
    }

    // Stack for integers
    static Stack<Integer> intStack = new Stack<>();

    // Push integer to stack
    static void pushInt(int item) {
        intStack.push(item);
    }

    // Pop integer from stack
    static int popInt() {
        return intStack.pop();
    }

    // Evaluates postfix expression
    static int evaluate(String postfix) {
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            if (Character.isDigit(ch)) {
                pushInt(ch - '0'); // Push the operand
            } else {
                int operand2 = popInt();
                int operand1 = popInt();

                // Perform the operation
                switch (ch) {
                    case '+': pushInt(operand1 + operand2); break;
                    case '-': pushInt(operand1 - operand2); break;
                    case '*': pushInt(operand1 * operand2); break;
                    case '/': pushInt(operand1 / operand2); break;
                }
            }
        }

        return intStack.peek();
    }
    public static void main(String[] args) {
         String infix = "3 5 6 * + 13 - 18 2 / + =";
        String postfix = convert(infix);

        System.out.println("Infix expression: " + infix);
        System.out.println("Postfix expression: " + postfix);
        System.out.println("Evaluated result: " + evaluate(postfix));
    }
}
