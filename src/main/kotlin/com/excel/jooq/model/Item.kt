package com.excel.jooq.model

import java.time.LocalDateTime

data class Item(
    val id: Long,
    val cost: String,
    val itemName: String,
    val purchaseDate: LocalDateTime?,
    val reviews: List<Review>?
)