package com.excel.jooq.model

import java.util.UUID

data class Order(
    val id: UUID,
    val customer: Customer,
    val item: Item
)