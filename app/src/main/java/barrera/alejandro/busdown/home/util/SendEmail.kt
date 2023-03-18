package barrera.alejandro.busdown.home.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Context.sendEmail(
    emails: List<String>,
    googleMapsLocationUrl: String?
) {
    try {
        val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
        val emailIntent = Intent(Intent.ACTION_SEND)
        val subject = "I'm at the bus stop and the BusUp bus didn't arrive on time."

        emailSelectorIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails.toTypedArray())
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Current location:\n\n$googleMapsLocationUrl")
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        emailIntent.selector = emailSelectorIntent
        startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "The selected application is not available", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show()
    }
}