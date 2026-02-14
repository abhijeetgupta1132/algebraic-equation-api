package com.example.algebraic.dto;

public class EquationItem {

    private int id;
    private ExpressionNode root;

    public EquationItem(int id, ExpressionNode root) {
        this.id = id;
        this.root = root;
    }

    public int getId() {
        return id;
    }

    public ExpressionNode getRoot() {
        return root;
    }
}
