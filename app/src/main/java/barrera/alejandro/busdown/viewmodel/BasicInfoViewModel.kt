package barrera.alejandro.busdown.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.model.preference.PreferenceStorage
import barrera.alejandro.busdown.model.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicInfoViewModel @Inject constructor(
    private val contactRepositoryImpl: ContactRepository,
    private val preferenceStorageImpl: PreferenceStorage,
    private val emailFormatter: EmailFormatter,
    private val emailValidator: EmailValidator
) : ViewModel() {
    fun formatEmails(unformattedEmails: String): List<String> = emailFormatter.formatEmails(unformattedEmails)

    fun emailsFormatIsCorrect(emails: List<String>): Boolean = emailValidator.formatIsCorrect(emails)

    fun insertEmails(emails: List<String>) {
        viewModelScope.launch {
            contactRepositoryImpl.insertAllContacts(emailFormatter.transformEmailsToContacts(emails))
        }
    }

    fun setShowBasicInfoFragment(key: Boolean) {
        viewModelScope.launch {
            preferenceStorageImpl.setSavedKey(key)
        }
    }
}