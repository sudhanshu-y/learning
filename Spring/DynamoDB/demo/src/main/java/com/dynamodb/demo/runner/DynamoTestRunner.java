package com.dynamodb.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dynamodb.demo.service.TableService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

@Component
@RequiredArgsConstructor
public class DynamoTestRunner implements CommandLineRunner {

    private final DynamoDbClient dynamoDbClient;
    private final TableService tableService;

    @Override
    public void run(String... args) {

        
        ListTablesResponse response = dynamoDbClient.listTables();

        if (!response.tableNames().contains("Employee")) {
            tableService.createEmployeeTable();
        }

        System.out.println("Tables: " + response.tableNames());
        System.out.println(dynamoDbClient.listTables().tableNames());
    }
}