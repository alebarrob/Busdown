package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import barrera.alejandro.busdown.databinding.FragmentBasicInfoBinding
import barrera.alejandro.busdown.model.enum.Error
import barrera.alejandro.busdown.viewmodel.SharedViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class BasicInfoFragment : Fragment() {
    private val basicInfoViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentBasicInfoBinding
    private lateinit var basicInfoAcceptButton: Button
    private lateinit var basicInfoTextInputLayout: TextInputLayout
    private lateinit var basicInfoTextInputEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        setupViewBinding()
        return binding.root
    }

    private fun setupViewBinding() {
        basicInfoAcceptButton = binding.basicInfoAcceptButton
        basicInfoTextInputEditText = binding.basicInfoTextInputEditText
        basicInfoTextInputLayout = binding.basicInfoTextInputLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickAcceptButton()
    }

    private fun onClickAcceptButton() {
        basicInfoAcceptButton.setOnClickListener { view ->
            val unformattedEmails = basicInfoTextInputEditText.text.toString()
            val emails = basicInfoViewModel.formatEmails(unformattedEmails)
            val action = BasicInfoFragmentDirections.actionBasicInfoFragmentToHomeFragment()

            if (basicInfoViewModel.emailsFormatIsCorrect(emails)) {
                basicInfoViewModel.insertEmails(emails)
                basicInfoTextInputLayout.isErrorEnabled = false
                view?.findNavController()?.navigate(action)
            } else {
                basicInfoTextInputLayout.error = Error.INCORRECT_EMAIL_FORMAT.message
            }
        }
    }
}