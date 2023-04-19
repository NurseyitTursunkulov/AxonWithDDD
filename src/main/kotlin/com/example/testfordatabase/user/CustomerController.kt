package com.example.testfordatabase.user

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.random.Random

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerRepository: CustomerRepository,
    private val queryGateway: QueryGateway,
    private val commandGateway: CommandGateway,
) {

    @GetMapping("/find/{foodCartId}")
    fun findFoodCart(@PathVariable("foodCartId") foodCartId: String): CompletableFuture<FoodCartView> =
        queryGateway.query(
            FindFoodCartQuery(UUID.fromString(foodCartId)),
            ResponseTypes.instanceOf(FoodCartView::class.java)
        )

    @GetMapping("/find")
    fun findFoodCartAll(): CompletableFuture<List<FoodCartView>> =
        queryGateway.query(
            "FindFoodCarts",Any(),
            ResponseTypes.multipleInstancesOf(FoodCartView::class.java)
        )

    @PostMapping("/create")
    fun createFoodCart(): CompletableFuture<UUID> = commandGateway.send(CreateFoodCartCommand(UUID.randomUUID()))

    @GetMapping("")
    fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long): Customer {
        return customerRepository.findById(id).get()
    }

    @PostMapping("")
    fun createCustomer(): Customer {
        queryGateway.query(
            FindFoodCartQuery(UUID.randomUUID()),
            ResponseTypes.instanceOf(FoodCartView::class.java)
        )
        return customerRepository.save(Customer(Random.nextLong(),"adf","fef","main"))
    }

    @PutMapping("/{id}")
    fun updateCustomerById(@PathVariable id: Long, @RequestBody customer: Customer): Customer {
        val existingCustomer = customerRepository.findById(id).get()
        return customerRepository.save(existingCustomer.copy(
            firstName = customer.firstName,
            lastName = customer.lastName,
            email = customer.email
        ))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomerById(@PathVariable id: Long) {
        customerRepository.deleteById(id)
    }
}