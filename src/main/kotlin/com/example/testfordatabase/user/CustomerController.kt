package com.example.testfordatabase.user

import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.random.Random

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerRepository: CustomerRepository,
                         private val queryGateway: QueryGateway) {

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