package com.example.algebraic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.algebraic.dto.EquationItem;
import com.example.algebraic.dto.EquationRequest;
import com.example.algebraic.dto.VariableRequest;
import com.example.algebraic.dto.ExpressionNode;
import com.example.algebraic.service.InfixToPostfixConverter;
import com.example.algebraic.service.ExpressionTreeBuilder;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equations")
public class EquationController {

    private List<EquationItem> equations = new ArrayList<>();
    private int counter = 1;

    private final InfixToPostfixConverter converter = new InfixToPostfixConverter();
    private final ExpressionTreeBuilder treeBuilder = new ExpressionTreeBuilder();

    // STEP 1: Store equation as postfix tree
    @PostMapping("/store")
    public String store(@RequestBody EquationRequest request) {

        if (request.getEquation() == null || request.getEquation().trim().isEmpty()) {
            throw new IllegalArgumentException("Equation cannot be empty");
        }

        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        ExpressionTreeBuilder treeBuilder = new ExpressionTreeBuilder();

        String postfix = converter.convert(request.getEquation());
        ExpressionNode root = treeBuilder.buildTree(postfix);

        equations.add(new EquationItem(counter++, root));
        return "Equation stored successfully";
    }


    // STEP 2: Get all stored equations (IDs only, tree is internal)
    @GetMapping
    public List<Integer> getAll() {
        List<Integer> ids = new ArrayList<>();
        for (EquationItem item : equations) {
            ids.add(item.getId());
        }
        return ids;
    }

    // STEP 3: Evaluate equation using postfix tree
    @PostMapping("/{id}/evaluate")
    public int evaluate(
            @PathVariable int id,
            @RequestBody VariableRequest request
    ) {

        EquationItem eq = equations.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Equation not found"
                        )
                );

        try {
            return treeBuilder.evaluate(eq.getRoot(), request.getVariables());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ex.getMessage()
            );
        }
    }


}
