package barrera.alejandro.busdown.welcome.util

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun NavDirections.navigateAfterDelay(view: View) {
    Handler(Looper.getMainLooper()).postDelayed({
        view.findNavController().navigate(this)
    }, 3000)
}