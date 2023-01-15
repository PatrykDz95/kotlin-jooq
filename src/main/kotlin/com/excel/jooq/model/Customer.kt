package com.excel.jooq.model

import java.math.BigDecimal

data class Customer(
    val username: String,
    val userPassword: String?,
    val email: String,
    val balance: BigDecimal,
    val customerStatus: CustomerStatus?
)