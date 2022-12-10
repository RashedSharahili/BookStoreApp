package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailsBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)

        val intent = intent.extras

        val bookImageIV = findViewById<ImageView>(R.id.book_image)
        val bookTitleTV = findViewById<TextView>(R.id.book_title)
        val bookPriceTV = findViewById<TextView>(R.id.book_price)
        val bookAuthorTV = findViewById<TextView>(R.id.book_author)

        val bookImage = intent!!.getInt("bookImage")
        val bookTitle = intent.getString("bookTitle")
        val bookPrice = intent.getString("bookPrice")
        val bookAuthor = intent.getString("bookAuthor")

        bookImageIV.setImageResource(bookImage)
        bookTitleTV.text = bookTitle
        bookPriceTV.text = bookPrice
        bookAuthorTV.text = bookAuthor
    }
}