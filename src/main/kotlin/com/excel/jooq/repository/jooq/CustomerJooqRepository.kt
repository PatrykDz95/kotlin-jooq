package com.excel.jooq.repository.jooq

import com.excel.jooq.database.Tables.CUSTOMER
import com.excel.jooq.database.Tables.CUSTOMER_ADDRESS
import com.excel.jooq.exception.CustomerRepositoryException
import com.excel.jooq.model.Customer
import com.excel.jooq.model.CustomerAddress
import com.excel.jooq.repository.CustomerRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import mu.KotlinLogging
import org.jooq.exception.DataAccessException
import org.springframework.dao.DuplicateKeyException

@Repository
class CustomerJooqRepository(private var dsl: DSLContext) : CustomerRepository {

    private final val logger = KotlinLogging.logger {}

    override fun findOneById(id: Int): Customer? = dsl.select()
        .from(CUSTOMER)
        .where(CUSTOMER.ID.eq(id))
        .fetchOne()
        ?.into(Customer::class.java)

    override fun findAll(): MutableList<Customer> = dsl.select()
        .from(CUSTOMER)
        .fetch()
        .into(Customer::class.java)

    override fun addAddress(customerAddress: CustomerAddress): CustomerAddress? {
        return dsl.insertInto(CUSTOMER_ADDRESS)
            .columns(CUSTOMER_ADDRESS.FIRST_NAME, CUSTOMER_ADDRESS.LAST_NAME, CUSTOMER_ADDRESS.ADDRESS, CUSTOMER_ADDRESS.ZIPCODE, CUSTOMER_ADDRESS.PHONE_NUMBER, CUSTOMER_ADDRESS.CUSTOMER_ID)
            .values(customerAddress.firstName, customerAddress.lastName, customerAddress.address, customerAddress.zipcode, customerAddress.phoneNumber, 1)
            .returningResult(CUSTOMER_ADDRESS.ID, CUSTOMER_ADDRESS.FIRST_NAME, CUSTOMER_ADDRESS.LAST_NAME, CUSTOMER_ADDRESS.ADDRESS, CUSTOMER_ADDRESS.ZIPCODE, CUSTOMER_ADDRESS.PHONE_NUMBER)
            .fetchOne()
            ?.into(CustomerAddress::class.java)
    }

    override fun insert(customer: Customer): Customer? {
        return try {

            dsl.insertInto(CUSTOMER)
                .columns(CUSTOMER.USERNAME, CUSTOMER.USERPASSWORD, CUSTOMER.EMAIL, CUSTOMER.BALANCE, CUSTOMER.CUSTOMERSTATUS)
                .values(customer.username, customer.userPassword, customer.email, customer.balance, customer.customerStatus!!.name)
                .returningResult(CUSTOMER.USERNAME, CUSTOMER.USERPASSWORD, CUSTOMER.EMAIL, CUSTOMER.BALANCE, CUSTOMER.CUSTOMERSTATUS)
                .fetchOne()
                ?.into(Customer::class.java)

        } catch (exception: DataAccessException) {
            logger.error { "Error while creating new Customer: $customer" }
            throw CustomerRepositoryException("Error while creating Customer: $customer, $exception")
        } catch (exception: DuplicateKeyException) {
            logger.error { "A Customer with that username already exists: ${customer.username}, $exception" }
            throw CustomerRepositoryException("A Customer with that username already exists: ${customer.username}")
        }
    }

    override fun deleteById(id: Int) {
        dsl.deleteFrom(CUSTOMER).where(CUSTOMER.ID.eq(id)).execute()
    }

}