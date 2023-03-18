package barrera.alejandro.busdown.core.domain.use_cases

data class CoreUseCases(
    val getContactEmails: GetContactEmails,
    val insertAllContacts: InsertAllContacts,
    val formatEmails: FormatEmails,
    val validateEmails: ValidateEmails
)
