package com.example.bookstore

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.adapter.BookAdapter
import com.example.bookstore.data.DataSource
import com.example.bookstore.databinding.FragmentBookBinding
import com.example.bookstore.model.Book

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    // Data source
    val bookList = DataSource().loadBook()

    // adapter
    private lateinit var adapter : BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBookBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerView
        val recyclerView = binding.rvBooks

//        val searchBar = binding.tbToolBar

        adapter = BookAdapter(requireContext(), bookList)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
//        this.setSupportActionBar(searchBar)
//        this.supportActionBar!!.title = ""
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_search_24)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)

        val menuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView : SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        setIcon(menuItem)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.filter.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}