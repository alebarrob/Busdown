package barrera.alejandro.busdown.settings.presentation.change_email

sealed class ChangeEmailEvent {
    data class OnClickAcceptButton(val unformattedEmails: String): ChangeEmailEvent()
    object LoadEmails: ChangeEmailEvent()
}
