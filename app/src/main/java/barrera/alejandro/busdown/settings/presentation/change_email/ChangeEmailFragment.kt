package barrera.alejandro.busdown.settings.presentation.change_email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import barrera.alejandro.busdown.core.util.UiEvent
import barrera.alejandro.busdown.databinding.FragmentChangeEmailBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class ChangeEmailFragment : Fragment() {
    private val viewModel: ChangeEmailViewModel by activityViewModels()
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
        observeState()
        loadEmails()
        onClickAcceptButton()
        updateUi()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            changeEmailTextInputEditText.setText(state.emails.joinToString())
            changeEmailTextInputLayout.isErrorEnabled = state.showErrorMessage
            changeEmailTextInputLayout.error = state.errorMessage
        }
    }

    private fun loadEmails() {
        lifecycleScope.launchWhenStarted {
            viewModel.onEvent(ChangeEmailEvent.LoadEmails)
        }
    }

    private fun onClickAcceptButton() {
        changeEmailAcceptButton.setOnClickListener {
            val unformattedEmails = changeEmailTextInputEditText.text.toString()
            viewModel.onEvent(ChangeEmailEvent.OnClickAcceptButton(unformattedEmails))
        }
    }

    private fun updateUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.ShowToast -> {
                        Toast.makeText(
                            context,
                            event.message.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}