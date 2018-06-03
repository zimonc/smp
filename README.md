# smp
Small Message Processing

# CONSIDERATIONS

To let the sale entity be more general I decided to include in it the quantity` field.  
This way, the message type 2 will refer to a sale entity with `quantity` greater than 1.

About the `SaleDaoInMemory` implementation, since it comes to work in a single-threaded environment,
I did not payed attention in synchronization issues nor in sharing the same `memory` list  
behind the in memory feature, since there is no need to have more than one SaleDao` instance to the application.  


