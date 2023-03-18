package barrera.alejandro.busdown.settings.presentation.settings

sealed class SettingsEvent {
    object OnClickChangeEmailButton: SettingsEvent()
    object OnClickTermsAndConditionsButton: SettingsEvent()
}
