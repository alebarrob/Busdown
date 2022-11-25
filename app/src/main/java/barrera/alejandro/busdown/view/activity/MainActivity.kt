package barrera.alejandro.busdown.view.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import barrera.alejandro.busdown.R
import barrera.alejandro.busdown.databinding.ActivityMainBinding
import barrera.alejandro.busdown.view.fragment.HomeFragmentDirections
import barrera.alejandro.busdown.view.fragment.SettingsFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationBar: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupBottomNavigationBar()
        onNavigationDestinationChanged()
    }

    private fun setupActionBar() {
        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.hide()
    }

    private fun setupBottomNavigationBar() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationBar = binding.bottomNavigation
        bottomNavigationBar.setupWithNavController(navController)
        bottomNavigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    if (bottomNavigationBar.selectedItemId != R.id.homeFragment) {
                        navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToHomeFragment())
                    }
                }
                R.id.settingsFragment -> {
                    if (bottomNavigationBar.selectedItemId != R.id.settingsFragment) {
                        navController.navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
                    }
                }
            }
            true
        }
    }

    private fun onNavigationDestinationChanged() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> bottomNavigationBar.isVisible = true
                R.id.settingsFragment -> {
                    bottomNavigationBar.isVisible = true
                    supportActionBar?.hide()
                }
                R.id.changeEmailFragment -> {
                    bottomNavigationBar.isVisible = false
                    supportActionBar?.show()
                }
                R.id.termsAndConditionsFragment -> {
                    bottomNavigationBar.isVisible = false
                    supportActionBar?.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}