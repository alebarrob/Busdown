package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import barrera.alejandro.busdown.databinding.FragmentBasicInfoBinding

class BasicInfoFragment : Fragment() {
    private lateinit var binding: FragmentBasicInfoBinding
    private lateinit var basicInfoAcceptButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBasicInfoAcceptButton()
        onClickAcceptButton()
    }

    private fun setupBasicInfoAcceptButton() {
        basicInfoAcceptButton = binding.basicInfoAcceptButton
    }

    private fun onClickAcceptButton() {
        basicInfoAcceptButton.setOnClickListener { view ->
            val action = BasicInfoFragmentDirections.actionBasicInfoFragmentToHomeFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}