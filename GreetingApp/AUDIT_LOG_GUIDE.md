# Audit Log System - User Guide

## Overview
This guide explains how to retrieve audit logs for clients in the Greeting App system. The audit logging system tracks all operations performed by clients with timestamps, operation details, and IP addresses.

## Quick Start

### 1. Start the Application
```bash
mvn spring-boot:run
```

### 2. Get Audit Logs for qa_manual1
```bash
./get-audit-log.sh qa_manual1
```

### 3. Get Audit Logs for a Specific Date
```bash
./get-audit-log.sh qa_manual1 2024-01-15
```

## Available API Endpoints

### Get All Audit Logs for a Client
```
GET /audit/client/{clientName}
```
Example: `http://localhost:8080/audit/client/qa_manual1`

### Get Audit Log Summary for a Client
```
GET /audit/client/{clientName}/summary
```
Example: `http://localhost:8080/audit/client/qa_manual1/summary`

### Get Audit Logs for a Specific Date
```
GET /audit/client/{clientName}/date?date=YYYY-MM-DD
```
Example: `http://localhost:8080/audit/client/qa_manual1/date?date=2024-01-15`

### Get Audit Logs for a Date Range
```
GET /audit/client/{clientName}/daterange?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
```
Example: `http://localhost:8080/audit/client/qa_manual1/daterange?startDate=2024-01-01&endDate=2024-01-31`

## How Operations are Logged

### Automatic Logging
Operations on the greeting endpoints are automatically logged when you include the `clientName` parameter:

- **Create Greeting**: `POST /greet/customMessage?clientName=qa_manual1`
- **Update Greeting**: `PUT /greet/edit_message/{id}?clientName=qa_manual1`
- **Delete Greeting**: `DELETE /greet/delete_greet/{id}?clientName=qa_manual1`

### Manual Logging
You can also manually log operations:
```
POST /audit/log?clientName=qa_manual1&operation=LOGIN&details=User login successful
```

## Sample Data

To create sample audit data for testing:
```
POST http://localhost:8080/data/seed-audit-logs
```

This will create several sample audit log entries for `qa_manual1` and other test clients.

## Example Usage

### Using cURL
```bash
# Get summary for qa_manual1
curl "http://localhost:8080/audit/client/qa_manual1/summary"

# Get detailed JSON logs
curl "http://localhost:8080/audit/client/qa_manual1" | python3 -m json.tool

# Get logs for today
curl "http://localhost:8080/audit/client/qa_manual1/date?date=$(date +%Y-%m-%d)"
```

### Using the Shell Script
```bash
# Basic usage
./get-audit-log.sh qa_manual1

# With specific date
./get-audit-log.sh qa_manual1 2024-01-15

# For different client
./get-audit-log.sh qa_auto2
```

## Audit Log Fields

Each audit log entry contains:
- **ID**: Unique identifier
- **Client Name**: Name of the client (e.g., qa_manual1)
- **Operation**: Type of operation (LOGIN, CREATE_GREETING, UPDATE_GREETING, etc.)
- **Details**: Detailed description of the operation
- **IP Address**: Client's IP address
- **Timestamp**: When the operation occurred

## Common Operations Tracked

- `LOGIN` - User authentication
- `LOGOUT` - User session end
- `CREATE_GREETING` - New greeting created
- `UPDATE_GREETING` - Existing greeting modified
- `DELETE_GREETING` - Greeting removed
- `VIEW_GREETING` - Greeting accessed
- `EXPORT_DATA` - Data export operations

## Troubleshooting

### Application Not Running
If you see "Application is not running", make sure to start it first:
```bash
mvn spring-boot:run
```

### No Logs Found
If no logs are found for qa_manual1, you can:
1. Create sample data: `curl -X POST http://localhost:8080/data/seed-audit-logs`
2. Perform some operations with the clientName parameter
3. Check if the client name is spelled correctly

### Database Issues
Make sure MySQL is running and the database `greeting_app` exists with proper credentials as configured in `application.properties`.