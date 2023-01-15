package com.excel.jooq.controller

import com.excel.jooq.model.Customer
import com.excel.jooq.model.CustomerAddress
import com.excel.jooq.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/findCustomer")
    fun findOne(@RequestBody id: Int): Customer? {
        return customerService.findOneById(id)
    }

    @GetMapping("/findCustomers")
    fun find(): List<Customer> {
        return customerService.findAll()
    }

    @PostMapping("/addCustomer")
    fun addCustomer(@RequestBody customer: Customer): Customer? {
        return customerService.insert(customer)
    }

    @PostMapping("/addAddress")
    fun addAddress(@RequestBody address: CustomerAddress): CustomerAddress? {
        return customerService.addAddress(address)
    }

    @DeleteMapping("/deleteCustomer")
    fun addCustomer(@RequestBody id: Int){
        return customerService.deleteById(id)
    }

}