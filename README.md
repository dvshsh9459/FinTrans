# FinTrans
The service should support returning • All transactions of a type • All transactions of a currency. • Also, transactions can be linked to each other (using a "parent_id") and the total amount involved
API Spec:
PUT /bookingservice/transaction/$transaction_id
Body: {“amount”: double, “currency”: string, “type”: string, “parent_id”: integer}
transaction_id: an integer that identifies the new transaction
amount: a double for the amount of the transaction
currency: a string for the currency of the transaction
type: a string for defining what the transaction is for
parent_id: an optional integer which might link to a related parent transaction
GET /bookingservice/transaction/$transaction_id
Returns the specified transaction as JSON.
GET /bookingservice/types/$type
Returns a JSON list of all transaction ids of the given type.
GET /bookingservice/currencies
Returns a JSON list with all used currencies in the existing transactions.
GET /bookingservice/sum/$transaction_id
Returns: {“sum”: double, “currency”: string}
Returns the sum of all linked transactions with the respective currency.
(Note: all linked transactions must have the same currency)
