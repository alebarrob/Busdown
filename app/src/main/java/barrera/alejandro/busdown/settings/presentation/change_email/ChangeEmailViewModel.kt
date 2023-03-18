package barrera.alejandro.busdown.settings.presentation.change_email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.R
import barrera.alejandro.busdown.core.data.mapper.toContact
import barrera.alejandro.busdown.core.domain.use_cases.CoreUseCases
import barrera.alejandro.busdown.core.domain.use_cases.ValidateEmails
import barrera.alejandro.busdown.core.util.UiEvent
import barrera.alejandro.busdown.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {
    private val _state = MutableLiveData<ChangeEmailState>()
    val state: LiveData<ChangeEmailState> = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ChangeEmailEvent) {
        when (event) {
            is ChangeEmailEvent.LoadEmails -> {
                viewModelScope.launch {
                    coreUseCases.getContactEmails.invoke().collect { loadedEmails ->
                        _state.value = state.value?.copy(emails = loadedEmails)
                    }
                }
            }
            is ChangeEmailEvent.OnClickAcceptButton -> {
                val emails = coreUseCases.formatEmails(event.unformattedEmails)
                val result = coreUseCases.validateEmails(emails)

                when (result) {
                    is ValidateEmails.Result.Success -> {
                        val contacts = emails.map { it.toContact() }

                        viewModelScope.launch {
                            // DELETE CONTACTS
                            coreUseCases.insertAllContacts(contacts)
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.modify_email_success)
                                )
                            )
                        }

                    }
                    is ValidateEmails.Result.Error -> {
                        _state.value = state.value?.copy(showErrorMessage = true)
                    }
                }
            }
        }
    }
}