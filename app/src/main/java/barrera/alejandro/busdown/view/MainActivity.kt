package barrera.alejandro.busdown.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import barrera.alejandro.busdown.R
import barrera.alejandro.busdown.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomNavigationView = binding.bottomNavigation
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        setContentView(binding.root)

        navigateAfterDelay(WelcomeFragmentDirections.actionWelcomeFragmentToBasicInfoFragment())
    }

    private fun navigateAfterDelay(direction: NavDirections) {
        Handler(Looper.getMainLooper()).postDelayed(
            { navController.navigate(direction) },
            2000
        )
    }

    fun navigateToHomeFragment() {
        navController.navigate(BasicInfoFragmentDirections.actionBasicInfoFragmentToHomeFragment())
    }

    fun bottomNavigationBarIsVisible(isVisible: Boolean) {
        binding.bottomNavigation.isVisible = isVisible
    }
}