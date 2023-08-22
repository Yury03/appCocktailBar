package com.example.appcocktailbar.ui.add_cocktail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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
            Glide.with(requireContext())
                .load(uri)
                .into(binding.camera)
            binding.camera.scaleType = ImageView.ScaleType.CENTER_CROP
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
        /** если была получена модель, значит фрагмент запущен в режиме редактирования **/
        val editModel = getCocktailModel()
        editModel?.let {
            fillInputFields(it)
        }
        initView(editModel?.id)
        viewModel.newIngredientName.observe(viewLifecycleOwner) {
            addNewChip(it)
        }
    }

    /**Метод заполняет все поля ввода, если фрагмент открят в режиме редактирования*/
    private fun fillInputFields(model: CocktailModel) {
        val editableFactory = Editable.Factory.getInstance()
        val descriptionEditable = editableFactory.newEditable(model.description)
        val recipeEditable = editableFactory.newEditable(model.recipe)
        val titleEditable = editableFactory.newEditable(model.name)
        with(binding) {
            addTitle.text = titleEditable
            addDescription.text = descriptionEditable.takeIf { it.isNotEmpty() }
            addRecipe.text = recipeEditable.takeIf { it.isNotEmpty() }
            model.photoPath?.let {
                Glide.with(requireContext())
                    .load(it)
                    .into(camera)
                camera.scaleType = ImageView.ScaleType.CENTER_CROP
            }
            model.ingredients.forEach { ingredient -> addNewChip(ingredient) }
        }
    }

    private fun initView(editModelID: Long?) {
        with(binding) {
            addSaveButton.setOnClickListener {
                if (isValid()) {
                    val finishedModel = createModel(editModelID)
                    editModelID?.let {
                        viewModel.editCocktail(finishedModel)
                        closeFragment(finishedModel)
                    } ?: run {
                        viewModel.addCocktail(finishedModel)
                        closeFragment()
                    }
                }
            }
            addCancelButton.setOnClickListener {
                editModelID?.let { closeFragment(createModel(editModelID)) }
                    ?: run { closeFragment() }
            }
            addChip.setOnClickListener {
                showAlertDialog()
            }
            camera.setOnClickListener {
                pickImageLauncher.launch("image/*")
            }
        }
    }

    /** в случае редактирования происходит закрытие фрагмента с передачей редактируемой модели на
     * фрагмент CocktailDetailsFragment*/
    private fun closeFragment(model: CocktailModel? = null) {
        model?.let {
            val bundle = Bundle().apply {
                putParcelable("cocktailModel", model)
            }
            findNavController().navigate(R.id.cocktailDetailsFragment, bundle)
        } ?: findNavController().navigate(R.id.cocktailsFragment)
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

    private fun createModel(editID: Long? = null): CocktailModel {
        with(binding) {
            val title = addTitle.editableText.toString()
            val description = addDescription.editableText.toString()
            val recipe = addRecipe.editableText.toString()
            val id = editID ?: System.currentTimeMillis()
            return CocktailModel(id, title, description, getChipsText(), recipe, _uri)
        }
    }

    /** Метод обходит все chips и возвращает List<String>, который является списком ингредиентов*/
    private fun getChipsText(): List<String> {
        val selectedChipTexts = mutableListOf<String>()
        for (i in 0 until binding.chipGroup.childCount - 1) {
            val chip = binding.chipGroup.getChildAt(i) as Chip
            val chipText = chip.text.toString()
            selectedChipTexts.add(chipText)
        }
        return selectedChipTexts

    }

    /**Добавление нового ингредиента*/
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

    /**Получение модели из аргументов фрагмента*/
    private fun getCocktailModel(): CocktailModel? =//TODO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arguments?.getParcelable("cocktailModel", CocktailModel::class.java)
        else arguments?.getParcelable("cocktailModel")
}