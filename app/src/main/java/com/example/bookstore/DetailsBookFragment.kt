package com.example.bookstore

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookstore.databinding.FragmentBookBinding
import com.example.bookstore.databinding.FragmentDetailsBookBinding

class DetailsBookFragment : Fragment() {

    private var _binding: FragmentDetailsBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookImage : String
    private lateinit var bookTitle: String
    private lateinit var bookPrice: String
    private lateinit var bookAuthor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            bookImage = it.getInt("bookImage").toString()
            bookTitle = it.getString("bookTitle").toString()
            bookPrice = it.getFloat("bookPrice").toString()
            bookAuthor = it.getString("bookAuthor").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailsBookBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookImage.setImageResource(bookImage.toInt())
        binding.bookTitle.text = bookTitle
        binding.bookPrice.text = bookPrice
        binding.bookAuthor.text = bookAuthor

        binding.imageButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND).apply {

                type = "text/*"

                val b = BitmapFactory.decodeResource(resources, bookImage.toInt())

                val path = MediaStore.Images.Media.insertImage(requireContext().contentResolver, b, bookTitle, null)

                val uri = Uri.parse(path)

                putExtra(Intent.EXTRA_TEXT, bookTitle)
                putExtra(Intent.EXTRA_STREAM, uri)
            }

            startActivity(intent)
        }
    }
}