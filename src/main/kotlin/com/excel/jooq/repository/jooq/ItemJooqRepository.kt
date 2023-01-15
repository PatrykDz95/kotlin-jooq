package com.excel.jooq.repository.jooq

import com.excel.jooq.database.Tables.ITEM
import com.excel.jooq.model.Item
import com.excel.jooq.repository.ItemRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ItemJooqRepository(private var dsl: DSLContext): ItemRepository {

    override fun findAllItems(): List<Item> = dsl.select()
        .from(ITEM)
        .fetch()
        .into(Item::class.java)

    override fun insertItems(item: Item): List<Item> {
        return dsl.insertInto(ITEM)
            .columns(ITEM.ITEM_NAME, ITEM.COST, ITEM.PURCHASE_DATE)
            .values(item.itemName, item.cost, LocalDateTime.now())
            .returningResult(ITEM.ID, ITEM.ITEM_NAME, ITEM.COST, ITEM.PURCHASE_DATE)
            .fetch()
            .into(Item::class.java)
    }
}