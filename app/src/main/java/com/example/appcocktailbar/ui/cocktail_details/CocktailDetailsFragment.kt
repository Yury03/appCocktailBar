package com.example.appcocktailbar.ui.cocktail_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appcocktailbar.R
import com.example.appcocktailbar.databinding.FragmentCocktailDetailsBinding


class CocktailDetailsFragment : Fragment(R.layout.fragment_cocktail_details) {
    private var _binding: FragmentCocktailDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}