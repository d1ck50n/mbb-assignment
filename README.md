
# Wiki

## Introduction
### The assignment details are as follows:
 
1. Prepare a batch job to consume the attached text file.
2. Prepare RESTFUL API to (1)Retrieve & (2) Update (description) the records
3. Must have authentication
4. Must have pagination
5. Can search by customer ID or account number(s) or description
6. For Update API, must able to handle concurrent update.
 
### Notes :-
1. Free to design the database structure.
2. Allowed to use many classes. (OOP is preferable)
3. Must follow RESTFUL API standard.
4. Code need to be shared.
5. Pls brief if you are using any Design pattern and why you choose it.
6. Documentation is required. (Class diagram and activity diagram)

## Prerequisite
1. JDK 11
2. Maven 3
3. Postman Client (optional)
4. Browser (optional)

## How to run the application
1. Clone the source code with `git clone https://github.com/d1ck50n/mbb-assignment.git`
2. Go into the project directory and run `mvn package`
3. Load the data into database by running command `java -jar target/maybank-assignment-1.0.jar`
4. Start up the application by running command `mvn spring-boot:run`
5. Check if the application start up correctly by visiting `http://localhost:8088/actuator/health` using browser, or curl command.
6. Import the [postman_collection.json](https://github.com/d1ck50n/mbb-assignment/blob/main/postman_collection.json) into your Postman API client and start your test.
7. There are 5 API test cases available which name appropriately, pay attention on the sample URL for parameters available.
8. If you do not have Postman install, then you can test using curl or other tools with API endpoint below. 
 
## Available End Point and Example
1. Get Transaction Based on Single Account Number (HTTP GET) 
> http://localhost:8088/api/transactions?accountNumbers=8872838299&page=1&size=10
2. Get Transaction Based on Multiple Account Number (HTTP GET) 
> http://localhost:8088/api/transactions?accountNumbers=8872838299,8872838283&page=1&size=10
3. Get Transaction Based on Customer ID (HTTP GET) 
> http://localhost:8088/api/transactions?customerId=222&page=1&size=10
4. Get Transaction Based on Description (HTTP GET) 
> http://localhost:8088/api/transactions?description=TRANSFER&page=1&size=10
5. Update Transaction's Description Based on ID (HTTP GET) 
> http://localhost:8088/api/transactions/2

## Credential For Authentication.
Username: `admin`
Password `admin`

## Diagram
1. [Activity Diagram](https://github.com/d1ck50n/mbb-assignment/tree/main/diagram/activity)
2. [Class Diagram](https://github.com/d1ck50n/mbb-assignment/tree/main/diagram/class) 
