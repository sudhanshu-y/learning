package com.dynamodb.demo.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Service
@RequiredArgsConstructor
public class TableService {

    private final DynamoDbClient dynamoDbClient;

    public void createEmployeeTable() {

        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Employee")
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName("employeeId")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName("employeeId")
                                .keyType(KeyType.HASH)
                                .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();

        dynamoDbClient.createTable(request);

        System.out.println("Employee table created.");
    }
}