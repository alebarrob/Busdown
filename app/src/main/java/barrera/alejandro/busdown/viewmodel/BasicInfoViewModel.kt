package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.model.entity.Contact
import barrera.alejandro.busdown.model.repository.ContactRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicInfoViewModel @Inject constructor(
    private val contactRepositoryImpl: ContactRepositoryImpl
) : ViewModel() {

    fun formatEmails(unformattedEmails: String): List<String> {
        return unformattedEmails.replace(" ", "").split(",")
    }

    fun emailsFormatIsCorrect(emails: List<String>): Boolean {
        val pattern = android.util.Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(pattern)

        emails.forEach {  email -> if (!regex.matches(email)) return false }
        return true
    }

    fun insertEmails(emails: List<String>) {
        val contacts = emails.map { Contact(0, it) }

        viewModelScope.launch {
            contactRepositoryImpl.insertAllContacts(contacts)
        }
    }
}