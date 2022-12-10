package com.example.bookstore

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.adapter.BookAdapter
import com.example.bookstore.data.DataSource
import com.example.bookstore.model.Book
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), BookAdapter.ClickListener {

    // Data source
    val bookList = DataSource().loadBook()

    // adapter
    val adapter = BookAdapter(this, bookList, this)

//    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rv_books)

        val searchBar = findViewById<Toolbar>(R.id.tb_tool_bar)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        this.setSupportActionBar(searchBar)
        this.supportActionBar!!.title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView : SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.filter.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)

    }

    override fun clickedItem(item: Book) {

        startActivity(Intent(this, DetailsBookActivity::class.java)
            .putExtra("bookImage", item.bookImage)
            .putExtra("bookTitle", item.title)
            .putExtra("bookPrice", item.price.toString())
            .putExtra("bookAuthor", item.author)
        )
    }
}