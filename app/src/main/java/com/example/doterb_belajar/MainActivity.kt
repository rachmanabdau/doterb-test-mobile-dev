package com.example.doterb_belajar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.doterb_belajar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.mainHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        setupToolbar(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.main_host_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun setupToolbar(navController: NavController) {
        val appbarConfig = AppBarConfiguration(navController.graph)
        binding.mainToolbar.toolbar.setupWithNavController(navController, appbarConfig)
        setSupportActionBar(binding.mainToolbar.toolbar)

        // Hide toolbar for login fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val fragmentDestination = setOf(R.id.loginFragment)
            setSupportActionBar(binding.mainToolbar.toolbar)
            binding.mainToolbar.toolbar.isVisible =
                fragmentDestination.contains(destination.id).not()
        }
    }
}