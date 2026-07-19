package com.dynamodb.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamodb.demo.model.Employee;
import com.dynamodb.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    @PostMapping
    public void save(@RequestBody Employee employee) {
        repository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable String id) {
        return repository.findById(id);
    }

    @PutMapping
    public void update(@RequestBody Employee employee) {
        repository.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }
}
