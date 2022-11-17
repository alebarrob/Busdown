package barrera.alejandro.busdown.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.Fade
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import barrera.alejandro.busdown.R
import barrera.alejandro.busdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToBasicInfoFragment())
        }, 3000)
    }
}