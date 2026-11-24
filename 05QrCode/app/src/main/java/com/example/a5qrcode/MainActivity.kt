package com.example.a5qrcode

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etText = findViewById<EditText>(R.id.etText)
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        val ivQr = findViewById<ImageView>(R.id.ivQr)
        val tvType = findViewById<TextView>(R.id.tvType)
        val tvLength = findViewById<TextView>(R.id.tvLength)

        btnCreate.setOnClickListener {
            val text = etText.text.toString().trim()

            if(text.isEmpty()) {
                ivQr.setImageDrawable(null)
                tvType.text = "Сначала введи текст"
                tvLength.text = ""
            } else if (text.length > 300) {
                ivQr.setImageDrawable(null)
                tvType.text = "Слишком длинный текст"
                tvLength.text = "Не больше 300 символов, сейчас ${text.length}"
            } else {
                val bitmap = BarcodeEncoder().encodeBitmap(text, BarcodeFormat.QR_CODE, 800, 800)
                ivQr.setImageBitmap(bitmap)
                tvType.text="Готово!"
                tvLength.text = "${text.length} симв."
            }
        }
    }
}