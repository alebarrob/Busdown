package barrera.alejandro.busdown.view.fragment

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import barrera.alejandro.busdown.databinding.FragmentHomeBinding
import barrera.alejandro.busdown.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val homeFragmentViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sendEmailButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupViewBinding()
        return binding.root
    }

    private fun setupViewBinding() {
        sendEmailButton = binding.sendEmailButton
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickSendEmailsButton()
    }

    private fun onClickSendEmailsButton() {
        sendEmailButton.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeFragmentViewModel.emails.collect { data ->
                    context?.sendEmail(data)
                }
            }
        }
    }

    private fun Context.sendEmail(
        emails: List<String>
    ) {
        try {
            val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
            val emailIntent = Intent(Intent.ACTION_SEND)
            val subject = "I'm at the bus stop and the BusUp bus didn't arrive on time."

            emailSelectorIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emails.toTypedArray())
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            emailIntent.selector = emailSelectorIntent
            startActivity(emailIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "La aplicación seleccionada no está disponible", Toast.LENGTH_SHORT).show()
        } catch (t: Throwable) {
            Toast.makeText(this, "Se ha producido un error", Toast.LENGTH_SHORT).show()
        }
    }
}