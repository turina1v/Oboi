package com.turina1v.oboi.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.turina1v.oboi.R
import com.turina1v.oboi.domain.Category

class CategoryAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    categories: List<Category>
) : ArrayAdapter<Category>(context, layoutResource, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return processView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return processView(position, convertView, parent)
    }

    private fun processView(position: Int, convertView: View?, parent: ViewGroup): View {
        val category = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        category?.let {
            view.findViewById<TextView>(R.id.spinner_item_text).text = category.name
        }
        return view
    }
}