package barrera.alejandro.busdown.core.util

sealed class UiEvent {
    data class ShowToast(val message: UiText): UiEvent()
    object NavigateToBasicInfoFragment: UiEvent()
    object NavigateToHomeFragment: UiEvent()
    object NavigateToChangeEmailFragment: UiEvent()
    object NavigateToTermsAndConditionsFragment: UiEvent()
}
