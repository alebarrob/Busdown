package barrera.alejandro.busdown.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.core.domain.preferences.PreferenceStorage
import barrera.alejandro.busdown.core.util.UiEvent
import barrera.alejandro.busdown.welcome.domain.use_case.WelcomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    welcomeUseCases: WelcomeUseCases,
    preferenceStorage: PreferenceStorage
) : ViewModel() {
    private val showBasicInfoFragment =
        welcomeUseCases.getShowBasicInfoFragment.invoke().asLiveData()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun navigateAfterDelay() {
        viewModelScope.launch {
            if (showBasicInfoFragment.value != false) {
                _uiEvent.send(UiEvent.NavigateToBasicInfoFragment)
            } else {
                _uiEvent.send(UiEvent.NavigateToHomeFragment)
            }
        }
    }
}