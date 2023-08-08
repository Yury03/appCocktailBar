package com.example.appcocktailbar.ui.main_activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.appcocktailbar.R
import com.example.appcocktailbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        initView()
    }

    private fun initView() {

        with(binding) {
            floatingActionButton.setOnClickListener {
                navController.navigate(R.id.addCocktailFragment)
                binding.floatingActionButton.visibility = View.GONE
            }
        }
    }
}