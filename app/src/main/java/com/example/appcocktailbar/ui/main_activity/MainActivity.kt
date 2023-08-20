package com.example.appcocktailbar.ui.main_activity

import android.Manifest
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
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
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.cocktailsFragment) {
                binding.floatingActionButton.visibility = View.VISIBLE
            }else{
                binding.floatingActionButton.visibility = View.GONE
            }
        }




    }

    private fun initView() {

        with(binding) {
            floatingActionButton.setOnClickListener {
                navController.navigate(R.id.addCocktailFragment)

            }
        }
    }
}