#!/bin/bash

# Audit Log Retrieval Script
# Usage: ./get-audit-log.sh <client_name> [date]

CLIENT_NAME=${1:-qa_manual1}
DATE=$2
BASE_URL="http://localhost:8080"

echo "==================================="
echo "   AUDIT LOG RETRIEVAL UTILITY    "
echo "==================================="
echo ""

if [ -z "$CLIENT_NAME" ]; then
    echo "Usage: $0 <client_name> [date]"
    echo "Example: $0 qa_manual1"
    echo "Example: $0 qa_manual1 2024-01-15"
    exit 1
fi

echo "Retrieving audit logs for client: $CLIENT_NAME"
echo ""

# Check if application is running
if ! curl -s "$BASE_URL/greet/" > /dev/null 2>&1; then
    echo "ERROR: Application is not running on $BASE_URL"
    echo "Please start the Spring Boot application first with: mvn spring-boot:run"
    exit 1
fi

# If date is provided, get logs for specific date
if [ ! -z "$DATE" ]; then
    echo "Fetching logs for date: $DATE"
    echo ""
    curl -s "$BASE_URL/audit/client/$CLIENT_NAME/date?date=$DATE" | python3 -m json.tool 2>/dev/null || echo "No logs found or invalid date format. Use YYYY-MM-DD format."
else
    # Get summary view
    echo "Fetching audit log summary..."
    echo ""
    curl -s "$BASE_URL/audit/client/$CLIENT_NAME/summary"
    echo ""
    echo ""
    echo "For detailed JSON output, use:"
    echo "curl -s $BASE_URL/audit/client/$CLIENT_NAME | python3 -m json.tool"
    echo ""
    echo "For logs on specific date, use:"
    echo "$0 $CLIENT_NAME YYYY-MM-DD"
fi

echo ""
echo "==================================="