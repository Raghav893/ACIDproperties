## ACID PROPERTIES 
## 1. Atomicity
This ensure transactions are threaded as single unit of work. If some failure happen during the transaction the entire transaction is rollback to its previous state.

## 2. Consistency

This property ensure that transaction brings that database from one state to another state. Its define rules for valid database transaction, if one or more rule violated, the whole transaction roll back to previous state.

## 3. Isolation

This ensure that each transaction is running on isolate enviornment. The other transaction cant view what happen inside another transaction.

## 4. Durability

This ensure that once transaction is committed, the all changes are permanent and changes will survive system failures as well.

Below mentioned that how to implement data base transaction can implement with spring boot. In spring there is @Tranactional annotation to implement database transaction inside method.
