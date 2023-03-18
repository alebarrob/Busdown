package barrera.alejandro.busdown.core.domain.use_cases

class FormatEmails() {
    operator fun invoke(unformattedEmails: String): List<String> {
        val formattedEmails = unformattedEmails.replace(" ", "").split(",")

        return if (formattedEmails.last() == "") formattedEmails.dropLast(1) else formattedEmails
    }
}