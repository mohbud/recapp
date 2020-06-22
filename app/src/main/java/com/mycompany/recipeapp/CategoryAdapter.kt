package com.mycompany.recipeapp

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CategoryAdapter (val context: Context, val catList: List<Categories>) : BaseAdapter() {
    override fun getItem(position: Int): Any {
        return catList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return catList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.category_card, null)
        val icons: ImageView = view.findViewById(R.id.category_image)
        val names: TextView = view.findViewById(R.id.cat_name)
        val listItem:Categories = catList.get(position)

        Picasso.get().load(listItem.strCategoryThumb).into(icons)
        names.text = listItem.strCategory
        icons.setOnClickListener { view ->
            val searchIntent = Intent(context, SearchActivity::class.java)
            searchIntent.putExtra("catTerm", names.text.toString())
            context.startActivity(searchIntent)
        }
        return view
    }
}