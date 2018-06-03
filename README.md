# smp
Small Message Processing

# INPUT FORMAT

We have three different kinds of messages:

1) PRODUCT_TYPE,PRICE 
2) PRODUCT_TYPE,PRICE,QUANTITY with QUANTITY > 1
3) For this type, since the text of the test refers to a generic sale (i.e. it could be both of types 1) and 2))  
   we have to sub-cases:
   3.1) PRODUCT_TYPE,PRICE,OPERATION,OPERATION_VALUE
   3.2) PRODUCT_TYPE,PRICE,QUANTITY,OPERATION,OPERATION_VALUE
   
PRODUCT_TYPE = string (alphabetic)
PRICE = double
QUANTITY = integer
OPERATION = string (ADD,SUBTRACT,MULTIPLY)
OPERATION_VALUE = double

# CONSIDERATIONS

To let the sale entity be more general I decided to include in it the `quantity` field.  
This way, the message type 2 will refer to a sale entity with `quantity` greater than 1.

About the `SaleDaoInMemory` implementation, since it comes to work in a single-threaded environment,
I did not payed attention in synchronization issues nor in sharing the same `memory` list  
behind the in memory feature, since there is no need to have more than one SaleDao` instance to the application.  

Messages, one transformed from text to objects (something not yet deal with in this moment)  
will be represented by the same `MessageDto` instances.
The above class have two `saleEntityDto` and `adjustmentDto` fields which are instances of  
`SaleEntity`and `AdjustmentDto` classes.  
Depending on some of the values encapsulated by the just mentioned fields,  
whe may have one of the three messages reported by the text of this test.  
Precisely:
1)  We have message of type 1 in case:  
    `saleEntityDto.quantity` = 1 && `adjustmentDto` = null
2)  We have message of type 1 in case:  
    `saleEntityDto.quantity` > 1 && `adjustmentDto` = null
3)  We have message of type 1 in case:  
    `saleEntityDto.quantity` > 1 && `adjustmentDto` = null
