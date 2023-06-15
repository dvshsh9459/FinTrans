# FinTrans

#INTRODUCTION, #UNDERSTANDING
It financial transactions in-memory database project and returns information about those transactions.
This support returning 
• All transactions of a type 
• All transactions of a currency. 
• Also, transactions can be linked to each other (using a "parent_id") and the total amount involved

In this project we are storing data related to transaction, for this implementation it is financial transaction.
 
#TECH STACK
Java17, Springboot3, docker, H2 Database

#Use Cases
1. If we make some expense calculator for personal use, this use case can be used.
2. If we make some accounting application.
3. We want to keep transactions in e-commerce website.
4. We can also use this sceanario in any application where we are monitoring transactions.

#About Implementation
For this one it is simple controller, service and repository pattern.
Also default beahavior is in place for every edge cases.
Default Open API ui is implemented i.e., documentation for APIs.

#Future Code improvement
We can create our own Exception handling mechanism, we can specify the standard format(Json/text/any other) request/response APIs.
Later we can customized swagger implementation.
For code improvement some rules and unit test coverage can be added for success build.
