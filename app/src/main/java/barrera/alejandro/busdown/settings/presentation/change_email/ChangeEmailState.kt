package barrera.alejandro.busdown.settings.presentation.change_email

data class ChangeEmailState(
    val emails: List<String> = emptyList(),
    val errorMessage: String? = null,
    val showErrorMessage: Boolean = false
)
