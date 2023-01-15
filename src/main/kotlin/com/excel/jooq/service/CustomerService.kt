package com.excel.jooq.service

import com.excel.jooq.model.Customer
import com.excel.jooq.model.CustomerAddress
import com.excel.jooq.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    fun findOneById(id: Int) = customerRepository.findOneById(id) ?: throw IllegalArgumentException("Customer with id: $id could not be found")

    fun findAll() = customerRepository.findAll()

    fun insert(customer: Customer) = customerRepository.insert(customer)

    fun addAddress(address: CustomerAddress) = customerRepository.addAddress(address)

    fun deleteById(id: Int) = customerRepository.deleteById(id)
}