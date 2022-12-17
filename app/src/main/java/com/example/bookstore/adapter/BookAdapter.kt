package com.example.bookstore.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.BookFragmentDirections
import com.example.bookstore.MainActivity
import com.example.bookstore.R
import com.example.bookstore.data.DataSource
import com.example.bookstore.model.Book
import java.util.*

class BookAdapter(private val context: Context, private var dataSet : List<Book>) : RecyclerView.Adapter<BookAdapter.ViewHolder>(), Filterable {

//    val bookListFiltered : List<Book> = DataSource().loadBook()

//    lateinit var clickListener: ClickListener

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val bookImg = view.findViewById<ImageView>(R.id.image_book)
        val bookTitle = view.findViewById<TextView>(R.id.title_book)
        val bookPrice = view.findViewById<TextView>(R.id.price_book)
        val bookAuthor = view.findViewById<TextView>(R.id.author_book)
        val bookLike = view.findViewById<ImageButton>(R.id.is_like_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataSet[position]
        holder.bookImg.setImageResource(item.bookImage)
        holder.bookTitle.text = item.title
        holder.bookPrice.text = item.price.toString()
        holder.bookAuthor.text = item.author

        holder.bookLike.setOnClickListener {

//            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()

            if(!item.isLiked) {

                item.isLiked = true
                holder.bookLike.setImageResource(R.drawable.ic_baseline_favorite_24)

            } else {

                item.isLiked = false
                holder.bookLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

        }

//        holder.bookLike.setImageResource(R.drawable.heart_icon)
        holder.itemView.setOnClickListener {

//            clickListener.clickedItem(item)

            val action = BookFragmentDirections.actionBookFragmentToDetailsBookFragment(
            bookImage = item.bookImage,
            bookTitle = item.title,
            bookPrice = item.price.toFloat(),
            bookAuthor = item.author
            )
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = dataSet.size

    override fun getFilter(): Filter {

        var filter = object : Filter() {

            override fun performFiltering(p0: CharSequence?): FilterResults {

                val filterResults = FilterResults()

                if(p0 == null || p0.isEmpty()) {

                    filterResults.values = DataSource().loadBook()
                    filterResults.count = DataSource().loadBook().size

                } else {

                    val searchChar = p0.toString().lowercase(Locale.ROOT)

                    val filteredResults = mutableListOf<Book>()

                    for(filtered in DataSource().loadBook()) {

                        if(filtered.title.lowercase(Locale.ROOT).contains(searchChar)) {

                            filteredResults.add(filtered)
                        }
                    }

                    filterResults.values = filteredResults
                    filterResults.count = filteredResults.size
                }

                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

                dataSet = p1!!.values as List<Book>
                notifyDataSetChanged()
            }

        }

        return filter
    }

    /*
    override fun getItemCount(): Int {

    }
     */

    interface ClickListener {

        fun clickedItem(item: Book)
    }
}