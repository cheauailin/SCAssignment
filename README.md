#SCAssignment - Backend 

This is a based64String comparison API for comparing 2 different objects which are retrieved from the request body.

Run the application locally with a list of Attachment object to get the differences.

Sample attachment request body:
{
    "attachmentList": [
        {
            "base64String": "aGVBBsbG8geW=9",
            "fileName": "abc.txt"
        },
        {
            "base64String": "aGVAAsbG8geW91",
            "fileName": "def.txt"
        }
    ]
}

This is a sample response body:
[
    {
    "operation": "EQUAL",
    "text": "aGV"
    },
    {
    "operation": "DELETE",
    "text": "BB"
    },
    {
    "operation": "INSERT",
    "text": "AA"
    },
    {
    "operation": "EQUAL",
    "text": "sbG8geW"
    },
    {
    "operation": "DELETE",
    "text": "="
    },
    {
    "operation": "EQUAL",
    "text": "9"
    },
    {
    "operation": "INSERT",
    "text": "1"
    }
]