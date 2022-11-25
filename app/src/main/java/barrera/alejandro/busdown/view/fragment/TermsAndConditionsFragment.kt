package barrera.alejandro.busdown.view.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import barrera.alejandro.busdown.databinding.FragmentTermsAndConditionsBinding

class TermsAndConditionsFragment : Fragment() {
    private lateinit var binding: FragmentTermsAndConditionsBinding
    private lateinit var termAndConditionsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTermsAndConditionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTermAndConditionsTextView()
        loadTermsAndConditions()
    }

    private fun setupTermAndConditionsTextView() {
        termAndConditionsTextView = binding.termsAndConditionsTextview
        termAndConditionsTextView.movementMethod = ScrollingMovementMethod()
    }

    private fun loadTermsAndConditions() {
        val termsAndConditions = context?.assets?.open("legal/terms_and_conditions.txt")
        termAndConditionsTextView.text = termsAndConditions!!.bufferedReader().use { it.readText() }
    }
}
