package com.excel.jooq.repository

import com.excel.jooq.model.Item

interface ItemRepository {

    fun findAllItems(): List<Item>

    fun insertItems(item: Item): List<Item>
}