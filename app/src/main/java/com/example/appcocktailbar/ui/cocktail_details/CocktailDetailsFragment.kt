package com.example.appcocktailbar.ui.cocktail_details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appcocktailbar.R
import com.example.appcocktailbar.databinding.FragmentCocktailDetailsBinding
import com.example.appcocktailbar.domain.models.CocktailModel
import com.example.appcocktailbar.ui.cocktail_details.adapters.IngredientListAdapter


class CocktailDetailsFragment : Fragment(R.layout.fragment_cocktail_details) {

    private lateinit var binding: FragmentCocktailDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = getCocktailModel()
        initView(model)
    }

    private fun initView(model: CocktailModel) {
        val recipeExist = model.recipe.isNotEmpty()
        val adapter = IngredientListAdapter(model.ingredients, recipeExist)
        with(binding) {
            detailsTitle.text = model.name
            if (model.description.isNotEmpty())
                detailsDescription.text = model.description
            else
                detailsDescription.visibility = View.GONE
            detailsRecipe.text =
                if (recipeExist) model.recipe
                else getString(R.string.placeholder_details_recipe)
            detailsRecipeText.visibility = if (recipeExist) View.VISIBLE else View.GONE
            detailsIngredientList.adapter = adapter
        }
    }

    private fun getCocktailModel(): CocktailModel =//TODO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arguments?.getParcelable("cocktailModel", CocktailModel::class.java)!!
        else arguments?.getParcelable("cocktailModel")!!

}