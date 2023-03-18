package barrera.alejandro.busdown.basic_info_form.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.basic_info_form.domain.use_case.BasicInfoUseCases
import barrera.alejandro.busdown.core.data.mapper.toContact
import barrera.alejandro.busdown.core.domain.use_cases.CoreUseCases
import barrera.alejandro.busdown.core.domain.use_cases.ValidateEmails
import barrera.alejandro.busdown.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicInfoViewModel @Inject constructor(
    private val basicInfoUseCases: BasicInfoUseCases,
    private val coreUseCases: CoreUseCases
) : ViewModel() {
    private val _state = MutableLiveData(BasicInfoState())
    val state: LiveData<BasicInfoState> = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onClickAcceptButton(unformattedEmails: String) {
        val emails = coreUseCases.formatEmails(unformattedEmails)
        val result = coreUseCases.validateEmails(emails)

        when (result) {
            is ValidateEmails.Result.Success -> {
                val contacts = emails.map { it.toContact() }

                viewModelScope.launch {
                    coreUseCases.insertAllContacts(contacts)
                    basicInfoUseCases.setShowBasicInfoFragment(false)
                    _uiEvent.send(UiEvent.NavigateToHomeFragment)
                }

            }
            is ValidateEmails.Result.Error -> {
                _state.value = state.value?.copy(
                    showErrorMessage = true,
                    errorMessage = "Incorrect format. Remember to separate " +
                            "each email with commas, e.g. email1@gmail.com, email2@gmail.com"
                )
            }
        }
    }
}