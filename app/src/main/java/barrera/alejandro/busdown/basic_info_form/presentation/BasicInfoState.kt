package barrera.alejandro.busdown.basic_info_form.presentation

data class BasicInfoState(
    val emails: List<String> = emptyList(),
    val errorMessage: String? = "Incorrect format. Remember to separate " +
            "each email with commas, e.g. email1@gmail.com, email2@gmail.com",
    val showErrorMessage: Boolean = false
)
