package barrera.alejandro.busdown.settings.presentation.change_email

data class ChangeEmailState(
    val emails: List<String> = emptyList(),
    val errorMessage: String = "Incorrect format. Remember to separate " +
            "each email with commas, e.g. email1@gmail.com, email2@gmail.com",
    val showErrorMessage: Boolean = false
)
