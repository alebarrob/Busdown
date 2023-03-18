package barrera.alejandro.busdown.welcome.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import barrera.alejandro.busdown.core.util.UiEvent
import barrera.alejandro.busdown.databinding.FragmentWelcomeBinding
import barrera.alejandro.busdown.welcome.util.navigateAfterDelay
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {
    private val viewModel: WelcomeViewModel by activityViewModels()
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
        viewModel.navigateAfterDelay()
        updateUi()
    }

    private fun updateUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.NavigateToBasicInfoFragment -> {
                        WelcomeFragmentDirections.actionWelcomeFragmentToBasicInfoFragment()
                            .navigateAfterDelay(requireView())
                    }
                    is UiEvent.NavigateToHomeFragment -> {
                        WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
                            .navigateAfterDelay(requireView())
                    }
                    else -> Unit
                }
            }
        }
    }
}