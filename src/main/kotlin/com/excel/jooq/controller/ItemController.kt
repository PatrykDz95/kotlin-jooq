package com.excel.jooq.controller

import com.excel.jooq.model.Item
import com.excel.jooq.repository.jooq.ItemJooqRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController {

    @Autowired
    lateinit var itemJooqRepository: ItemJooqRepository

    @GetMapping("/allItems")
    fun find(): List<Item> {
        return itemJooqRepository.findAllItems()
    }

    @PostMapping("/saveItem")
    fun save(@RequestBody item: Item): List<Item> {
        return itemJooqRepository.insertItems(item)
    }
}