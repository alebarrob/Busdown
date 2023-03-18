package barrera.alejandro.busdown.settings.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.busdown.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnClickChangeEmailButton -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateToChangeEmailFragment)
                }
            }
            is SettingsEvent.OnClickTermsAndConditionsButton -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateToTermsAndConditionsFragment)
                }
            }
        }
    }
}