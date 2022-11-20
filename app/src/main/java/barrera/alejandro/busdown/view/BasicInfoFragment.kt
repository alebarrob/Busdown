package barrera.alejandro.busdown.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import barrera.alejandro.busdown.databinding.FragmentBasicInfoBinding

class BasicInfoFragment : Fragment() {
    private var _binding: FragmentBasicInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = (requireActivity() as MainActivity)

        onClickAcceptButton()
    }

    private fun onClickAcceptButton() {
        binding.acceptButton.setOnClickListener {
            mainActivity.navigateToHomeFragment()
            mainActivity.bottomNavigationBarIsVisible(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}