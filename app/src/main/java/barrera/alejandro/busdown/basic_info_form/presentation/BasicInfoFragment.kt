package barrera.alejandro.busdown.basic_info_form.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import barrera.alejandro.busdown.core.util.UiEvent
import barrera.alejandro.busdown.databinding.FragmentBasicInfoBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class BasicInfoFragment : Fragment() {
    private val viewModel: BasicInfoViewModel by activityViewModels()
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
        observeState()
        onClickAcceptButton()
        updateUi()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            basicInfoTextInputLayout.isErrorEnabled = state.showErrorMessage
            basicInfoTextInputLayout.error = state.errorMessage
        }
    }

    private fun onClickAcceptButton() {
        basicInfoAcceptButton.setOnClickListener {
            val unformattedEmails = basicInfoTextInputEditText.text.toString()

            viewModel.onClickAcceptButton(unformattedEmails)
        }
    }

    private fun updateUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.NavigateToHomeFragment -> {
                        view?.findNavController()?.navigate(
                            BasicInfoFragmentDirections.actionBasicInfoFragmentToHomeFragment()
                        )
                    }
                    else -> Unit
                }
            }
        }
    }
}