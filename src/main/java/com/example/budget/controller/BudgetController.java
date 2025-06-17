package com.example.budget.controller;

import com.example.budget.model.Budget;
import com.example.budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @PostMapping("/add")
    public Budget addBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    @GetMapping("/all")
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBudget(@PathVariable Long id) {
        budgetRepository.deleteById(id);
    }
}