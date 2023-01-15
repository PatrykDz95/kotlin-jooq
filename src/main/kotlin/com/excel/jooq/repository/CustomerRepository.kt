package com.excel.jooq.repository

import com.excel.jooq.model.Customer
import com.excel.jooq.model.CustomerAddress

interface CustomerRepository {

    fun findOneById(id: Int): Customer?

    fun findAll(): MutableList<Customer>

    fun addAddress(customerAddress: CustomerAddress): CustomerAddress?

    fun insert( customer: Customer): Customer?

    fun deleteById(id: Int)
}