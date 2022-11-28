package barrera.alejandro.busdown.view.fragment

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
import barrera.alejandro.busdown.databinding.FragmentChangeEmailBinding
import barrera.alejandro.busdown.model.enum.Error
import barrera.alejandro.busdown.viewmodel.ChangeEmailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class ChangeEmailFragment : Fragment() {
    private val changeEmailViewModel: ChangeEmailViewModel by activityViewModels()
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
                changeEmailViewModel.emails.collect { data ->
                    binding.changeEmailTextInputEditText.setText(data.joinToString())
                }
            }
        }
    }

    private fun onClickAcceptButton() {
        changeEmailAcceptButton.setOnClickListener {
            val unformattedEmails = changeEmailTextInputEditText.text.toString()
            val emails = changeEmailViewModel.formatEmails(unformattedEmails)

            if (changeEmailViewModel.emailsFormatIsCorrect(emails)) {
                changeEmailViewModel.deleteAllContactsExceptBusUp()
                changeEmailViewModel.insertEmails(emails)
                changeEmailTextInputLayout.isErrorEnabled = false
                Toast.makeText(context, "Email list successfully modified", Toast.LENGTH_LONG).show()
            } else {
                changeEmailTextInputLayout.error = Error.INCORRECT_EMAIL_FORMAT.message
            }
        }
    }
}