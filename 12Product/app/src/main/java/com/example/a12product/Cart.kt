package com.example.a12product

class Cart {
    private val items = mutableListOf<Product>()

    fun add(product: Product) {
        items.add(product)
    }

    fun count(): Int {
        return items.size
    }

    fun total(): Int {
        var sum = 0
        for (item in items) {
            sum += item.price
        }
        return sum
    }
    fun clear() {
        items.clear()
    }
}