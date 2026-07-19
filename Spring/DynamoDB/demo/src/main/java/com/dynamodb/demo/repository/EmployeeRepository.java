package com.dynamodb.demo.repository;

import org.springframework.stereotype.Repository;

import com.dynamodb.demo.model.Employee;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final DynamoDbEnhancedClient enhancedClient;

    private DynamoDbTable<Employee> employeeTable;

    @PostConstruct
    public void init() {
        employeeTable = enhancedClient.table(
                "Employee",
                TableSchema.fromBean(Employee.class)
        );
    }

    // CREATE
    public void save(Employee employee) {
        employeeTable.putItem(employee);
    }

    // READ
    public Employee findById(String employeeId) {
        return employeeTable.getItem(r ->
                r.key(k -> k.partitionValue(employeeId)));
    }

    // UPDATE
    public void update(Employee employee) {
        employeeTable.updateItem(employee);
    }

    // DELETE
    public void delete(String employeeId) {
        employeeTable.deleteItem(r ->
                r.key(k -> k.partitionValue(employeeId)));
    }
}