package com.example.bookstore.data

import com.example.bookstore.R
import com.example.bookstore.model.Book

class DataSource {

    fun loadBook() : List<Book> {

        return mutableListOf(
            Book(R.drawable.book1, "بلا صوت", 29.0, "ليلى ربيع", false),
            Book(R.drawable.book2, "تعلم بالمشاريع", 20.0, "هيام علي", false),
            Book(R.drawable.book3, "كانت بجمل", 29.0, "رغد بنت عبدالله", false),
            Book(R.drawable.book4, "باب الخروج", 19.99, "عز الدين شكري فشير", false),
            Book(R.drawable.book5, "موت في حياة ما", 69.0, "جيهان رافع", false),
            Book(R.drawable.book6, "ولدت من العدم", 41.0, "وعد الشمري", false),
            Book(R.drawable.book7, "لم تأت بعد", 15.0, "تسنيم التلاوي", false),
            Book(R.drawable.book8, "بارقه", 40.25, "هاجري بنت عبدالله", false),
            Book(R.drawable.book9, "أمواج أكما", 39.0, "عمرو عبد الحميد", false),
            Book(R.drawable.book10, "ميلاد", 29.99, "حنان الغامدي", false),
            Book(R.drawable.book11, "أبواب ومفاتيح", 12.99, "سوزان دروزة", false),
            Book(R.drawable.book12, "النشر الذاتي", 90.0, "رغد بنت عبدالله", false),
            Book(R.drawable.book13, "خاتم سليمان", 19.99, "أونوريه دي بلزاك", false)
        )
    }
}