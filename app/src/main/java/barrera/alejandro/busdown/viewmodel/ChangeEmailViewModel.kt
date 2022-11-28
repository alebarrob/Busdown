package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.model.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    private val contactRepositoryImpl: ContactRepository,
    private val emailFormatter: EmailFormatter,
    private val emailValidator: EmailValidator
) : ViewModel() {
    val emails = contactRepositoryImpl.emails

    fun formatEmails(unformattedEmails: String): List<String> = emailFormatter.formatEmails(unformattedEmails)

    fun emailsFormatIsCorrect(emails: List<String>): Boolean = emailValidator.formatIsCorrect(emails)

    fun deleteAllContactsExceptBusUp() {
        viewModelScope.launch {
            contactRepositoryImpl.deleteAllContactsExceptBusUp()
        }
    }

    fun insertEmails(emails: List<String>) {
        viewModelScope.launch {
            contactRepositoryImpl.insertAllContacts(emailFormatter.transformEmailsToContacts(emails))
        }
    }
}