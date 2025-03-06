package com.example.onefin.presentation.activities

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.onefin.R
import com.example.onefin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.retryButton.setOnClickListener {init()}
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom - systemBars.bottom
            )
            insets
        }
    }

    private fun isInternetAvailable(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->
                true
            else -> false
        }
    }

    private fun init(){
        if (isInternetAvailable(this)){
            binding.bottomNav.visibility = View.VISIBLE
            binding.fragmentContainer.visibility = View.VISIBLE
            binding.retryButton.visibility = View.GONE
            binding.noInternetText.visibility = View.GONE
            initClickListeners()
        }else{
            binding.retryButton.visibility=View.VISIBLE
            binding.noInternetText.visibility=View.VISIBLE
            binding.bottomNav.visibility = View.GONE
            binding.fragmentContainer.visibility = View.GONE
        }
    }


    private fun initClickListeners() {
        val bottomNavigationBar = binding.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationBar.setupWithNavController(navController)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}