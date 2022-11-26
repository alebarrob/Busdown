package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import barrera.alejandro.busdown.model.repository.ContactRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasicInfoViewModel @Inject constructor(
    private val contactRepositoryImpl: ContactRepositoryImpl
) : ViewModel() {
    fun emailsFormatIsCorrect(emails: String): Boolean {
        val emailsList = emails.replace(" ", "").split(",")
        val pattern = android.util.Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(pattern)

        emailsList.forEach {  email -> if (!regex.matches(email)) return false }
        return true
    }
}