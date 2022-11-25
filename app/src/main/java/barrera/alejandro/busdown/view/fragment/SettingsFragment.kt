package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import barrera.alejandro.busdown.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var modifyEmailsText: TextView
    private lateinit var termsAndConditionsText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEmailsText()
        setupTermsAndConditionsText()
        onClickModifyEmailButton()
        onClickTermsAndConditionsButton()
    }

    private fun setupEmailsText() {
        modifyEmailsText = binding.modifyEmailsText
    }

    private fun setupTermsAndConditionsText() {
        termsAndConditionsText = binding.termsAndConditionsText
    }

    private fun onClickModifyEmailButton() {
        modifyEmailsText.setOnClickListener { view ->
            val action = SettingsFragmentDirections.actionSettingsFragmentToChangeEmailFragment()
            view?.findNavController()?.navigate(action)
        }
    }

    private fun onClickTermsAndConditionsButton() {
        termsAndConditionsText.setOnClickListener { view ->
            val action = SettingsFragmentDirections.actionSettingsFragmentToTermsAndConditionsFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}