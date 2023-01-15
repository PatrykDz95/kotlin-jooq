package com.excel.jooq.model

import java.time.LocalDateTime

data class Review(
    val customer: Customer,
    val text: String,
    val date: LocalDateTime
)
