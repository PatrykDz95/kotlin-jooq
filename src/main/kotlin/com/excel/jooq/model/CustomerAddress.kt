package com.excel.jooq.model


data class CustomerAddress(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val address: String,
    val zipcode: String,
    val phoneNumber: String
)