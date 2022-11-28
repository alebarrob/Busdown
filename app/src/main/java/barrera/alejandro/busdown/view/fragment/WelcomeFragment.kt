package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import barrera.alejandro.busdown.databinding.FragmentWelcomeBinding
import barrera.alejandro.busdown.viewmodel.WelcomeViewModel

class WelcomeFragment : Fragment() {
    private val welcomeViewModel: WelcomeViewModel by activityViewModels()
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onConditionalNavigation()
    }

    private fun onConditionalNavigation() {
        welcomeViewModel.showBasicInfoFragment.observe(viewLifecycleOwner) { showBasicInfofragment ->
            if (showBasicInfofragment) {
                welcomeViewModel.setShowBasicInfoFragment(false)
                navigateAfterDelay(WelcomeFragmentDirections.actionWelcomeFragmentToBasicInfoFragment())
            } else {
                navigateAfterDelay(WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment())
            }
        }
    }

    private fun navigateAfterDelay(directions: NavDirections) {
        Handler(Looper.getMainLooper()).postDelayed(
            { view?.findNavController()?.navigate(
                directions
            ) },
            3000
        )
    }
}