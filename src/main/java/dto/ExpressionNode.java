package com.example.algebraic.dto;

public class ExpressionNode {

    private String value;
    private ExpressionNode left;
    private ExpressionNode right;

    public ExpressionNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public void setRight(ExpressionNode right) {
        this.right = right;
    }

    public boolean isOperator() {
        return value.equals("+") || value.equals("-") ||
                value.equals("*") || value.equals("/");
    }
}
