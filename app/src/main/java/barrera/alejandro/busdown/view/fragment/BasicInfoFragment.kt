package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import barrera.alejandro.busdown.databinding.FragmentBasicInfoBinding

class BasicInfoFragment : Fragment() {
    private lateinit var binding: FragmentBasicInfoBinding

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

        onClickAcceptButton()
    }

    private fun onClickAcceptButton() {
        binding.acceptButton.setOnClickListener { view ->
            val action = BasicInfoFragmentDirections.actionBasicInfoFragmentToHomeFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}