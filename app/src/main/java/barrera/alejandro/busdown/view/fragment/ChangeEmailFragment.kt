package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import barrera.alejandro.busdown.databinding.FragmentChangeEmailBinding
import barrera.alejandro.busdown.model.enum.Error
import barrera.alejandro.busdown.viewmodel.BusdownViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class ChangeEmailFragment : Fragment() {
    private val busdownViewModel: BusdownViewModel by activityViewModels()
    private lateinit var binding: FragmentChangeEmailBinding
    private lateinit var changeEmailTextInputLayout: TextInputLayout
    private lateinit var changeEmailTextInputEditText: TextInputEditText
    private lateinit var changeEmailAcceptButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeEmailBinding.inflate(inflater, container, false)
        setupViewBinding()
        return binding.root
    }

    private fun setupViewBinding() {
        changeEmailTextInputEditText = binding.changeEmailTextInputEditText
        changeEmailAcceptButton = binding.changeEmailAcceptButton
        changeEmailTextInputLayout = binding.changeEmailTextInputLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadEmails()
        onClickAcceptButton()
    }

    private fun loadEmails() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                busdownViewModel.emails.collect { data ->
                    binding.changeEmailTextInputEditText.setText(data.joinToString())
                }
            }
        }
    }

    private fun onClickAcceptButton() {
        changeEmailAcceptButton.setOnClickListener {
            val unformattedEmails = changeEmailTextInputEditText.text.toString()
            val emails = busdownViewModel.formatEmails(unformattedEmails)

            if (busdownViewModel.emailsFormatIsCorrect(emails)) {
                busdownViewModel.deleteAllContactsExceptBusUp()
                busdownViewModel.insertEmails(emails)
            } else {
                changeEmailTextInputLayout.error = Error.INCORRECT_EMAIL_FORMAT.message
            }
        }
    }
}