package barrera.alejandro.busdown.settings.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import barrera.alejandro.busdown.core.util.UiEvent
import barrera.alejandro.busdown.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    private val viewModel: SettingsViewModel by activityViewModels()
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var changeEmailsText: TextView
    private lateinit var termsAndConditionsText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setupViewBinding()
        return binding.root
    }

    private fun setupViewBinding() {
        changeEmailsText = binding.changeEmailsText
        termsAndConditionsText = binding.termsAndConditionsText
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickChangeEmailButton()
        onClickTermsAndConditionsButton()
        updateUi()
    }

    private fun updateUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.NavigateToChangeEmailFragment -> {
                        view?.findNavController()?.navigate(
                            SettingsFragmentDirections.actionSettingsFragmentToChangeEmailFragment()
                        )
                    }
                    is UiEvent.NavigateToTermsAndConditionsFragment -> {
                        view?.findNavController()?.navigate(
                            SettingsFragmentDirections.actionSettingsFragmentToTermsAndConditionsFragment()
                        )
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun onClickChangeEmailButton() {
        changeEmailsText.setOnClickListener {
            viewModel.onEvent(SettingsEvent.OnClickChangeEmailButton)
        }
    }

    private fun onClickTermsAndConditionsButton() {
        termsAndConditionsText.setOnClickListener {
            viewModel.onEvent(SettingsEvent.OnClickTermsAndConditionsButton)
        }
    }
}