package com.example.appcocktailbar.ui.add_cocktail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.appcocktailbar.R
import com.example.appcocktailbar.databinding.FragmentAddCocktailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddCocktailFragment() : Fragment(R.layout.fragment_add_cocktail) {
    private val viewModel by viewModel<AddCocktailViewModel>()
    private var _binding: FragmentAddCocktailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddCocktailBinding.bind(view)
//        binding.addSaveButton.setOnClickListener {
//            if(isValid()){
////                createModel()
////            viewModel.saveCocktail()
//            }
//        }
        binding.addChip.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun isValid(): Boolean {
        if (binding.addTitle.text.toString().isEmpty()) {
            binding.titleFrame.boxStrokeColor = Color.RED
            return false
        }
        return true
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        val customLayout: View = layoutInflater.inflate(R.layout.add_ingridient_alert_dialog, null)
        with(alertDialogBuilder) {
            setView(customLayout)
            background = ColorDrawable(Color.TRANSPARENT)
        }
        val alertDialog = alertDialogBuilder.create()
        with(customLayout) {
            val ingredientName = findViewById<TextInputEditText>(R.id.ingredient_name)
            val addButton = findViewById<AppCompatButton>(R.id.addIngredient)
            val cancelButton = findViewById<AppCompatButton>(R.id.cancelAdd)
            //слушатель кнопки добавления нового ингредиента
            //значение отправляется во вьюМодел, а потом срабатывает слушатель во фрагменте
            //и элемент добавляется в chips group
            addButton.setOnClickListener {
                val name = ingredientName.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.saveNewIngredient(name)
                }
            }
            //слушатель кнопки отмены
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }

//    private fun createModel(): CocktailModel {
//        with(binding) {
//            val title = addTitle.editableText.toString()
//            val description = addDescription.editableText.toString()
//            val recipe = addRecipe.editableText.toString()
//        }
//        val id = LocalDateTime.now()
//
//        return CocktailModel(id, title, description, null, recipe, null)
//
//    }


}