package barrera.alejandro.busdown.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import barrera.alejandro.busdown.R
import barrera.alejandro.busdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToBasicInfoFragmentAfterTwoSeconds()
    }

    private fun navigateToBasicInfoFragmentAfterTwoSeconds() {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToBasicInfoFragment())
        }, 2000)
    }
}