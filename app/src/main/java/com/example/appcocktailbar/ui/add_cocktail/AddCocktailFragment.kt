package com.example.appcocktailbar.ui.add_cocktail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appcocktailbar.R
import com.example.appcocktailbar.databinding.FragmentAddCocktailBinding
import com.example.appcocktailbar.domain.models.CocktailModel
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddCocktailFragment : Fragment(R.layout.fragment_add_cocktail) {
    private val viewModel by viewModel<AddCocktailViewModel>()
    private lateinit var binding: FragmentAddCocktailBinding
    private var _uri: String? = null
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            _uri = uri.toString()
            with(binding.camera) {
                setImageURI(uri)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCocktailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.newIngredientName.observe(viewLifecycleOwner) {
            addNewChip(it)
        }
    }

    private fun initView() {
        with(binding) {
            addSaveButton.setOnClickListener {
                if (isValid()) {
                    viewModel.addCocktail(createModel())
                    findNavController().navigate(R.id.cocktailsFragment)
                }
            }
            addCancelButton.setOnClickListener {
                findNavController().navigate(R.id.cocktailsFragment)
            }
            addChip.setOnClickListener {
                showAlertDialog()
            }
            camera.setOnClickListener {
                pickImageLauncher.launch("image/*")
            }
        }
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
            addButton.setOnClickListener {
                val name = ingredientName.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.saveNewIngredient(name)
                    alertDialog.dismiss()
                } else {
                    findViewById<TextInputLayout>(R.id.addTitleFrame).boxStrokeColor =
                        Color.RED
                    ingredientName.requestFocus()
                }
            }
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }

    private fun createModel(): CocktailModel {
        with(binding) {
            val title = addTitle.editableText.toString()
            val description = addDescription.editableText.toString()
            val recipe = addRecipe.editableText.toString()
            val id = System.currentTimeMillis()
            return CocktailModel(id, title, description, getChipsText(), recipe, _uri)
        }
    }

    private fun getChipsText(): List<String> {

        val selectedChipTexts = mutableListOf<String>()

        for (i in 0 until binding.chipGroup.childCount - 1) {
            val chip = binding.chipGroup.getChildAt(i) as Chip
            val chipText = chip.text.toString()
            selectedChipTexts.add(chipText)
        }
        return selectedChipTexts

    }

    private fun addNewChip(text: String) {
        val newChip = Chip(binding.chipGroup.context)
        newChip.text = text
        newChip.isCloseIconVisible = true
        newChip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(newChip)
        }
        binding.chipGroup.addView(newChip, binding.chipGroup.childCount - 1)
    }

    private fun isValid(): Boolean {
        with(binding) {
            if (addTitle.text.toString().isEmpty()) {
                titleFrame.boxStrokeColor = Color.RED
                addTitle.requestFocus()
                return false
            }
        }
        return true
    }

}