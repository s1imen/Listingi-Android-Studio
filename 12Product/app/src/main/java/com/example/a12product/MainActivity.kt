package com.example.a12product

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a12product.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val products = listOf(
        Product("Смартфон", 24990, 4.7, true, "📱"),
        Product("Наушники", 5490, 4.5, true, "🎧"),
        Product("Умные часы", 12990, 4.2, false, "⌚"),
        Product("Ноутбук", 69990, 4.8, true,"💻")
    )

    private var currentIndex = 0
    private val cart = Cart()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showProduct(products[currentIndex])
        showCart()

        binding.btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex == products.size) currentIndex = 0
            showProduct(products[currentIndex])
        }

        binding.btnAdd.setOnClickListener {
            val product = products[currentIndex]
            if (product.inStock) {
                cart.add(product)
                showCart()
            } else {
                Toast.makeText(this, "${product.name}: нет в наличии", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            cart.clear()
            showCart()
        }
    }

    private fun showProduct(product: Product) {
        binding.tvEmoji.text = product.emoji
        binding.tvName.text = product.name
        binding.tvPrice.text = "${product.price} ₽"
        binding.tvRating.text = "★ ${product.rating}"
        if (product.inStock) {
            binding.tvStock.text = "В наличии"
            binding.tvStock.setTextColor(getColor(R.color.in_stock))
        } else {
            binding.tvStock.text = "Нет в наличии"
            binding.tvStock.setTextColor(getColor(R.color.out_of_stock))
        }
    }

    private fun showCart() {
        binding.tvCart.text = "В корзине: ${cart.count()} шт. на ${cart.total()}₽"
    }
}