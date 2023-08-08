package com.example.appcocktailbar.ui.cocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appcocktailbar.R
import com.example.appcocktailbar.databinding.FragmentCocktailsBinding
import com.example.appcocktailbar.domain.models.CocktailModel
import com.example.appcocktailbar.ui.cocktails.adapters.CocktailsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private val viewModel by viewModel<CocktailsViewModel>()
    private lateinit var binding: FragmentCocktailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initView()

    }

    private fun initView() {
        val showDetails = { it: CocktailModel ->
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }
        viewModel.cocktailsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) binding.cocktailsListRV.adapter =
                CocktailsListAdapter(it, showDetails)
            with(binding) {
                placeholderArrow.visibility = View.GONE
                placeholderHint.visibility = View.GONE
                placeholderPhoto.visibility = View.GONE
                cocktailsListRV.visibility = View.VISIBLE
            }
        }
    }


}