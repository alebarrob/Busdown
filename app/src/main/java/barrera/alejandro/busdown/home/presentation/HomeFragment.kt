package barrera.alejandro.busdown.home.presentation

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import barrera.alejandro.busdown.databinding.FragmentHomeBinding
import com.google.android.gms.location.LocationServices

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sendEmailButton: Button
    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupViewBinding()
        launchLocationPermissionRequest()
        homeViewModel.loadEmails()

        return binding.root
    }

    private fun setupViewBinding() {
        sendEmailButton = binding.sendEmailButton
    }

    private fun launchLocationPermissionRequest() {
        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[ACCESS_FINE_LOCATION] == true -> {
                    sendEmailButton.isEnabled = true
                }
                permissions[ACCESS_COARSE_LOCATION] == true -> {
                    sendEmailButton.isEnabled = true
                } else -> {
                sendEmailButton.isEnabled = false
                Toast.makeText(context, "The app needs to access your location in order to send the email", Toast.LENGTH_LONG).show()
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickSendEmailsButton()
    }

    private fun onClickSendEmailsButton() {
        sendEmailButton.setOnClickListener {
            sendEmailIfLocationPermissionIsGranted()
        }
    }

    private fun sendEmailIfLocationPermissionIsGranted() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                val latitude = location?.latitude.toString()
                val longitude = location?.longitude.toString()
                val googleMapsLocationUrl = "http://maps.google.com/maps?q=$latitude,$longitude"

                sendEmail(googleMapsLocationUrl)
            }
        }
    }

    private fun sendEmail(googleMapsLocationUrl: String) {
        homeViewModel.emails.observe(viewLifecycleOwner) { emails ->
            context?.sendEmail(emails, googleMapsLocationUrl)
        }
    }

    private fun Context.sendEmail(
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
}