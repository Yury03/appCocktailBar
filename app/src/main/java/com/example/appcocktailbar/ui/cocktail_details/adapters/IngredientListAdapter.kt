package com.example.appcocktailbar.ui.cocktail_details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocktailbar.R

class IngredientListAdapter(
    private val ingredientsList: List<String>,
    private val recipeExist: Boolean,
) :
    RecyclerView.Adapter<IngredientListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredient_item_name_TV)
        val separator: ImageView = itemView.findViewById(R.id.ingredient_item_separator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = ingredientsList[position]
        holder.ingredientName.text = dataItem
        if (position + 1 == ingredientsList.size && recipeExist)
            holder.separator.visibility = View.GONE //убирается разделитель последнего элемента
    }

    override fun getItemCount() = ingredientsList.size
}