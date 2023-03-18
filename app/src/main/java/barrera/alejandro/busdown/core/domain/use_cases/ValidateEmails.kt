package barrera.alejandro.busdown.core.domain.use_cases

class ValidateEmails {
    operator fun invoke(emails: List<String>): Result {
        val pattern = android.util.Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(pattern)

        return if (emails.isEmpty()) {
            Result.Error
        } else if (emails.all { regex.matches(it) }) {
            Result.Success
        } else {
            Result.Error
        }
    }

    sealed class Result {
        object Success: Result()
        object Error: Result()
    }
}