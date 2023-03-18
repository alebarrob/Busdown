package barrera.alejandro.busdown.basic_info_form.presentation

data class BasicInfoState(
    val emails: List<String> = emptyList(),
    val errorMessage: String? = null,
    val showErrorMessage: Boolean = false
)
