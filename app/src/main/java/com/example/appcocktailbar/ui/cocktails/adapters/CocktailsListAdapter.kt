package com.example.appcocktailbar.ui.cocktails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocktailbar.R
import com.example.appcocktailbar.domain.models.CocktailModel

class CocktailsListAdapter(
    private val itemList: List<CocktailModel>,
    private val showDetails: (cocktail: CocktailModel) -> Unit,
) :
    RecyclerView.Adapter<CocktailsListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cocktailPhoto: ImageView = itemView.findViewById(R.id.cocktail_photo)
        val cocktailTitle: TextView = itemView.findViewById(R.id.cocktail_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cocktail_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = itemList[position]
        with(holder) {
            cocktailTitle.text = dataItem.name
            cocktailPhoto.setOnClickListener {
                showDetails(dataItem)
            }
            val path: String? = dataItem.photoPath
            path?.let { cocktailPhoto.setImageURI(path.toUri()) }
        }
    }

    override fun getItemCount() = itemList.size
}