package com.example.algebraic.service;

import com.example.algebraic.dto.ExpressionNode;

import java.util.Map;
import java.util.Stack;

public class ExpressionTreeBuilder {


    public ExpressionNode buildTree(String postfix) {

        Stack<ExpressionNode> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {

            if (token.matches("[a-zA-Z0-9]+")) {
                stack.push(new ExpressionNode(token));
            } else {
                ExpressionNode right = stack.pop();
                ExpressionNode left = stack.pop();

                ExpressionNode node = new ExpressionNode(token);
                node.setLeft(left);
                node.setRight(right);

                stack.push(node);
            }
        }
        return stack.pop();
    }
    //
    public int evaluate(ExpressionNode node, Map<String, Integer> variables) {

        if (!node.isOperator()) {

            if (node.getValue().matches("\\d+")) {
                return Integer.parseInt(node.getValue());
            }

            if (!variables.containsKey(node.getValue())) {
                throw new IllegalArgumentException(
                        "Missing variable: " + node.getValue()
                );
            }

            return variables.get(node.getValue());
        }


        int left = evaluate(node.getLeft(), variables);
        int right = evaluate(node.getRight(), variables);

        return switch (node.getValue()) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

}
