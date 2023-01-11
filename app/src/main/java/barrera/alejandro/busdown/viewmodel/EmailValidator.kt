package barrera.alejandro.busdown.viewmodel

class EmailValidator {
    fun formatIsCorrect(emails: List<String>): Boolean {
        val pattern = android.util.Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(pattern)

        emails.forEach { email -> if (!regex.matches(email)) return false }
        return true
    }
}