package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import barrera.alejandro.busdown.R
import barrera.alejandro.busdown.databinding.FragmentBasicInfoBinding
import barrera.alejandro.busdown.viewmodel.BasicInfoViewModel
import com.google.android.material.textfield.TextInputEditText

class BasicInfoFragment : Fragment() {
    private val basicInfoViewModel: BasicInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentBasicInfoBinding
    private lateinit var basicInfoAcceptButton: Button
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickAcceptButton()
    }

    private fun onClickAcceptButton() {
        basicInfoAcceptButton.setOnClickListener { view ->
            val emails = basicInfoTextInputEditText.text.toString()
            val action = BasicInfoFragmentDirections.actionBasicInfoFragmentToHomeFragment()

            if (basicInfoViewModel.emailsFormatIsCorrect(emails)) {
                view?.findNavController()?.navigate(action)
            } else {
                Toast.makeText(context, R.string.emails_incorrect_format, Toast.LENGTH_LONG).show()
            }
        }
    }
}